package by.epam.hockeyschool.navigation.commands;

import by.epam.hockeyschool.config.ConfigManager;
import by.epam.hockeyschool.entity.Coach;
import by.epam.hockeyschool.entity.Player;
import by.epam.hockeyschool.service.PersonService;
import by.epam.hockeyschool.service.PlayerService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Dmitry Olifer on 02.07.2015.
 * <p>
 * The {@code CoachStartCommand} class represents an ability of manipulation
 * with an instance of {@code HttpServletRequest}.
 */
public class CoachStartCommand implements Command {

    /**
     * Gets a player that should be trained from session's attribute, then
     * changes one of his skill and updates the player's data in
     * database. After that receives a new school player list from database
     * and puts it into session's attribute. Also gives an ability to be
     * redirected to current page after changing the value of language console
     * by putting the name of this command to session's attribute.
     *
     * @param request is an instance of {@code HttpServletRequest}.
     * @return a result page at which should be forwarded to.
     */
    @Override
    public String execute(HttpServletRequest request) {
        PlayerService playerService = new PlayerService();
        HttpSession session = request.getSession();
        Coach currentCoach = (Coach) session.getAttribute("person");
        Player trainingPlayer = (Player) session.getAttribute("trainingPlayer");
        String trainingSkill = request.getParameter("skill");
        session.setAttribute("skill", trainingSkill);
        session.setAttribute("skillInc", null);
        int trainingPlayerId = (int) session.getAttribute("trainingPlayerId");
        trainingPlayer.setId(trainingPlayerId);
        if (session.getAttribute("previousCommand").equals("coach_do_player_train")) {
            int skillInc = new PersonService().trainResult(currentCoach, trainingPlayer, trainingSkill);
            session.setAttribute("skillInc", skillInc);
        } else {
            session.setAttribute("skill", null);
        }
        playerService.updatePlayer(trainingPlayer);
        List<Player> schoolPlayers = playerService.getSchoolPlayers();
        String nextPage = ConfigManager.getProperty("page.coachplayers");
        session.setAttribute("schoolPlayers", schoolPlayers);
        session.setAttribute("previousCommand", "coach_start");
        return nextPage;
    }
}