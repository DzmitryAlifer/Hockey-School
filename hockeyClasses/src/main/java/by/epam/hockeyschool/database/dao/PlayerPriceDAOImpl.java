package by.epam.hockeyschool.database.dao;

import by.epam.hockeyschool.database.pool.ConnectionPool;
import by.epam.hockeyschool.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by demonwtf on 10.08.2015.
 */
public class PlayerPriceDAOImpl implements PriceDAO {

    private static final Logger LOG = Logger.getLogger(PlayerPriceDAOImpl.class);

    private static final String SQL_CREATE_PRICE = "INSERT INTO hockey_school.players_prices (player_id, price) VALUES (?,?);";
    private static final String SQL_DELETE_PRICE = "DELETE FROM hockey_school.players_prices WHERE player_id=?;";
    private static final String SQL_DEFINE_PRICE = "SELECT price FROM hockey_school.players_prices WHERE player_id=?;";

    @Override
    public void createPrice(int playerId, int price) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_CREATE_PRICE);
            preparedStatement.setInt(1, playerId);
            preparedStatement.setInt(2, price);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation during player's price adding", e);
        } finally {
            close(preparedStatement);
            pool.returnConnection(connection);
        }
    }

    @Override
    public void deletePrice(int playerId) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_PRICE);
            preparedStatement.setInt(1, playerId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during player's price deleting", e);
        } finally {
            close(preparedStatement);
            pool.returnConnection(connection);
        }
    }

    public int definePriceById(int playerId) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Integer price;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DEFINE_PRICE);
            preparedStatement.setInt(1, playerId);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            price = resultSet.getInt("price");
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during player's price defining", e);
        } finally {
            close(preparedStatement);
            pool.returnConnection(connection);
        }
        return price == null ? 0 : price;
    }
}
