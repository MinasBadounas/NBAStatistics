package com.nbaproject.service.teamstatsbypositionpergame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbaproject.repository.teamstatsbypositionpergame.TeamstatsbypositionpergameRepository;

@Service
public class TeamstatsbypositionpergameServiceImp implements TeamstatsbypositionpergameService {

	@Autowired 
	TeamstatsbypositionpergameRepository teamstatsbypositionpergameRepository;
	
	@Override
	public void saveTeamstatsbypositionpergame(int categoryposition, int gameid,int teamid , int minposition, int maxposition) {

		teamstatsbypositionpergameRepository.InsertTeamstatsbypositionpergame(categoryposition, gameid ,teamid , minposition, maxposition);;
		
	}

}
