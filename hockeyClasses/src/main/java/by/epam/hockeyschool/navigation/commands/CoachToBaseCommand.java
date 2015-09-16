package by.epam.hockeyschool.navigation.commands;

import by.epam.hockeyschool.config.ConfigManager;
import by.epam.hockeyschool.entity.Coach;
import by.epam.hockeyschool.service.CoachService;
import by.epam.hockeyschool.service.PersonService;
import by.epam.hockeyschool.validator.HashCoder;
import by.epam.hockeyschool.validator.RegExpValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Dmitry Olifer on 02.07.2015.
 * <p>
 * The {@code CoachToBaseCommand} class represents an ability of manipulation
 * with an instance of {@code HttpServletRequest}.
 */
public class CoachToBaseCommand implements Command {

    String nextPage = ConfigManager.getProperty("page.coachlist");

    /**
     * Adds already checked coach to database. Puts the list of coaches into
     * session's attribute. Gives a string value of the page on which you
     * can see this list.
     *
     * @param request is an instance of {@code HttpServletRequest}.
     * @return a result page at which should be forwarded to.
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String differentPasswords = (String) session.getAttribute("differentPasswords");
        String previousCommand = (String) session.getAttribute("previousCommand");
        if (previousCommand.equals("add_coach") || (previousCommand.equals("coach_to_base") && differentPasswords != null)) {
            addCoachToDatabase(request);
        }
        List<Coach> coaches = new CoachService().getPersons();
        session.setAttribute("coaches", coaches);
        session.setAttribute("previousCommand", "coach_to_base");
        return nextPage;
    }

    /**
     * Tries to find already existed coach with the same login in database.
     * If there is now such login, checks inputted data and adds this coach
     * to database. Otherwise, puts a warning message to session's attribute.
     *
     * @param request is an instance of {@code HttpServletRequest}.
     */
    private void addCoachToDatabase(HttpServletRequest request) {
        HttpSession session = request.getSession();
        PersonService personService = new PersonService();
        CoachService coachService = new CoachService();
        RegExpValidator validator = new RegExpValidator();
        HashCoder hashCoder = new HashCoder();
        String firstName = request.getParameter("coachFirstName");
        String lastName = request.getParameter("coachLastName");
        String login = request.getParameter("newCoachLogin");
        String password = request.getParameter("newCoachPassword");
        String repeatedPassword = request.getParameter("newCoachPasswordCopy");
        request.setAttribute("coachFirstName", firstName);
        request.setAttribute("coachLastName", lastName);
        request.setAttribute("coachLogin", login);
        request.setAttribute("coachPassword", password);
        if (! coachService.isLoginNew(login)) {
            session.setAttribute("invalidLogin", "Warning! Such login was already existed!");
            nextPage = ConfigManager.getProperty("page.addcoach");
        } else if (! password.equals(repeatedPassword)) {
            session.setAttribute("differentPasswords", "Warning! You entered two different passwords!");
            nextPage = ConfigManager.getProperty("page.addcoach");
        } else if (validator.validate("name", firstName) && validator.validate("name", lastName)) {
            firstName = personService.transformName(firstName);
            lastName = personService.transformName(lastName);
            int defenceInc = Integer.parseInt(request.getParameter("defenceInc"));
            int attackInc = Integer.parseInt(request.getParameter("attackInc"));
            String loginHash = hashCoder.createHashedValue(login);
            String passwordHash = hashCoder.createHashedValue(password);
            coachService.addPerson(firstName, lastName, defenceInc, attackInc, loginHash, passwordHash);
        } else {
            session.setAttribute("invalidName", "Warning! Invalid first or last name!");
            nextPage = ConfigManager.getProperty("page.addcoach");
        }
    }
}
