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
 * The {@code CoachListCommand} class represents an ability of manipulation
 * with an instance of {@code HttpServletRequest}.
 */
public class CoachListCommand implements Command {

    /**
     * Gives a string value of the page on which you can see a list of
     * coaches. Defines this list and puts it as a session's attribute. Also
     * gives an ability to be redirected to current page after changing the
     * value of language console by putting the name of this command to
     * session's attribute.
     * @param   request is an instance of {@code HttpServletRequest}.
     * @return  a result page at which should be forwarded to.
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String nextPage = ConfigManager.getProperty("page.coachlist");
        List<Coach> coaches = new CoachService().getPersons();
        session.setAttribute("coaches", coaches);
        session.setAttribute("previousCommand", "coach_list");
        return nextPage;
    }
}
