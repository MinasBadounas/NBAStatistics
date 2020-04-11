package com.nbaproject.service.player;

import java.util.ArrayList;
import java.util.List;

import com.nbaproject.entities.Player;

public interface PlayerService {

	public void savePlayer(Player player) ;
	
	public Integer findPlayerById(int playerid) ;
	
	public Player findPlayerByPlayerId(int playerid) ;
	
	public ArrayList<Player> findPlayersByName(String playername);
	
	public ArrayList<Player> findAll();
}
