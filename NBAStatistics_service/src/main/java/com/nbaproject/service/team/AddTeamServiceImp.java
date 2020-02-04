package com.nbaproject.service.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbaproject.entities.Team;
import com.nbaproject.repository.team.TeamRepository;

@Service
public class AddTeamServiceImp implements AddTeamService {

	
	@Autowired
	private TeamRepository teamRepository;
	
	
	@Override
	public void saveTeam(Team team) {
		
		teamRepository.save(team);
		
	}

}
