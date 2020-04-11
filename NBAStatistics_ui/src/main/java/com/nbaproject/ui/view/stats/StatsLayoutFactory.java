package com.nbaproject.ui.view.stats;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.nbaproject.entities.Boxscore;
import com.nbaproject.entities.Playerstatspergame;
import com.nbaproject.entities.Teamstatsbypositionpergame;
import com.nbaproject.service.boxscore.BoxscoreService;
import com.nbaproject.service.teamstatsbypositionpergame.TeamstatsbypositionpergameService;
import com.nbaproject.ui.chart.Chart;
import com.nbaproject.ui.view.mainview.MainView;
import com.nbaproject.utils.chart.PointsPerPosition;
import com.nbaproject.utils.chart.UsageRatingPerPosition;
import com.nbaproject.utils.filter.TopPlayers;
import com.nbaproject.utils.staticInitializer.PlayerstatspergameServiceStaticInitializer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = StatsLayoutFactory.NAME, ui = MainView.class)
public class StatsLayoutFactory extends VerticalLayout implements View {

	public static final String NAME = "stats";

	@Autowired
	private Chart chart;

	@Autowired
	private TopPlayers topPlayers;

	@Autowired
	BoxscoreService boxscoreService;

	@Autowired
	TeamstatsbypositionpergameService teamstatsbypositionpergameService;

