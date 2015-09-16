package by.epam.hockeyschool.service;

import by.epam.hockeyschool.database.dao.PlayerPriceDAOImpl;
import by.epam.hockeyschool.exception.DAOException;
import org.apache.log4j.Logger;

/**
 * Created by demonwtf on 10.08.2015.
 */
public class PlayerPriceService {

    final static Logger LOG = Logger.getLogger(PlayerPriceService.class);

    PlayerPriceDAOImpl playerPriceDAO = new PlayerPriceDAOImpl();

    public void createPrice(int playerId, int price) {
        try {
            playerPriceDAO.createPrice(playerId, price);
        } catch (DAOException e) {
            LOG.error(e);
        }
    }

    public void deletePrice(int playerId) {
        try {
            playerPriceDAO.deletePrice(playerId);
        } catch (DAOException e) {
            LOG.error(e);
        }
    }

    public int definePriceById(int playerId) {
        int price = 0;
        try {
            price = playerPriceDAO.definePriceById(playerId);
        } catch (DAOException e) {
            LOG.error(e);
        }
        return price;
    }
}
