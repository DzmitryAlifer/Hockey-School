package by.epam.hockeyschool.navigation.commands;

import by.epam.hockeyschool.config.ConfigManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by demonwtf on 10.08.2015.
 */
public class PlayerPriceToBaseCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String nextPage = ConfigManager.getProperty("page.chooseplayerprice");

        return nextPage;
    }
}