	public void enter(ViewChangeEvent event) {

		Page.getCurrent().getStyles().add("nbastatistics_uitheme");
		
		int gameid = Integer.parseInt(event.getParameterMap().get("gameid"));
		int awayTeam = Integer.parseInt(event.getParameterMap().get("awayteam"));
		int homeTeam = Integer.parseInt(event.getParameterMap().get("hometeam"));

		Boxscore boxscore = boxscoreService.findBoxscorebyGameId(gameid);

		ArrayList<Teamstatsbypositionpergame> teamstatsbypositionpergameList = teamstatsbypositionpergameService
				.findTeamStatsByPositionPerGameByGameid(gameid);
		ArrayList<Playerstatspergame> homeTeamPlayerstatspergameList = PlayerstatspergameServiceStaticInitializer
				.findAllPlayerStatsPerGameByGameIdAndTeam(gameid, homeTeam);
		ArrayList<Playerstatspergame> awayTeamPlayerstatspergameList = PlayerstatspergameServiceStaticInitializer
				.findAllPlayerStatsPerGameByGameIdAndTeam(gameid, awayTeam);

		GridLayout gridStats = new GridLayout(12, 18);
		
		/**** GRIDLAYOUT HEADER****/
		Label homeTeamImage = new Label(
				"<img class=\"HomeTeamImage\" src=\"" + boxscore.getTeam2().getWikipedialogourl() + "\">",
				ContentMode.HTML);
		Label awayTeamImage = new Label(
				"<img class=\"AwayTeamImage\" src=\"" + boxscore.getTeam1().getWikipedialogourl() + "\">",
				ContentMode.HTML);
		Label title = new Label("<p class=\"pageHeader\">" + boxscore.getTeam2().getTeamname() + "   "
				+ boxscore.getHometeamscore() + "    -     " + boxscore.getAwayteamscore() + "   "
				+ boxscore.getTeam1().getTeamname() + "</p>", ContentMode.HTML);

		/**** GRIDLAYOUT HOME TEAM LABEL****/
		Label homeTeamLabel = new Label(" <p class=\"TeamLabel\">" + boxscore.getTeam2().getTeamname() + "</p>", ContentMode.HTML);
		
		
		/**** GRIDLAYOUT HOME -  CHARTS AND TOP 5 PLAYERS PER CATEGORY****/

		Map<String, Integer> mapHomeTeamPointsPerPositionList = PointsPerPosition
				.createMapList(teamstatsbypositionpergameList, homeTeam);
		Map<String, Integer> mapHomeTeamUsageRatingPerPositionList = UsageRatingPerPosition
				.createMapList(teamstatsbypositionpergameList, homeTeam);
		
		Label htmlLabelHomePieChart = new Label(chart.PieChartHtml(homeTeam), ContentMode.HTML);
		Label htmlLabelHomeBarChart = new Label(chart.BarChartHtml(homeTeam), ContentMode.HTML);
		
		ArrayList<Playerstatspergame> gridTopPlayersPointsHomeList = topPlayers
				.topPlayersPerCategory(homeTeamPlayerstatspergameList, homeTeam, 5, "Points");
		ArrayList<Playerstatspergame> gridTopPlayersReboundsHomeList = topPlayers
				.topPlayersPerCategory(homeTeamPlayerstatspergameList, homeTeam, 5, "Rebounds");
		ArrayList<Playerstatspergame> gridTopPlayersAssistsHomeList = topPlayers
				.topPlayersPerCategory(homeTeamPlayerstatspergameList, homeTeam, 5, "Assists");
		
		gridStats.addComponent(new Label("<p class=\"HeaderTopPlayer\">" + "Points" + "</p>", ContentMode.HTML),6,2,7,2);

		for (int i = 0; i < gridTopPlayersPointsHomeList.size(); i++) {
			Label labelName = new Label("<p class=\"RowTopPlayerName\">" + (i + 1) + ". "
					+ gridTopPlayersPointsHomeList.get(i).getPlayer().getFirstname() + " "
					+ gridTopPlayersPointsHomeList.get(i).getPlayer().getLastname() + "</p>", ContentMode.HTML);
			gridStats.addComponent(labelName,6,3+i);

		}
		for (int i = 0; i < gridTopPlayersPointsHomeList.size(); i++) {
			Label labelPointsValue = new Label("<p class=\"RowTopPlayerValue\">"
					+ String.valueOf(gridTopPlayersPointsHomeList.get(i).getPoints()) + "</p>", ContentMode.HTML);
			gridStats.addComponent(labelPointsValue, 7,3+i);
		}
		
		gridStats.addComponent(new Label("<p class=\"HeaderTopPlayer\">" + "Rebounds" + "</p>", ContentMode.HTML),8,2,9,2);
		
		for (int i = 0; i < gridTopPlayersReboundsHomeList.size(); i++) {
			Label labelName = new Label("<p class=\"RowTopPlayerName\">" + (i + 1) + ". "
					+ gridTopPlayersReboundsHomeList.get(i).getPlayer().getFirstname() + " "
					+ gridTopPlayersReboundsHomeList.get(i).getPlayer().getLastname() + "</p>", ContentMode.HTML);
			gridStats.addComponent(labelName,8,3+i);

		}
		for (int i = 0; i < gridTopPlayersReboundsHomeList.size(); i++) {
			Label labelPointsValue = new Label("<p class=\"RowTopPlayerValue\">"
					+ String.valueOf(gridTopPlayersReboundsHomeList.get(i).getRebounds()) + "</p>", ContentMode.HTML);
			gridStats.addComponent(labelPointsValue, 9,3+i);
		}
		
		gridStats.addComponent(new Label("<p class=\"HeaderTopPlayer\">" + "Assists" + "</p>", ContentMode.HTML),10,2,11,2);
		
		for (int i = 0; i < gridTopPlayersAssistsHomeList.size(); i++) {
			Label labelName = new Label("<p class=\"RowTopPlayerName\">" + (i + 1) + ". "
					+ gridTopPlayersAssistsHomeList.get(i).getPlayer().getFirstname() + " "
					+ gridTopPlayersAssistsHomeList.get(i).getPlayer().getLastname() + "</p>", ContentMode.HTML);
			gridStats.addComponent(labelName,10,3+i);

		}
		for (int i = 0; i < gridTopPlayersAssistsHomeList.size(); i++) {
			Label labelPointsValue = new Label("<p class=\"RowTopPlayerValue\">"
					+ String.valueOf(gridTopPlayersAssistsHomeList.get(i).getAssists()) + "</p>", ContentMode.HTML);
			gridStats.addComponent(labelPointsValue, 11,3+i);
		}
		
		/***************************************/
		
		/**** GRIDLAYOUT HOME TEAM LABEL****/
		Label awayTeamLabel = new Label(" <p class=\"TeamLabel\">" + boxscore.getTeam1().getTeamname() + "</p>", ContentMode.HTML);
		
		
		/**** GRIDLAYOUT AWAY -  CHARTS AND TOP 5 PLAYERS PER CATEGORY****/
		Map<String, Integer> mapAwayTeamPointsPerPositionList = PointsPerPosition
				.createMapList(teamstatsbypositionpergameList, awayTeam);
		Map<String, Integer> mapAwayTeamUsageRatingPerPositionList = UsageRatingPerPosition
				.createMapList(teamstatsbypositionpergameList, awayTeam);
		

		Label htmlLabelAwayPieChart = new Label(chart.PieChartHtml(awayTeam), ContentMode.HTML);
		Label htmlLabelAwayBarChart = new Label(chart.BarChartHtml(awayTeam), ContentMode.HTML);
		

		ArrayList<Playerstatspergame> gridTopPlayersPointsAwayList = topPlayers
				.topPlayersPerCategory(awayTeamPlayerstatspergameList, awayTeam, 5, "Points");
		ArrayList<Playerstatspergame> gridTopPlayersReboundsAwayList = topPlayers
				.topPlayersPerCategory(awayTeamPlayerstatspergameList, awayTeam, 5, "Rebounds");
		ArrayList<Playerstatspergame> gridTopPlayersAssistsAwayList = topPlayers
				.topPlayersPerCategory(awayTeamPlayerstatspergameList, awayTeam, 5, "Assists");
		
		gridStats.addComponent(new Label("<p class=\"HeaderTopPlayer\">" + "Points" + "</p>", ContentMode.HTML),6,10,7,10);
		
		for (int i = 0; i < gridTopPlayersPointsAwayList.size(); i++) {
			Label labelName = new Label("<p class=\"RowTopPlayerName\">" + (i + 1) + ". "
					+ gridTopPlayersPointsAwayList.get(i).getPlayer().getFirstname() + " "
					+ gridTopPlayersPointsAwayList.get(i).getPlayer().getLastname() + "</p>", ContentMode.HTML);
			gridStats.addComponent(labelName,6,11+i);

		}
		for (int i = 0; i < gridTopPlayersPointsAwayList.size(); i++) {
			Label labelPointsValue = new Label("<p class=\"RowTopPlayerValue\">"
					+ String.valueOf(gridTopPlayersPointsAwayList.get(i).getPoints()) + "</p>", ContentMode.HTML);
			gridStats.addComponent(labelPointsValue, 7,11+i);
		}
		
		gridStats.addComponent(new Label("<p class=\"HeaderTopPlayer\">" + "Rebounds" + "</p>", ContentMode.HTML),8,10,9,10);
		
		for (int i = 0; i < gridTopPlayersReboundsAwayList.size(); i++) {
			Label labelName = new Label("<p class=\"RowTopPlayerName\">" + (i + 1) + ". "
					+ gridTopPlayersReboundsAwayList.get(i).getPlayer().getFirstname() + " "
					+ gridTopPlayersReboundsAwayList.get(i).getPlayer().getLastname() + "</p>", ContentMode.HTML);
			gridStats.addComponent(labelName,8,11+i);

		}
		for (int i = 0; i < gridTopPlayersReboundsAwayList.size(); i++) {
			Label labelPointsValue = new Label("<p class=\"RowTopPlayerValue\">"
					+ String.valueOf(gridTopPlayersReboundsAwayList.get(i).getRebounds()) + "</p>", ContentMode.HTML);
			gridStats.addComponent(labelPointsValue, 9,11+i);
		}
		
		gridStats.addComponent(new Label("<p class=\"HeaderTopPlayer\">" + "Assists" + "</p>", ContentMode.HTML),10,10,11,10);
		
		for (int i = 0; i < gridTopPlayersAssistsAwayList.size(); i++) {
			Label labelName = new Label("<p class=\"RowTopPlayerName\">" + (i + 1) + ". "
					+ gridTopPlayersAssistsAwayList.get(i).getPlayer().getFirstname() + " "
					+ gridTopPlayersAssistsAwayList.get(i).getPlayer().getLastname() + "</p>", ContentMode.HTML);
			gridStats.addComponent(labelName,10,11+i);

		}
		for (int i = 0; i < gridTopPlayersAssistsAwayList.size(); i++) {
			Label labelPointsValue = new Label("<p class=\"RowTopPlayerValue\">"
					+ String.valueOf(gridTopPlayersAssistsAwayList.get(i).getAssists()) + "</p>", ContentMode.HTML);
			gridStats.addComponent(labelPointsValue, 11,11+i);
		}
		/***************************************/
		
		/***** IMPORT COMPONENTS IN GRIDLAYOUT******/
//		gridStats.setSizeFull();
		gridStats.setHeight("90%");
		gridStats.setWidth("90%");
		gridStats.setSpacing(true);
		homeTeamImage.addStyleName("HomeTeamImage");
		awayTeamImage.addStyleName("AwayTeamImage");
		homeTeamLabel.setWidthFull();
		homeTeamLabel.addStyleName("TeamLabel");
		awayTeamLabel.setWidthFull();
		awayTeamLabel.addStyleName("TeamLabel");		
		gridStats.addComponent(homeTeamImage, 0, 0,	3, 0);
		gridStats.addComponent(title, 4, 0,	7, 0);
		gridStats.addComponent(awayTeamImage, 8, 0,	11, 0);
		gridStats.addComponent(homeTeamLabel, 0, 1,	11, 1);
		gridStats.addComponent(htmlLabelHomePieChart, 0, 2,	2, 8);
		gridStats.addComponent(htmlLabelHomeBarChart, 3, 2,	5, 8);
		gridStats.addComponent(awayTeamLabel, 0, 9,	11, 9);
		gridStats.addComponent(htmlLabelAwayPieChart, 0, 10,2, 15);
		gridStats.addComponent(htmlLabelAwayBarChart, 3, 10,5, 15);
		gridStats.setComponentAlignment(title, Alignment.MIDDLE_CENTER);
		gridStats.setComponentAlignment(homeTeamImage, Alignment.MIDDLE_CENTER);
		gridStats.setComponentAlignment(awayTeamImage, Alignment.MIDDLE_CENTER);
		gridStats.setComponentAlignment(htmlLabelHomePieChart, Alignment.MIDDLE_CENTER);
		gridStats.setComponentAlignment(htmlLabelHomeBarChart, Alignment.MIDDLE_CENTER);
		gridStats.setComponentAlignment(htmlLabelAwayPieChart, Alignment.MIDDLE_CENTER);
		gridStats.setComponentAlignment(htmlLabelAwayBarChart, Alignment.MIDDLE_CENTER);
//		addComponent(gridStats);
		/***************************************/
		
		/***** JAVASCRIPT FOR CHARTS******/
		JavaScript.getCurrent()
				.execute(chart.PieChartScript(mapHomeTeamPointsPerPositionList, homeTeam, "Points Per Position"));
		JavaScript.getCurrent()
				.execute(chart.PieChartScript(mapAwayTeamPointsPerPositionList, awayTeam, "Points Per Position"));
		JavaScript.getCurrent().execute(
				chart.BarChartScript(mapHomeTeamUsageRatingPerPositionList, homeTeam, "Usage Rate Per Position"));
		JavaScript.getCurrent().execute(
				chart.BarChartScript(mapAwayTeamUsageRatingPerPositionList, awayTeam, "Usage Rate Per Position"));

		/***************************************/
		
		/***** BOXSCORE TABLE ******/
		Grid<Playerstatspergame> grid = new Grid<Playerstatspergame>();

		Button awayTeamButton = new Button(boxscore.getAwayteam());
		Button homeTeamButton = new Button(boxscore.getHometeam());
		homeTeamButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
		awayTeamButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
		homeTeamButton.addStyleName("HomeTeamButton");
		awayTeamButton.addStyleName("AwayTeamButton");
		gridStats.addComponent(awayTeamButton, 5, 16,5,16);
		gridStats.addComponent(homeTeamButton, 6, 16,6,16);
		gridStats.setComponentAlignment(homeTeamButton, Alignment.MIDDLE_LEFT);
		gridStats.setComponentAlignment(awayTeamButton, Alignment.MIDDLE_RIGHT);

		awayTeamButton.addClickListener(playerstatsList -> {

			gridStats.removeComponent(grid);
			grid.removeAllColumns();
			grid.setItems(awayTeamPlayerstatspergameList);

//			grid.addColumn(Playerstatspergame::getTeamid).setCaption("TeamID");
			grid.addColumn(Playerstatspergame -> {
				return Playerstatspergame.getPlayer().getFirstname() + " " + Playerstatspergame.getPlayer().getLastname();
			}).setCaption("Name").setStyleGenerator(Playerstatspergame->Playerstatspergame.getStarted()==1?"bold":null);
//			grid.addColumn(Playerstatspergame -> {
//				return Playerstatspergame.getPlayer().getLastname();
//			}).setCaption("Last Name").setStyleGenerator(Playerstatspergame->Playerstatspergame.getStarted()==1?"bold":null);
//			grid.addColumn(Playerstatspergame::getOpponentid).setCaption("opponentid");
//			grid.addColumn(Playerstatspergame::getSeasontype).setCaption("seasontype");
//			grid.addColumn(Playerstatspergame::getSeason).setCaption("season");
//			grid.addColumn(Playerstatspergame::getFanduelposition).setCaption("fanduelposition");
//			grid.addColumn(Playerstatspergame::getDraftkingsposition).setCaption("draftkingsposition");
//			grid.addColumn(Playerstatspergame::getStarted).setCaption("started").isHidden();
//			grid.addColumn(Playerstatspergame::getOpponentrank).setCaption("opponentrank");
//			grid.addColumn(Playerstatspergame::getOpponentpositionrank).setCaption("opponentpositionrank");
//			grid.addColumn(Playerstatspergame::getGames).setCaption("games");
			grid.addColumn(Playerstatspergame -> {
				return Playerstatspergame.getMinutes()+":"+Playerstatspergame.getSeconds();
			}).setCaption("MP").setWidth(78);
			grid.addColumn(Playerstatspergame::getPoints).setCaption("PTS").setWidth(78);
			grid.addColumn(Playerstatspergame::getFieldgoalsattempted).setCaption("FGA").setWidth(78);
			grid.addColumn(Playerstatspergame::getFieldgoalspercentage).setCaption("FG%").setWidth(78);
			grid.addColumn(Playerstatspergame::getTwopointersmade).setCaption("2P").setWidth(78);
			grid.addColumn(Playerstatspergame::getTwopointersattempted).setCaption("2PA").setWidth(78);
			grid.addColumn(Playerstatspergame::getTwopointerspercentage).setCaption("2P%").setWidth(78);
			grid.addColumn(Playerstatspergame::getThreepointersmade).setCaption("3P").setWidth(78);
			grid.addColumn(Playerstatspergame::getThreepointersattempted).setCaption("3PA").setWidth(78);
			grid.addColumn(Playerstatspergame::getThreepointerspercentage).setCaption("3P%").setWidth(78);
			grid.addColumn(Playerstatspergame::getFreethrowssmade).setCaption("FT").setWidth(78);
			grid.addColumn(Playerstatspergame::getFreethrowsattempted).setCaption("FTA").setWidth(78);
			grid.addColumn(Playerstatspergame::getFreethrowspercentage).setCaption("FT%").setWidth(78);
			grid.addColumn(Playerstatspergame::getOffensiverebounds).setCaption("ORB").setWidth(78);
			grid.addColumn(Playerstatspergame::getDefensiverebounds).setCaption("DRB").setWidth(78);
			grid.addColumn(Playerstatspergame::getRebounds).setCaption("TRB").setWidth(78);
			grid.addColumn(Playerstatspergame::getAssists).setCaption("AST").setWidth(78);
			grid.addColumn(Playerstatspergame::getSteals).setCaption("STL").setWidth(78);
			grid.addColumn(Playerstatspergame::getBlockedshots).setCaption("BLK").setWidth(78);
			grid.addColumn(Playerstatspergame::getTurnovers).setCaption("TOV").setWidth(78);
			grid.addColumn(Playerstatspergame::getPersonalfouls).setCaption("PF").setWidth(78);
			grid.addColumn(Playerstatspergame::getPlayerefficiencyrating).setCaption("RATE").setWidth(78);
			grid.addColumn(Playerstatspergame::getUsageratepercentage).setCaption("USG%").setWidth(78);
			grid.addColumn(Playerstatspergame::getUsageratepercentage).setCaption("+/-").setWidth(78);
//			grid.addColumn(Playerstatspergame::getEffectivefieldgoalspercentage)
//			.setCaption("effectivefieldgoalspercentage");
			
			grid.setSizeFull();
			grid.setFrozenColumnCount(1);
			gridStats.addComponent(grid, 0, 17,11,17);
		});

		homeTeamButton.addClickListener(playerstatsList -> {

			gridStats.removeComponent(grid);
			grid.removeAllColumns();
			
			grid.setItems(homeTeamPlayerstatspergameList);

//			grid.addColumn(Playerstatspergame::getTeamid).setCaption("TeamID");
			grid.addColumn(Playerstatspergame -> {
				return Playerstatspergame.getPlayer().getFirstname() + " " + Playerstatspergame.getPlayer().getLastname();
			}).setCaption("Name").setStyleGenerator(Playerstatspergame->Playerstatspergame.getStarted()==1?"bold":null);
//			grid.addColumn(Playerstatspergame -> {
//				return Playerstatspergame.getPlayer().getLastname();
//			}).setCaption("Last Name").setStyleGenerator(Playerstatspergame->Playerstatspergame.getStarted()==1?"bold":null);
//			grid.addColumn(Playerstatspergame::getOpponentid).setCaption("opponentid");
//			grid.addColumn(Playerstatspergame::getSeasontype).setCaption("seasontype");
//			grid.addColumn(Playerstatspergame::getSeason).setCaption("season");
//			grid.addColumn(Playerstatspergame::getFanduelposition).setCaption("fanduelposition");
//			grid.addColumn(Playerstatspergame::getDraftkingsposition).setCaption("draftkingsposition");
//			grid.addColumn(Playerstatspergame::getStarted).setCaption("started").isHidden();
//			grid.addColumn(Playerstatspergame::getOpponentrank).setCaption("opponentrank");
//			grid.addColumn(Playerstatspergame::getOpponentpositionrank).setCaption("opponentpositionrank");
//			grid.addColumn(Playerstatspergame::getGames).setCaption("games");
			grid.addColumn(Playerstatspergame -> {
				return Playerstatspergame.getMinutes()+":"+Playerstatspergame.getSeconds();
			}).setCaption("MP").setWidth(78);
			grid.addColumn(Playerstatspergame::getPoints).setCaption("PTS").setWidth(78);
			grid.addColumn(Playerstatspergame::getFieldgoalsattempted).setCaption("FGA").setWidth(78);
			grid.addColumn(Playerstatspergame::getFieldgoalspercentage).setCaption("FG%").setWidth(78);
			grid.addColumn(Playerstatspergame::getTwopointersmade).setCaption("2P").setWidth(78);
			grid.addColumn(Playerstatspergame::getTwopointersattempted).setCaption("2PA").setWidth(78);
			grid.addColumn(Playerstatspergame::getTwopointerspercentage).setCaption("2P%").setWidth(78);
			grid.addColumn(Playerstatspergame::getThreepointersmade).setCaption("3P").setWidth(78);
			grid.addColumn(Playerstatspergame::getThreepointersattempted).setCaption("3PA").setWidth(78);
			grid.addColumn(Playerstatspergame::getThreepointerspercentage).setCaption("3P%").setWidth(78);
			grid.addColumn(Playerstatspergame::getFreethrowssmade).setCaption("FT").setWidth(78);
			grid.addColumn(Playerstatspergame::getFreethrowsattempted).setCaption("FTA").setWidth(78);
			grid.addColumn(Playerstatspergame::getFreethrowspercentage).setCaption("FT%").setWidth(78);
			grid.addColumn(Playerstatspergame::getOffensiverebounds).setCaption("ORB").setWidth(78);
			grid.addColumn(Playerstatspergame::getDefensiverebounds).setCaption("DRB").setWidth(78);
			grid.addColumn(Playerstatspergame::getRebounds).setCaption("TRB").setWidth(78);
			grid.addColumn(Playerstatspergame::getAssists).setCaption("AST").setWidth(78);
			grid.addColumn(Playerstatspergame::getSteals).setCaption("STL").setWidth(78);
			grid.addColumn(Playerstatspergame::getBlockedshots).setCaption("BLK").setWidth(78);
			grid.addColumn(Playerstatspergame::getTurnovers).setCaption("TOV").setWidth(78);
			grid.addColumn(Playerstatspergame::getPersonalfouls).setCaption("PF").setWidth(78);
			grid.addColumn(Playerstatspergame::getPlayerefficiencyrating).setCaption("RATE").setWidth(78);
			grid.addColumn(Playerstatspergame::getUsageratepercentage).setCaption("USG%").setWidth(78);
			grid.addColumn(Playerstatspergame::getUsageratepercentage).setCaption("+/-").setWidth(78);
//			grid.addColumn(Playerstatspergame::getEffectivefieldgoalspercentage)
//			.setCaption("effectivefieldgoalspercentage");
			
			grid.setSizeFull();
			grid.setFrozenColumnCount(1);
			gridStats.addComponent(grid, 0, 17,11,17);
		});

		awayTeamButton.click();
		addComponent(gridStats);
	}

}
