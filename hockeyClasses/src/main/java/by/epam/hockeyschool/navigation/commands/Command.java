package by.epam.hockeyschool.navigation.commands;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Dmitry Olifer on 20.07.2015.
 *
 * The {@code Command} interface provides the only method to
 * perform any manipulations with an instance of
 * {@code HttpServletRequest}.
 */
public interface Command {

    /**
     * Performs all manipulations with request object in definite
     * command class and returns a result page.
     * @param   request is an instance of {@code HttpServletRequest}.
     * @return  a result page at which should be forwarded to.
     */
    String execute(HttpServletRequest request);
}
