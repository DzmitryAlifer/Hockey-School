package by.epam.hockeyschool.navigation.commands;

import by.epam.hockeyschool.config.ConfigManager;
import by.epam.hockeyschool.entity.*;
import by.epam.hockeyschool.service.AdminService;
import by.epam.hockeyschool.service.CoachService;
import by.epam.hockeyschool.service.PhysioService;
import by.epam.hockeyschool.service.PlayerService;
import by.epam.hockeyschool.validator.HashCoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Dmitry Olifer on 02.07.2015.
 * <p>
 * The {@code LoginCommand} class represents an ability of manipulation
 * with an instance of {@code HttpServletRequest}.
 */
public class LoginCommand implements Command {

    HttpSession session;

    /**
     * Compares inputted login and password with database. If there is
     * no such pair of login-password in database, shows warning message.
     * Otherwise, checks the role: is it an administrator or is it a
     * coach. As long as it is an administrator, next shown page will be
     * summary. And if it is a coach, next page will be player list.
     *
     * @param request is an instance of {@code HttpServletRequest}.
     * @return a result page at which should be forwarded to.
     */
    @Override
    public String execute(HttpServletRequest request) {
        AdminService adminService = new AdminService();
        CoachService coachService = new CoachService();
        PhysioService physioService = new PhysioService();
        HashCoder hashCoder = new HashCoder();
        session = request.getSession();
        session.setAttribute("previousCommand", "login");
        String nextPage = null;
        if (session.getAttribute("person") == null &&
                request.getParameter("login") != null &&
                request.getParameter("password") != null) {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            session.setAttribute("login", login);
            session.setAttribute("password", password);
            String loginHash = hashCoder.createHashedValue(login);
            String passwordHash = hashCoder.createHashedValue(password);
            request.setAttribute("enteredLogin", login);
            request.setAttribute("enteredPassword", password);
            Admin admin = (Admin) adminService.defineAdmin(loginHash, passwordHash);
            Coach coach = (Coach) coachService.defineCoach(loginHash, passwordHash);
            Physio physio = (Physio) physioService.definePhysio(loginHash, passwordHash);
            Person person = admin != null ? admin : coach != null ? coach : physio != null ? physio : null;
            session.setAttribute("person", person);
            session.setAttribute("loginWarning", null);
            String personClassName = "";
            if (person != null) {
                personClassName = person.getClass().getSimpleName().toLowerCase();
            }
            switch (personClassName) {
                case "admin":
                    defineSummary(request);
                    int adminId = adminService.defineAdminId(loginHash, passwordHash);
                    boolean isOwner = adminService.isOwner(adminId);
                    if (!isOwner) {
                        session.setAttribute("role", "administrator");
                    } else {
                        session.setAttribute("role", "owner");
                    }
                    nextPage = ConfigManager.getProperty("page.summary");
                    break;
                case "coach":
                    definePlayerList(request);
                    session.setAttribute("role", "coach");
                    nextPage = ConfigManager.getProperty("page.coachplayers");
                    break;
                case "physio":
                    definePlayerList(request);
                    session.setAttribute("role", "physiotherapist");
                    nextPage = ConfigManager.getProperty("page.physioplayers");
                    break;
                default:
                    session.setAttribute("loginWarning", "Wrong login or password!");
                    nextPage = ConfigManager.getProperty("page.login");
                    break;
            }
        } else if (session.getAttribute("person") != null &&
                session.getAttribute("login") != null &&
                session.getAttribute("password") != null) {
            String role = (String) session.getAttribute("role");
            switch (role) {
                case "owner":
                    nextPage = ConfigManager.getProperty("page.summary");
                    break;
                case "administrator":
                    nextPage = ConfigManager.getProperty("page.summary");
                    break;
                case "coach":
                    nextPage = ConfigManager.getProperty("page.coachplayers");
                    break;
                case "physiotherapist":
                    nextPage = ConfigManager.getProperty("page.coachplayers");
                    break;
            }
        }
        return nextPage;
    }

    /**
     * As long as current user is a coach, defines and puts into session's
     * attribute player list, that will be shown on the next page.
     *
     * @param request is an instance of {@code HttpServletRequest}.
     */
    private void definePlayerList(HttpServletRequest request) {
        PlayerService playerService = new PlayerService();
        List<Player> schoolPlayers = playerService.getSchoolPlayers();
        List<Player> teamPlayers = playerService.getTeamPlayers();
        session.setAttribute("schoolPlayers", schoolPlayers);
        session.setAttribute("teamPlayers", teamPlayers);
    }

    /**
     * As long as current user is an administrator, defines and puts into session's
     * attribute summary information, that will be shown on the next page.
     *
     * @param request is an instance of {@code HttpServletRequest}.
     */
    private void defineSummary(HttpServletRequest request) {
        AdminService adminService = new AdminService();
        PlayerService playerService = new PlayerService();
        CoachService coachService = new CoachService();
        PhysioService physioService = new PhysioService();
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
