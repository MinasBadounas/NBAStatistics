package com.nbaproject.ui.chart;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.nbaproject.entities.Opponentplayerstatspergame;
import com.nbaproject.entities.Playerprop;
import com.nbaproject.entities.Playerstatspergame;

@Component
public class PlayerStatsChart {

	public String LineChartHtml(String Description) {

		String html = "	<div class=\"container_chart\">\r\n" + "<canvas id=\"" + Description
				+ "LineChart\" ></canvas>\r\n" + "	</div>\r\n";

		return html;
	}

	public String LineChartScript(ArrayList<Playerprop> playerpropList,
			ArrayList<Playerstatspergame> playerstatspergameList, int playerid, int teamid, String Description) {

		StringBuilder stringBuilderLabels = new StringBuilder();
		StringBuilder stringBuilderOverUnderLimitData = new StringBuilder();
		StringBuilder stringBuilderPointsPerGameData = new StringBuilder();

		for (Playerprop playerprop : playerpropList) {
			if (playerprop.getBoxscore().getTeam1().getTeamid() != teamid)
				stringBuilderLabels.append("\'" + playerprop.getBoxscore().getTeam1().getTeamkey() + "\',");
			else {
				stringBuilderLabels.append("\'" + playerprop.getBoxscore().getTeam2().getTeamkey() + "\',");
			}
		}

		for (Playerstatspergame playerstatspergame : playerstatspergameList) {

			switch (Description) {

			case "Points":
				stringBuilderPointsPerGameData.append(playerstatspergame.getPoints() + ",");
			case "Rebounds":
				stringBuilderPointsPerGameData.append(playerstatspergame.getRebounds() + ",");
			case "Assists":
				stringBuilderPointsPerGameData.append(playerstatspergame.getAssists() + ",");
				
			}

		}

		for (Playerprop playerprop : playerpropList) {
			stringBuilderOverUnderLimitData.append(playerprop.getOverunder() + ",");
		}

		String stringLabels = stringBuilderLabels.substring(0, stringBuilderLabels.length() - 1);
		String stringPointsPerGameData = stringBuilderPointsPerGameData.substring(0,
				stringBuilderPointsPerGameData.length() - 1);
		String stringOverUnderLimitData = stringBuilderOverUnderLimitData.substring(0,
				stringBuilderOverUnderLimitData.length() - 1);

		String scriptJavascript = "let ctx = document.getElementById('" + Description
				+ "LineChart').getContext('2d');\r\n" + "" + "let config =new Chart(ctx, {" + "type: 'line',"
				+ "data: {" + "labels: [" + stringLabels + "]," + "datasets: [{" + "label: '" + Description + "',"
				+ "fill: false," + "backgroundColor: window.chartColors.blue," + "borderColor: window.chartColors.blue,"
				+ "data: [" + stringPointsPerGameData + "]," + "}, {" + "label: 'OverUnder Limit'," + "fill: false,"
				+ "backgroundColor: window.chartColors.green," + "borderColor: window.chartColors.green,"
				+ "borderDash: [5, 5]," + "data: [" + stringOverUnderLimitData + "]," + "}]" + "}," + "options: {"
				+ "responsive: true," + "title: {" + "display: true," + "text: '" + Description
				+ " per Game - OverUnder Limit' ," + "fontSize: 30" + "}," + "tooltips: {" + "mode: 'index',"
				+ "intersect: false," + "}," + "hover: {" + "mode: 'nearest'," + "intersect: true" + "}," + "scales: {"
				+ "x: {" + "display: true," + "scaleLabel: {" + "display: true," + "labelString: 'Teams'" + "}" + "},"
				+ "y: {" + "display: true," + "scaleLabel: {" + "display: true," + "labelString: '" + Description + "'"
				+ "}" + "}" + "}" + "}" + "});";

		return scriptJavascript;

	}

}
