package by.epam.hockeyschool.service;

import by.epam.hockeyschool.database.dao.CoachDAOImpl;
import by.epam.hockeyschool.entity.Coach;
import by.epam.hockeyschool.entity.Person;
import by.epam.hockeyschool.entity.Physio;
import by.epam.hockeyschool.exception.DAOException;
import by.epam.hockeyschool.validator.HashCoder;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Dmitry Olifer on 19.06.2015.
 *
 * The {@code CoachService} class represents a layer-class between
 * the {@code CoachDAOImpl} class (DAO class) and servlet.
 */
public class CoachService {

    final static Logger LOG = Logger.getLogger(CoachService.class);
    CoachDAOImpl coachDAO = new CoachDAOImpl();
    HashCoder hashCoder = new HashCoder();

    /**
     * Adds definite coach to database.
     * @param   firstName is coach's first name.
     * @param   lastName is coach's last name.
     * @param   defenceInc is a defence teaching skill.
     * @param   attackInc is an attack teaching skill.
     * @param   loginHash is a hashed value of coach's login
     * @param   passwordHash is a hashed value of coach's password.
     */
    public void addPerson(String firstName, String lastName, int defenceInc, int attackInc, String loginHash, String passwordHash) {
        try {
            Coach coach = new Coach(firstName, lastName, defenceInc, attackInc, loginHash, passwordHash);
            coachDAO.addPerson(coach);
        } catch (DAOException e) {
            LOG.error(e);
        }
    }

    public void updateCoach(Coach coach) {
        try {
            coachDAO.updateCoach(coach);
        } catch (DAOException e) {
            LOG.error(e);
        }
    }

    /**
     * Deletes definite coach from database using coach's {@code id}. The
     * process consist of two parts: deleting of the note with names and
     * {@code id} from table {@code person} and also deleting of his hashed
     * login and hashed password from related table {@code coaches}.
     * @param   id is an integer personal identifier.
     */
    public void deletePerson(int id) {
        try {
            coachDAO.deletePerson(id);
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
            isNew = coachDAO.isLoginNew(loginHash);
        } catch (DAOException e) {
            LOG.error(e);
        }
        return isNew;
    }

    /**
     * Represents the way of getting coach as an instance of {@code Person}
     * through his {@param loginHash} and {@param passwordHash} values.
     * @param   loginHash submits the inputted value of login which was
     *          already hashed.
     * @param   passwordHash submits the inputted value of login which was
     *          already hashed.
     * @return  the only instance of {@code Person} that defined just by using
     *          his {@code loginHash} and {@code passwordHash}.
     */
    public Person defineCoach(String loginHash, String passwordHash) {
        Person coach = null;
        try {
            coach = coachDAO.defineCoach(loginHash, passwordHash);
        } catch (DAOException e) {
            LOG.error(e);
        }
        return coach;
    }

    /**
     * Gives the list of persons of definite type from database.
     * @return  the list of persons collected by definite rules.
     */
    public List<Coach> getPersons() {
        try {
            List<Person> persons = coachDAO.takePersons();
            List<Coach> coaches = new ArrayList<>();
            for (Person person : persons) {
                coaches.add((Coach) person);
            }
            return coaches;
        } catch (DAOException e) {
            LOG.error(e);
        }
        return Collections.emptyList();
    }

    /**
     * Calculates an average arithmetical value of all coaches' rates.
     * @param   coaches is a list of coaches extracted from database.
     * @return  an average arithmetical integer value of all coaches' rates.
     */
    public int averCoachRate (List<Coach> coaches) {
        double totalSum = 0;
        for (Coach coach : coaches) {
            totalSum += (coach.getAttackInc() + coach.getAttackInc());
        }
        double averRate = totalSum / (2 * coaches.size());
        return (int)averRate;
    }

    public HashMap<Integer, Integer[]> coachesRandomImproveSkills() {
        List<Coach> coaches = getPersons();
        HashMap<Integer, Integer[]> coachTrainResults = new HashMap<>();
        for (Coach coach : coaches) {
            Integer[] grows = coachImproveSkills(coach);
            coachTrainResults.put(coach.getId(), grows);
            updateCoach(coach);
        }
        return coachTrainResults;
    }

    private Integer[] coachImproveSkills(Coach coach) {
        int defenceInc = coach.getDefenceInc();
        int attackInc = coach.getAttackInc();
        int defenceGrow = (int) Math.round(Math.random());
        int attackGrow = (int) Math.round(Math.random());
        defenceInc += defenceGrow;
        attackInc += attackGrow;
        coach.setDefenceInc(defenceInc);
        coach.setAttackInc(attackInc);
        return new Integer[] {defenceGrow, attackGrow};
    }
}
