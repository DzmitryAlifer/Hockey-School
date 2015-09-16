package by.epam.hockeyschool.navigation.commands;

import by.epam.hockeyschool.config.ConfigManager;
import by.epam.hockeyschool.entity.Player;
import by.epam.hockeyschool.service.PlayerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Dmitry Olifer on 02.07.2015.
 *
 * The {@code DeletePlayerCommand} class represents an ability of manipulation
 * with an instance of {@code HttpServletRequest}.
 */
public class DeletePlayerCommand implements Command {

    /**
     * Represents the list of players where you can choose one or more of them
     * for deleting. Before it checks previous page. If the page is not the
     * same as current, it will not show a warning message. Also makes able to
     * be redirected to this page after changing the value of language console
     * by putting the name of this command to session's attribute.
     * @param   request is an instance of {@code HttpServletRequest}.
     * @return  a result page at which should be forwarded to.
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (! request.getHeader("Referer").matches("(.*)command=delete_player(.*)")) {
            session.setAttribute("nobodyToDelete", null);
        }
        List<Player> schoolPlayers = new PlayerService().getSchoolPlayers();
        session.setAttribute("schoolPlayers", schoolPlayers);
        session.setAttribute("previousCommand", "delete_player");
        String nextPage = ConfigManager.getProperty("page.deleteplayer");
        return nextPage;
    }
}
