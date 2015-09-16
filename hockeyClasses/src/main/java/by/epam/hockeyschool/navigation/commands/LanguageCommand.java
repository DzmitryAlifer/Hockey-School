package by.epam.hockeyschool.navigation.commands;

import by.epam.hockeyschool.config.ConfigManager;
import by.epam.hockeyschool.entity.Admin;
import by.epam.hockeyschool.entity.Person;
import by.epam.hockeyschool.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Dmitry Olifer on 02.07.2015.
 * <p>
 * The {@code LangusgeCommand} class represents an ability of manipulation
 * with an instance of {@code HttpServletRequest}.
 */
public class LanguageCommand implements Command {

    /**
     * Sets up the chosen language for the user's session. After language
     * changing returns to the last visited page.
     *
     * @param request is an instance of {@code HttpServletRequest}.
     * @return a result page at which should be forwarded to.
     */
    @Override
    public String execute(HttpServletRequest request) {
        AdminService adminService = new AdminService();
        String language = request.getParameter("lang");
        HttpSession session = request.getSession();
        switch (language) {
            case "en":
                session.setAttribute("locale", "en_US");
                break;
            case "ru":
                session.setAttribute("locale", "ru_RU");
                break;
            case "by":
                session.setAttribute("locale", "be_BY");
                break;
        }
        String nextPage = ConfigManager.getProperty("page.index");
        Person person = (Person) session.getAttribute("person");
        if (person != null) {
            String personClass = person.getClass().getSimpleName().toLowerCase();
            switch (personClass) {
                case "admin":
                    int adminId = adminService.defineAdminId(((Admin) person).getLogin(), ((Admin) person).getPassword());
                    boolean isOwner = adminService.isOwner(adminId);
                    if (!isOwner) {
                        session.setAttribute("role", "administrator");
                    } else {
                        session.setAttribute("role", "owner");
                    }
                    break;
                case "coach":
                    session.setAttribute("role", "coach");
                    break;
                case "physio":
                    session.setAttribute("role", "physiotherapist");
                    break;
            }
            String previousCommand = (String) session.getAttribute("previousCommand");
            nextPage = "/Controller?command=" + previousCommand;
        }
        return nextPage;
    }
}