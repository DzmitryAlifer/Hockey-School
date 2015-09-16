package by.epam.hockeyschool.service;

import by.epam.hockeyschool.database.dao.PlayerDAOImpl;
import by.epam.hockeyschool.entity.MoneyTransfer;
import by.epam.hockeyschool.entity.Person;
import by.epam.hockeyschool.entity.Player;
import by.epam.hockeyschool.entity.PlayerQuestionnaire;
import by.epam.hockeyschool.exception.DAOException;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;

/**
 * Created by Dmitry Olifer on 19.06.2015.
 *
 * The {@code PlayerService} class represents a layer-class between
 * the {@code PlayerDAOImpl} class (DAO class) and servlet.
 */
public class PlayerService {

    final static Logger LOG = Logger.getLogger(PlayerService.class);

    PlayerDAOImpl playerDAO = new PlayerDAOImpl();

    /**
     * Relocates player from hockey school to a team, changing the value
     * of his current {@param location}.
     * @param   id means player's identification number.
     */
    public void promotePlayer(int id) {
        try {
            playerDAO.promotePlayer(id);
        } catch (DAOException e) {
            LOG.error(e);
        }
    }

    public void sellPlayer(int id) {
        try {
            playerDAO.sellPlayer(id);
            java.sql.Date sqlDate = new java.sql.Date(new Date().getTime()) ;
            int price = new PlayerPriceService().definePriceById(id);
            MoneyTransfer transfer = new MoneyTransfer(sqlDate, price, "player sale");
            new MoneyTransferService().addMoneyTransfer(transfer);
        } catch (DAOException e) {
            LOG.error(e);
        }
    }

    /**
     * Updates player's attack/defence skills after training by coach or
     * speed/strength after training by physiotherapist.
     * @param   person is an instance of {@code Person}, that need to be
     *          updated.
     */
    public void updatePlayer(Person person) {
        try {
            //new MoneyTransfer().addTransfer();
            playerDAO.updatePlayer(person);
        } catch (DAOException e) {
            LOG.error(e);
        }
    }

    /**
     * Adds definite player to database.
     * @param   firstName is player's first name.
     * @param   lastName is player's last name.
     * @param   defence is player's defence skill.
     * @param   attack is player's attack skill.
     * @param   speed is player's speed skill.
     * @param   strength is player's strength skill.
     */
    public void addPerson(String firstName, String lastName, int defence, int attack, int speed, int strength) {
        try {
            Player player = new Player(firstName, lastName, defence, attack, speed, strength);
            playerDAO.addPerson(player);
        } catch (DAOException e) {
            LOG.error(e);
        }
    }

    /**
     * Deletes definite player from database using player's {@param id}.
     * The process consists of two parts: deleting of the note with names and
     * {@code id} from table {@code person} and also deleting of his hashed
     * login and hashed password from related table {@code players}.
     * @param   id is an integer personal identifier.
     */
    public void deletePerson(int id) {
        try {
            new PlayerQuestService().deleteQuestionnaire(id);
            playerDAO.deletePerson(id);
        } catch (DAOException e) {
            LOG.error(e);
        }
    }

    /**
     * Gives the list of players of definite type from database.
     * @return  the list of players collected by definite rules.
     */
    public List<Player> getPersons() {
        try {
            List<Person> persons = playerDAO.takePersons();
            List<Player> players = new ArrayList<>();
            for (Person person : persons) {
                Player player = (Player) person;
                int playerId = player.getId();
                PlayerQuestionnaire questionnaire = new PlayerQuestService().defineQuestionnaire(playerId);
                if (questionnaire != null) {
                    player.setQuest(questionnaire);
                }
                players.add(player);
            }
            return players;
        } catch (DAOException e) {
            LOG.error(e);
        }
        return Collections.emptyList();
    }

    /**
     * Gets the list of players from database and then removes from
     * it those players, who are not in school.
     * @return  the list of hockey school players.
     */
    public List<Player> getSchoolPlayers() {
        List<Player> players = getPersons();
        addQuestsToPlayers(players);
        Iterator<Player> iterator = players.iterator();
        while(iterator.hasNext()) {
            Player player = iterator.next();
            if (! player.getLocation().equals("school")) {
                iterator.remove();
            }
        }
        return players;
    }

    /**
     * Gets the list of players from database and then removes from
     * it those players, who are not in team.
     * @return  the list of team players.
     */
    public List<Player> getTeamPlayers() {
        List<Player> players = getPersons();
        addQuestsToPlayers(players);
        Iterator<Player> iterator = players.iterator();
        while(iterator.hasNext()) {
            Player player = iterator.next();
            if (player.getLocation().equals("team")) {
                int price = new PlayerPriceService().definePriceById(player.getId());
                player.setPrice(price);
            } else {
                iterator.remove();
            }
        }
        return players;
    }

    public List<Player> getUnpricedTeamPlayers() {
        List<Player> unpricedTeamPlayers = getTeamPlayers();
        Iterator<Player> iterator = unpricedTeamPlayers.iterator();
        while(iterator.hasNext()) {
            Player player = iterator.next();
            int playerId = player.getId();
            int price = new PlayerPriceService().definePriceById(playerId);
            if (price > 0) {
                iterator.remove();
            }
        }
        return unpricedTeamPlayers;
    }

