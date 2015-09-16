package by.epam.hockeyschool.database.dao;

import by.epam.hockeyschool.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by demonwtf on 10.08.2015.
 */
public interface PriceDAO {

    final static Logger LOG = Logger.getLogger(PriceDAO.class);

    void createPrice(int id, int price) throws DAOException;

    void deletePrice(int id) throws DAOException;

    int definePriceById(int id) throws DAOException;

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
