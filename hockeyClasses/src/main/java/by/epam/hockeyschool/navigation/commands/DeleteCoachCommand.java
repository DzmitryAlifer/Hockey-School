package by.epam.hockeyschool.navigation.commands;

import by.epam.hockeyschool.config.ConfigManager;
import by.epam.hockeyschool.entity.Coach;
import by.epam.hockeyschool.service.CoachService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Dmitry Olifer on 02.07.2015.
 *
 * The {@code DeleteCoachCommand} class represents an ability of manipulation
 * with an instance of {@code HttpServletRequest}.
 */
public class DeleteCoachCommand implements Command {

    /**
     * Represents the list of coaches where you can choose one or more of
     * them for deleting. Before it checks previous page. If the page is not
     * the same as current, it will not show a warning message. Also makes
     * able to be redirected to this page after changing the value of
     * language console by putting the name of this command to session's
     * attribute.
     * @param   request is an instance of {@code HttpServletRequest}.
     * @return  a result page at which should be forwarded to.
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (! request.getHeader("Referer").matches("(.*)command=delete_coach(.*)")) {
            session.setAttribute("nobodyToDelete", null);
        }
        session.setAttribute("warning", null);
        List<Coach> coaches = new CoachService().getPersons();
        session.setAttribute("coaches", coaches);
        session.setAttribute("previousCommand", "delete_coach");
        String nextPage = ConfigManager.getProperty("page.deletecoach");
        return nextPage;
    }
}
