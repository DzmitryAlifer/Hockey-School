package by.epam.hockeyschool.navigation.commands;

import by.epam.hockeyschool.config.ConfigManager;
import by.epam.hockeyschool.entity.Player;
import by.epam.hockeyschool.entity.PlayerQuestionnaire;
import by.epam.hockeyschool.exception.TechnicalException;
import by.epam.hockeyschool.service.PlayerQuestService;
import by.epam.hockeyschool.service.PlayerService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

/**
 * Created by Dmitry Olifer on 30.07.2015.
 */
public class QuestToBaseCommand implements Command {

    private static final Logger LOG = Logger.getLogger(QuestToBaseCommand.class);

    String nextPage = ConfigManager.getProperty("page.playerlist");

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        PlayerService playerService = new PlayerService();
        if (session.getAttribute("previousCommand").equals("quest_form")) {
            makeQuestionnaire(request);
        }
        List<Player> schoolPlayers = playerService.getSchoolPlayers();
        List<Player> teamPlayers = playerService.getTeamPlayers();
        session.setAttribute("schoolPlayers", schoolPlayers);
        session.setAttribute("teamPlayers", teamPlayers);
        session.setAttribute("previousCommand", "quest_to_base");
        return nextPage;
    }

    private void makeQuestionnaire(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Player currentPlayer = (Player) session.getAttribute("player");
        int playerId = (int) session.getAttribute("playerId");
        int birthYear = Integer.parseInt(request.getParameter("birthYear"));
        String birthPlace = request.getParameter("birthPlace");
        String nationality = request.getParameter("nationality");
        int height = Integer.parseInt(request.getParameter("height"));
        int weight = Integer.parseInt(request.getParameter("weight"));
        int jersey = Integer.parseInt(request.getParameter("jersey"));
        String preferableSide = request.getParameter("preferableSide");
        String gameRole = request.getParameter("gameRole");
        String interests = request.getParameter("interests");
        PlayerQuestionnaire newQuestionnaire = new PlayerQuestionnaire(playerId, birthYear, birthPlace, nationality, jersey, preferableSide, gameRole, height, weight, interests);
        PlayerQuestionnaire currentPlayerQuest = currentPlayer.getQuest();
        if (currentPlayerQuest == null) {
            new PlayerQuestService().createQuestionnaire(newQuestionnaire);
        } else {
            new PlayerQuestService().updateQuestionnaire(newQuestionnaire);
        }
        currentPlayer.setQuest(newQuestionnaire);

    }

}
