package com.nbaproject.ui.view.playerstats;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.nbaproject.entities.Opponentplayerstatspergame;
import com.nbaproject.entities.Player;
import com.nbaproject.entities.Playerprop;
import com.nbaproject.entities.Playerstatspergame;
import com.nbaproject.service.opponentplayerstatspergame.OpponentplayerstatspergameService;
import com.nbaproject.service.player.PlayerService;
import com.nbaproject.service.playerprop.PlayerpropService;
import com.nbaproject.service.playerstatspergame.PlayerstatspergameService;
import com.nbaproject.ui.chart.PlayerStatsChart;
import com.nbaproject.ui.view.mainview.MainView;
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
	
	public static final String NAME = "playerstats";

	public void enter(ViewChangeEvent event) {
		
		Page.getCurrent().getStyles().add("nbastatistics_uitheme");
		
		int playerid = Integer.parseInt(event.getParameterMap().get("playerid"));
		
		Player player = playerService.findPlayerByPlayerId(playerid);
		ArrayList<Playerprop> playerpropPointsList = playerpropService.findPlayerpropsByPlayerIdAndDescription(playerid, 1);
		ArrayList<Opponentplayerstatspergame> opponentplayerstatspergameList = opponentplayerstatpergameService.findOpponentplayerstatspergameByPlayerId(playerid);
		ArrayList<Playerstatspergame> playerstatspergameList =playerstatspergameService.findPlayerStatsPerGameByPlayerId(playerid);
		
		int teamid= playerpropPointsList.get(0).getPlayer().getTeam().getTeamid();
		
		GridLayout gridPlayerStats = new GridLayout(12, 18);
		
		/**** GRIDLAYOUT HEADER****/
		Label playerName = new Label("<p class=\"PlayerStatsName\">" + player.getFirstname() + "   "
				+ player.getLastname() +"</p>", ContentMode.HTML);
		Label playerImage = new Label(
				"<img class=\"PlayerStatsImage\" src=\"" + player.getPhotoUrl() + "\">",
				ContentMode.HTML);
	
		/**** GRIDLAYOUT LINE CHART ****/
		Label htmlLabelHomeLineChart = new Label(playerStatsChart.LineChartHtml(), ContentMode.HTML);
		
		
		/***** IMPORT COMPONENTS IN GRIDLAYOUT******/
		gridPlayerStats.setHeight("90%");
		gridPlayerStats.setWidth("90%");
		gridPlayerStats.setSpacing(true);
		htmlLabelHomeLineChart.setSizeFull();
		playerName.addStyleName("PlayerStatsName");
		playerImage.addStyleName("PlayerStatsImage");
		gridPlayerStats.addComponent(playerName,4,0,8,0);
		gridPlayerStats.addComponent(playerImage,3,0,3,0);
		gridPlayerStats.addComponent(htmlLabelHomeLineChart,0,1,11,1);
		gridPlayerStats.setComponentAlignment(playerName, Alignment.MIDDLE_CENTER);
		gridPlayerStats.setComponentAlignment(playerImage, Alignment.MIDDLE_CENTER);
		gridPlayerStats.setComponentAlignment(htmlLabelHomeLineChart, Alignment.MIDDLE_CENTER);
		

		
		addComponent(gridPlayerStats);
		
		/***** JAVASCRIPT FOR CHARTS******/
		JavaScript.getCurrent()
				.execute(playerStatsChart.LineChartScript(playerpropPointsList, playerstatspergameList, playerid, teamid));
		/***************************************/
	}
}
