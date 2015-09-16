package by.epam.hockeyschool.navigation.commands;

import by.epam.hockeyschool.config.ConfigManager;
import by.epam.hockeyschool.entity.Coach;
import by.epam.hockeyschool.entity.Physio;
import by.epam.hockeyschool.entity.Player;
import by.epam.hockeyschool.service.PersonService;
import by.epam.hockeyschool.service.PlayerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by demonwtf on 20.08.2015.
 */
public class PhysioStartCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        PlayerService playerService = new PlayerService();
        HttpSession session = request.getSession();
        Physio currentPhysio = (Physio) session.getAttribute("person");
        Player trainingPlayer = (Player) session.getAttribute("trainingPlayer");
        String trainingSkill = request.getParameter("skill");
        session.setAttribute("skill", trainingSkill);
        session.setAttribute("skillInc", null);
        int trainingPlayerId = (int) session.getAttribute("trainingPlayerId");
        trainingPlayer.setId(trainingPlayerId);
        if (session.getAttribute("previousCommand").equals("physio_do_player_train")) {
            int skillInc = new PersonService().trainResult(currentPhysio, trainingPlayer, trainingSkill);
            session.setAttribute("skillInc", skillInc);
        } else {
            session.setAttribute("skill", null);
        }
        playerService.updatePlayer(trainingPlayer);
        List<Player> schoolPlayers = playerService.getSchoolPlayers();
        String nextPage = ConfigManager.getProperty("page.physioplayers");
        session.setAttribute("schoolPlayers", schoolPlayers);
        session.setAttribute("previousCommand", "physio_start");
        return nextPage;
    }
}
