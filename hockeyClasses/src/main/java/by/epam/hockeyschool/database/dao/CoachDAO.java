package by.epam.hockeyschool.database.dao;

import by.epam.hockeyschool.entity.Coach;
import by.epam.hockeyschool.entity.Person;
import by.epam.hockeyschool.exception.DAOException;

/**
 * Created by Dmitry Olifer on 16.06.2015.
 *
 * The {@code CoachDAO} interface enumerates all the necessary methods
 * for manipulation with coach's data in database.
 */
public interface CoachDAO extends PersonDAO {

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
    Person defineCoach(String loginHash, String passwordHash) throws DAOException;

    /**
     * According to database, defines which {@code id} conforms to such
     * {@param loginHash} and {@param passwordHash}.
     * @param   loginHash submits the inputted value of login which was
     *          already hashed.
     * @param   passwordHash submits the inputted value of login which was
     *          already hashed.
     * @return  a positive integer value of definite {@code id}.
     * @throws  DAOException if unable to get connection to database or
     *          if there are any troubles with query execution.
     */
    int defineCoachId(String loginHash, String passwordHash) throws DAOException;

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
    String defineCoachInfo(int id) throws DAOException;

    /**
     * According to database, defines is the inputted login new or not.
     * @param   loginHash submits the inputted value of login which was
     *          already hashed.
     * @return  {@code true} if the same login is already in database,
     *          otherwise returns {@code false}.
     * @throws  DAOException if unable to get connection to database or
     *          if there are any troubles with query execution.
     */
    boolean isLoginNew(String loginHash) throws DAOException;

    /**
     * According to database, defines coach's hashed login and hashed
     * password that conforms to its {@param id}.
     * @param   id means coach's identification number.
     * @return  an instance of {@code String} that consists of coach's
     *          hashed login and hashed password.
     * @throws  DAOException if unable to get connection to database or
     *          if there are any troubles with query execution.
     */
    //String defineLogPass(int id) throws DAOException;

    void updateCoach(Coach coach) throws DAOException;
}
