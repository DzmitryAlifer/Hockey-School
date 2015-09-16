package by.epam.hockeyschool.database.dao;

import by.epam.hockeyschool.database.pool.ConnectionPool;
import by.epam.hockeyschool.entity.Admin;
import by.epam.hockeyschool.entity.Person;
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
 * The {@code AdminDAOImpl} class implements all the necessary methods
 * that were inherited from {@code PersonDAO} and {@code AdminDAO}
 * interfaces for manipulation with administrator's data in database.
 */
public class AdminDAOImpl implements AdminDAO {

    private static final Logger LOG = Logger.getLogger(AdminDAOImpl.class);

    private static final String SQL_ADD_ADMIN = "INSERT INTO hockey_school.admins (login, password, admin_id) VALUES (?, ?, (SELECT max(id) FROM hockey_school.person));";
    private static final String SQL_DELETE_ADMIN = "DELETE FROM hockey_school.admins WHERE admin_id=?;";
    private static final String SQL_GET_ADMINS = "SELECT * FROM hockey_school.admins;";
    private static final String SQL_CHECK_ADMIN_LOG = "SELECT * FROM hockey_school.admins WHERE login=?";
    private static final String SQL_CHECK_ADMIN_LOG_PASS = "SELECT * FROM hockey_school.admins WHERE login=? AND password=?;";
    private static final String SQL_GET_ADMIN_FULL_NAME = "SELECT firstname, lastname FROM hockey_school.person WHERE id=?;";
    private static final String SQL_GET_ADMIN_LOG_PASS = "SELECT login, password FROM hockey_school.admins WHERE id=?;";
    private static final String SQL_IS_OWNER = "SELECT is_owner FROM hockey_school.admins WHERE admin_id=?;";

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
            preparedStatement = connection.prepareStatement(SQL_ADD_ADMIN);
            preparedStatement.setString(1, ((Admin) person).getLogin());
            preparedStatement.setString(2, ((Admin) person).getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during administrator adding", e);
        } finally {
            close(preparedStatement);
            pool.returnConnection(connection);
        }
    }

    /**
     * Deletes definite administrator from database using administrator's
     * {@code id}. The process consist of two parts: deleting of the note
     * with names and {@code id} from table {@code person} and also
     * deleting of his hashed login and hashed password from related
     * table {@code administrators}.
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
            preparedStatement1 = connection.prepareStatement(SQL_DELETE_ADMIN);
            preparedStatement1.setInt(1, id);
            preparedStatement1.executeUpdate();
            preparedStatement2 = connection.prepareStatement(PersonDAOImpl.SQL_DELETE_PERSON);
            preparedStatement2.setInt(1, id);
            preparedStatement2.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during administrator deleting", e);
        } finally {
            close(preparedStatement1);
            close(preparedStatement2);
            pool.returnConnection(connection);
        }
    }

    /**
     * Represents the way of getting administrator as an instance of
     * {@code Person} through its {@code loginHash} and {@code passwordHash}
     * values.
     * @param   loginHash submits the inputted value of login which was
     *          already hashed.
     * @param   passwordHash submits the inputted value of login which was
     *          already hashed.
     * @return  the only instance of {@code Person} that defined just by using
     *          its {@code loginHash} and {@code passwordHash}.
     * @throws  DAOException if unable to get connection to database.
     */
    @Override
    public Person defineAdmin(String loginHash, String passwordHash) throws DAOException {
        int id;
        String fullName;
        Admin admin = null;
        if ((id = defineAdminId(loginHash, passwordHash)) > 0) {
            fullName = defineFullName(id);
            admin = new Admin(fullName.split(" ")[0], fullName.split(" ")[1], loginHash, loginHash);
        }
        return admin;
    }

    /**
     * According to database, defines is the inputted login new or not.
     * @param   loginHash submits the inputted value of login which was
     *          already hashed.
     * @return  {@code true} if the same login is already in database,
     *          otherwise returns {@code false}.
     * @throws  DAOException if unable to get connection to database or
     *          if there are any troubles with query execution.
     */
    @Override
    public boolean isLoginNew(String loginHash) throws DAOException {
        boolean isNew = false;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int adminId = 0;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_CHECK_ADMIN_LOG);
            preparedStatement.setString(1, loginHash);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
        } catch (SQLException e) {
             throw new DAOException("Invalid database operation occurred during administrator deleting", e);
        }
        try {
            adminId = resultSet.getInt("admin_id");
        } catch (SQLException e) {
            return adminId == 0; // in case when such login were already existed
        }
        finally {
            close(preparedStatement);
            pool.returnConnection(connection);
        }
        return adminId == 0;
    }

    /**
     * According to database, defines which {@code id} conforms to such
     * {@code loginHash} and {@code passwordHash}
     * @param   loginHash submits the inputted value of login which was
     *          already hashed.
     * @param   passwordHash submits the inputted value of login which was
     *          already hashed.
     * @return  a positive integer value of definite {@code id}.
     * @throws  DAOException if unable to get connection to database or
     *          if there are any troubles with query execution.
     */
    @Override
    public int defineAdminId(String loginHash, String passwordHash) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int adminId = 0;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_CHECK_ADMIN_LOG_PASS);
            preparedStatement.setString(1, loginHash);
            preparedStatement.setString(2, passwordHash);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            adminId = resultSet.getInt("admin_id");
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during administrator deleting", e);
        } finally {
            try {
                resultSet.getString("login");
                resultSet.getString("password");
            } catch (SQLException e) {
                return -1;
            }
            close(preparedStatement);
            pool.returnConnection(connection);
        }
        return adminId;
    }

    /**
     * According to database, defines which login and password conforms to
     * such {@param id}.
     * @param   id means administrator's identification number.
     * @return  an instance of {@code String} that consists of {@code loginHash}
     *          and {@code passwordHash} divided by space character.
     * @throws  DAOException if unable to get connection to database or
     *          if there are any troubles with query execution.
     */
    @Override
    public String defineLogPass(int id) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String logPass = null;
        try {connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_ADMIN_LOG_PASS);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            logPass = resultSet.getString("login") + " " + resultSet.getString("password");
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during administrator getting", e);
        } finally {
            close(preparedStatement);
            pool.returnConnection(connection);
        }
        return logPass;
    }

    /**
     * According to database, defines administrator's full name that conforms
     * to such {@param id}.
     * @param   id means administrator's identification number.
     * @return  an instance of {@code String} that consists of {@code firstName}
     *          and {@code lastName} divided by space character.
     * @throws  DAOException if unable to get connection to database or
     *          if there are any troubles with query execution.
     */
    @Override
    public String defineFullName(int id) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String fullName = null;
        try {
            connection = pool.getConnection();//что с проверкой на null?
            preparedStatement = connection.prepareStatement(SQL_GET_ADMIN_FULL_NAME);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            fullName = resultSet.getString("firstname") + " " + resultSet.getString("lastname");
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during administrator getting", e);
        } finally {
            close(preparedStatement);
            pool.returnConnection(connection);
        }
        return fullName;
    }

    @Override
    public boolean isOwner(int id) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String value;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_IS_OWNER);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            value = resultSet.getString("is_owner");
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during administrator getting", e);
        } finally {
            close(preparedStatement);
            pool.returnConnection(connection);
        }
        return value.equals("1");
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
            preparedStatement = connection.prepareStatement(SQL_GET_ADMINS);
            resultSet = preparedStatement.executeQuery();
            persons = initPersons(resultSet);
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during administrator getting", e);
        } finally {
            close(preparedStatement);
            pool.returnConnection(connection);
        }
        return persons;
    }

    /**
     * Local method, that gives the list of persons, extracted from
     * {@param resultSet} after query execution.
     * @param   resultSet is an instance of {@code ResultSet} which
     *          includes all necessary information after query execution.
     * @return  the list of persons collected by definite rules.
     * @throws  DAOException if unable to get connection to database or
     *          if there are any troubles with query execution.
     */
    private List<Person> initPersons(ResultSet resultSet) throws DAOException {
        List<Person> persons = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Person admin = new Admin();
                int adminId = resultSet.getInt("admin_id");
                admin.setId(adminId);
                admin.setFirstName(personDAO.getNameById(adminId)[0]);
                admin.setLastName(personDAO.getNameById(adminId)[1]);
                ((Admin) admin).setLogin(resultSet.getString("login"));
                ((Admin) admin).setPassword(resultSet.getString("password"));
                persons.add(admin);
            }
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during administrator getting", e);
        }
        return persons;
    }
}