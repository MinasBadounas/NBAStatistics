package com.nbaproject.service.boxscore;

import java.util.ArrayList;

import com.nbaproject.entities.Boxscore;

public interface BoxscoreService {
	
	public void saveBoxscore(Boxscore boxscore) ;
	
	public int findMaxGameId() ;
	
	public ArrayList<Integer> findAllGameIdBoxscore() ;

}
