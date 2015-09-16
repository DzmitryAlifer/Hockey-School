package by.epam.hockeyschool.database.dao;

import by.epam.hockeyschool.entity.Person;
import by.epam.hockeyschool.entity.Player;
import by.epam.hockeyschool.exception.DAOException;

/**
 * Created by Dmitry Olifer on 16.06.2015.
 *
 * The {@code PlayerDAO} interface enumerates all the necessary methods
 * for manipulation with player's data in database.
 */
public interface PlayerDAO extends PersonDAO {

    /**
     * Relocates player from hockey school to a team, changing the value
     * of his current {@param location}.
     * @param   id means player's identification number.
     * @throws  DAOException if unable to get connection to database or
     *          if there are any troubles with query execution.
     */
    void promotePlayer(int id) throws DAOException;

    /**
     * Updates player's attack/defence skills after training by coach or
     * speed/strength after training by physiotherapist.
     * @param   player is an instance of {@code Person}, that need to be
     *          updated.
     * @throws  DAOException if unable to get connection to database or
     *          if there are any troubles with query execution.
     */
    void updatePlayer(Person player) throws DAOException;

    /**
     * Collects all the player's data from database by its {@param id},
     * then creates and returns an instance of {@code Player} with this
     * data inside.
     * @param   id means player's identification number.
     * @return  an instance of {@code Player} with all information about
     *          the player inside this instance.
     * @throws  DAOException if unable to get connection to database or
     *          if there are any troubles with query execution.
     */
    Player definePlayer(int id) throws DAOException;

    void sellPlayer(int id) throws DAOException;

}
