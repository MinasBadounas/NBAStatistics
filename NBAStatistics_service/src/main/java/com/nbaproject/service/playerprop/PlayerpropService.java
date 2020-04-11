package com.nbaproject.service.playerprop;

import java.util.ArrayList;

import com.nbaproject.entities.Playerprop;

public interface PlayerpropService {

	public ArrayList<Playerprop> findPlayerpropsByPlayerId(int playerid);

	public ArrayList<Playerprop> findPlayerpropsByGameId(int gameid);

	public ArrayList<Playerprop> findPlayerpropsByGameIdPlayerId(int gameid, int playerid);
	
	public void savePlayerprops(Playerprop playerprop);
	
	public  ArrayList<Playerprop> findPlayerpropsByPlayerIdAndDescription(int playerid, int description);
}
