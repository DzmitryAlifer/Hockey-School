package by.epam.hockeyschool.database.dao;

import by.epam.hockeyschool.database.pool.ConnectionPool;
import by.epam.hockeyschool.entity.Player;
import by.epam.hockeyschool.entity.PlayerQuestionnaire;
import by.epam.hockeyschool.exception.DAOException;
import by.epam.hockeyschool.exception.TechnicalException;
import org.apache.log4j.Logger;

import java.io.*;
import java.sql.*;

/**
 * Created by Dmitry Olifer on 30.07.2015.
 */
public class PlayerQuestDAOImpl implements QuestDAO {

    private static final Logger LOG = Logger.getLogger(PlayerQuestDAOImpl.class);

    private static final String SQL_ADD_QUEST = "INSERT INTO hockey_school.player_questionnaire (quest_id, birth_year, birth_place, nationality, jersey, preferable_side, game_role, height, weight, interests) VALUES (?,?,?,?,?,?,?,?,?,?);";
    private static final String SQL_DEFINE_QUEST = "SELECT * FROM hockey_school.player_questionnaire WHERE quest_id = ?;";
    private static final String SQL_DELETE_QUEST = "DELETE FROM hockey_school.player_questionnaire WHERE quest_id=?;";
    private static final String SQL_UPDATE_QUEST = "UPDATE hockey_school.player_questionnaire SET birth_year=?, birth_place=?, nationality=?, jersey=?, preferable_side=?, game_role=?, height=?, weight=?, interests=? WHERE quest_id=?;";

    @Override
    public void createQuestionnaire(PlayerQuestionnaire quest) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_ADD_QUEST);
            preparedStatement.setInt(1, quest.getPlayerId());
            preparedStatement.setInt(2, quest.getBirthYear());
            preparedStatement.setString(3, quest.getBirthPlace());
            preparedStatement.setString(4, quest.getNationality());
            preparedStatement.setInt(5, quest.getJersey());
            preparedStatement.setString(6, quest.getPreferableSide());
            preparedStatement.setString(7, quest.getGameRole());
            preparedStatement.setInt(8, quest.getHeight());
            preparedStatement.setInt(9, quest.getWeight());
            preparedStatement.setString(10, quest.getInterests());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation during player's questionnaire adding", e);
        } finally {
            close(preparedStatement);
            pool.returnConnection(connection);
        }
    }

    @Override
    public PlayerQuestionnaire defineQuestionnaire(int playerId) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        PlayerQuestionnaire quest = null;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DEFINE_QUEST);
            preparedStatement.setInt(1, playerId);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int birthYear = resultSet.getInt("birth_year");
            String birthPlace = resultSet.getString("birth_place");
            String nationality = resultSet.getString("nationality");
            int jersey = resultSet.getInt("jersey");
            String preferableSide = resultSet.getString("preferable_side");
            String gameRole = resultSet.getString("game_role");
            int height = resultSet.getInt("height");
            int weight = resultSet.getInt("weight");
            String interests = resultSet.getString("interests");
            quest = new PlayerQuestionnaire(playerId, birthYear, birthPlace, nationality, jersey, preferableSide, gameRole, height, weight, interests);
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during player's questionnaire getting", e);
        } finally {
            close(preparedStatement);
            pool.returnConnection(connection);
        }
        return quest;
    }

    @Override
    public void updateQuestionnaire(PlayerQuestionnaire quest) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_QUEST);
            preparedStatement.setInt(1, quest.getBirthYear());
            preparedStatement.setString(2, quest.getBirthPlace());
            preparedStatement.setString(3, quest.getNationality());
            preparedStatement.setInt(4, quest.getJersey());
            preparedStatement.setString(5, quest.getPreferableSide());
            preparedStatement.setString(6, quest.getGameRole());
            preparedStatement.setInt(7, quest.getHeight());
            preparedStatement.setInt(8, quest.getWeight());
            preparedStatement.setString(9, quest.getInterests());
            preparedStatement.setInt(10, quest.getPlayerId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation during player's questionnaire updating", e);
        } finally {
            close(preparedStatement);
            pool.returnConnection(connection);
        }
    }

    @Override
    public void deleteQuestionnaire(int playerId) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_QUEST);
            preparedStatement.setInt(1, playerId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Invalid database operation occurred during player's questionnaire deleting", e);
        } finally {
            close(preparedStatement);
            pool.returnConnection(connection);
        }
    }


}
