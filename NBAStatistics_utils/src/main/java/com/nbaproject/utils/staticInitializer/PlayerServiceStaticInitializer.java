package com.nbaproject.utils.staticInitializer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.nbaproject.entities.Player;
import com.nbaproject.service.player.PlayerService;

@Component
@Order(3)
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

		if (playerStaticContextInitializer.playerService.findPlayerById(playerid) == null) {
			playerId = 0;
		} else {
			playerId = playerStaticContextInitializer.playerService.findPlayerById(playerid);
		}

		return playerId;
	}
	
	public static void addPlayer(Player player) {

		playerStaticContextInitializer.playerService.savePlayer(player);

	}

}
