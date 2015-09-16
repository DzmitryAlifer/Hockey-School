package by.epam.hockeyschool.database.dao;

import by.epam.hockeyschool.database.pool.ConnectionPool;
import by.epam.hockeyschool.entity.Person;
import by.epam.hockeyschool.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dmitry Olifer on 23.06.2015.
 *
 * The {@code PersonDAOImpl} class implements all the necessary methods
 * that were inherited from {@code PersonDAO} interface and also some
 * its own methods for manipulation with person's data in database.
 */
public class PersonDAOImpl implements PersonDAO {

    private static final Logger LOG = Logger.getLogger(PersonDAOImpl.class);

    private static final String SQL_ADD_PERSON = "INSERT INTO hockey_school.person (firstname, lastname) VALUES (?, ?);";
    public static final String SQL_DELETE_PERSON = "DELETE FROM hockey_school.person WHERE id=?;";
    private static final String SQL_GET_NAME_BY_ID = "SELECT firstname, lastname FROM hockey_school.person WHERE id=?;";
    private static final String SQL_GET_NEWEST_ID = "SELECT max(id) FROM hockey_school.person;";

    /**
     * Adds definite person to database.
     * @param   person is an instance of {@code Person} class that
     *          has to be divided and placed into two different tables.
     * @throws  DAOException if {@code SQLException} during the person
     *          adding occurs.
     */
    @Override
    public void addPerson(Person person) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_ADD_PERSON);
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during person adding", e);
        } finally {
            close(preparedStatement);
            pool.returnConnection(connection);
        }
    }

    /**
     * Gets person from database via his {@param id}.
     * @param   id is an integer personal identifier.
     * @return  an instance of {@code Person} with an information about
     *          his first name, last name and identifier from the table
     *          called 'person'.
     */
    public Person getPerson(int id) {
        String[] fullName = new String[0];
        try {
            fullName = getNameById(id);
        } catch (DAOException e) {
            LOG.error("Invalid database operation occurred during person getting");
        }
        String firstName = fullName[0];
        String lastName = fullName[1];
        Person person = new Person(firstName, lastName);
        person.setId(id);
        return person;
    }

    /**
     * Deletes definite person from database using person's {@param id}.
     * @param   id is an integer personal identifier.
     * @throws  DAOException if unable to get connection to database or
     *          if there are any troubles with query execution.
     */
    @Override
    public void deletePerson(int id) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_PERSON);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during person deleting", e);
        } finally {
            close(preparedStatement);
            pool.returnConnection(connection);
        }
    }

    /**
     * Defines the latest auto-generated {@param id} from the table
     * called 'person'.
     * @return  an integer value of the {@param id}.
     */
    public int getNewestId() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int id = 0;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_NEWEST_ID);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getInt(1);
        } catch (SQLException | DAOException e){
            LOG.error("Technical exception occurred during person deleting", e);
        } finally {
            close(preparedStatement);
            pool.returnConnection(connection);
        }
        return id;

    }

    /**
     * Defines first and last name of a person with definite {@param id}
     * and puts them into an string array.
     * @param   id is an integer personal identifier.
     * @return  a string array of two elements: first name and last name.
     * @throws  DAOException if unable to get connection to database or
     *          if there are any troubles with query execution.
     */
    public String[] getNameById(int id) throws DAOException {
        String[] fullName = new String[2];
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_NAME_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            fullName[0] = resultSet.getString("firstname");
            fullName[1] = resultSet.getString("lastname");
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during person's name getting", e);
        } finally {
            close(preparedStatement);
            pool.returnConnection(connection);
        }
        return fullName;
    }

    /**
     * Gives the list of persons of definite type from database.
     * @return  the list of persons collected by definite rules.
     * @throws  DAOException if unable to get connection to database or
     *          if there are any troubles with query execution.
     */
    @Override
    public List<Person> takePersons() throws DAOException {
        return null;
    }
}