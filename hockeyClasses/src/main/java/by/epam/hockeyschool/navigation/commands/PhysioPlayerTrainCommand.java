package by.epam.hockeyschool.navigation.commands;

import by.epam.hockeyschool.config.ConfigManager;
import by.epam.hockeyschool.entity.Player;
import by.epam.hockeyschool.service.PlayerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by demonwtf on 20.08.2015.
 */
public class PhysioPlayerTrainCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("skill", null);
        session.setAttribute("skillInc", null);
        List<Player> schoolPlayers = new PlayerService().getSchoolPlayers();
        session.setAttribute("schoolPlayers", schoolPlayers);
        session.setAttribute("previousCommand", "physio_player_train");
        String nextPage = ConfigManager.getProperty("page.physioplayertrain");
        return nextPage;
    }
}
