package com.nbaproject.service.team;

import com.nbaproject.entities.Team;

public interface TeamService {
	
	public void saveTeam(Team team) ;
	
	public Team findTeamById(int teamid) ;

}
