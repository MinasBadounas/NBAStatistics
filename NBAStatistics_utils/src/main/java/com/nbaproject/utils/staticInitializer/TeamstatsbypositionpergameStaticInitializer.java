package com.nbaproject.utils.staticInitializer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nbaproject.service.teamstatsbypositionpergame.TeamstatsbypositionpergameService;

@Component
public class TeamstatsbypositionpergameStaticInitializer {
	
	@Autowired
	private TeamstatsbypositionpergameService teamstatsbypositionpergameService;
		
	private static TeamstatsbypositionpergameStaticInitializer staticContextInitializer;
	
	@PostConstruct
    public void init()
    {
		staticContextInitializer = this;
		staticContextInitializer.teamstatsbypositionpergameService = this.teamstatsbypositionpergameService;
    }
	
	public TeamstatsbypositionpergameService getTeamstatsbypositionpergameService()
    {
        return teamstatsbypositionpergameService;
    }

    public void setTeamstatsbypositionpergameService(TeamstatsbypositionpergameService teamstatsbypositionpergameService)
    {
    	teamstatsbypositionpergameService = teamstatsbypositionpergameService;
    }
    
    public static void saveTeamstatsbypositionpergame(int categoryposition, int gameid,int teamid, int minposition, int maxposition)
    {

        staticContextInitializer.teamstatsbypositionpergameService.saveTeamstatsbypositionpergame(categoryposition, gameid, teamid, minposition, maxposition);
    }
}
