package by.epam.hockeyschool.navigation.commands;

import by.epam.hockeyschool.config.ConfigManager;
import by.epam.hockeyschool.entity.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

/**
 * Created by demonwtf on 15.08.2015.
 */
public class LetterCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String radioButtonValue = request.getParameter("objective");
        if (radioButtonValue == null) { //protection from locale changing
            radioButtonValue = (String) session.getAttribute("radioButtonValue");
        }
        session.setAttribute("radioButtonValue", radioButtonValue);
        ResourceBundle bundle = ResourceBundle.getBundle("pagecontent_en_US");
        String objective = null;
        int money = 0;
        switch (radioButtonValue) {
            case "obj1":
                objective = bundle.getString("askforfunds.obj1");
                money = Integer.parseInt(bundle.getString("askforfunds.obj1.cost"));
                break;
            case "obj2":
                objective = bundle.getString("askforfunds.obj2");
                money = Integer.parseInt(bundle.getString("askforfunds.obj2.cost"));
                break;
            case "obj3":
                objective = bundle.getString("askforfunds.obj3");
                money = Integer.parseInt(bundle.getString("askforfunds.obj3.cost"));
                break;
            case "obj4":
                objective = bundle.getString("askforfunds.obj4");
                money = Integer.parseInt(bundle.getString("askforfunds.obj4.cost"));
                break;
            case "obj5":
                objective = bundle.getString("askforfunds.obj5");
                money = Integer.parseInt(bundle.getString("askforfunds.obj5.cost"));
                break;
        }
        session.setAttribute("signature", makeSignature(request));
        session.setAttribute("objective", objective);
        session.setAttribute("money", money);
        session.setAttribute("previousCommand", "letter");
        String nextPage = ConfigManager.getProperty("page.letter");
        return nextPage;
    }

    private String makeSignature(HttpServletRequest request) {
        Person person = (Person) request.getSession().getAttribute("person");
        String signature = person.getFirstName().substring(0, 1) + "." + person.getLastName().substring(0, 4) + "-";
        return signature;
    }
}
