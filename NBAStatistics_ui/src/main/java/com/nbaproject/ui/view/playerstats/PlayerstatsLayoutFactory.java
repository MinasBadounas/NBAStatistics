package com.nbaproject.ui.view.playerstats;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.nbaproject.entities.Opponentplayerstatspergame;
import com.nbaproject.entities.Player;
import com.nbaproject.entities.Playerprop;
import com.nbaproject.entities.Playerseasonstat;
import com.nbaproject.entities.Playerstatspergame;
import com.nbaproject.service.opponentplayerstatspergame.OpponentplayerstatspergameService;
import com.nbaproject.service.player.PlayerService;
import com.nbaproject.service.playerprop.PlayerpropService;
import com.nbaproject.service.playerseasonstats.PlayerseasonstatsService;
import com.nbaproject.service.playerstatspergame.PlayerstatspergameService;
import com.nbaproject.ui.chart.PlayerStatsChart;
import com.nbaproject.ui.view.mainview.MainView;
import com.nbaproject.utils.tools.RoundDoubles;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = PlayerstatsLayoutFactory.NAME, ui = MainView.class)
public class PlayerstatsLayoutFactory extends VerticalLayout implements View {

	@Autowired
	private PlayerStatsChart playerStatsChart;

	@Autowired
	private PlayerService playerService;

	@Autowired
	private PlayerpropService playerpropService;

	@Autowired
	private OpponentplayerstatspergameService opponentplayerstatpergameService;

	@Autowired
	private PlayerstatspergameService playerstatspergameService;
	
	@Autowired
	private PlayerseasonstatsService playerseasonstatsService;

	public static final String NAME = "playerstats";

	public void enter(ViewChangeEvent event) {

		Page.getCurrent().getStyles().add("nbastatistics_uitheme");

		int playerid = Integer.parseInt(event.getParameterMap().get("playerid"));

		Player player = playerService.findPlayerByPlayerId(playerid);
		Playerseasonstat playerseasonstat = playerseasonstatsService.findLastPlayerseasonstatsByPlayerId(playerid);
		ArrayList<Playerprop> playerpropPointsList = playerpropService.findPlayerpropsByPlayerIdAndDescription(playerid,
				1);
		ArrayList<Opponentplayerstatspergame> opponentplayerstatspergameList = opponentplayerstatpergameService
				.findOpponentplayerstatspergameByPlayerId(playerid);
		ArrayList<Playerstatspergame> playerstatspergameList = playerstatspergameService
				.findPlayerStatsPerGameByPlayerId(playerid);

		int teamid = playerpropPointsList.get(0).getPlayer().getTeam().getTeamid();

		GridLayout gridHeaderPlayerStats = new GridLayout(12, 1);

		/**** GRIDLAYOUT HEADER ****/
		Label playerHeader = new Label("<img class=\"PlayerStatsImage\" src=\"" + player.getPhotoUrl() + "\">"+
				"<p class=\"PlayerStatsName\">" + player.getFirstname() + "   " + player.getLastname() + "</p>"
						+ "<p class=\"PlayerStatsDescription\">" + player.getPositionCategory() + "  |  "
						+ player.getTeam().getTeamname() + " | " + player.getTeam().getDivision() + "</p>",
				ContentMode.HTML);
		
		Label playerSeasonStatsTable = new Label("<table class=\"AverageSeasonStats\" >\r\n" + 
				"	<tr>\r\n" + 
				"	  <th align=\"center\">MPG</th>\r\n" + 
				"	  <th align=\"center\">PPG</th>\r\n" + 
				"	  <th align=\"center\">RPG</th>\r\n" + 
				"	  <th align=\"center\">APG</th>\r\n" + 
				"	  <th align=\"center\">Usage Rate</th>\r\n" + 
				"	  <th align=\"center\">+/-</th>\r\n" + 
				"	</tr>\r\n" + 
				"	<tr>\r\n" + 
				"	  <td align=\"center\">"+RoundDoubles.round(playerseasonstat.getMinutes()/playerseasonstat.getGames(),2)+"</td>\r\n" + 
				"	  <td align=\"center\">"+RoundDoubles.round(playerseasonstat.getPoints()/playerseasonstat.getGames(),2)+"</td>\r\n" + 
				"	  <td align=\"center\">"+RoundDoubles.round(playerseasonstat.getRebounds()/playerseasonstat.getGames(),2)+"</td>\r\n" + 
				"	  <td align=\"center\">"+RoundDoubles.round(playerseasonstat.getAssists()/playerseasonstat.getGames(),2)+"</td>\r\n" + 
				"	  <td align=\"center\">"+RoundDoubles.round(playerseasonstat.getUsageratepercentage(),2)+"%"+"</td>\r\n" + 
				"	  <td align=\"center\">"+RoundDoubles.round(playerseasonstat.getPlusminus()/playerseasonstat.getGames(),2)+"</td>\r\n" + 
				"	</tr>\r\n" + 
				"  </table> ",ContentMode.HTML);

		/**** GRIDLAYOUT LINE CHART ****/
		GridLayout gridLineChartPlayerStats = new GridLayout(12, 1);
		Label htmlLabelHomeLineChart = new Label(playerStatsChart.LineChartHtml(), ContentMode.HTML);

		/***** IMPORT COMPONENTS IN GRIDLAYOUT ******/
		gridHeaderPlayerStats.setHeight("100%");
		gridHeaderPlayerStats.setWidth("100%");
		gridLineChartPlayerStats.setHeight("100%");
		gridLineChartPlayerStats.setWidth("100%");
		gridHeaderPlayerStats.setSpacing(false);
		gridHeaderPlayerStats.addStyleName("GridHeaderPlayerStats");
		gridLineChartPlayerStats.addStyleName("GridLineChartPlayerstats");
		playerHeader.setSizeFull();
		playerSeasonStatsTable.setSizeFull();
		htmlLabelHomeLineChart.setSizeFull();
		playerHeader.addStyleName("PlayerStatsName");
		gridHeaderPlayerStats.addComponent(playerHeader, 0, 0, 6, 0);
		gridHeaderPlayerStats.addComponent(playerSeasonStatsTable, 7, 0, 11, 0);
		gridLineChartPlayerStats.addComponent(htmlLabelHomeLineChart, 0, 0, 11, 0);
		gridHeaderPlayerStats.setRowExpandRatio(0, 1);
		gridHeaderPlayerStats.setComponentAlignment(playerHeader, Alignment.MIDDLE_LEFT);
		gridHeaderPlayerStats.setComponentAlignment(playerSeasonStatsTable, Alignment.BOTTOM_RIGHT);
		gridLineChartPlayerStats.setComponentAlignment(htmlLabelHomeLineChart, Alignment.MIDDLE_CENTER);

		addComponents(gridHeaderPlayerStats,gridLineChartPlayerStats);

		/***** JAVASCRIPT FOR CHARTS ******/
		JavaScript.getCurrent().execute(
				playerStatsChart.LineChartScript(playerpropPointsList, playerstatspergameList, playerid, teamid));
		/***************************************/
	}
}
