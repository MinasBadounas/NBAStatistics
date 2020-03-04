package com.nbaproject.service.opponentplayerstatspergame;

public interface OpponentplayerstatspergameService {

	public void saveOpponentPlayerStatsPerGame(int gameid,int playerid);
	
	public void saveNullOpponentPlayerStatsPerGame(int gameid,int playerid);
}
