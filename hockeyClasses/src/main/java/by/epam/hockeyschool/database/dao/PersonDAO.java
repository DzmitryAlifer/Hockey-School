package by.epam.hockeyschool.database.dao;

import by.epam.hockeyschool.entity.Person;
import by.epam.hockeyschool.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Dmitry Olifer on 16.06.2015.
 *
 * The {@code PersonDAO} interface is the head DAO interface that
 * involves only basic methods for database manipulations.
 */
public interface PersonDAO {

    final static Logger LOG = Logger.getLogger(PersonDAO.class);

    /**
     * Adds definite person to database.
     * @param   person is an instance of {@code Person} class that
     *          has to be divided and placed into two different tables.
     * @throws by.epam.hockeyschool.exception.DAOException if {@code SQLException} during the person
     *          adding occurs.
     */
    void addPerson(Person person) throws DAOException;

    /**
     * Deletes definite person from database using person's {@param id}.
     * @param   id is an integer personal identifier.
     * @throws by.epam.hockeyschool.exception.DAOException if {@code SQLException} during the person
     *          adding occurs.
     */
    void deletePerson(int id) throws DAOException;

    /**
     * Gives the list of persons of definite type from database.
     * @return  the list of persons collected by definite rules.
     * @throws by.epam.hockeyschool.exception.DAOException if {@code SQLException} during the person
     *          adding occurs.
     */
    List<Person> takePersons() throws DAOException;

    /**
     * Releases this {@code Statement} object's database and JDBC
     * resources immediately instead of waiting for this to happen
     * when it is automatically closed.
     * @param   statement is the instance of SQL statement object
     */
    default void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
    }
}
