package com.nbaproject.service.playerseasonstats;

import java.util.ArrayList;

import com.nbaproject.entities.Playerseasonstat;

public interface PlayerseasonstatsService {

	public ArrayList<Playerseasonstat> findPlayerseasonstatsByPlayerId(int playerid);
	
	public Playerseasonstat findLastPlayerseasonstatsByPlayerId(int playerid);
	
	public void savePlayerseasonstats(Playerseasonstat playerseasonstat);
}
