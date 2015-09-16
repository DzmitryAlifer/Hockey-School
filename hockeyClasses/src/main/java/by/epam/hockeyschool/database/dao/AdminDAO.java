package by.epam.hockeyschool.database.dao;

import by.epam.hockeyschool.entity.Person;
import by.epam.hockeyschool.exception.DAOException;

/**
 * Created by Dmitry Olifer on 18.06.2015.
 *
 * The {@code AdminDAO} interface enumerates all the necessary methods
 * for manipulation with administrator's data in database.
 */
public interface AdminDAO extends PersonDAO {

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
    public Person defineAdmin(String loginHash, String passwordHash) throws DAOException;

    /**
     * Defines according to database, is the inputted login new or not.
     * @param   loginHash submits the inputted value of login which was
     *          already hashed.
     * @return  {@code true} if the same login is already in database,
     *          otherwise returns {@code false}.
     * @throws  DAOException if unable to get connection to database or
     *          if there are any troubles with query execution.
     */
    public boolean isLoginNew(String loginHash) throws DAOException;

    /**
     * Defines according to database, which {@code id} conforms to such
     * {@code loginHash} and {@code passwordHash}
     * @param   loginHash submits the inputted value of login which was
     *          already hashed.
     * @param   passwordHash submits the inputted value of login which was
     *          already hashed.
     * @return  a positive integer value of definite {@code id}.
     * @throws  DAOException if unable to get connection to database or
     *          if there are any troubles with query execution.
     */
    public int defineAdminId(String loginHash, String passwordHash) throws DAOException;

    /**
     * According to database, defines which login and password conforms to
     * such {@param id}.
     * @param   id means administrator's identification number.
     * @return  an instance of {@code String} that consists of {@code loginHash}
     *          and {@code passwordHash} divided by space character.
     * @throws  DAOException if unable to get connection to database or
     *          if there are any troubles with query execution.
     */
    public String defineLogPass(int id) throws DAOException;

    /**
     * According to database, defines administrator's full name that conforms
     * to such {@param id}.
     * @param   id means administrator's identification number.
     * @return  an instance of {@code String} that consists of {@code firstName}
     *          and {@code lastName} divided by space character.
     * @throws  DAOException if unable to get connection to database or
     *          if there are any troubles with query execution.
     */
    public String defineFullName(int id) throws DAOException;

    public boolean isOwner(int id) throws DAOException;
}
