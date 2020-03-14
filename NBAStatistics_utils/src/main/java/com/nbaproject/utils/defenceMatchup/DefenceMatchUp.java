package com.nbaproject.utils.defenceMatchup;

import java.util.ArrayList;

import com.nbaproject.entities.Defencematchup;
import com.nbaproject.entities.DefencematchupPK;
import com.nbaproject.entities.Playerstatspergame;
import com.nbaproject.utils.staticInitializer.AppconfigServiceStaticInitializer;
import com.nbaproject.utils.staticInitializer.DefencematchupServiceStaticInitializer;
import com.nbaproject.utils.staticInitializer.PlayerstatspergameServiceStaticInitializer;

public class DefenceMatchUp {

	public static void calculateDefenceMatchUp(int gameid) {

		/** ---------Method Description--------- **/
		// - Compare Every player(a list with players which had played for than 25
		// minutes) for the specific gameid.
		// - The comparison is been accordingly with the position of each player

		ArrayList<Playerstatspergame> playerStatsPerGameList = PlayerstatspergameServiceStaticInitializer
				.findPlayerStatsPerGameByGameId(gameid);

		for (int i = 0; i < playerStatsPerGameList.size(); i++) {

			for (int y = 0; y < playerStatsPerGameList.size(); y++) {

				if (playerStatsPerGameList.get(y).getTeamid() != playerStatsPerGameList.get(i).getTeamid()
						&& ((Math.min(playerStatsPerGameList.get(y).getDraftkingsposition(),
								playerStatsPerGameList.get(y).getFanduelposition()) >= Math.min(
										playerStatsPerGameList.get(i).getDraftkingsposition(),
										playerStatsPerGameList.get(i).getFanduelposition())
								&& Math.min(playerStatsPerGameList.get(y).getDraftkingsposition(),
										playerStatsPerGameList.get(y).getFanduelposition()) <= Math.max(
												playerStatsPerGameList.get(i).getDraftkingsposition(),
												playerStatsPerGameList.get(i).getFanduelposition()))
								|| (Math.max(playerStatsPerGameList.get(y).getDraftkingsposition(),
										playerStatsPerGameList.get(y).getFanduelposition()) <= Math.max(
												playerStatsPerGameList.get(i).getDraftkingsposition(),
												playerStatsPerGameList.get(i).getFanduelposition())
										&& Math.max(playerStatsPerGameList.get(y).getDraftkingsposition(),
												playerStatsPerGameList.get(y).getFanduelposition()) >= Math.min(
														playerStatsPerGameList.get(i).getDraftkingsposition(),
														playerStatsPerGameList.get(i).getFanduelposition()))
								|| (Math.min(playerStatsPerGameList.get(y).getDraftkingsposition(),
										playerStatsPerGameList.get(y).getFanduelposition()) < Math.min(
												playerStatsPerGameList.get(i).getDraftkingsposition(),
												playerStatsPerGameList.get(i).getFanduelposition())
										&& Math.max(playerStatsPerGameList.get(y).getDraftkingsposition(),
												playerStatsPerGameList.get(y).getFanduelposition()) > Math.max(
														playerStatsPerGameList.get(i).getDraftkingsposition(),
														playerStatsPerGameList.get(i).getFanduelposition())))) {

					DefencematchupPK newdefencematchupPK = new DefencematchupPK(
							playerStatsPerGameList.get(i).getId().getGameid(),
							playerStatsPerGameList.get(i).getId().getPlayerid(),
							playerStatsPerGameList.get(y).getId().getPlayerid());
					Defencematchup newdefencematchup = new Defencematchup();

					newdefencematchup.setId(newdefencematchupPK);
					DefencematchupServiceStaticInitializer.addDefencematchup(newdefencematchup);
					System.out.println(
							"Save DefenceMatchUp for gameId " + playerStatsPerGameList.get(i).getId().getGameid()
									+ " and playerIds: " + playerStatsPerGameList.get(i).getId().getPlayerid() + " - "
									+ playerStatsPerGameList.get(y).getId().getPlayerid());

				}

			}
		}

		int minutes = Integer
				.parseInt(AppconfigServiceStaticInitializer.getKeyValuefromAppconfig("minimum.playerMinutes"));
		ArrayList<Playerstatspergame> playerStatsPerGameListWhichNotInDefencemMatchUp = PlayerstatspergameServiceStaticInitializer
				.findAllPlayerStatsPerGameWhichNotInDefencemMatchUpAndOverMinutesByGameId(gameid, minutes);

		ArrayList<Playerstatspergame> allPlayerStatsPerGameList = PlayerstatspergameServiceStaticInitializer
				.findAllPlayerStatsPerGameByGameId(gameid);
		
		
		for (int i = 0; i < playerStatsPerGameListWhichNotInDefencemMatchUp.size(); i++) {

			for (int y = 0; y < allPlayerStatsPerGameList.size(); y++) {

				if (allPlayerStatsPerGameList.get(y).getMinutes() > 1
						&& allPlayerStatsPerGameList.get(y).getTeamid() != playerStatsPerGameListWhichNotInDefencemMatchUp.get(i).getTeamid()
						&& ((Math.min(allPlayerStatsPerGameList.get(y).getDraftkingsposition(),
								allPlayerStatsPerGameList.get(y).getFanduelposition()) >= 
								Math.min(playerStatsPerGameListWhichNotInDefencemMatchUp.get(i).getDraftkingsposition(),
										playerStatsPerGameListWhichNotInDefencemMatchUp.get(i).getFanduelposition())
								&& Math.min(allPlayerStatsPerGameList.get(y).getDraftkingsposition(),
										allPlayerStatsPerGameList.get(y).getFanduelposition()) <= 
										Math.max(playerStatsPerGameListWhichNotInDefencemMatchUp.get(i).getDraftkingsposition(),
												playerStatsPerGameListWhichNotInDefencemMatchUp.get(i).getFanduelposition()))
								|| (Math.max(allPlayerStatsPerGameList.get(y).getDraftkingsposition(),
										allPlayerStatsPerGameList.get(y).getFanduelposition()) <= 
										Math.max(playerStatsPerGameListWhichNotInDefencemMatchUp.get(i).getDraftkingsposition(),
												playerStatsPerGameListWhichNotInDefencemMatchUp.get(i).getFanduelposition())
										&& Math.max(allPlayerStatsPerGameList.get(y).getDraftkingsposition(),
												allPlayerStatsPerGameList.get(y).getFanduelposition()) >= 
												Math.min(playerStatsPerGameListWhichNotInDefencemMatchUp.get(i).getDraftkingsposition(),
														playerStatsPerGameListWhichNotInDefencemMatchUp.get(i).getFanduelposition()))
								|| (Math.min(allPlayerStatsPerGameList.get(y).getDraftkingsposition(),
										allPlayerStatsPerGameList.get(y).getFanduelposition()) < 
										Math.min(playerStatsPerGameListWhichNotInDefencemMatchUp.get(i).getDraftkingsposition(),
												playerStatsPerGameListWhichNotInDefencemMatchUp.get(i).getFanduelposition())
										&& Math.max(allPlayerStatsPerGameList.get(y).getDraftkingsposition(),
												allPlayerStatsPerGameList.get(y).getFanduelposition()) > 
								Math.max(playerStatsPerGameListWhichNotInDefencemMatchUp.get(i).getDraftkingsposition(),
														playerStatsPerGameListWhichNotInDefencemMatchUp.get(i).getFanduelposition())))) {

					DefencematchupPK newdefencematchupPK1 = new DefencematchupPK(
							playerStatsPerGameListWhichNotInDefencemMatchUp.get(i).getId().getGameid(),
							playerStatsPerGameListWhichNotInDefencemMatchUp.get(i).getId().getPlayerid(),
							allPlayerStatsPerGameList.get(y).getId().getPlayerid());
					Defencematchup newdefencematchup1 = new Defencematchup();

					newdefencematchup1.setId(newdefencematchupPK1);
					DefencematchupServiceStaticInitializer.addDefencematchup(newdefencematchup1);
					System.out.println(
							"Save DefenceMatchUp for gameId " + playerStatsPerGameListWhichNotInDefencemMatchUp.get(i).getId().getGameid()
									+ " and playerIds: " + playerStatsPerGameListWhichNotInDefencemMatchUp.get(i).getId().getPlayerid() + " - "
									+ allPlayerStatsPerGameList.get(y).getId().getPlayerid());

				}
			}
		}
	}

}
