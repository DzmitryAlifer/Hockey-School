package by.epam.hockeyschool.navigation;

import by.epam.hockeyschool.exception.TechnicalException;
import by.epam.hockeyschool.navigation.commands.Command;
import org.apache.log4j.Logger;

/**
 * Created by Dmitry Olifer on 19.06.2015.
 *
 * The {@code CommandFactory} class represents an ability of calling
 * definite consequence of instructions for definite page.
 */
public class CommandFactory {

    final static Logger LOG = Logger.getLogger(CommandFactory.class);

    /**
     * Assigns definite command (a member of {@code Commands} enumeration
     * class) for definite page.
     * @param   page is a string value that have to be converted into a
     *          call of definite command.
     * @return  definite command (a member of {@code Commands} enumeration
     *          class), that corresponds to this {@param page}.
     * @throws  by.epam.hockeyschool.exception.TechnicalException if there is no command for such
     *          {@param page} in the {@code Commands} enumeration class.
     */
    public Command getCommand(String page) throws TechnicalException {
        Commands currentCommand = null;
        try {
            currentCommand = Commands.valueOf(page.toUpperCase());
        } catch (EnumConstantNotPresentException e) {
            throw new TechnicalException("No such command error", e);
        }
        return currentCommand.getCommand();
    }
}
