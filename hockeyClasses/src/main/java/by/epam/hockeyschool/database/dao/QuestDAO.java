package by.epam.hockeyschool.database.dao;

import by.epam.hockeyschool.entity.PlayerQuestionnaire;
import by.epam.hockeyschool.exception.DAOException;
import by.epam.hockeyschool.exception.TechnicalException;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Dmitry Olifer on 30.07.2015.
 */
public interface QuestDAO {

    final static Logger LOG = Logger.getLogger(QuestDAO.class);

    void createQuestionnaire(PlayerQuestionnaire quest) throws DAOException;

    PlayerQuestionnaire defineQuestionnaire(int playerId) throws DAOException;

    void updateQuestionnaire(PlayerQuestionnaire quest) throws DAOException;

    void deleteQuestionnaire(int playerId) throws DAOException;

    default void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
    }
}
