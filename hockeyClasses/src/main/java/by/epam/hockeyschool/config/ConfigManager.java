package by.epam.hockeyschool.config;

import java.util.ResourceBundle;

/**
 * Created by Dmitry Olifer on 17.07.2015.
 *
 * The {@code Controller} class represents the way to use
 * notes from configuration property file.
 */
public class ConfigManager {

    private final static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("config");

    /**
     * Simple class constructor.
     */
    private ConfigManager() {
    }

    /**
     * Gives the way to get from configuration file the
     * property we need.
     * @param   key means the name of a property we need.
     * @return  the value of property, which represents
     *          a file path.
     */
    public static String getProperty(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }
}
