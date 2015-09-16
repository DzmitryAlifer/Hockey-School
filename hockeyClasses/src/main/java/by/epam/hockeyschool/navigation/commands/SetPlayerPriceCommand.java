package by.epam.hockeyschool.navigation.commands;

import by.epam.hockeyschool.config.ConfigManager;
import by.epam.hockeyschool.entity.Player;
import by.epam.hockeyschool.service.PlayerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by demonwtf on 10.08.2015.
 */
public class SetPlayerPriceCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String nextPage = null;
        if (session.getAttribute("role").equals("owner")) {
            nextPage = ConfigManager.getProperty("page.setplayerprice");
        }
        List<Player> unpricedTeamPlayers = new PlayerService().getUnpricedTeamPlayers();
        session.setAttribute("teamPlayers", unpricedTeamPlayers);
        session.setAttribute("previousCommand", "set_player_price");
        return nextPage;
    }
}
