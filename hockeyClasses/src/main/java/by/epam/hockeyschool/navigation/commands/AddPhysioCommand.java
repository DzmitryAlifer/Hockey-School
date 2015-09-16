package by.epam.hockeyschool.navigation.commands;

import by.epam.hockeyschool.config.ConfigManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Dmitry Olifer on 02.07.2015.
 *
 * The {@code AddPhysioCommand} class represents an ability of manipulation
 * with an instance of {@code HttpServletRequest}.
 */
public class AddPhysioCommand implements Command {

    /**
     * Gives a string value of the page on which you can create a new
     * physiotherapist. Before it checks previous page. If the page is
     * not the same as current, it will not show a warning message.
     * @param   request is an instance of {@code HttpServletRequest}.
     * @return  a result page at which should be forwarded to.
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (! request.getHeader("Referer").matches("(.*)command=physio_to_base(.*)")) {
            session.setAttribute("invalidName", null);
            session.setAttribute("invalidLogin", null);
            session.setAttribute("differentPasswords", null);
        }
        session.setAttribute("previousCommand", "add_physio");
        String nextPage = ConfigManager.getProperty("page.addphysio");
        return nextPage;
    }
}
