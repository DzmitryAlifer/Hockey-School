package by.epam.hockeyschool.navigation.commands;

import by.epam.hockeyschool.config.ConfigManager;
import by.epam.hockeyschool.entity.Player;
import by.epam.hockeyschool.service.PlayerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by demonwtf on 20.08.2015.
 */
public class PhysioDoPlayerTrainCommand implements Command {

    String nextPage;

    @Override
    public String execute(HttpServletRequest request) {
        nextPage = ConfigManager.getProperty("page.physiodoplayertrain");
        HttpSession session = request.getSession();
        if (session.getAttribute("previousCommand").equals("physio_player_train")) {
            defineTrain(request);
        }
        session.setAttribute("previousCommand", "physio_do_player_train");
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
            nextPage = ConfigManager.getProperty("page.physioplayertrain");
        }
    }
}
