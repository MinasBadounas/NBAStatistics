package com.nbaproject.service.opponentplayerstatspergame;

import java.util.ArrayList;

import com.nbaproject.entities.Opponentplayerstatspergame;

public interface OpponentplayerstatspergameService {

	public void saveOpponentPlayerStatsPerGame(int gameid,int playerid);
	
	public void saveNullOpponentPlayerStatsPerGame(int gameid,int playerid);
	
	public ArrayList<Opponentplayerstatspergame> findOpponentplayerstatspergameByPlayerId(int playerid);
}
