package by.epam.hockeyschool.navigation.commands;

import by.epam.hockeyschool.config.ConfigManager;
import by.epam.hockeyschool.entity.Coach;
import by.epam.hockeyschool.entity.MoneyTransfer;
import by.epam.hockeyschool.entity.Physio;
import by.epam.hockeyschool.entity.Player;
import by.epam.hockeyschool.service.CoachService;
import by.epam.hockeyschool.service.MoneyTransferService;
import by.epam.hockeyschool.service.PhysioService;
import by.epam.hockeyschool.service.PlayerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by demonwtf on 16.08.2015.
 */
public class SpendMoneyResultCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        MoneyTransferService moneyTransferService = new MoneyTransferService();
        String nextPage = null;
        String objectiveStr = request.getParameter("objective");
        if (objectiveStr == null) { //protection from locale changing
            objectiveStr = (String) session.getAttribute("objectiveStr");
        }
        session.setAttribute("objectiveStr", objectiveStr);
        String previousCommand = (String) session.getAttribute("previousCommand");
        int objectiveNumber = Integer.parseInt(objectiveStr.substring(3));
        int[] objectiveIncrement = {2, 2, 7};
        switch (objectiveNumber) {
            case 1:
                if (previousCommand.equals("spend_money") && performPlayerObjective(request, objectiveNumber, new double[]{1, 1, 1, 1}, objectiveIncrement[objectiveNumber - 1])) {
                    nextPage = ConfigManager.getProperty("page.playerlist");
                    session.setAttribute("previousCommand", "spend_money_result");
                } else {
                    nextPage = ConfigManager.getProperty("page.spendmoney");
                    session.setAttribute("previousCommand", "spend_money");
                }
                break;
            case 2:
                if (previousCommand.equals("spend_money") && performPlayerObjective(request, objectiveNumber, new double[]{1, 1, 0, 0}, objectiveIncrement[objectiveNumber - 1])) {
                    nextPage = ConfigManager.getProperty("page.playerlist");
                    session.setAttribute("previousCommand", "spend_money_result");
                } else {
                    nextPage = ConfigManager.getProperty("page.spendmoney");
                    session.setAttribute("previousCommand", "spend_money");
                }
                break;
            case 3:
                if (previousCommand.equals("spend_money") && performPlayerObjective(request, objectiveNumber, new double[]{0, 0, 0, 1}, objectiveIncrement[objectiveNumber - 1])) {
                    nextPage = ConfigManager.getProperty("page.playerlist");
                    session.setAttribute("previousCommand", "spend_money_result");
                } else {
                    nextPage = ConfigManager.getProperty("page.spendmoney");
                    session.setAttribute("previousCommand", "spend_money");
                }
                break;
            case 4:
                if (previousCommand.equals("spend_money") && performCoachObjective(request)) {
                    nextPage = ConfigManager.getProperty("page.coachlist");
                    session.setAttribute("previousCommand", "spend_money_result");
                } else {
                    nextPage = ConfigManager.getProperty("page.spendmoney");
                    session.setAttribute("previousCommand", "spend_money");
                }
                break;
            case 5:
                if (previousCommand.equals("spend_money") && performPhysioObjective(request)) {
                    nextPage = ConfigManager.getProperty("page.physiolist");
                    session.setAttribute("previousCommand", "spend_money_result");
                } else {
                    nextPage = ConfigManager.getProperty("page.spendmoney");
                    session.setAttribute("previousCommand", "spend_money");
                }
                break;
        }
        int budgetAmount = moneyTransferService.budgetAmount(moneyTransferService.receiveAllTransfers());
        session.setAttribute("budget", budgetAmount);
        return nextPage;
    }

    private boolean performPlayerObjective(HttpServletRequest request, int objectiveNumber, double[] probabilities, int increment) {
        boolean isObjectiveCompleted = false;
        HttpSession session = request.getSession();
        PlayerService playerService = new PlayerService();
        ResourceBundle bundle = ResourceBundle.getBundle("pagecontent");
        String objectiveName = bundle.getString("askforfunds.obj" + objectiveNumber);
        String costObjStr = bundle.getString("askforfunds.obj" + objectiveNumber + ".cost");
        int costObj = Integer.parseInt(costObjStr);
        MoneyTransferService moneyTransferService = new MoneyTransferService();
        List<MoneyTransfer> transfers = moneyTransferService.receiveAllTransfers();
        int budgetAmount = moneyTransferService.budgetAmount(transfers);
        if (budgetAmount >= costObj) {
            isObjectiveCompleted = true;
            MoneyTransfer transferObj = new MoneyTransfer();
            transferObj.setOperation(objectiveName);
            transferObj.setSum(-costObj);
            List<Player> schoolPlayers = playerService.getSchoolPlayers();
            new MoneyTransferService().addMoneyTransfer(transferObj);
            playerService.playersImproveDefiniteSkill("defence", increment, probabilities[0]);
            playerService.playersImproveDefiniteSkill("attack", increment, probabilities[1]);
            playerService.playersImproveDefiniteSkill("speed", increment, probabilities[2]);
            playerService.playersImproveDefiniteSkill("strength", increment, probabilities[3]);
            HashMap<Integer, Integer[]> idAndIncrementMap = playerService.giveIdAndIncrementMap(schoolPlayers);
            List<Player> objSchoolPlayers = playerService.getSchoolPlayers();
            List<Player> objTeamPlayers = playerService.getTeamPlayers();
            session.setAttribute("idAndIncrementMap", idAndIncrementMap);
            session.setAttribute("schoolPlayers", objSchoolPlayers);
            session.setAttribute("teamPlayers", objTeamPlayers);
        } else {
            session.setAttribute("warning", bundle.getString("spendmoney.insufficientfunds"));
        }
        return isObjectiveCompleted;
    }

    private boolean performCoachObjective(HttpServletRequest request) {
        boolean isObjectiveReached = false;
        HttpSession session = request.getSession();
        String previousCommand = (String) session.getAttribute("previousCommand");
        CoachService coachService = new CoachService();
        ResourceBundle bundle = ResourceBundle.getBundle("pagecontent");
        String objectiveName = bundle.getString("askforfunds.obj4");
        String costObjStr = bundle.getString("askforfunds.obj4.cost");
        int costObj = Integer.parseInt(costObjStr);
        MoneyTransferService moneyTransferService = new MoneyTransferService();
        List<MoneyTransfer> transfers = moneyTransferService.receiveAllTransfers();
        int budgetAmount = moneyTransferService.budgetAmount(transfers);
        if (budgetAmount >= costObj && previousCommand.equals("spend_money")) {
            isObjectiveReached = true;
            MoneyTransfer transferObj = new MoneyTransfer();
            transferObj.setOperation(objectiveName);
            transferObj.setSum(-costObj);
            new MoneyTransferService().addMoneyTransfer(transferObj);
            HashMap<Integer, Integer[]> coachGrows = coachService.coachesRandomImproveSkills();
            List<Coach> coaches = coachService.getPersons();
            session.setAttribute("coaches", coaches);
            session.setAttribute("coachGrows", coachGrows);
        } else {
            session.setAttribute("warning", bundle.getString("spendmoney.insufficientfunds"));
            session.setAttribute("previousCommand", "spend_money");
            budgetAmount = moneyTransferService.budgetAmount(transfers);
            session.setAttribute("budget", budgetAmount);
        }
        return isObjectiveReached;
    }

    private boolean performPhysioObjective(HttpServletRequest request) {
        boolean isObjectiveReached = false;
        HttpSession session = request.getSession();
        PhysioService physioService = new PhysioService();
        ResourceBundle bundle = ResourceBundle.getBundle("pagecontent");
        String objectiveName = bundle.getString("askforfunds.obj5");
        String costObjStr = bundle.getString("askforfunds.obj5.cost");
        int costObj = Integer.parseInt(costObjStr);
        MoneyTransferService moneyTransferService = new MoneyTransferService();
        List<MoneyTransfer> transfers = moneyTransferService.receiveAllTransfers();
        int budgetAmount = moneyTransferService.budgetAmount(transfers);
        if (budgetAmount >= costObj) {
            isObjectiveReached = true;
            MoneyTransfer transferObj = new MoneyTransfer();
            transferObj.setOperation(objectiveName);
            transferObj.setSum(-costObj);
            new MoneyTransferService().addMoneyTransfer(transferObj);
            HashMap<Integer, Integer[]> physioGrows = physioService.physiosRandomImproveSkills();
            List<Physio> physios = physioService.getPersons();
            session.setAttribute("physios", physios);
            session.setAttribute("physioGrows", physioGrows);
        } else {
            session.setAttribute("warning", bundle.getString("spendmoney.insufficientfunds"));
            session.setAttribute("previousCommand", "spend_money");
            budgetAmount = moneyTransferService.budgetAmount(transfers);
            session.setAttribute("budget", budgetAmount);
        }
        return isObjectiveReached;
    }
}
