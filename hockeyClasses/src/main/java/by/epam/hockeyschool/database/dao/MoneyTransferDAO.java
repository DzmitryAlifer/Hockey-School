package by.epam.hockeyschool.database.dao;

import by.epam.hockeyschool.entity.MoneyTransfer;
import by.epam.hockeyschool.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by demonwtf on 16.08.2015.
 */
public interface MoneyTransferDAO {

    final static Logger LOG = Logger.getLogger(MoneyTransferDAO.class);

    void addMoneyTransfer(MoneyTransfer transfer) throws DAOException;

    List<MoneyTransfer> receiveAllTransfers() throws DAOException;

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
