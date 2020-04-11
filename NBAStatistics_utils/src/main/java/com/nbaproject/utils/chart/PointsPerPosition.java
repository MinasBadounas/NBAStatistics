package com.nbaproject.utils.chart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.nbaproject.entities.Teamstatsbypositionpergame;

public class PointsPerPosition {

	public static Map<String, Integer> createMapList(ArrayList<Teamstatsbypositionpergame> teamstatsbypositionpergameList,
			int team) {

		Map<String, Integer> mapTeamList = new HashMap<>();

		for (int i = 0; i < teamstatsbypositionpergameList.size(); i++) {
			if (teamstatsbypositionpergameList.get(i).getTeam1().getTeamid() == team) {

				switch (teamstatsbypositionpergameList.get(i).getId().getCategoryposition()) {
				case 1:
					mapTeamList.put("PG-PG/SG", (int) teamstatsbypositionpergameList.get(i).getPoints());
				case 2:
					mapTeamList.put("SG-SG/SF", (int) teamstatsbypositionpergameList.get(i).getPoints());
				case 3:
					mapTeamList.put("SF-SF/PF", (int) teamstatsbypositionpergameList.get(i).getPoints());
				case 4:
					mapTeamList.put("C-PF", (int) teamstatsbypositionpergameList.get(i).getPoints());
				}
			}		
			

		}
		return mapTeamList;

	}
}
