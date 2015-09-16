package by.epam.hockeyschool.navigation.commands;

import by.epam.hockeyschool.config.ConfigManager;
import by.epam.hockeyschool.entity.Physio;
import by.epam.hockeyschool.service.PhysioService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Dmitry Olifer on 02.07.2015.
 *
 * The {@code DeletePhysioBaseCommand} class represents an ability of manipulation
 * with an instance of {@code HttpServletRequest}.
 */
public class DeletePhysioBaseCommand implements Command {

    /**
     * Tries to delete the chosen physiotherapists from database. After that
     * prepares a new physiotherapist list, putting it into session's attribute.
     * @param   request is an instance of {@code HttpServletRequest}.
     * @return  a result page at which should be forwarded to.
     */
    @Override
    public String execute(HttpServletRequest request) {
        String nextPage = ConfigManager.getProperty("page.physiolist");
        HttpSession session = request.getSession();
        PhysioService physioService = new PhysioService();
        String[] physioId = request.getParameterValues("id");
        if (physioId == null) {
            session.setAttribute("nobodyToDelete", "You have not chosen anybody!");
            nextPage = ConfigManager.getProperty("page.deletephysio");
            return nextPage;
        }
        for (String idString : physioId) {
            int id = Integer.parseInt(idString);
            physioService.deletePerson(id);
        }
        List<Physio> physios = physioService.getPersons();
        session.setAttribute("physios", physios);
        return nextPage;
    }
}
