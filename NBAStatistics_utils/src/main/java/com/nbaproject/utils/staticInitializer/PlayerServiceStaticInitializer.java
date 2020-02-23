package com.nbaproject.utils.staticInitializer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nbaproject.entities.Player;
import com.nbaproject.service.player.PlayerService;

@Component
public class PlayerServiceStaticInitializer {

	@Autowired
	private PlayerService playerService;

	private static PlayerServiceStaticInitializer playerStaticContextInitializer;

	@PostConstruct
	public void init() {
		playerStaticContextInitializer = this;
		playerStaticContextInitializer.playerService = this.playerService;
	}

	public PlayerService getPlayerService() {
		return playerService;
	}

	public void setPlayerService(PlayerService playerService) {
		playerService = playerService;
	}

	public static Integer findPlayerById(int playerid) {

		int playerId;

		if (playerStaticContextInitializer.playerService.findPlayerByPlayerId(playerid) == null) {
			playerId = 0;
		} else {
			playerId = playerStaticContextInitializer.playerService.findPlayerByPlayerId(playerid);
		}

		return playerId;
	}
	
	public static void addPlayer(Player player) {

		playerStaticContextInitializer.playerService.savePlayer(player);

	}

}
