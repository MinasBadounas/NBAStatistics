package com.nbaproject.utils.staticInitializer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nbaproject.entities.Team;
import com.nbaproject.service.team.TeamService;

@Component
public class StaticContextInitializer {

	@Autowired
	private TeamService teamService;
		
	private static StaticContextInitializer staticContextInitializer;
	
	@PostConstruct
    public void init()
    {
		staticContextInitializer = this;
		staticContextInitializer.teamService = this.teamService;
    }
	
	public TeamService getTeamService()
    {
        return teamService;
    }

    public void setTeamService(TeamService teamService)
    {
    	teamService = teamService;
    }
    
    public static Team findByIdNQ(int teamid)
    {

        return staticContextInitializer.teamService.findTeamById(teamid);
    }
}
