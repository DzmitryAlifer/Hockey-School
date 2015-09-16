package by.epam.hockeyschool.database.dao;

import by.epam.hockeyschool.database.pool.ConnectionPool;
import by.epam.hockeyschool.entity.MoneyTransfer;
import by.epam.hockeyschool.entity.Person;
import by.epam.hockeyschool.entity.Player;
import by.epam.hockeyschool.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by demonwtf on 16.08.2015.
 */
public class MoneyTransferDAOImpl implements MoneyTransferDAO {

    private static final String SQL_ADD_TRANSFER = "INSERT INTO hockey_school.money_transfers (date, sum, operation) VALUES (?,?,?);";
    private static final String SQL_RECEIVE_TRANSFERS ="SELECT * FROM hockey_school.money_transfers;";

    @Override
    public void addMoneyTransfer(MoneyTransfer transfer) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_ADD_TRANSFER);
            preparedStatement.setDate(1, new java.sql.Date(new Date().getTime()));
            preparedStatement.setInt(2, transfer.getSum());
            preparedStatement.setString(3, transfer.getOperation());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation during player's price adding", e);
        } finally {
            close(preparedStatement);
            pool.returnConnection(connection);
        }
    }

    @Override
    public List<MoneyTransfer> receiveAllTransfers() throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<MoneyTransfer> transfers = null;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_RECEIVE_TRANSFERS);
            resultSet = preparedStatement.executeQuery();
            transfers = initTransfers(resultSet);
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during player getting", e);
        } finally {
            close(preparedStatement);
            pool.returnConnection(connection);
        }
        return transfers;
    }

    private List<MoneyTransfer> initTransfers(ResultSet resultSet) throws DAOException {
        List<MoneyTransfer> transfers = new ArrayList<>();
        try {
            while (resultSet.next()) {
                MoneyTransfer transfer = new MoneyTransfer();
                java.sql.Date date = resultSet.getDate("date");
                int sum = resultSet.getInt("sum");
                String operation = resultSet.getString("operation");
                transfer.setDate(date);
                transfer.setSum(sum);
                transfer.setOperation(operation);
                transfers.add(transfer);
            }
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during player getting", e);
        }
        return transfers;
    }
}
