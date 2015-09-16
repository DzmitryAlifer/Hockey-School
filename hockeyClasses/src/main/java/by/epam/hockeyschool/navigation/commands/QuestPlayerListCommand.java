package by.epam.hockeyschool.navigation.commands;

import by.epam.hockeyschool.config.ConfigManager;
import by.epam.hockeyschool.entity.Player;
import by.epam.hockeyschool.service.PlayerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Dmitry Olifer on 30.07.2015.
 */
public class QuestPlayerListCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        PlayerService playerService = new PlayerService();
        if (! request.getHeader("Referer").matches("(.*)command=quest_to_base(.*)")) {
            session.setAttribute("invalidName", null);
        }
        List<Player> schoolPlayers = playerService.getSchoolPlayers();
        schoolPlayers = playerService.choosePlayersWithoutQuests(schoolPlayers);
        List<Player> teamPlayers = playerService.getTeamPlayers();
        teamPlayers = playerService.choosePlayersWithoutQuests(teamPlayers);
        session.setAttribute("schoolPlayers", schoolPlayers);
        session.setAttribute("teamPlayers", teamPlayers);
        session.setAttribute("previousCommand", "quest_player_list");
        String nextPage = ConfigManager.getProperty("page.questplayer");
        return nextPage;
    }
}
