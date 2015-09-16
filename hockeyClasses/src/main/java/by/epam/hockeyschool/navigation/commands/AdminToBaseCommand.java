package by.epam.hockeyschool.navigation.commands;

import by.epam.hockeyschool.config.ConfigManager;
import by.epam.hockeyschool.entity.Admin;
import by.epam.hockeyschool.service.AdminService;
import by.epam.hockeyschool.service.PersonService;
import by.epam.hockeyschool.validator.HashCoder;
import by.epam.hockeyschool.validator.RegExpValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Dmitry Olifer on 02.07.2015.
 * <p>
 * The {@code AdminToBaseCommand} class represents an ability of manipulation
 * with an instance of {@code HttpServletRequest}.
 */
public class AdminToBaseCommand implements Command {

    PersonService personService = new PersonService();
    AdminService adminService = new AdminService();
    RegExpValidator validator = new RegExpValidator();
    HashCoder hashCoder = new HashCoder();
    String nextPage = ConfigManager.getProperty("page.adminlist");

    /**
     * Adds already checked administrator to database. Puts the list
     * of administrators to session's attribute. Gives a string value
     * of the page on which you can see this list.
     *
     * @param request is an instance of {@code HttpServletRequest}.
     * @return a result page at which should be forwarded to.
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String differentPasswords = (String) session.getAttribute("differentPasswords");
        String previousCommand = (String) session.getAttribute("previousCommand");
        if (previousCommand.equals("add_admin") || (previousCommand.equals("admin_to_base") && differentPasswords != null)) {
            addAdminToDatabase(request);
        }
        List<Admin> admins = adminService.getPersons();
        session.setAttribute("admins", admins);
        session.setAttribute("previousCommand", "admin_to_base");
        return nextPage;
    }

    /**
     * Tries to find already existed administrator with the same login
     * in database. If there is now such login, checks inputted data
     * and adds this administrator to database. Otherwise, puts a
     * warning message to session's attribute.
     *
     * @param request is an instance of {@code HttpServletRequest}.
     */
    private void addAdminToDatabase(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String firstName = request.getParameter("newFirstName");
        String lastName = request.getParameter("newLastName");
        String login = request.getParameter("newAdminLogin");
        String password = request.getParameter("newAdminPassword");
        String repeatedPassword = request.getParameter("newAdminPasswordCopy");
        request.setAttribute("adminFirstName", firstName);
        request.setAttribute("adminLastName", lastName);
        request.setAttribute("adminLogin", login);
        request.setAttribute("adminPassword", password);
        if (! adminService.isLoginNew(login)) {
            session.setAttribute("invalidLogin", "Warning! Such login was already existed!");
            nextPage = ConfigManager.getProperty("page.addadmin");
        } else if (! password.equals(repeatedPassword)) {
            session.setAttribute("differentPasswords", "Warning! You entered two different passwords!");
            nextPage = ConfigManager.getProperty("page.addadmin");
        } else if (isAdminDataValid(firstName, lastName, login, password)) {
            firstName = personService.transformName(firstName);
            lastName = personService.transformName(lastName);
            String loginHash = hashCoder.createHashedValue(login);
            String passwordHash = hashCoder.createHashedValue(password);
            adminService.addPerson(firstName, lastName, loginHash, passwordHash);
        } else {
            session.setAttribute("invalidName", "Warning! Invalid first or last name!");
            nextPage = ConfigManager.getProperty("page.addadmin");
        }
    }

    /**
     * Checks the correctness of the inputted administrator's data.
     *
     * @param firstName is a first name of administrator.
     * @param lastName  is a last name of administrator.
     * @param login     ia an administrator's login.
     * @param password  is an administrator's password.
     * @return {@code true} if all the administrator's information
     * is correct, otherwise returns {@code false}.
     */
    private boolean isAdminDataValid(String firstName, String lastName, String login, String password) {
        return validator.validate("name", firstName) &&
                validator.validate("name", lastName) &&
                validator.validate("login", login) &&
                validator.validate("password", password);
    }
}
