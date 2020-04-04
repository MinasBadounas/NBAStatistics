package com.nbaproject.ui.view.stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.nbaproject.entities.Playerstatspergame;
import com.nbaproject.entities.Teamstatsbypositionpergame;
import com.nbaproject.service.boxscore.BoxscoreService;
import com.nbaproject.service.teamstatsbypositionpergame.TeamstatsbypositionpergameService;
import com.nbaproject.ui.chart.Chart;
import com.nbaproject.ui.view.mainview.MainView;
import com.nbaproject.utils.chart.PointsPerPosition;
import com.nbaproject.utils.chart.UsageRatingPerPosition;
import com.nbaproject.utils.staticInitializer.PlayerstatspergameServiceStaticInitializer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = StatsLayoutFactory.NAME, ui = MainView.class)
public class StatsLayoutFactory extends VerticalLayout implements View {

	public static final String NAME = "stats";

	@Autowired
	private Chart chart;

	@Autowired
	BoxscoreService boxscoreService;

	@Autowired
	TeamstatsbypositionpergameService teamstatsbypositionpergameService;

	public void enter(ViewChangeEvent event) {

		int gameid = Integer.parseInt(event.getParameterMap().get("gameid"));
		int awayTeam = Integer.parseInt(event.getParameterMap().get("awayteam"));
		int homeTeam = Integer.parseInt(event.getParameterMap().get("hometeam"));
		ArrayList<Teamstatsbypositionpergame> teamstatsbypositionpergameList = teamstatsbypositionpergameService
				.findTeamStatsByPositionPerGameByGameid(gameid);

		HorizontalLayout horizontalCharts = new HorizontalLayout();

		Map<String, Integer> mapAwayTeamPointsPerPositionList = PointsPerPosition
				.createMapList(teamstatsbypositionpergameList, awayTeam);
		Map<String, Integer> mapHomeTeamPointsPerPositionList = PointsPerPosition
				.createMapList(teamstatsbypositionpergameList, homeTeam);
		Map<String, Integer> mapAwayTeamUsageRatingPerPositionList = UsageRatingPerPosition
				.createMapList(teamstatsbypositionpergameList, awayTeam);
		Map<String, Integer> mapHomeTeamUsageRatingPerPositionList = UsageRatingPerPosition
				.createMapList(teamstatsbypositionpergameList, homeTeam);

		Label htmlLabelHomePieChart = new Label(chart.PieChartHtml(homeTeam), ContentMode.HTML);
		Label htmlLabelAwayPieChart = new Label(chart.PieChartHtml(awayTeam), ContentMode.HTML);
		Label htmlLabelHomeBarChart = new Label(chart.BarChartHtml(homeTeam), ContentMode.HTML);
		Label htmlLabelAwayBarChart = new Label(chart.BarChartHtml(awayTeam), ContentMode.HTML);

		horizontalCharts.addComponent(htmlLabelHomePieChart);
		horizontalCharts.addComponent(htmlLabelAwayPieChart);
		horizontalCharts.addComponent(htmlLabelHomeBarChart);
		horizontalCharts.addComponent(htmlLabelAwayBarChart);

		JavaScript.getCurrent().execute(chart.PieChartScript(mapHomeTeamPointsPerPositionList,homeTeam,"Points Per Position"));
		JavaScript.getCurrent().execute(chart.PieChartScript(mapAwayTeamPointsPerPositionList,awayTeam,"Points Per Position"));
		JavaScript.getCurrent().execute(chart.BarChartScript(mapHomeTeamUsageRatingPerPositionList, homeTeam,"Usage Rate Per Position"));
		JavaScript.getCurrent().execute(chart.BarChartScript(mapAwayTeamUsageRatingPerPositionList, awayTeam,"Usage Rate Per Position"));

		addComponent(horizontalCharts);

		Grid<Playerstatspergame> grid = new Grid<Playerstatspergame>();

		HorizontalLayout horizontal = new HorizontalLayout();

		Button awayTeamButton = new Button("Away");
		Button homeTeamButton = new Button("Home");
		homeTeamButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
		awayTeamButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
		horizontal.addComponent(awayTeamButton);
		horizontal.addComponent(homeTeamButton);
		horizontal.setDefaultComponentAlignment(Alignment.TOP_CENTER);
		addComponent(horizontal);

		awayTeamButton.addClickListener(playerstatsList -> {

			grid.removeAllColumns();
			ArrayList<Playerstatspergame> playerstatspergameList = PlayerstatspergameServiceStaticInitializer
					.findAllPlayerStatsPerGameByGameIdAndTeam(gameid, awayTeam);

			grid.setItems(playerstatspergameList);

			grid.addColumn(Playerstatspergame::getTeamid).setCaption("TeamID");
			grid.addColumn(Playerstatspergame -> {
				return Playerstatspergame.getPlayer().getFirstname();
			}).setCaption("First Name");
			grid.addColumn(Playerstatspergame -> {
				return Playerstatspergame.getPlayer().getLastname();
			}).setCaption("Last Name");
			grid.addColumn(Playerstatspergame::getOpponentid).setCaption("opponentid");
			grid.addColumn(Playerstatspergame::getSeasontype).setCaption("seasontype");
			grid.addColumn(Playerstatspergame::getSeason).setCaption("season");
			grid.addColumn(Playerstatspergame::getFanduelposition).setCaption("fanduelposition");
			grid.addColumn(Playerstatspergame::getDraftkingsposition).setCaption("draftkingsposition");
			grid.addColumn(Playerstatspergame::getStarted).setCaption("started");
			grid.addColumn(Playerstatspergame::getOpponentrank).setCaption("opponentrank");
			grid.addColumn(Playerstatspergame::getOpponentpositionrank).setCaption("opponentpositionrank");
			grid.addColumn(Playerstatspergame::getGames).setCaption("games");
			grid.addColumn(Playerstatspergame::getMinutes).setCaption("minutes");
			grid.addColumn(Playerstatspergame::getSeconds).setCaption("seconds");
			grid.addColumn(Playerstatspergame::getFieldgoalsmade).setCaption("fieldgoalsmade");
			grid.addColumn(Playerstatspergame::getFieldgoalsattempted).setCaption("fieldgoalsattempted");
			grid.addColumn(Playerstatspergame::getFieldgoalspercentage).setCaption("fieldgoalspercentage");
			grid.addColumn(Playerstatspergame::getEffectivefieldgoalspercentage)
					.setCaption("effectivefieldgoalspercentage");
			grid.addColumn(Playerstatspergame::getTwopointersmade).setCaption("twopointersmade");
			grid.addColumn(Playerstatspergame::getTwopointersattempted).setCaption("twopointersattempted");
			grid.addColumn(Playerstatspergame::getTwopointerspercentage).setCaption("twopointerspercentage");
			grid.addColumn(Playerstatspergame::getThreepointersmade).setCaption("threepointersmade");
			grid.addColumn(Playerstatspergame::getThreepointersattempted).setCaption("threepointersattempted");
			grid.addColumn(Playerstatspergame::getThreepointerspercentage).setCaption("threepointerspercentage");
			grid.addColumn(Playerstatspergame::getFreethrowssmade).setCaption("freethrowssmade");
			grid.addColumn(Playerstatspergame::getFreethrowsattempted).setCaption("freethrowsattempted");

			grid.setSizeFull();
			grid.setFrozenColumnCount(3);
			addComponent(grid);
		});

		homeTeamButton.addClickListener(playerstatsList -> {
			ArrayList<Playerstatspergame> playerstatspergameList = PlayerstatspergameServiceStaticInitializer
					.findAllPlayerStatsPerGameByGameIdAndTeam(gameid, homeTeam);

			grid.setItems(playerstatspergameList);

			grid.addColumn(Playerstatspergame::getTeamid).setCaption("TeamID");
			grid.addColumn(Playerstatspergame -> {
				return Playerstatspergame.getPlayer().getFirstname();
			}).setCaption("First Name");
			grid.addColumn(Playerstatspergame -> {
				return Playerstatspergame.getPlayer().getLastname();
			}).setCaption("Last Name");
			grid.addColumn(Playerstatspergame::getOpponentid).setCaption("opponentid");
			grid.addColumn(Playerstatspergame::getSeasontype).setCaption("seasontype");
			grid.addColumn(Playerstatspergame::getSeason).setCaption("season");
			grid.addColumn(Playerstatspergame::getFanduelposition).setCaption("fanduelposition");
			grid.addColumn(Playerstatspergame::getDraftkingsposition).setCaption("draftkingsposition");
			grid.addColumn(Playerstatspergame::getStarted).setCaption("started");
			grid.addColumn(Playerstatspergame::getOpponentrank).setCaption("opponentrank");
			grid.addColumn(Playerstatspergame::getOpponentpositionrank).setCaption("opponentpositionrank");
			grid.addColumn(Playerstatspergame::getGames).setCaption("games");
			grid.addColumn(Playerstatspergame::getMinutes).setCaption("minutes");
			grid.addColumn(Playerstatspergame::getSeconds).setCaption("seconds");
			grid.addColumn(Playerstatspergame::getFieldgoalsmade).setCaption("fieldgoalsmade");
			grid.addColumn(Playerstatspergame::getFieldgoalsattempted).setCaption("fieldgoalsattempted");
			grid.addColumn(Playerstatspergame::getFieldgoalspercentage).setCaption("fieldgoalspercentage");
			grid.addColumn(Playerstatspergame::getEffectivefieldgoalspercentage)
					.setCaption("effectivefieldgoalspercentage");
			grid.addColumn(Playerstatspergame::getTwopointersmade).setCaption("twopointersmade");
			grid.addColumn(Playerstatspergame::getTwopointersattempted).setCaption("twopointersattempted");
			grid.addColumn(Playerstatspergame::getTwopointerspercentage).setCaption("twopointerspercentage");
			grid.addColumn(Playerstatspergame::getThreepointersmade).setCaption("threepointersmade");
			grid.addColumn(Playerstatspergame::getThreepointersattempted).setCaption("threepointersattempted");
			grid.addColumn(Playerstatspergame::getThreepointerspercentage).setCaption("threepointerspercentage");
			grid.addColumn(Playerstatspergame::getFreethrowssmade).setCaption("freethrowssmade");
			grid.addColumn(Playerstatspergame::getFreethrowsattempted).setCaption("freethrowsattempted");

			grid.setSizeFull();
			grid.setFrozenColumnCount(3);
			addComponent(grid);
		});

		awayTeamButton.click();
	}

}
