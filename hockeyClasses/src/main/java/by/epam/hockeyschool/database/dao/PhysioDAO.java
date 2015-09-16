package by.epam.hockeyschool.database.dao;

import by.epam.hockeyschool.entity.Person;
import by.epam.hockeyschool.entity.Physio;
import by.epam.hockeyschool.exception.DAOException;

/**
 * Created by Dmitry Olifer on 20.07.2015.
 *
 * The {@code PhysioDAO} interface is empty now but it was
 * created in advance and ready to enumerate some new
 * functionality methods for physiotherapist's entity in
 * the future.
 */
public interface PhysioDAO extends PersonDAO {

    void updatePhysio(Physio physio) throws DAOException;

    boolean isLoginNew(String loginHash) throws DAOException;

    int definePhysioId(String loginHash, String passwordHash) throws DAOException;

    Person definePhysio(String loginHash, String passwordHash) throws DAOException;

    String definePhysioInfo(int id) throws DAOException;
}
