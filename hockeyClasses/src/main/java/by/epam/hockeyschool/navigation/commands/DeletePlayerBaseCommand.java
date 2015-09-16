package by.epam.hockeyschool.navigation.commands;

import by.epam.hockeyschool.config.ConfigManager;
import by.epam.hockeyschool.entity.Player;
import by.epam.hockeyschool.service.PlayerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Dmitry Olifer on 02.07.2015.
 *
 * The {@code DeletePlayerBaseCommand} class represents an ability of manipulation
 * with an instance of {@code HttpServletRequest}.
 */
public class DeletePlayerBaseCommand implements Command {

    /**
     * Tries to delete the chosen players from database. After that prepares a new
     * player list, putting it into session's attribute.
     * @param   request is an instance of {@code HttpServletRequest}.
     * @return  a result page at which should be forwarded to.
     */
    @Override
    public String execute(HttpServletRequest request) {
        String nextPage = ConfigManager.getProperty("page.playerlist");
        HttpSession session = request.getSession();
        PlayerService playerService = new PlayerService();
        String[] playerId = request.getParameterValues("id");
        if (playerId == null) {
            session.setAttribute("nobodyToDelete", "You have not chosen anybody!");
            nextPage = ConfigManager.getProperty("page.deleteplayer");
            return nextPage;
        }
        for (String idString : playerId) {
            int id = Integer.parseInt(idString);
            playerService.deletePerson(id);
        }
        List<Player> schoolPlayers = playerService.getSchoolPlayers();
        session.setAttribute("schoolPlayers", schoolPlayers);
        List<Player> teamPlayers = playerService.getTeamPlayers();
        session.setAttribute("teamPlayers", teamPlayers);
        return nextPage;
    }
}
