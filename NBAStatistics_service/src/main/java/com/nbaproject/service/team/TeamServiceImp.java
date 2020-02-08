package com.nbaproject.service.team;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbaproject.entities.Team;
import com.nbaproject.repository.team.TeamRepository;

@Service
public class TeamServiceImp implements TeamService {

	
	@Autowired
	private TeamRepository teamRepository;
	
	
	@Override
	public void saveTeam(Team team) {
		
		teamRepository.save(team);
		
	}
	

	@Override
	public Team findTeamById(int teamid) {
		
		return teamRepository.findTeamById(teamid);
		
	}

}
