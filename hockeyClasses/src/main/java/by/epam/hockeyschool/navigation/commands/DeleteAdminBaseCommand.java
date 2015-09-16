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
 * The {@code DeleteAdminBaseCommand} class represents an ability of manipulation
 * with an instance of {@code HttpServletRequest}.
 */
public class DeleteAdminBaseCommand implements Command {

    /**
     * Tries to delete the chosen administrators from database. Avoid self-
     * deleting by comparing personal identifiers of chosen and current
     * administrators.
     * @param   request is an instance of {@code HttpServletRequest}.
     * @return  a result page at which should be forwarded to.
     */
    @Override
    public String execute(HttpServletRequest request) {
        String nextPage = ConfigManager.getProperty("page.adminlist");
        HttpSession session = request.getSession();
        Admin currentAdmin = (Admin) session.getAttribute("person");
        AdminService adminService = new AdminService();
        int currentAdminId = adminService.defineAdminId(currentAdmin.getLogin(), currentAdmin.getPassword());
        String[] adminID = request.getParameterValues("id");
        if (adminID == null) {
            session.setAttribute("nobodyToDelete", "You have not chosen anybody!");
            nextPage = ConfigManager.getProperty("page.deleteadmin");
            return nextPage;
        }
        for (String idString : adminID) {
            if (Integer.parseInt(idString) == currentAdminId) {
                session.setAttribute("selfDelete", "You can not delete yourself!");
                ConfigManager.getProperty("page.deleteadmin");
                return nextPage;
            }
        }
        for (String idString : adminID) {
            int id = Integer.parseInt(idString);
            adminService.deletePerson(id);
        }
        List<Admin> admins = adminService.getPersons();
        session.setAttribute("admins", admins);
        return nextPage;
    }
}
