package by.epam.hockeyschool.service;


import by.epam.hockeyschool.database.dao.PhysioDAO;
import by.epam.hockeyschool.database.dao.PhysioDAOImpl;
import by.epam.hockeyschool.entity.Person;
import by.epam.hockeyschool.entity.Physio;
import by.epam.hockeyschool.exception.DAOException;
import by.epam.hockeyschool.validator.HashCoder;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;

/**
 * Created by Dmitry Olifer on 19.06.2015.
 * <p>
 * The {@code PhysioService} class represents a layer-class between
 * the {@code PhysioDAOImpl} class (DAO class) and servlet.
 */
public class PhysioService {

    final static Logger LOG = Logger.getLogger(PhysioService.class);
    HashCoder hashCoder = new HashCoder();
    PhysioDAO physioDAO = new PhysioDAOImpl();

    /**
     * Adds definite physiotherapist to database.
     *
     * @param firstName   is coach's first name.
     * @param lastName    is coach's last name.
     * @param speedInc    is a speed teaching skill.
     * @param strengthInc is a strength teaching skill.
     */
    public void addPerson(String firstName, String lastName, int speedInc, int strengthInc, String loginHash, String passwordHash) {
        try {
            Physio physio = new Physio(firstName, lastName, speedInc, strengthInc, loginHash, passwordHash);
            physioDAO.addPerson(physio);
        } catch (DAOException e) {
            LOG.error(e);

        }
    }

    public void updatePhysio(Physio physio) {
        try {
            physioDAO.updatePhysio(physio);
        } catch (DAOException e) {
            LOG.error(e);
        }
    }

    /**
     * Deletes definite physiotherapist from database using his {@code id}.
     * The process consist of two parts: deleting of the note with names and
     * {@code id} from table {@code person} and also deleting of his hashed
     * login and hashed password from related table {@code coaches}.
     *
     * @param id is an integer personal identifier.
     */
    public void deletePerson(int id) {
        try {
            physioDAO.deletePerson(id);
        } catch (DAOException e) {
            LOG.error(e);
        }
    }

    public boolean isLoginNew(String login) {
        String loginHash = hashCoder.createHashedValue(login);
        boolean isNew = false;
        try {
            isNew = physioDAO.isLoginNew(loginHash);
        } catch (DAOException e) {
            LOG.error(e);
        }
        return isNew;
    }

    public Person definePhysio(String loginHash, String passwordHash) {
        Person coach = null;
        try {
            coach = physioDAO.definePhysio(loginHash, passwordHash);
        } catch (DAOException e) {
            LOG.error(e);
        }
        return coach;
    }

    /**
     * Gives the list of persons of definite type from database.
     *
     * @return the list of persons collected by definite rules.
     */
    public List<Physio> getPersons() {
        try {
            List<Person> persons = physioDAO.takePersons();
            List<Physio> physios = new ArrayList<>();
            for (Person person : persons) {
                physios.add((Physio) person);
            }
            return physios;
        } catch (DAOException e) {
            LOG.error(e);
        }
        return Collections.emptyList();
    }

    /**
     * Calculates an average arithmetical value of all physiotherapists' rates.
     *
     * @param physios is a list of physiotherapists extracted from database.
     * @return an average arithmetical integer value of all physiotherapists'
     * rates.
     */
    public int averPhysioRate(List<Physio> physios) {
        int totalSum = 0;
        for (Physio physio : physios) {
            totalSum += (physio.getSpeedInc() + physio.getStrengthInc());
        }
        int averRate = 0;
        try {
            averRate = totalSum / (2 * physios.size());
        } catch (ArithmeticException e) {
        }
        return averRate;
    }

    public HashMap<Integer, Integer[]> physiosRandomImproveSkills() {
        List<Physio> physios = getPersons();
        HashMap<Integer, Integer[]> physioTrainResults = new HashMap<>();
        for (Physio physio : physios) {
            Integer[] grows = physioImproveSkills(physio);
            physioTrainResults.put(physio.getId(), grows);
            updatePhysio(physio);
        }
        return physioTrainResults;
    }

    private Integer[] physioImproveSkills(Physio physio) {
        int speedInc = physio.getSpeedInc();
        int strengthInc = physio.getStrengthInc();
        int speedGrow = (int) Math.round(Math.random());
        int strengthGrow = (int) Math.round(Math.random());
        speedInc += speedGrow;
        strengthInc += strengthGrow;
        physio.setSpeedInc(speedInc);
        physio.setStrengthInc(strengthInc);
        return new Integer[]{speedGrow, strengthGrow};
    }
}
