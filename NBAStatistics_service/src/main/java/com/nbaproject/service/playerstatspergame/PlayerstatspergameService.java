package com.nbaproject.service.playerstatspergame;

import java.util.ArrayList;

import com.nbaproject.entities.Playerstatspergame;

public interface PlayerstatspergameService {

	public void savePlayerstatspergame(Playerstatspergame playerstatspergame);
	
	public ArrayList<Integer> findAllGameIdInPlayerStatsPerGame();
	
	public int maxGameIdInPlayerStatsPerGame();

}
