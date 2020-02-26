package com.nbaproject.utils.defenceMatchup;

import java.util.ArrayList;


import com.nbaproject.entities.Playerstatspergame;
import com.nbaproject.utils.staticInitializer.PlayerstatspergameServiceStaticInitializer;

public class DefenceMatchUp {

	public static void calculateDefenceMatchUp(int gameid) {

		ArrayList<Playerstatspergame> playerStatsPerGameList = PlayerstatspergameServiceStaticInitializer
				.findPlayerStatsPerGameByGameId(gameid);

		for (int i = 0; i < playerStatsPerGameList.size(); i++) {

			for (int y = 0; y < playerStatsPerGameList.size(); y++) {

				if (playerStatsPerGameList.get(y).getTeamid() != playerStatsPerGameList.get(i).getTeamid()
						&& Math.min(playerStatsPerGameList.get(y).getDraftkingsposition(),
								playerStatsPerGameList.get(y).getFanduelposition()) >= Math.min(
										playerStatsPerGameList.get(i).getDraftkingsposition(),
										playerStatsPerGameList.get(i).getFanduelposition())
						&& Math.max(playerStatsPerGameList.get(y).getDraftkingsposition(),
								playerStatsPerGameList.get(y).getFanduelposition()) <= Math.max(
										playerStatsPerGameList.get(i).getDraftkingsposition(),
										playerStatsPerGameList.get(i).getFanduelposition())) {
					
					

				}

			}
		}
	}

}
