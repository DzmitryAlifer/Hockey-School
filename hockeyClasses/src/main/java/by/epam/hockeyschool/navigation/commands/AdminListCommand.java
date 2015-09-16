package by.epam.hockeyschool.navigation.commands;

import by.epam.hockeyschool.config.ConfigManager;
import by.epam.hockeyschool.entity.Admin;
import by.epam.hockeyschool.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Dmitry Olifer on 02.07.2015.
 *
 * The {@code AdminListCommand} class represents an ability of manipulation
 * with an instance of {@code HttpServletRequest}.
 */
public class AdminListCommand implements Command {

    /**
     * Gives a string value of the page on which you can see a list of
     * administrators. Defines this list and puts it as a session's
     * attribute. Also makes able to be redirected to this page after
     * changing the value of language console by putting the name of
     * this command to session's attribute.
     * @param   request is an instance of {@code HttpServletRequest}.
     * @return  a result page at which should be forwarded to.
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (! request.getHeader("Referer").matches("(.*)command=admin_list(.*)")) {
            session.setAttribute("nobodyToDelete", null);
            session.setAttribute("selfDelete", null);
        }
        String nextPage = ConfigManager.getProperty("page.adminlist");
        List<Admin> admins = new AdminService().getPersons();
        session.setAttribute("admins", admins);
        session.setAttribute("previousCommand", "admin_list");
        return nextPage;
    }
}