    public List<Player> getPricedTeamPlayers() {
        List<Player> teamPlayers = getTeamPlayers();
        Iterator<Player> iterator = teamPlayers.iterator();
        while(iterator.hasNext()) {
            Player player = iterator.next();
            int playerId = player.getId();
            int price = new PlayerPriceService().definePriceById(playerId);
            player.setPrice(price);
            if (price == 0) {
                iterator.remove();
            }
        }
        return teamPlayers;
    }

    public List<Player> getSoldPlayers() {
        List<Player> soldPlayers = getPersons();
        addQuestsToPlayers(soldPlayers);
        Iterator<Player> iterator = soldPlayers.iterator();
        while(iterator.hasNext()) {
            Player player = iterator.next();
            if (player.getLocation().equals("sold")) {
                int playerId = player.getId();
                int price = new PlayerPriceService().definePriceById(playerId);
                player.setPrice(price);
            } else {
                iterator.remove();
            }
        }
        return soldPlayers;
    }

    private void addQuestsToPlayers(List<Player> players) {
        for (Player player : players) {
            int playerId = player.getId();
            PlayerQuestionnaire questionnaire = new PlayerQuestService().defineQuestionnaire(playerId);
            if (questionnaire != null) {
                player.setQuest(questionnaire);
            }
        }
    }

    public List<Player> choosePlayersWithoutQuests(List<Player> players) {
        Iterator<Player> iterator = players.iterator();
        while(iterator.hasNext()) {
            Player player = iterator.next();
            if (player.getQuest() != null) {
                iterator.remove();
            }
        }
        return players;
    }
    /**
     * Calculates an average arithmetical value of all players' rates.
     * @param   players is a list of definite players.
     * @return  an integer value of average players' rate.
     */
    public int averPlayerRate (List<Player> players) {
        int totalSum = 0;
        for (Player player : players) {
            totalSum += (player.getAttack() + player.getDefence() + player.getSpeed() + player.getStrength());
        }
        int averRate = totalSum / (4 * players.size());
        return averRate;
    }

    /**
     * Collects all the player's data from database by its {@param id},
     * then creates and returns an instance of {@code Player} with this
     * data inside.
     * @param   id means player's identification number.
     * @return  an instance of {@code Player} with all information about
     *          the player inside this instance.
     */
    public Player definePlayer(int id) {
        Player player = null;
        try {
            player = playerDAO.definePlayer(id);
        } catch (DAOException e) {
            LOG.error(e);
        }
        PlayerQuestionnaire questionnaire = new PlayerQuestService().defineQuestionnaire(id);
        if (questionnaire != null) {
            player.setQuest(questionnaire);
        }
        return player;
    }

    public void updateSchoolPlayers(List<Player> schoolPlayers) {
        for (Player player : schoolPlayers) {
            updatePlayer(player);
        }
    }

    public int defineRecommendedPlayerPrice(int id) {
        Player player = definePlayer(id);
        int defence = player.getDefence();
        int attack = player.getAttack();
        int speed = player.getSpeed();
        int strength = player.getStrength();
        int honestPrice = 20 * (defence + attack) + 14 * (speed + strength);
        return honestPrice;
    }

    public int totalSoldPlayersIncome() {
        int totalIncome = 0;
        List<Player> soldPlayers = getSoldPlayers();
        for (Player soldPlayer : soldPlayers) {
            totalIncome += soldPlayer.getPrice();
        }
        return totalIncome;
    }

    public void playersImproveDefiniteSkill(String skill, int maxIncValue, double probability) {
        List<Player> schoolPlayers = getSchoolPlayers();
        if (Math.random() < probability) {
            for (Player player : schoolPlayers) {
                int currentIncValue = (int) Math.round(Math.random() * maxIncValue);
                improveDefiniteSkill(player, skill, currentIncValue);
                updatePlayer(player);
            }
        }
    }

    public void improveDefiniteSkill(Player player, String skill, int value) {
        switch (skill) {
            case "defence":
                int defence = player.getDefence();
                player.setDefence(defence + value);
                break;
            case "attack":
                int attack = player.getAttack();
                player.setAttack(attack + value);
                break;
            case "speed":
                int speed = player.getSpeed();
                player.setSpeed(speed + value);
                break;
            case "strength":
                int strength = player.getStrength();
                player.setStrength(strength + value);
                break;
        }
        updatePlayer(player);
    }

    public HashMap<Integer, Integer[]> giveIdAndIncrementMap(List<Player> schoolPlayers) {
        HashMap<Integer, Integer[]> idAndIncMap = new HashMap<>();
        for (Player player : schoolPlayers) {
            int id = player.getId();
            Player updatedPlayer = null;
            int oldDefence, newDefence, oldAttack, newAttack, oldSpeed, newSpeed, oldStrength, newStrength;
            oldDefence = player.getDefence();
            oldAttack = player.getAttack();
            oldSpeed = player.getSpeed();
            oldStrength = player.getStrength();
            updatedPlayer = definePlayer(id);
            newDefence = updatedPlayer.getDefence();
            newAttack = updatedPlayer.getAttack();
            newSpeed = updatedPlayer.getSpeed();
            newStrength = updatedPlayer.getStrength();
            idAndIncMap.put(id, new Integer[] {newDefence - oldDefence, newAttack - oldAttack, newSpeed - oldSpeed, newStrength - oldStrength});
        }
        return idAndIncMap;
    }
}
