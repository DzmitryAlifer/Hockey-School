package by.epam.hockeyschool.service;

import by.epam.hockeyschool.entity.Coach;
import by.epam.hockeyschool.entity.Person;
import by.epam.hockeyschool.entity.Physio;
import by.epam.hockeyschool.entity.Player;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Dmitry Olifer on 19.06.2015.
 *
 * The {@code PersonService} class represents a layer-class between
 * the {@code PersonDAOImpl} class (DAO class) and servlet.
 */
public class PersonService {

    final static Logger LOG = Logger.getLogger(PersonService.class);

    /**
     * Converts person's name (first as well as last), that passed a
     * validation, into syntactically correct form with the first upper-
     * cased letter and the lower-cased others.
     * @param   name is a string value that can be first as well as
     *          last person's name.
     * @return  syntactically correct first or last person's name.
     */
    public String transformName(String name) {
        String result = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        return result;
    }

    /**
     * Increases definite player's skill by the value, that defines in
     * {@code raiseSkill()} method.
     * @param   person is a definite coach or a physiotherapist who trains
     *          definite {@param player}.
     * @param   player is an instance of {@code Player}, who is trained by
     *          {@param person}.
     * @param   skill is a name of player's characteristic that should be
     *          risen after training.
     */
    public int trainResult(Person person, Player player, String skill) {
        int teacherSkill;
        int playerSkill;
        int[] skillAndInc = new int[2];
        switch (skill) {
            case "defence":
                teacherSkill = ((Coach) person).getDefenceInc();
                playerSkill = player.getDefence();
                skillAndInc = raiseSkill(teacherSkill, playerSkill);
                player.setDefence(skillAndInc[0]);
                break;
            case "attack":
                teacherSkill = ((Coach) person).getAttackInc();
                playerSkill = player.getAttack();
                skillAndInc = raiseSkill(teacherSkill, playerSkill);
                player.setAttack(skillAndInc[0]);
                break;
            case "speed":
                teacherSkill = ((Physio) person).getSpeedInc();
                playerSkill = player.getSpeed();
                skillAndInc = raiseSkill(teacherSkill, playerSkill);
                player.setSpeed(skillAndInc[0]);
                break;
            case "strength":
                teacherSkill = ((Physio) person).getStrengthInc();
                playerSkill = player.getStrength();
                skillAndInc = raiseSkill(teacherSkill, playerSkill);
                player.setStrength(skillAndInc[0]);
                break;
        }
        return skillAndInc[1];
    }

    /**
     * Defines an increment value for player's skill that depend on
     * teacher's skill as well as player's skill.
     * @param   teacherSkill an integer value of teacher's skill.
     * @param   playerSkill an integer value of player's teaching skill.
     * @return  an integer value that represents definite skill's increment.
     */
    private int[] raiseSkill(int teacherSkill, int playerSkill) {
        int increment = (int) (teacherSkill * Math.random());
        playerSkill += increment;
        return new int[] {playerSkill, increment};
    }

    public List<Player> giveTacticTrainedPlayers(Coach currentCoach, String tacticTraining) {
        int coachSkill;
        List<Player> schoolPlayers = new PlayerService().getSchoolPlayers();
        int listSize = schoolPlayers.size();
        switch(tacticTraining) {
            case "defence":
                coachSkill = (int)(currentCoach.getDefenceInc() * Math.random());
                for (int i = 0; i < coachSkill; i++) {
                    int randomIndex = (int) (Math.random() * listSize);
                    Player currentPlayer = schoolPlayers.get(randomIndex);
                    int currentDefence = currentPlayer.getDefence();
                    currentPlayer.setDefence(++currentDefence);
                }
                break;
            case "attack":
                coachSkill = (int)(currentCoach.getAttackInc() * Math.random());
                for (int i = 0; i < coachSkill; i++) {
                    int randomIndex = (int) (Math.random() * listSize);
                    Player currentPlayer = schoolPlayers.get(randomIndex);
                    int currentAttack = currentPlayer.getAttack();
                    currentPlayer.setAttack(++currentAttack);
                }
                break;
        }
        return schoolPlayers;
    }
}
