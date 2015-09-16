package by.epam.hockeyschool.navigation.commands;

import by.epam.hockeyschool.config.ConfigManager;
import by.epam.hockeyschool.entity.Player;
import by.epam.hockeyschool.service.PlayerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Dmitry Olifer on 30.07.2015.
 */
public class QuestFormCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int playerId;
        String playerIdStr = request.getParameter("id");
        if (playerIdStr != null) {
            playerId = Integer.parseInt(playerIdStr);
        } else {
            playerId = (int)session.getAttribute("playerId");
        }
        Player chosenPlayer = new PlayerService().definePlayer(playerId);
        session.setAttribute("player", chosenPlayer);
        session.setAttribute("playerId", playerId);
        session.setAttribute("previousCommand", "quest_form");

        String nextPage = ConfigManager.getProperty("page.questform");
        return nextPage;
    }
}
