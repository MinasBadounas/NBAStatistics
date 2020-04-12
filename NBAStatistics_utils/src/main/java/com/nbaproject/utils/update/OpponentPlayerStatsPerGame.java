package com.nbaproject.utils.update;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.nbaproject.entities.Playerstatspergame;
import com.nbaproject.utils.staticInitializer.AppconfigServiceStaticInitializer;
import com.nbaproject.utils.staticInitializer.OpponentPlayerStatsPerGameServiceStaticInitializer;
import com.nbaproject.utils.staticInitializer.PlayerstatspergameServiceStaticInitializer;

@Component
public class OpponentPlayerStatsPerGame {

	public static int minutes = Integer
			.parseInt(AppconfigServiceStaticInitializer.getKeyValuefromAppconfig("minimum.playerMinutes"));

	public static void calulateOpponentPlayerStatsPerGame(int gameid) {

		/** ---------Method Description--------- **/
		// - Create an entry in table "OpponentPlayerStatsPerGame" for every player for
		// the specific gameId
		// - If the player have played less than 25 minutes create an null entry to the
		// table
		// - If the player have played more than 25 minutes create an entry which
		// calculate the average of
		// his opponents with the same position

		ArrayList<Playerstatspergame> playerstatspergameList = PlayerstatspergameServiceStaticInitializer
				.findAllPlayerStatsPerGameByGameId(gameid);

		for (Playerstatspergame playerstatspergame : playerstatspergameList) {

			if (playerstatspergame.getMinutes() > minutes) {
				OpponentPlayerStatsPerGameServiceStaticInitializer.addOpponentPlayerStatsPerGame(gameid,
						playerstatspergame.getPlayer().getPlayerid());
				System.out.println("Save to OpponentPlayerStatsPerGame(minutes>" + minutes + ") for playerId: "
						+ playerstatspergame.getPlayer().getPlayerid());
			} else {
				OpponentPlayerStatsPerGameServiceStaticInitializer.addNullOpponentPlayerStatsPerGame(gameid,
						playerstatspergame.getPlayer().getPlayerid());
				System.out.println("Save to OpponentPlayerStatsPerGame(minutes<=" + minutes + ") for playerId: "
						+ playerstatspergame.getPlayer().getPlayerid());
			}
		}
	}
}
