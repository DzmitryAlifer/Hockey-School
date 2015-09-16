package by.epam.hockeyschool.navigation.commands;

import by.epam.hockeyschool.config.ConfigManager;
import by.epam.hockeyschool.entity.Coach;
import by.epam.hockeyschool.entity.Player;
import by.epam.hockeyschool.service.PersonService;
import by.epam.hockeyschool.service.PlayerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.HashMap;

/**
 * Created by demonwtf on 06.08.2015.
 */
public class CoachDoTacticTrainCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String nextPage = ConfigManager.getProperty("page.coachplayers");
        if (session.getAttribute("previousCommand").equals("coach_tactic_train")) {
            PlayerService playerService = new PlayerService();
            String tacticTrainingSkill = request.getParameter("tactic");
            Coach currentCoach = (Coach) session.getAttribute("person");
            List<Player> schoolPlayers = playerService.getSchoolPlayers();
            List<Player> tacticTrainedPlayers = new PersonService().giveTacticTrainedPlayers(currentCoach, tacticTrainingSkill);
            playerService.updateSchoolPlayers(tacticTrainedPlayers);
            //HashMap<Integer, Integer> idAndIncrementMap = giveIdAndIncrementMap(schoolPlayers, tacticTrainingSkill);
            HashMap<Integer, Integer[]> idAndIncrementMap = playerService.giveIdAndIncrementMap(schoolPlayers);
            List<Player> updatedPlayers = playerService.getSchoolPlayers();
            session.setAttribute("skill", tacticTrainingSkill);
            session.setAttribute("idAndIncrementMap", idAndIncrementMap);
            session.setAttribute("schoolPlayers", updatedPlayers);
        }
        session.setAttribute("previousCommand", "coach_do_tactic_train");
        return nextPage;
    }

    private HashMap<Integer, Integer> giveIdAndIncrementMap(List<Player> schoolPlayers, String skill) {
        PlayerService playerService = new PlayerService();
        HashMap<Integer, Integer> schoolPlayerIncMap = new HashMap<>();
        for (Player player : schoolPlayers) {
            int id = player.getId();
            Player updatedPlayer = null;
            int increment = 0;
            int oldValue, newValue;
            switch (skill) {
                case "defence":
                    oldValue = player.getDefence();
                    updatedPlayer = playerService.definePlayer(id);
                    newValue = updatedPlayer.getDefence();
                    increment = newValue - oldValue;
                    break;
                case "attack":
                    oldValue = player.getAttack();
                    updatedPlayer = playerService.definePlayer(id);
                    newValue = updatedPlayer.getAttack();
                    increment = newValue - oldValue;
                    break;
            }
            schoolPlayerIncMap.put(id, increment);
        }
        return schoolPlayerIncMap;
    }



}
