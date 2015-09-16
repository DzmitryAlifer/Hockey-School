package by.epam.hockeyschool.validator;

import by.epam.hockeyschool.exception.LogicalException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dmitry Olifer on 17.07.2015.
 *
 * The {@code NameValidator} class represents the way of validation
 * of inputted data, such as first name, last name, login and password,
 * using regular expressions. Note, that the same regular expression
 * will be used for the first name and for the last name validation.
 */
public class RegExpValidator {

    private static final String REGEXP_LOGIN = "^\\w[A-Za-z0-9_-]+";
    private static final String REGEXP_PASSWORD = "[A-Za-z0-9]+";
    private static final String REGEXP_NAME = "^[A-aZ-z\\u0410-\\u042f\\u0430-\\u044f]{3,20}$";
    private static final String REGEXP_CITY = "^[A-aZ-z\\u0410-\\u042f\\u0430-\\u044f-]{3,20}$";
    private static final String REGEXP_HEIGHT = "^[1][5-9][0-9]$|^[2][0-4][0-9]$";
    private static final String REGEXP_WEIGHT = "^[4-9][0-9]$|^[1][0-5][0-9]$";
    private static final String REGEXP_JERSEY = "^[1-9]$|^[1-9][0-9]$";
    private static final String REGEXP_INTERESTS = "[A-Za-z\\u0410-\\u042f\\u0430-\\u044f\\s,]+";

    public boolean validate(String field, String value) {
        int counter = 0;
        String regexpPattern = null;
        switch(field) {
            case "login":
                regexpPattern = REGEXP_LOGIN;
                break;
            case "password":
                regexpPattern = REGEXP_PASSWORD;
                break;
            case "name":
                regexpPattern = REGEXP_NAME;
                break;
            case "city":
                regexpPattern = REGEXP_CITY;
                break;
            case "height":
                regexpPattern = REGEXP_HEIGHT;
                break;
            case "weight":
                regexpPattern = REGEXP_WEIGHT;
                break;
            case "jersey":
                regexpPattern = REGEXP_JERSEY;
                break;
            case "interests":
                regexpPattern = REGEXP_INTERESTS;
                break;
            /*default:
                throw new LogicalException("Unable to validate such data");*/
        }
        Pattern pattern = Pattern.compile(regexpPattern);
        Matcher matcher = pattern.matcher(value);
        while (matcher.find()) {
            counter++;
        }
        return counter == 1;
    }

    /**
     * Defines the way of person's first name and last name validation.
     * @param   name is the string value of person's name, which have
     *          to be validated.
     * @return  if the value of {@param name} is valid, returns
     *          {@code true}, otherwise returns {@code false}.
     */
    /*public boolean validateName(String name) {
        int counter = 0;
        String namePattern = REGEXP_NAME;
        Pattern pattern = Pattern.compile(namePattern);
        Matcher matcher = pattern.matcher(name);
        while (matcher.find()) {
            counter++;
        }
        return counter == 1;
    }

    *//**
     * Defines the way of person's login validation.
     * @param   login is a string value of person's login, that
     *          have to be validated.
     * @return  if the value of {@param name} is valid, returns
     *          {@code true}, otherwise returns {@code false}.
     *//*
    public boolean validateLogin(String login) {
        int counter = 0;
        String loginPattern = REGEXP_LOGIN;
        Pattern pattern = Pattern.compile(loginPattern);
        Matcher matcher = pattern.matcher(login);
        while (matcher.find()) {
            counter++;
        }
        return counter == 1;
    }

    *//**
     * Defines the way of person's password validation.
     * @param   password is a string value of person's password, that
     *          have to be validated.
     * @return  if the value of {@param name} is valid, returns
     *          {@code true}, otherwise returns {@code false}.
     *//*
    public boolean validatePassword(String password) {
        int counter = 0;
        String passwordPattern = REGEXP_PASSWORD;
        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(password);
        while (matcher.find()) {
            counter++;
        }
        return counter == 1;
    }

    public boolean validateCity(String city) {
        int counter = 0;
        String passwordPattern = REGEXP_CITY;
        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(city);
        while (matcher.find()) {
            counter++;
        }
        return counter == 1;
    }
*/
}
