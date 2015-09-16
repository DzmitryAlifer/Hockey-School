package by.epam.hockeyschool.navigation.commands;

import by.epam.hockeyschool.config.ConfigManager;
import by.epam.hockeyschool.entity.MoneyTransfer;
import by.epam.hockeyschool.service.AdminService;
import by.epam.hockeyschool.service.MoneyTransferService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by demonwtf on 16.08.2015.
 */
public class AnswerCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        boolean isAnswerYes = new AdminService().defineAnswer();
        String previousCommand = (String) session.getAttribute("previousCommand");
        if (isAnswerYes && previousCommand.equals("letter")) {
            java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
            int sum = (int) session.getAttribute("money");
            MoneyTransfer transfer = new MoneyTransfer(sqlDate, sum, "money from bosses");
            new MoneyTransferService().addMoneyTransfer(transfer);
        }
        session.setAttribute("answer", isAnswerYes);
        session.setAttribute("previousCommand", "answer");
        String nextPage = ConfigManager.getProperty("page.answer");
        return nextPage;
    }
}
