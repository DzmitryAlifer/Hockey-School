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
 * The {@code PlayerListCommand} class represents an ability of manipulation
 * with an instance of {@code HttpServletRequest}.
 */
public class PlayerListCommand implements Command {

    /**
     * Gives a string value of the page on which you can see a list of
     * players. As long as current user is an administrator, next page will
     * contain a simple player list. And if current user is a coach, on the
     * next page he will able to choose player for training. Defines this
     * list and puts it into a session's attribute. Also gives an ability
     * to be redirected to current page after changing the value of
     * language console by putting the name of this command to session's
     * attribute.
     * @param   request is an instance of {@code HttpServletRequest}.
     * @return  a result page at which should be forwarded to.
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        PlayerService playerService = new PlayerService();
        List<Player> schoolPlayers = playerService.getSchoolPlayers();
        List<Player> teamPlayers = playerService.getTeamPlayers();
        session.setAttribute("schoolPlayers", schoolPlayers);
        session.setAttribute("teamPlayers", teamPlayers);
        String previousCommand = (String) session.getAttribute("previousCommand");
        if (! previousCommand.equals("coach_start") && ! previousCommand.equals("coach_do_tactic_train") && ! previousCommand.equals("physio_do_tactic_train")) {
            session.setAttribute("skill", null);
            session.setAttribute("skillInc", null);
            session.setAttribute("idAndIncrementMap", null);
        }
        String nextPage = null;
        String role = (String) session.getAttribute("role");
        session.setAttribute("previousCommand", "player_list");
        switch (role) {
            case "owner":
                nextPage = ConfigManager.getProperty("page.playerlist");
                break;
            case "administrator":
                nextPage = ConfigManager.getProperty("page.playerlist");
                break;
            case "coach":
                nextPage = ConfigManager.getProperty("page.coachplayers");
                break;
            case "physiotherapist":
                nextPage = ConfigManager.getProperty("page.physioplayers");
                break;
            default:
                nextPage = ConfigManager.getProperty("page.error");
                break;
        }
        return nextPage;
    }
}