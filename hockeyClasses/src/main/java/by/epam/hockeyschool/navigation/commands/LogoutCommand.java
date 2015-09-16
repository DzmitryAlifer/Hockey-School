package by.epam.hockeyschool.navigation.commands;

import by.epam.hockeyschool.config.ConfigManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Dmitry Olifer on 02.07.2015.
 *
 * The {@code LogoutCommand} class represents an ability of manipulation
 * with an instance of {@code HttpServletRequest}.
 */
public class LogoutCommand implements Command {

    /**
     * Invalidates current session. Next shown page will be welcom page.
     * @param   request is an instance of {@code HttpServletRequest}.
     * @return  a result page at which should be forwarded to.
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return ConfigManager.getProperty("page.index");
    }
}
