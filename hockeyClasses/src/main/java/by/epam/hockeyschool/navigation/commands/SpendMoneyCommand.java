package by.epam.hockeyschool.navigation.commands;

import by.epam.hockeyschool.config.ConfigManager;
import by.epam.hockeyschool.entity.MoneyTransfer;
import by.epam.hockeyschool.service.MoneyTransferService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by demonwtf on 16.08.2015.
 */
public class SpendMoneyCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        MoneyTransferService moneyTransferService = new MoneyTransferService();
        List<MoneyTransfer> transfers = moneyTransferService.receiveAllTransfers();
        int budgetAmount = moneyTransferService.budgetAmount(transfers);
        session.setAttribute("warning", null);
        session.setAttribute("budget", budgetAmount);
        session.setAttribute("previousCommand", "spend_money");
        String nextPage = ConfigManager.getProperty("page.spendmoney");
        return nextPage;
    }
}
