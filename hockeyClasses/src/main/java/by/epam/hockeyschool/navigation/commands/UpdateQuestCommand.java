package by.epam.hockeyschool.navigation.commands;

import by.epam.hockeyschool.config.ConfigManager;
import by.epam.hockeyschool.entity.Player;
import by.epam.hockeyschool.entity.PlayerQuestionnaire;
import by.epam.hockeyschool.service.PlayerQuestService;
import by.epam.hockeyschool.service.PlayerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by demonwtf on 05.08.2015.
 */
public class UpdateQuestCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int playerId = Integer.parseInt(request.getParameter("playerId"));
        session.setAttribute("playerId", playerId);
        Player chosenPlayer = new PlayerService().definePlayer(playerId);
        PlayerQuestionnaire quest = new PlayerQuestService().defineQuestionnaire(playerId);
        session.setAttribute("quest", quest);
        session.setAttribute("player", chosenPlayer);
        session.setAttribute("previousCommand", "update_quest");
        String nextPage = ConfigManager.getProperty("page.questform");
        return nextPage;
    }
}
