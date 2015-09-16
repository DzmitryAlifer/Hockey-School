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
 * The {@code CoachTrainCommand} class represents an ability of manipulation
 * with an instance of {@code HttpServletRequest}.
 */
public class CoachPlayerTrainCommand implements Command {

    /**
     * Receives the list of players who can be trained and puts this list
     * into session's attribute. Before it checks previous page. If the page is
     * not the same as current, it will not show a warning message.
     * @param   request is an instance of {@code HttpServletRequest}.
     * @return  a result page at which should be forwarded to.
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("skill", null);
        session.setAttribute("skillInc", null);
        session.setAttribute("idAndIncrementMap", null);
        if (! request.getHeader("Referer").matches("(.*)command=coach_player_train(.*)")) {
            session.setAttribute("nobodyToTrain", null);
        }
        List<Player> schoolPlayers = new PlayerService().getSchoolPlayers();
        session.setAttribute("schoolPlayers", schoolPlayers);
        session.setAttribute("previousCommand", "coach_player_train");
        String nextPage = ConfigManager.getProperty("page.coachplayertrain");
        return nextPage;
    }
}
