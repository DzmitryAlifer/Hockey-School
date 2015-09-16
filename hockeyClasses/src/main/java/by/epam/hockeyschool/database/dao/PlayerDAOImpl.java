package by.epam.hockeyschool.database.dao;

import by.epam.hockeyschool.database.pool.ConnectionPool;
import by.epam.hockeyschool.entity.Person;
import by.epam.hockeyschool.entity.Player;
import by.epam.hockeyschool.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry Olifer on 18.06.2015.
 *
 * The {@code PlayerDAOImpl} class implements all the necessary methods
 * that were inherited from {@code PersonDAO} and {@code PlayerDAO}
 * interfaces for manipulation with player's data in database.
 */
public class PlayerDAOImpl implements PlayerDAO {

    private static final Logger LOG = Logger.getLogger(PlayerDAOImpl.class);

    private static final String SQL_ADD_PLAYER = "INSERT INTO hockey_school.players (defence, attack, speed, strength, location, player_id) VALUES (?,?,?,?, 'school', (SELECT max(id) FROM hockey_school.person));";
    private static final String SQL_DELETE_PLAYER = "DELETE FROM hockey_school.players WHERE player_id=?;";
    private static final String SQL_GET_PLAYERS = "SELECT * FROM hockey_school.players;";
    private static final String SQL_DEFINE_PLAYER = "SELECT firstname, lastname, defence, attack, speed, strength, location FROM person INNER JOIN players ON person.id = players.player_id WHERE person.id = ?;";
    private static final String SQL_PROMOTE_PLAYER = "UPDATE hockey_school.players SET location='team' WHERE player_id=?;";
    private static final String SQL_SELL_PLAYER = "UPDATE hockey_school.players SET location='sold' WHERE player_id=?;";
    private static final String SQL_UPDATE_PLAYER = "UPDATE hockey_school.players SET defence=?, attack=?, speed=?, strength=? WHERE player_id=?;";

    PersonDAOImpl personDAO = new PersonDAOImpl();

    /**
     * Relocates player from hockey school to a team, changing the value
     * of his current {@param location}.
     * @param   id means player's identification number.
     * @throws  DAOException if unable to get connection to database or
     *          if there are any troubles with query execution.
     */
    @Override
    public void promotePlayer(int id) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_PROMOTE_PLAYER);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred player promoting", e);
        } finally {
            close(preparedStatement);
            pool.returnConnection(connection);
        }
    }

    /**
     * Updates player's attack/defence skills after training by coach or
     * speed/strength after training by physiotherapist.
     * @param   player is an instance of {@code Person}, that need to be
     *          updated.
     * @throws  DAOException if unable to get connection to database or
     *          if there are any troubles with query execution.
     */
    @Override
    public void updatePlayer(Person player) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_PLAYER);
            preparedStatement.setInt(1, ((Player) player).getDefence());
            preparedStatement.setInt(2, ((Player) player).getAttack());
            preparedStatement.setInt(3, ((Player) player).getSpeed());
            preparedStatement.setInt(4, ((Player) player).getStrength());
            preparedStatement.setInt(5, player.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during player updating", e);
        } finally {
            close(preparedStatement);
            pool.returnConnection(connection);
        }
    }

    /**
     * Collects all the player's data from database by its {@param id},
     * then creates and returns an instance of {@code Player} with this
     * data inside.
     * @param   id means player's identification number.
     * @return  an instance of {@code Player} with all information about
     *          the player inside this instance.
     * @throws  DAOException if unable to get connection to database or
     *          if there are any troubles with query execution.
     */
    @Override
    public Player definePlayer(int id) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Player player = null;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DEFINE_PLAYER);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String firstName = resultSet.getString("firstname");
            String lastName = resultSet.getString("lastname");
            int defence = resultSet.getInt("defence");
            int attack = resultSet.getInt("attack");
            int speed = resultSet.getInt("speed");
            int strength = resultSet.getInt("strength");
            String location = resultSet.getString("location");
            player = new Player(firstName, lastName, defence, attack, speed, strength);
            player.setLocation(location);
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during player updating", e);
        } finally {
            close(preparedStatement);
            pool.returnConnection(connection);
        }
        return player;
    }

    /**
     * Adds definite person to database.
     * @param   person is an instance of {@code Person} class that
     *          has to be divided and placed into two different tables.
     * @throws  DAOException if {@code SQLException} during the person
     *          adding occurs.
     */
    @Override
    public void addPerson(Person person) throws DAOException {
        personDAO.addPerson(person);
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_ADD_PLAYER);
            preparedStatement.setInt(1, ((Player) person).getDefence());
            preparedStatement.setInt(2, ((Player) person).getAttack());
            preparedStatement.setInt(3, ((Player) person).getSpeed());
            preparedStatement.setInt(4, ((Player) person).getStrength());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation during player adding", e);
        } finally {
            close(preparedStatement);
            pool.returnConnection(connection);
        }
    }

    /**
     * Deletes definite person from database using person's {@param id}.
     * @param   id is an integer personal identifier.
     * @throws  DAOException if {@code SQLException} during the person
     *          adding occurs.
     */
    @Override
    public void deletePerson(int id) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_PLAYER);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during player deleting", e);
        } finally {
            close(preparedStatement);
            pool.returnConnection(connection);
        }
        personDAO.deletePerson(id);
    }

    @Override
    public void sellPlayer(int id) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELL_PLAYER);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during player selling", e);
        } finally {
            close(preparedStatement);
            pool.returnConnection(connection);
        }
    }

    /**
     * Gives the list of persons of definite type from database.
     * @return  the list of persons collected by definite rules.
     * @throws  DAOException if {@code SQLException} during the person
     *          adding occurs.
     */
    @Override
    public List<Person> takePersons() throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Person> persons = null;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_PLAYERS);
            resultSet = preparedStatement.executeQuery();
            persons = initPersons(resultSet);
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during player getting", e);
        } finally {
            close(preparedStatement);
            pool.returnConnection(connection);
        }
        return persons;
    }

    /**
     * Local method, hat gives the list of persons, extracted from
     * {@param resultSet} after query execution.
     * @param   resultSet is an instance of {@code ResultSet} which includes
     *          all necessary information after query execution.
     * @return  the list of persons collected by definite rules.
     * @throws  DAOException if unable to get connection to database or
     *          if there are any troubles with query execution.
     */
    private List<Person> initPersons(ResultSet resultSet) throws DAOException {
        List<Person> persons = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Person player = new Player();
                int playerId = resultSet.getInt("player_id");
                player.setId(playerId);
                player.setFirstName(personDAO.getNameById(playerId)[0]);
                player.setLastName(personDAO.getNameById(playerId)[1]);
                ((Player) player).setDefence(resultSet.getInt("defence"));
                ((Player) player).setAttack(resultSet.getInt("attack"));
                ((Player) player).setSpeed(resultSet.getInt("speed"));
                ((Player) player).setStrength(resultSet.getInt("strength"));
                ((Player) player).setLocation(resultSet.getString("location"));
                persons.add(player);
            }
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during player getting", e);
        }
        return persons;
    }

}
