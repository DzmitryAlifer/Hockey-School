package by.epam.hockeyschool.navigation.commands;

import by.epam.hockeyschool.config.ConfigManager;
import by.epam.hockeyschool.entity.Player;
import by.epam.hockeyschool.service.PersonService;
import by.epam.hockeyschool.service.PlayerService;
import by.epam.hockeyschool.validator.RegExpValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Dmitry Olifer on 02.07.2015.
 * <p>
 * The {@code PlayerToBaseCommand} class represents an ability of manipulation
 * with an instance of {@code HttpServletRequest}.
 */
public class PlayerToBaseCommand implements Command {

    PersonService personService = new PersonService();
    RegExpValidator validator = new RegExpValidator();
    String nextPage = ConfigManager.getProperty("page.playerlist");

    /**
     * After adding a player to database, puts the lists of school and team
     * players into session's attributes. Gives a string value of the page on
     * which you can see these lists.
     *
     * @param request is an instance of {@code HttpServletRequest}.
     * @return a result page at which should be forwarded to.
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("previousCommand").equals("add_player")) {
            addPlayerToDatabase(request);
        }
        List<Player> schoolPlayers = new PlayerService().getSchoolPlayers();
        List<Player> teamPlayers = new PlayerService().getTeamPlayers();
        session.setAttribute("schoolPlayers", schoolPlayers);
        session.setAttribute("teamPlayers", teamPlayers);
        session.setAttribute("previousCommand", "player_to_base");
        return nextPage;
    }

    /**
     * Checks the validness of player names and, put him with his skills to
     * database. If the name was invalid, shows warning message.
     *
     * @param request is an instance of {@code HttpServletRequest}.
     */
    private void addPlayerToDatabase(HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        if (validator.validate("name", firstName) && validator.validate("name", lastName)) {
            firstName = personService.transformName(firstName);
            lastName = personService.transformName(lastName);
            int defence = Integer.parseInt(request.getParameter("defence"));
            int attack = Integer.parseInt(request.getParameter("attack"));
            int speed = Integer.parseInt(request.getParameter("speed"));
            int strength = Integer.parseInt(request.getParameter("strength"));
            new PlayerService().addPerson(firstName, lastName, defence, attack, speed, strength);
        } else {
            request.getSession().setAttribute("invalidName", "Warning! Invalid first or last name!");
            nextPage = ConfigManager.getProperty("page.addplayer");
        }
    }
}
