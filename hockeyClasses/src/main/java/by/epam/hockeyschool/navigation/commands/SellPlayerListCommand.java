package by.epam.hockeyschool.navigation.commands;

import by.epam.hockeyschool.config.ConfigManager;
import by.epam.hockeyschool.entity.Player;
import by.epam.hockeyschool.service.PlayerPriceService;
import by.epam.hockeyschool.service.PlayerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by demonwtf on 10.08.2015.
 */
public class SellPlayerListCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        PlayerService playerService = new PlayerService();
        PlayerPriceService priceService = new PlayerPriceService();
        String previousCommand = (String) session.getAttribute("previousCommand");
        switch (previousCommand) {
            case "choose_player_price":
                int price = Integer.parseInt(request.getParameter("price"));
                Player teamPlayer = (Player) session.getAttribute("teamPlayer");
                priceService.createPrice(teamPlayer.getId(), price);
                break;
            case "sell_player_list":
                String idStr = request.getParameter("id");
                if (idStr != null) {
                    int id = Integer.parseInt(idStr);
                    int sellPrice = priceService.definePriceById(id);
                    Player justSoldPlayer = playerService.definePlayer(id);
                    justSoldPlayer.setLocation("sold");
                    justSoldPlayer.setPrice(sellPrice);
                    playerService.sellPlayer(id);
                }
                break;
        }
        List<Player> pricedPlayers = playerService.getPricedTeamPlayers();
        session.setAttribute("teamPlayers", pricedPlayers);
        session.setAttribute("previousCommand", "sell_player_list");
        String nextPage = ConfigManager.getProperty("page.sellplayerlist");
        return nextPage;
    }
}
