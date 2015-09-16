package by.epam.hockeyschool.database.dao;

import by.epam.hockeyschool.database.pool.ConnectionPool;
import by.epam.hockeyschool.entity.Coach;
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
 * The {@code CoachDAOImpl} class implements all the necessary methods
 * that were inherited from {@code PersonDAO} and {@code CoachDAO}
 * interfaces for manipulation with coach's data in database.
 */
public class CoachDAOImpl implements CoachDAO {

    private static final Logger LOG = Logger.getLogger(CoachDAOImpl.class);

    private static final String SQL_ADD_COACH = "INSERT INTO hockey_school.coaches (defence_inc, attack_inc, coach_login, password, coach_id) VALUES (?, ?, ?, ?, (SELECT max(id) FROM hockey_school.person));";
    private static final String SQL_UPDATE_COACH = "UPDATE hockey_school.coaches SET defence_inc=?, attack_inc=? WHERE coach_id=?;";
    private static final String SQL_DELETE_COACH = "DELETE FROM hockey_school.coaches WHERE coach_id=?;";
    private static final String SQL_GET_COACHES = "SELECT * FROM hockey_school.coaches;";
    private static final String SQL_CHECK_COACH_LOG = "SELECT * FROM hockey_school.coaches WHERE coach_login=?";
    private static final String SQL_CHECK_COACH_LOG_PASS = "SELECT * FROM hockey_school.coaches WHERE coach_login=? AND password=?;";
    private static final String SQL_GET_COACH_STATS = "SELECT defence_inc, attack_inc FROM hockey_school.coaches WHERE coach_id=?;";
    private static final String SQL_GET_COACH_FULL_NAME = "SELECT firstname, lastname FROM hockey_school.person WHERE id=?;";
    //private static final String SQL_GET_COACH_LOG_PASS = "SELECT coach_login, password FROM hockey_school.coaches WHERE id=?;";

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
            preparedStatement = connection.prepareStatement(SQL_ADD_COACH);
            preparedStatement.setInt(1, ((Coach) person).getDefenceInc());
            preparedStatement.setInt(2, ((Coach) person).getAttackInc());
            preparedStatement.setString(3, ((Coach) person).getLogin());
            preparedStatement.setString(4, ((Coach) person).getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during coach adding");
        } finally {
            close(preparedStatement);
            pool.returnConnection(connection);
        }
    }

    @Override
    public void updateCoach(Coach coach) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_COACH);
            preparedStatement.setInt(1, coach.getDefenceInc());
            preparedStatement.setInt(2, coach.getAttackInc());
            preparedStatement.setInt(3, coach.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during coach updating", e);
        } finally {
            close(preparedStatement);
            pool.returnConnection(connection);
        }
    }

    /**
     * Deletes definite coach from database using coach's {@code id}.
     * The process consist of two parts: deleting of the note with names
     * and {@code id} from table {@code person} and also deleting of his
     * hashed login and hashed password from related table
     * {@code administrators}.
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
            preparedStatement1 = connection.prepareStatement(SQL_DELETE_COACH);
            preparedStatement1.setInt(1, id);
            preparedStatement1.executeUpdate();
            preparedStatement2 = connection.prepareStatement(PersonDAOImpl.SQL_DELETE_PERSON);
            preparedStatement2.setInt(1, id);
            preparedStatement2.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during coach deleting");
        } finally {
            close(preparedStatement1);
            close(preparedStatement2);
            pool.returnConnection(connection);
        }
        personDAO.deletePerson(id);
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
        int coachId = 0;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_CHECK_COACH_LOG);
            preparedStatement.setString(1, loginHash);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during coach deleting");
        }
        try {
            coachId = resultSet.getInt("coach_id");
        } catch (SQLException e) {
            return coachId == 0; // in case when such login was already existed
        }
        finally {
            close(preparedStatement);
            pool.returnConnection(connection);
        }
        return coachId == 0;
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
    public int defineCoachId(String loginHash, String passwordHash) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int coachId = 0;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_CHECK_COACH_LOG_PASS);
            preparedStatement.setString(1, loginHash);
            preparedStatement.setString(2, passwordHash);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            coachId = resultSet.getInt("coach_id");
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during coach deleting");
        } finally {
            try {
                resultSet.getString("coach_login");
                resultSet.getString("password");
            } catch (SQLException e) {
                return -1;
            }
            close(preparedStatement);
            pool.returnConnection(connection);
        }
        return coachId;
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
   /* @Override
    public String defineLogPass(int id) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String logPass = null;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_COACH_LOG_PASS);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            logPass = resultSet.getString("coach_login") + " " + resultSet.getString("password");
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during coach getting");
        } finally {
            close(preparedStatement);
            pool.returnConnection(connection);
        }
        return logPass;
    }*/

    /**
     * Represents the way of getting coach as an instance of {@code Person}
     * through its {@param loginHash} and {@param passwordHash} values.
     * @param   loginHash submits the inputted value of login which was
     *          already hashed.
     * @param   passwordHash submits the inputted value of login which was
     *          already hashed.
     * @return  the only instance of {@code Person} that defined just by using
     *          its {@param loginHash} and {@param passwordHash}.
     * @throws  DAOException if unable to get connection to database.
     */
    @Override
    public Person defineCoach(String loginHash, String passwordHash) throws DAOException {
        int id;
        String fullInfo;
        Coach coach = null;
        if ((id = defineCoachId(loginHash, passwordHash)) > 0) {
            fullInfo = defineCoachInfo(id);
            String[] info = fullInfo.split(" ");
            coach = new Coach(info[0], info[1], Integer.parseInt(info[2]), Integer.parseInt(info[3]), loginHash, loginHash);
        }
        return coach;
    }

    /**
     * According to database, defines all the coach's info (full name and
     * skills) that conforms to its {@param id}.
     * @param   id means coach's identification number.
     * @return  an instance of {@code String} that consists of first name,
     *          last name, attack-teaching skill and defence-teaching skill
     *          divided by space character.
     * @throws  DAOException if unable to get connection to database or
     *          if there are any troubles with query execution.
     */
    @Override
    public String defineCoachInfo(int id) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement1 = null, preparedStatement2 = null;
        ResultSet resultSet1 = null, resultSet2 = null;
        String fullInfo = null;
        try {
            connection = pool.getConnection();
            preparedStatement1 = connection.prepareStatement(SQL_GET_COACH_FULL_NAME);
            preparedStatement1.setInt(1, id);
            resultSet1 = preparedStatement1.executeQuery();
            resultSet1.next();
            preparedStatement2 = connection.prepareStatement(SQL_GET_COACH_STATS);
            preparedStatement2.setInt(1, id);
            resultSet2 = preparedStatement2.executeQuery();
            resultSet2.next();
            fullInfo = resultSet1.getString("firstname") + " " + resultSet1.getString("lastname") + " " + resultSet2.getInt("defence_inc") + " " + resultSet2.getInt("attack_inc");
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during coach getting");
        } finally {
            close(preparedStatement1);
            close(preparedStatement2);
            pool.returnConnection(connection);
        }
        return fullInfo;
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
            preparedStatement = connection.prepareStatement(SQL_GET_COACHES);
            resultSet = preparedStatement.executeQuery();
            persons = initPersons(resultSet);
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during coach getting", e);
        } finally {
            close(preparedStatement);
            pool.returnConnection(connection);
        }
        return persons;
    }

    /**
     * Gives the list of persons, extracted from {@param resultSet} after
     * query execution.
     * @param   resultSet is an instance of {@code ResultSet} which includes
     *          all necessary information after query execution.
     * @return  the list of persons collected by definite rules.
     * @throws  DAOException if unable to get connection to database or
     *          if there are any troubles with query execution.
     */
    private List<Person> initPersons(ResultSet resultSet) throws DAOException {
        List<Person> persons = new ArrayList<Person>();
        try {
            while (resultSet.next()) {
                Person coach = new Coach();
                int coachId = resultSet.getInt("coach_id");
                coach.setId(coachId);
                coach.setFirstName(personDAO.getNameById(coachId)[0]);
                coach.setLastName(personDAO.getNameById(coachId)[1]);
                ((Coach) coach).setDefenceInc(resultSet.getInt("defence_inc"));
                ((Coach) coach).setAttackInc(resultSet.getInt("attack_inc"));
                persons.add(coach);
            }
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during coach getting", e);
        }
        return persons;
    }
}