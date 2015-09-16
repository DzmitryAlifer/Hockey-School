package by.epam.hockeyschool.service;

import by.epam.hockeyschool.database.dao.MoneyTransferDAOImpl;
import by.epam.hockeyschool.entity.MoneyTransfer;
import by.epam.hockeyschool.exception.DAOException;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by demonwtf on 16.08.2015.
 */
public class MoneyTransferService {

    final static Logger LOG = Logger.getLogger(MoneyTransferService.class);

    MoneyTransferDAOImpl moneyTransferDAO = new MoneyTransferDAOImpl();

    public void addMoneyTransfer(MoneyTransfer transfer) {
        try {
            moneyTransferDAO.addMoneyTransfer(transfer);
        } catch (DAOException e) {
            LOG.error(e);
        }
    }

    public List<MoneyTransfer> receiveAllTransfers() {
        List<MoneyTransfer> transfers = null;
        try {
            transfers = moneyTransferDAO.receiveAllTransfers();
        } catch (DAOException e) {
            LOG.error(e);
        }
        return transfers;
    }

    public int budgetAmount(List<MoneyTransfer> transfers) {
        int totalAmount = 0;
        for (MoneyTransfer transfer : transfers) {
            totalAmount += transfer.getSum();
        }
        return totalAmount;
    }
}
