package by.epam.hockeyschool.navigation.commands;

import by.epam.hockeyschool.config.ConfigManager;
import by.epam.hockeyschool.entity.MoneyTransfer;
import by.epam.hockeyschool.entity.Player;
import by.epam.hockeyschool.service.MoneyTransferService;
import by.epam.hockeyschool.service.PlayerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by demonwtf on 13.08.2015.
 */
public class BudgetCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        MoneyTransferService moneyTransferService = new MoneyTransferService();
        List<MoneyTransfer> transfers = moneyTransferService.receiveAllTransfers();
        int budgetAmount = moneyTransferService.budgetAmount(transfers);
        session.setAttribute("transfers", transfers);
        session.setAttribute("budgetAmount", budgetAmount);
        session.setAttribute("previousCommand", "budget");
        String nextPage = ConfigManager.getProperty("page.budget");
        return nextPage;
    }
}
