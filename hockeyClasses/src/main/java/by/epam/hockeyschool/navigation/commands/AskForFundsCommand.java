package by.epam.hockeyschool.navigation.commands;

import by.epam.hockeyschool.config.ConfigManager;
import by.epam.hockeyschool.service.PlayerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by demonwtf on 13.08.2015.
 */
public class AskForFundsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("previousCommand", "ask_for_funds");
        String nextPage = ConfigManager.getProperty("page.askforfunds");
        return nextPage;
    }
}
