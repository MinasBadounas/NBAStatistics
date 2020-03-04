package com.nbaproject.utils.opponentplayerstatspergame;

import java.util.ArrayList;

import com.nbaproject.entities.Playerstatspergame;
import com.nbaproject.utils.staticInitializer.AppconfigServiceStaticInitializer;
import com.nbaproject.utils.staticInitializer.OpponentPlayerStatsPerGameServiceStaticInitializer;
import com.nbaproject.utils.staticInitializer.PlayerstatspergameServiceStaticInitializer;

public class OpponentPlayerStatsPerGame {

	public static void calulateOpponentPlayerStatsPerGame(int gameid) {

		ArrayList<Playerstatspergame> playerstatspergameList = PlayerstatspergameServiceStaticInitializer
				.findPlayerStatsPerGameByGameId(gameid);

		for (Playerstatspergame playerstatspergame : playerstatspergameList) {
			System.out.println(playerstatspergame.getPlayer().getPlayerid());
			if (playerstatspergame.getMinutes() > 25) {
				OpponentPlayerStatsPerGameServiceStaticInitializer.addOpponentPlayerStatsPerGame(gameid,
						playerstatspergame.getPlayer().getPlayerid());
			} else {
				OpponentPlayerStatsPerGameServiceStaticInitializer.addNullOpponentPlayerStatsPerGame(gameid,
						playerstatspergame.getPlayer().getPlayerid());
			}
		}
	}
}
