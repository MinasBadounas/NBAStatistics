package com.nbaproject.service.player;

import java.util.ArrayList;

import com.nbaproject.entities.Player;

public interface PlayerService {

	public void savePlayer(Player player) ;
	
	public Integer findPlayerByPlayerId(int playerid) ;
}
