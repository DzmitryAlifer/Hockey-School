package by.epam.hockeyschool.navigation.commands;

import by.epam.hockeyschool.config.ConfigManager;
import by.epam.hockeyschool.entity.Physio;
import by.epam.hockeyschool.service.PersonService;
import by.epam.hockeyschool.service.PhysioService;
import by.epam.hockeyschool.validator.HashCoder;
import by.epam.hockeyschool.validator.RegExpValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Dmitry Olifer on 02.07.2015.
 * <p>
 * The {@code PhysioToBaseCommand} class represents an ability of manipulation
 * with an instance of {@code HttpServletRequest}.
 */
public class PhysioToBaseCommand implements Command {

    String nextPage = ConfigManager.getProperty("page.physiolist");

    /**
     * Adds already checked physiotherapist to database. Puts the list of
     * physiotherapists into session's attribute. Gives a string value of the
     * page on which you can see this list.
     *
     * @param request is an instance of {@code HttpServletRequest}.
     * @return a result page at which should be forwarded to.
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String differentPasswords = (String) session.getAttribute("differentPasswords");
        String previousCommand = (String) session.getAttribute("previousCommand");
        if (previousCommand.equals("add_physio") || (previousCommand.equals("physio_to_base") && differentPasswords != null)) {
            addPhysioToDatabase(request);
        }
        List<Physio> physios = new PhysioService().getPersons();
        session.setAttribute("physios", physios);
        session.setAttribute("previousCommand", "physio_to_base");
        return nextPage;
    }

    /**
     * Tries to find already existed physiotherapist with the same login in
     * database. If there is now such login, checks inputted data and adds this
     * physiotherapist to database. Otherwise, puts a warning message to
     * session's attribute.
     *
     * @param request is an instance of {@code HttpServletRequest}.
     */
    private void addPhysioToDatabase(HttpServletRequest request) {
        HttpSession session = request.getSession();
        PersonService personService = new PersonService();
        PhysioService physioService = new PhysioService();
        RegExpValidator validator = new RegExpValidator();
        HashCoder hashCoder = new HashCoder();
        String firstName = request.getParameter("physioFirstName");
        String lastName = request.getParameter("physioLastName");
        String login = request.getParameter("newPhysioLogin");
        String password = request.getParameter("newPhysioPassword");
        String repeatedPassword = request.getParameter("newPhysioPasswordCopy");
        request.setAttribute("physioFirstName", firstName);
        request.setAttribute("physioLastName", lastName);
        request.setAttribute("physioLogin", login);
        request.setAttribute("physioPassword", password);
        if (! physioService.isLoginNew(login)) {
            session.setAttribute("invalidLogin", "Warning! Such login was already existed!");
            nextPage = ConfigManager.getProperty("page.addphysio");
        } else if (! password.equals(repeatedPassword)) {
            session.setAttribute("differentPasswords", "Warning! You entered two different passwords!");
            nextPage = ConfigManager.getProperty("page.addphysio");
        } else if (validator.validate("name", firstName) && validator.validate("name", lastName)) {
            firstName = personService.transformName(firstName);
            lastName = personService.transformName(lastName);
            int speedInc = Integer.parseInt(request.getParameter("speedInc"));
            int strengthInc = Integer.parseInt(request.getParameter("strengthInc"));
            String loginHash = hashCoder.createHashedValue(login);
            String passwordHash = hashCoder.createHashedValue(password);
            physioService.addPerson(firstName, lastName, speedInc, strengthInc, loginHash, passwordHash);
        } else {
            session.setAttribute("invalidName", "Warning! Invalid first or last name!");
            nextPage = ConfigManager.getProperty("page.addphysio");
        }
    }
}
