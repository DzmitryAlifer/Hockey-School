package by.epam.hockeyschool.database.dao;

import by.epam.hockeyschool.database.pool.ConnectionPool;
import by.epam.hockeyschool.entity.Coach;
import by.epam.hockeyschool.entity.Person;
import by.epam.hockeyschool.entity.Physio;
import by.epam.hockeyschool.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry Olifer on 15.07.2015.
 *
 * The {@code PhysioDAOImpl} class implements all the necessary methods
 * that were inherited from {@code PersonDAO} and {@code PhysioDAO}
 * interfaces for manipulation with physiotherapist's data in database.
 */
public class PhysioDAOImpl implements PhysioDAO {

    private static final Logger LOG = Logger.getLogger(PhysioDAOImpl.class);

    private static final String SQL_ADD_PHYSIO = "INSERT INTO hockey_school.physios (speed_inc, strength_inc, physio_login, password, physio_id) VALUES (?, ?, ?, ?, (SELECT max(id) FROM hockey_school.person));";
    private static final String SQL_UPDATE_PHYSIO = "UPDATE hockey_school.physios SET speed_inc=?, strength_inc=? WHERE physio_id=?;";
    private static final String SQL_DELETE_PHYSIO = "DELETE FROM hockey_school.physios WHERE physio_id=?;";
    private static final String SQL_GET_PHYSIOS = "SELECT * FROM hockey_school.physios;";
    private static final String SQL_CHECK_PHYSIO_LOG = "SELECT * FROM hockey_school.physios WHERE physio_login=?";
    private static final String SQL_CHECK_PHYSIO_LOG_PASS = "SELECT * FROM hockey_school.physios WHERE physio_login=? AND password=?;";
    private static final String SQL_GET_PHYSIO_STATS = "SELECT speed_inc, strength_inc FROM hockey_school.physios WHERE physio_id=?;";
    private static final String SQL_GET_PHYSIO_FULL_NAME = "SELECT firstname, lastname FROM hockey_school.person WHERE id=?;";
    private static final String SQL_GET_PHYSIO_LOG_PASS = "SELECT physio_login, password FROM hockey_school.physios WHERE id=?;";

    private PersonDAOImpl personDAO = new PersonDAOImpl();

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
            preparedStatement = connection.prepareStatement(SQL_ADD_PHYSIO);
            preparedStatement.setInt(1, ((Physio) person).getSpeedInc());
            preparedStatement.setInt(2, ((Physio) person).getStrengthInc());
            preparedStatement.setString(3, ((Physio) person).getLoginHash());
            preparedStatement.setString(4, ((Physio) person).getPasswordHash());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during physio adding", e);
        } finally {
            close(preparedStatement);
            pool.returnConnection(connection);
        }
    }

    @Override
    public void updatePhysio(Physio physio) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_PHYSIO);
            preparedStatement.setInt(1, physio.getSpeedInc());
            preparedStatement.setInt(2, physio.getStrengthInc());
            preparedStatement.setInt(3, physio.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during physio updating", e);
        } finally {
            close(preparedStatement);
            pool.returnConnection(connection);
        }
    }

    @Override
    public boolean isLoginNew(String loginHash) throws DAOException {
        boolean isNew = false;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int physioId = 0;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_CHECK_PHYSIO_LOG);
            preparedStatement.setString(1, loginHash);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during physio deleting");
        }
        try {
            physioId = resultSet.getInt("physio_id");
        } catch (SQLException e) {
            return physioId == 0; // in case when such login was already existed
        }
        finally {
            close(preparedStatement);
            pool.returnConnection(connection);
        }
        return physioId == 0;
    }

    @Override
    public int definePhysioId(String loginHash, String passwordHash) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int physioId = 0;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_CHECK_PHYSIO_LOG_PASS);
            preparedStatement.setString(1, loginHash);
            preparedStatement.setString(2, passwordHash);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            physioId = resultSet.getInt("physio_id");
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during physiotherapist deleting");
        } finally {
            try {
                resultSet.getString("physio_login");
                resultSet.getString("password");
            } catch (SQLException e) {
                return -1;
            }
            close(preparedStatement);
            pool.returnConnection(connection);
        }
        return physioId;
    }

    @Override
    public Person definePhysio(String loginHash, String passwordHash) throws DAOException {
        int id;
        String fullInfo;
        Physio physio = null;
        if ((id = definePhysioId(loginHash, passwordHash)) > 0) {
            fullInfo = definePhysioInfo(id);
            String[] info = fullInfo.split(" ");
            physio = new Physio(info[0], info[1], Integer.parseInt(info[2]), Integer.parseInt(info[3]), loginHash, loginHash);
        }
        return physio;
    }

    @Override
    public String definePhysioInfo(int id) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement1 = null, preparedStatement2 = null;
        ResultSet resultSet1 = null, resultSet2 = null;
        String fullInfo = null;
        try {
            connection = pool.getConnection();
            preparedStatement1 = connection.prepareStatement(SQL_GET_PHYSIO_FULL_NAME);
            preparedStatement1.setInt(1, id);
            resultSet1 = preparedStatement1.executeQuery();
            resultSet1.next();
            preparedStatement2 = connection.prepareStatement(SQL_GET_PHYSIO_STATS);
            preparedStatement2.setInt(1, id);
            resultSet2 = preparedStatement2.executeQuery();
            resultSet2.next();
            fullInfo = resultSet1.getString("firstname") + " " + resultSet1.getString("lastname") + " " + resultSet2.getInt("speed_inc") + " " + resultSet2.getInt("strength_inc");
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during physiotherapist getting");
        } finally {
            close(preparedStatement1);
            close(preparedStatement2);
            pool.returnConnection(connection);
        }
        return fullInfo;
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
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        try {
            connection = pool.getConnection();
            preparedStatement1 = connection.prepareStatement(SQL_DELETE_PHYSIO);
            preparedStatement1.setInt(1, id);
            preparedStatement1.executeUpdate();
            preparedStatement2 = connection.prepareStatement(PersonDAOImpl.SQL_DELETE_PERSON);
            preparedStatement2.setInt(1, id);
            preparedStatement2.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during physio deleting", e);
        }finally {
            close(preparedStatement1);
            close(preparedStatement2);
            pool.returnConnection(connection);
        }
        personDAO.deletePerson(id);
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
            preparedStatement = connection.prepareStatement(SQL_GET_PHYSIOS);
            resultSet = preparedStatement.executeQuery();
            persons = initPersons(resultSet);
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during physio getting", e);
        } finally {
            new PhysioDAOImpl().close(preparedStatement);
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
                Person physio = new Physio();
                int physioId = resultSet.getInt("physio_id");
                physio.setId(physioId);
                physio.setFirstName(personDAO.getNameById(physioId)[0]);
                physio.setLastName(personDAO.getNameById(physioId)[1]);
                ((Physio) physio).setSpeedInc(resultSet.getInt("speed_inc"));
                ((Physio) physio).setStrengthInc(resultSet.getInt("strength_inc"));
                persons.add(physio);
            }
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during physio getting", e);
        }
        return persons;
    }


}