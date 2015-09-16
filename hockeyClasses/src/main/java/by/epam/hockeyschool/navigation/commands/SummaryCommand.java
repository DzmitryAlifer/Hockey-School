package by.epam.hockeyschool.navigation.commands;

import by.epam.hockeyschool.config.ConfigManager;
import by.epam.hockeyschool.entity.Person;
import by.epam.hockeyschool.service.AdminService;
import by.epam.hockeyschool.service.CoachService;
import by.epam.hockeyschool.service.PhysioService;
import by.epam.hockeyschool.service.PlayerService;
import by.epam.hockeyschool.validator.HashCoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Dmitry Olifer on 02.07.2015.
 *
 * The {@code SummaryCommand} class represents an ability of manipulation
 * with an instance of {@code HttpServletRequest}.
 */
public class SummaryCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        AdminService adminService = new AdminService();
        HashCoder hashCoder = new HashCoder();
        String nextPage = ConfigManager.getProperty("page.summary");
        defineSummary(request);
        if (session.getAttribute("admin") == null && request.getParameter("login") != null && request.getParameter("password") != null) {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            String loginHash = hashCoder.createHashedValue(login);
            String passwordHash = hashCoder.createHashedValue(password);
            Person admin = adminService.defineAdmin(loginHash, passwordHash);
            session.setAttribute("admin", admin);
            session.setAttribute("previousCommand", "summary");
            if (admin != null) {
                session.setAttribute("loginWarning", null);
            } else {
                session.setAttribute("loginWarning", "Wrong login or password!");
                nextPage = ConfigManager.getProperty("page.login");
            }
        }
        return nextPage;
    }

    private void defineSummary(HttpServletRequest request) {
        AdminService adminService = new AdminService();
        PlayerService playerService = new PlayerService();
        CoachService coachService = new CoachService();
        PhysioService physioService = new PhysioService();
        HttpSession session = request.getSession();
        int schoolPlayerCount = playerService.getSchoolPlayers().size();
        int teamPlayerCount = playerService.getTeamPlayers().size();
        int averSchoolRate = playerService.averPlayerRate(playerService.getSchoolPlayers());
        int averTeamRate = playerService.averPlayerRate(playerService.getTeamPlayers());
        int coachCount = coachService.getPersons().size();
        int averCoachRate = coachService.averCoachRate(coachService.getPersons());
        int physioCount = physioService.getPersons().size();
        int averPhysioRate = physioService.averPhysioRate(physioService.getPersons());
        int adminCount = adminService.getPersons().size();
        int totalIncome = new PlayerService().totalSoldPlayersIncome();
        session.setAttribute("schoolPlayerCount", schoolPlayerCount);
        session.setAttribute("teamPlayerCount", teamPlayerCount);
        session.setAttribute("averSchoolRate", averSchoolRate);
        session.setAttribute("averTeamRate", averTeamRate);
        session.setAttribute("coachCount", coachCount);
        session.setAttribute("averCoachRate", averCoachRate);
        session.setAttribute("physioCount", physioCount);
        session.setAttribute("averPhysioRate", averPhysioRate);
        session.setAttribute("adminCount", adminCount);
        session.setAttribute("totalIncome", totalIncome);
    }
}
