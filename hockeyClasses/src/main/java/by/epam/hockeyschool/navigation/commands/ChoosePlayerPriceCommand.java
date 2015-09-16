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
public class ChoosePlayerPriceCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        PlayerService playerService = new PlayerService();
        String nextPage = ConfigManager.getProperty("page.chooseplayerprice");
        String idStr = request.getParameter("id");
        int id = 0;
        if (idStr != null) {
            id = Integer.parseInt(idStr);
        }
        List<Player> unpricedTeamPlayers = (List<Player>) session.getAttribute("teamPlayers");
        Player player = null;
        for (Player currentPlayer : unpricedTeamPlayers) {
            if (currentPlayer.getId() == id) {
                player = currentPlayer;
                break;
            }
        }
        int recommendedPrice = playerService.defineRecommendedPlayerPrice(id) / 10 * 10;
        session.setAttribute("teamPlayer", player);
        session.setAttribute("recommendedPrice", recommendedPrice);
        session.setAttribute("previousCommand", "choose_player_price");
        return nextPage;
    }
}
