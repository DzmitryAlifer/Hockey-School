package by.epam.hockeyschool.service;

import by.epam.hockeyschool.database.dao.PlayerQuestDAOImpl;
import by.epam.hockeyschool.entity.PlayerQuestionnaire;
import by.epam.hockeyschool.exception.DAOException;
import by.epam.hockeyschool.exception.TechnicalException;
import org.apache.log4j.Logger;

/**
 * Created by Dmitry Olifer on 31.07.2015.
 */
public class PlayerQuestService {

    final static Logger LOG = Logger.getLogger(PlayerQuestService.class);

    PlayerQuestDAOImpl playerQuestDAO = new PlayerQuestDAOImpl();

    public void createQuestionnaire(PlayerQuestionnaire quest) {
        try {
            playerQuestDAO.createQuestionnaire(quest);
        } catch (DAOException e) {
            LOG.error(e);
        }
    }

    public void updateQuestionnaire(PlayerQuestionnaire quest) {
        try {
            playerQuestDAO.updateQuestionnaire(quest);
        } catch (DAOException e) {
            LOG.error(e);
        }
    }

    public PlayerQuestionnaire defineQuestionnaire(int playerId) {
        PlayerQuestionnaire quest = null;
        try {
            quest = playerQuestDAO.defineQuestionnaire(playerId);
        } catch (DAOException e) {
            LOG.error(e);
        }
        return quest;
    }

    public void deleteQuestionnaire(int playerId) {
        try {
            playerQuestDAO.deleteQuestionnaire(playerId);
        } catch (DAOException e) {
            LOG.error(e);
        }
    }
}
