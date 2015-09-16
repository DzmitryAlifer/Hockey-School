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
 * The {@code DeleteCoachBaseCommand} class represents an ability of manipulation
 * with an instance of {@code HttpServletRequest}.
 */
public class DeleteCoachBaseCommand implements Command {

    /**
     * Tries to delete the chosen coaches from database. After that prepares a new
     * coach list, putting it into session's attribute.
     * @param   request is an instance of {@code HttpServletRequest}.
     * @return  a result page at which should be forwarded to.
     */
    @Override
    public String execute(HttpServletRequest request) {
        String nextPage = ConfigManager.getProperty("page.coachlist");
        HttpSession session = request.getSession();
        CoachService coachService = new CoachService();
        String[] coachId = request.getParameterValues("id");
        if (coachId == null) {
            session.setAttribute("nobodyToDelete", "You have not chosen anybody!");
            nextPage = ConfigManager.getProperty("page.deletecoach");
            return nextPage;
        }
        for (String idString : coachId) {
            int id = Integer.parseInt(idString);
            coachService.deletePerson(id);
        }
        List<Coach> coaches = coachService.getPersons();
        session.setAttribute("coaches", coaches);
        return nextPage;
    }
}
