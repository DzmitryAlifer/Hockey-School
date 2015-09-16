package by.epam.hockeyschool.navigation.commands;

import by.epam.hockeyschool.config.ConfigManager;
import by.epam.hockeyschool.entity.Player;
import by.epam.hockeyschool.service.PlayerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Dmitry Olifer on 02.07.2015.
 * <p>
 * The {@code CoachDoPlayerTrainCommand} class represents an ability of manipulation
 * with an instance of {@code HttpServletRequest}.
 */
public class CoachDoPlayerTrainCommand implements Command {

    String nextPage;

    /**
     * Defines player that will be trained. Puts a link for player's
     * object into session's attribute. If there is no chosen player,
     * puts a warning message into session's attribute.
     *
     * @param request is an instance of {@code HttpServletRequest}.
     * @return a result page at which should be forwarded to.
     */
    @Override
    public String execute(HttpServletRequest request) {
        nextPage = ConfigManager.getProperty("page.coachdoplayertrain");
        HttpSession session = request.getSession();
        if (session.getAttribute("previousCommand").equals("coach_player_train")) {
            defineTrain(request);
        }
        session.setAttribute("previousCommand", "coach_do_player_train");
        return nextPage;
    }

    private void defineTrain(HttpServletRequest request) {
        String playerIdStr;
        HttpSession session = request.getSession();
        PlayerService playerService = new PlayerService();
        if ((playerIdStr = request.getParameter("id")) != null) {
            int currentId = Integer.parseInt(playerIdStr);
            Player player = playerService.definePlayer(currentId);
            session.setAttribute("trainingPlayerId", currentId);
            session.setAttribute("trainingPlayer", player);
        } else {
            session.setAttribute("nobodyToTrain", "You have not chosen any player to train!");
            nextPage = ConfigManager.getProperty("page.coachplayertrain");
        }
    }
}
