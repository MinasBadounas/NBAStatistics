package com.nbaproject.utils.staticInitializer;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.nbaproject.entities.Playerstatspergame;
import com.nbaproject.service.playerstatspergame.PlayerstatspergameService;

@Component
@Order(4)
public class PlayerstatspergameServiceStaticInitializer {

	@Autowired
	private PlayerstatspergameService playerstatspergameService;
	
	@Autowired
	private static AppconfigServiceStaticInitializer appconfigServiceStaticInitializer;

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

		int minutes = Integer.parseInt(appconfigServiceStaticInitializer.getKeyValuefromAppconfig("minimum.playerMinutes"));
		
		ArrayList<Playerstatspergame> playerstatspergameList = playerstatspergameServiceStaticInitializer.playerstatspergameService
				.findPlayerStatsPerGameByGameId(gameid,minutes);

		return playerstatspergameList;
	}
	
	public static ArrayList<Playerstatspergame> findAllPlayerStatsPerGameByGameId(int gameid) {

		ArrayList<Playerstatspergame> playerstatspergameList = playerstatspergameServiceStaticInitializer.playerstatspergameService
				.findPlayerStatsPerGameByGameId(gameid,0);

		return playerstatspergameList;
	}
	
	public static ArrayList<Playerstatspergame> findAllPlayerStatsPerGameWhichNotInDefencemMatchUpAndOverMinutesByGameId(int gameid, int minutes) {
	
		ArrayList<Playerstatspergame> playerstatspergameList = playerstatspergameServiceStaticInitializer.playerstatspergameService
				.findPlayerStatsPerGameWhichNotInDefenceMatchUpByGameId(gameid, minutes);

		return playerstatspergameList;
	}

}
