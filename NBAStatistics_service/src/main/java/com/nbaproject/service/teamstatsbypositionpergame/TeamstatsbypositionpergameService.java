package com.nbaproject.service.teamstatsbypositionpergame;

import java.util.ArrayList;

import com.nbaproject.entities.Teamstatsbypositionpergame;

public interface TeamstatsbypositionpergameService {
	
	public void saveTeamstatsbypositionpergame(int categoryposition, int gameid,int teamid , int minposition, int maxposition);

	public ArrayList<Teamstatsbypositionpergame> findTeamStatsByPositionPerGameByGameid(int gameid);

}
