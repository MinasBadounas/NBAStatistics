package com.nbaproject.utils.staticInitializer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.nbaproject.entities.Defencematchup;
import com.nbaproject.service.defencematchup.DefencematchupService;
import com.nbaproject.service.opponentplayerstatspergame.OpponentplayerstatspergameService;

@Component
@Order(6)
public class OpponentPlayerStatsPerGameServiceStaticInitializer {
	
	@Autowired
	private OpponentplayerstatspergameService opponentplayerstatspergameService;
	
	private static OpponentPlayerStatsPerGameServiceStaticInitializer opponentPlayerStatsPerGameServiceStaticInitializer;
	
	@PostConstruct
	public void init() {
		opponentPlayerStatsPerGameServiceStaticInitializer = this;
		opponentPlayerStatsPerGameServiceStaticInitializer.opponentplayerstatspergameService = this.opponentplayerstatspergameService;
	}

	public OpponentplayerstatspergameService getOpponentplayerstatspergameService() {
		return opponentplayerstatspergameService;
	}

	public void setoOpponentplayerstatspergameService(OpponentplayerstatspergameService opponentplayerstatspergameService) {
		opponentplayerstatspergameService = opponentplayerstatspergameService;
	}
	
	public static void addOpponentPlayerStatsPerGame(int gameid, int playerid) {

		opponentPlayerStatsPerGameServiceStaticInitializer.opponentplayerstatspergameService.saveOpponentPlayerStatsPerGame(gameid, playerid);

	}
	
	public static void addNullOpponentPlayerStatsPerGame(int gameid, int playerid) {

		opponentPlayerStatsPerGameServiceStaticInitializer.opponentplayerstatspergameService.saveNullOpponentPlayerStatsPerGame(gameid, playerid);

	}

}
