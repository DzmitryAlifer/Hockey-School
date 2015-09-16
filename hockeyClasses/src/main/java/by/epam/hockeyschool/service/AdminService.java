package by.epam.hockeyschool.service;

import by.epam.hockeyschool.database.dao.AdminDAOImpl;
import by.epam.hockeyschool.entity.Admin;
import by.epam.hockeyschool.entity.Person;
import by.epam.hockeyschool.exception.DAOException;
import by.epam.hockeyschool.validator.HashCoder;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Dmitry Olifer on 19.06.2015.
 *
 * The {@code AdminService} class represents a layer-class between
 * the {@code AdminDAOImpl} class (DAO class) and servlet.
 */
public class AdminService {

    final static Logger LOG = Logger.getLogger(AdminService.class);
    AdminDAOImpl adminDAO = new AdminDAOImpl();
    HashCoder hashCoder = new HashCoder();

    /**
     * Adds definite administrator to database.
     * @param   firstName is administrator's first name.
     * @param   lastName is administrator's last name.
     * @param   loginHash is a hashed value of administrator's login
     * @param   passwordHash is a hashed value of administrator's password.
     */
    public void addPerson(String firstName, String lastName, String loginHash, String passwordHash) {
        try {
            Admin admin = new Admin(firstName, lastName, loginHash, passwordHash);
            adminDAO.addPerson(admin);
        } catch (DAOException e) {
            LOG.error(e);
        }
    }

    /**
     * Deletes definite administrator from database using administrator's
     * {@code id}. The process consist of two parts: deleting of the note
     * with names and {@code id} from table {@code person} and also
     * deleting of his hashed login and hashed password from related
     * table {@code administrators}.
     * @param   id is an integer personal identifier.
     */
    public void deletePerson(int id) {
        try {
            adminDAO.deletePerson(id);
        } catch (DAOException e) {
            LOG.error(e);
        }
    }

    /**
     * According to database, defines is the inputted login new or not.
     * @param   login submits the inputted value of login which was not
     *          hashed yet.
     * @return  {@code true} if the same login is already in database,
     *          otherwise returns {@code false}.
     */
    public boolean isLoginNew(String login) {
        String loginHash = hashCoder.createHashedValue(login);
        boolean isNew = false;
        try {
            isNew = adminDAO.isLoginNew(loginHash);
        } catch (DAOException e) {
            LOG.error(e);

        }
        return isNew;
    }

    /**
     * According to database, defines administrator's full name that conforms
     * to such {@param login} and {@param password}.
     * @param   login submits the inputted value of administrator's login
     *          which was not hashed yet.
     * @param   password submits the inputted value of administrator's password
     *          which was not hashed yet.
     * @return  an instance of {@code String} that consists of {@code firstName}
     *          and {@code lastName} divided by space character.
     */
    public String defineFullName(String login, String password) {
        int adminId = defineAdminId(hashCoder.createHashedValue(login), hashCoder.createHashedValue(password));
        String fullName = null;
        try {
            fullName = adminDAO.defineFullName(adminId);
        } catch (DAOException e) {
            LOG.error(e);
        }
        return fullName;
    }

    /**
     * According to database, defines which {@code id} conforms to such
     * {@param hashedLogin} and {@param hashedPassword}
     * @param   hashedLogin submits the inputted value of login which was
     *          already hashed.
     * @param   hashedPassword submits the inputted value of login which was
     *          already hashed.
     * @return  a positive integer value of definite {@code id}.
     */
    public int defineAdminId(String hashedLogin, String hashedPassword) {
        int adminId = 0;
        try {
            adminId = adminDAO.defineAdminId(hashedLogin, hashedPassword);
        } catch (DAOException e) {
            LOG.error(e);
        }
        return adminId;
    }

    /**
     * According to database, defines which login and password conforms to
     * such {@param id}.
     * @param   id means administrator's identification number.
     * @return  an instance of {@code String} that consists of {@code loginHash}
     *          and {@code passwordHash} divided by space character.
     */
    public String defineLogPass(int id) {
        String logPass = null;
        try {
            logPass = adminDAO.defineLogPass(id);
        } catch (DAOException e) {
            LOG.error(e);
        }
        return logPass;
    }

    /**
     * Represents the way of getting administrator as an instance of
     * {@code Person} through its {@param loginHash} and {@param passwordHash}
     * values.
     * @param   loginHash submits the inputted value of login which was
     *          already hashed.
     * @param   passwordHash submits the inputted value of login which was
     *          already hashed.
     * @return  the only instance of {@code Person} that defined just by using
     *          its {@code loginHash} and {@code passwordHash}.
     */
    public Person defineAdmin(String loginHash, String passwordHash) {
        Person admin = null;
        try {
            admin = adminDAO.defineAdmin(loginHash, passwordHash);
        } catch (DAOException e) {
            LOG.error(e);
        }
        return admin;
    }

    /**
     * Gives the list of persons of definite type from database.
     * @return  the list of persons collected by definite rules.
     */
    public List<Admin> getPersons() {
        try {
            List<Person> persons = adminDAO.takePersons();
            List<Admin> admins = new ArrayList<>();
            for (Person person : persons) {
                admins.add((Admin) person);
            }
            return admins;
        } catch (DAOException e) {
            LOG.error(e);
        }
        return Collections.emptyList();
    }

    public boolean isOwner(int id) {
        boolean isOwner = false;
        try {
            isOwner = adminDAO.isOwner(id);
        } catch (DAOException e) {
            LOG.error(e);
        }
        return isOwner;
    }

    public boolean defineAnswer() {
        double randomValue = Math.random() - 0.5;
        return randomValue >= 0;
    }
}