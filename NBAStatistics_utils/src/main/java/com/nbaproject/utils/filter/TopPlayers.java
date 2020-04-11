package com.nbaproject.utils.filter;

import java.util.ArrayList;
import java.util.Comparator;

import org.springframework.stereotype.Component;

import com.nbaproject.entities.Playerstatspergame;

@Component
public class TopPlayers {

	public ArrayList<Playerstatspergame> topPlayersPerCategory(ArrayList<Playerstatspergame> PlayerstatspergameList,
			int team, int limit, String filter) {

		switch (filter) {

		case ("Points"): {

			ArrayList<Playerstatspergame> playerstatspergamePointsList = new ArrayList<Playerstatspergame>();
			PlayerstatspergameList.sort(Comparator.comparing(Playerstatspergame::getPoints).reversed());
			for (int i = 0; i < limit; i++) {

				playerstatspergamePointsList.add(PlayerstatspergameList.get(i));

			}

			return playerstatspergamePointsList;
		}
		case ("Rebounds"): {

			ArrayList<Playerstatspergame> playerstatspergameReboundsList = new ArrayList<Playerstatspergame>();
			PlayerstatspergameList.sort(Comparator.comparing(Playerstatspergame::getRebounds).reversed());
			for (int i = 0; i < limit; i++) {

				playerstatspergameReboundsList.add(PlayerstatspergameList.get(i));

			}

			return playerstatspergameReboundsList;
		}
		case ("Assists"): {
			ArrayList<Playerstatspergame> playerstatspergameAssistsList = new ArrayList<Playerstatspergame>();
			PlayerstatspergameList.sort(Comparator.comparing(Playerstatspergame::getAssists).reversed());
			for (int i = 0; i < limit; i++) {

				playerstatspergameAssistsList.add(PlayerstatspergameList.get(i));

			}

			return playerstatspergameAssistsList;
		}

		}
		return null;

	}

}
