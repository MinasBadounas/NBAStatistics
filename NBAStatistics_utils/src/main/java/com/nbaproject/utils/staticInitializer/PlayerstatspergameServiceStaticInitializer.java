package com.nbaproject.utils.staticInitializer;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nbaproject.entities.Playerstatspergame;
import com.nbaproject.service.playerstatspergame.PlayerstatspergameService;

@Component
public class PlayerstatspergameServiceStaticInitializer {

	@Autowired
	private PlayerstatspergameService playerstatspergameService;

	private static PlayerstatspergameServiceStaticInitializer playerstatspergameServiceStaticInitializer;

	@PostConstruct
	public void init() {
		playerstatspergameServiceStaticInitializer = this;
		playerstatspergameServiceStaticInitializer.playerstatspergameService = this.playerstatspergameService;
	}

	public PlayerstatspergameService getPlayerstatspergameService() {
		return playerstatspergameService;
	}

	public void setPlayerstatspergameService(PlayerstatspergameService playerstatspergameService) {
		playerstatspergameService = playerstatspergameService;
	}

	public static ArrayList<Playerstatspergame> findPlayerStatsPerGameByGameId(int gameid) {

		ArrayList<Playerstatspergame> playerstatspergameList = playerstatspergameServiceStaticInitializer.playerstatspergameService
				.findPlayerStatsPerGameByGameId(gameid);

		return playerstatspergameList;
	}

}
