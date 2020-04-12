package com.nbaproject.utils.update;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nbaproject.entities.Boxscore;
import com.nbaproject.service.boxscore.BoxscoreService;
import com.nbaproject.utils.staticInitializer.BoxscoreServiceStaticInitializer;
import com.nbaproject.utils.staticInitializer.TeamstatsbypositionpergameStaticInitializer;

@Component
public class Teamstatsbypositionpergame {

	public static void calculateTeamstatsbypositionpergame(int gameid) {

		Boxscore boxscore = BoxscoreServiceStaticInitializer.findBoxscoreByGameId(gameid);

		TeamstatsbypositionpergameStaticInitializer.saveTeamstatsbypositionpergame(1, gameid,
				boxscore.getTeam1().getTeamid(), 1, 3);
		TeamstatsbypositionpergameStaticInitializer.saveTeamstatsbypositionpergame(2, gameid,
				boxscore.getTeam1().getTeamid(), 4, 5);
		TeamstatsbypositionpergameStaticInitializer.saveTeamstatsbypositionpergame(3, gameid,
				boxscore.getTeam1().getTeamid(), 6, 7);
		TeamstatsbypositionpergameStaticInitializer.saveTeamstatsbypositionpergame(4, gameid,
				boxscore.getTeam1().getTeamid(), 8, 10);
		TeamstatsbypositionpergameStaticInitializer.saveTeamstatsbypositionpergame(1, gameid,
				boxscore.getTeam2().getTeamid(), 1, 3);
		TeamstatsbypositionpergameStaticInitializer.saveTeamstatsbypositionpergame(2, gameid,
				boxscore.getTeam2().getTeamid(), 4, 5);
		TeamstatsbypositionpergameStaticInitializer.saveTeamstatsbypositionpergame(3, gameid,
				boxscore.getTeam2().getTeamid(), 6, 7);
		TeamstatsbypositionpergameStaticInitializer.saveTeamstatsbypositionpergame(4, gameid,
				boxscore.getTeam2().getTeamid(), 8, 10);

	}

}
