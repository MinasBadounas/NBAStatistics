package com.nbaproject.ui.view.playerstats;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.nbaproject.entities.Opponentplayerstatspergame;
import com.nbaproject.entities.Player;
import com.nbaproject.entities.Playerprop;
import com.nbaproject.entities.Playerseasonstat;
import com.nbaproject.entities.Playerstatspergame;
import com.nbaproject.entities.Team;
import com.nbaproject.service.opponentplayerstatspergame.OpponentplayerstatspergameService;
import com.nbaproject.service.player.PlayerService;
import com.nbaproject.service.playerprop.PlayerpropService;
import com.nbaproject.service.playerseasonstats.PlayerseasonstatsService;
import com.nbaproject.service.playerstatspergame.PlayerstatspergameService;
import com.nbaproject.service.team.TeamService;
import com.nbaproject.ui.chart.PlayerStatsChart;
import com.nbaproject.ui.view.mainview.MainView;
import com.nbaproject.utils.tools.RoundDoubles;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
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
	private TeamService teamService;

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
		ArrayList<Playerprop> playerpropPointsList_LastX = playerpropService
				.findLastPlayerpropsByPlayerIdAndDescription(playerid, 1, 20);
		ArrayList<Opponentplayerstatspergame> opponentplayerstatspergameList = opponentplayerstatpergameService
				.findOpponentplayerstatspergameByPlayerId(playerid);
		ArrayList<Playerstatspergame> playerstatspergameList = playerstatspergameService
				.findPlayerStatsPerGameByPlayerId(playerid);

		int teamid = playerpropPointsList.get(0).getPlayer().getTeam().getTeamid();

		GridLayout gridHeaderPlayerStats = new GridLayout(12, 2);

		/**** GRIDLAYOUT HEADER ****/
		Label playerHeader = new Label(
				"<img class=\"PlayerStatsImage\" src=\"" + player.getPhotoUrl() + "\">"
						+ "<p class=\"PlayerStatsName\">" + player.getFirstname() + "   " + player.getLastname()
						+ "</p>" + "<p class=\"PlayerStatsDescription\">" + player.getPositionCategory() + "  |  "
						+ player.getTeam().getTeamname() + " | " + player.getTeam().getDivision() + "</p>",
				ContentMode.HTML);

		Label playerSeasonStatsTable = new Label("<table class=\"AverageSeasonStats\" >\r\n" + "	<tr>\r\n"
				+ "	  <th align=\"center\">MPG</th>\r\n" + "	  <th align=\"center\">PPG</th>\r\n"
				+ "	  <th align=\"center\">RPG</th>\r\n" + "	  <th align=\"center\">APG</th>\r\n"
				+ "	  <th align=\"center\">Usage Rate</th>\r\n" + "	  <th align=\"center\">+/-</th>\r\n"
				+ "	</tr>\r\n" + "	<tr>\r\n" + "	  <td align=\"center\">"
				+ RoundDoubles.round((double) playerseasonstat.getMinutes() / playerseasonstat.getGames(), 2)
				+ "</td>\r\n" + "	  <td align=\"center\">"
				+ RoundDoubles.round((double) playerseasonstat.getPoints() / playerseasonstat.getGames(), 2)
				+ "</td>\r\n" + "	  <td align=\"center\">"
				+ RoundDoubles.round((double) playerseasonstat.getRebounds() / playerseasonstat.getGames(), 2)
				+ "</td>\r\n" + "	  <td align=\"center\">"
				+ RoundDoubles.round((double) playerseasonstat.getAssists() / playerseasonstat.getGames(), 2)
				+ "</td>\r\n" + "	  <td align=\"center\">"
				+ RoundDoubles.round((double) playerseasonstat.getUsageratepercentage(), 2) + "%" + "</td>\r\n"
				+ "	  <td align=\"center\">"
				+ RoundDoubles.round((double) playerseasonstat.getPlusminus() / playerseasonstat.getGames(), 2)
				+ "</td>\r\n" + "	</tr>\r\n" + "  </table> ", ContentMode.HTML);

		Label playerHeaderTeamImage = new Label(
				"<img class=\"PlayerStatsTeamImage\" src=\"" + player.getTeam().getWikipedialogourl() + "\">",
				ContentMode.HTML);

		/**** GRIDLAYOUT LINE CHART (POINTS) ****/
		GridLayout gridLineChartPlayerStats = new GridLayout(12, 1);
		Label htmlLabelHomeLineChart = new Label(playerStatsChart.LineChartHtml(), ContentMode.HTML);

		Grid<Playerstatspergame> gridLayoutPlayerStatsPoints = new Grid<Playerstatspergame>();
		gridLayoutPlayerStatsPoints.setItems(playerstatspergameList);

		gridLayoutPlayerStatsPoints.addColumn(Playerstatspergame -> {
			int oponentid = Playerstatspergame.getOpponentid();
			Team oponentTeam = teamService.findTeamById(oponentid);
			return oponentTeam.getTeamkey();
		}).setCaption("Opponent Name").setStyleGenerator(Playerstatspergame -> {
			for (int i = 0; i < playerpropPointsList.size(); i++) {
				if (Playerstatspergame.getBoxscore().getGameid() == playerpropPointsList.get(i).getId().getGameid()
						&& Playerstatspergame.getPoints() > playerpropPointsList.get(i).getOverunder()) {
					return "green";
				} else if (Playerstatspergame.getBoxscore().getGameid() == playerpropPointsList.get(i).getId()
						.getGameid() && Playerstatspergame.getPoints() < playerpropPointsList.get(i).getOverunder()) {
					return "red";
				} else if (Playerstatspergame.getBoxscore().getGameid() == playerpropPointsList.get(i).getId()
						.getGameid() && Playerstatspergame.getPoints() == playerpropPointsList.get(i).getOverunder()) {
					return "grey";
				}
			}
			return null;
		}).setExpandRatio(1).setWidth(114.5);
		gridLayoutPlayerStatsPoints.addColumn(Playerstatspergame::getPoints).setCaption("Points")
				.setStyleGenerator(Playerstatspergame -> {
					for (int i = 0; i < playerpropPointsList.size(); i++) {
						if (Playerstatspergame.getBoxscore().getGameid() == playerpropPointsList.get(i).getId()
								.getGameid()
								&& Playerstatspergame.getPoints() > playerpropPointsList.get(i).getOverunder()) {
							return "green";
						} else if (Playerstatspergame.getBoxscore().getGameid() == playerpropPointsList.get(i).getId()
								.getGameid()
								&& Playerstatspergame.getPoints() < playerpropPointsList.get(i).getOverunder()) {
							return "red";
						} else if (Playerstatspergame.getBoxscore().getGameid() == playerpropPointsList.get(i).getId()
								.getGameid()
								&& Playerstatspergame.getPoints() == playerpropPointsList.get(i).getOverunder()) {
							return "grey";
						}
					}
					return null;
				}).setExpandRatio(1).setWidth(114.5);
		gridLayoutPlayerStatsPoints.addColumn(Playerstatspergame -> {
			for (int i = 0; i < playerpropPointsList.size(); i++) {
				if (Playerstatspergame.getBoxscore().getGameid() == playerpropPointsList.get(i).getId().getGameid()) {
					return playerpropPointsList.get(i).getOverunder();
				}
			}
			return 0;
		}).setCaption("OverUnder Limit").setStyleGenerator(Playerstatspergame -> {
			for (int i = 0; i < playerpropPointsList.size(); i++) {
				if (Playerstatspergame.getBoxscore().getGameid() == playerpropPointsList.get(i).getId().getGameid()
						&& Playerstatspergame.getPoints() > playerpropPointsList.get(i).getOverunder()) {
					return "green";
				} else if (Playerstatspergame.getBoxscore().getGameid() == playerpropPointsList.get(i).getId()
						.getGameid() && Playerstatspergame.getPoints() < playerpropPointsList.get(i).getOverunder()) {
					return "red";
				} else if (Playerstatspergame.getBoxscore().getGameid() == playerpropPointsList.get(i).getId()
						.getGameid() && Playerstatspergame.getPoints() == playerpropPointsList.get(i).getOverunder()) {
					return "grey";
				}
			}
			return null;
		}).setExpandRatio(1).setWidth(114.5);
		gridLayoutPlayerStatsPoints.addColumn(Playerstatspergame::getOpponentrank).setCaption("Oponent Rank")
				.setStyleGenerator(Playerstatspergame -> {
					for (int i = 0; i < playerpropPointsList.size(); i++) {
						if (Playerstatspergame.getBoxscore().getGameid() == playerpropPointsList.get(i).getId()
								.getGameid()
								&& Playerstatspergame.getPoints() > playerpropPointsList.get(i).getOverunder()) {
							return "green";
						} else if (Playerstatspergame.getBoxscore().getGameid() == playerpropPointsList.get(i).getId()
								.getGameid()
								&& Playerstatspergame.getPoints() < playerpropPointsList.get(i).getOverunder()) {
							return "red";
						} else if (Playerstatspergame.getBoxscore().getGameid() == playerpropPointsList.get(i).getId()
								.getGameid()
								&& Playerstatspergame.getPoints() == playerpropPointsList.get(i).getOverunder()) {
							return "grey";
						}
					}
					return null;
				}).setExpandRatio(1).setWidth(114.5);
		gridLayoutPlayerStatsPoints.addColumn(Playerstatspergame::getOpponentpositionrank)
				.setCaption("Oponent PositionRank").setStyleGenerator(Playerstatspergame -> {
					for (int i = 0; i < playerpropPointsList.size(); i++) {
						if (Playerstatspergame.getBoxscore().getGameid() == playerpropPointsList.get(i).getId()
								.getGameid()
								&& Playerstatspergame.getPoints() > playerpropPointsList.get(i).getOverunder()) {
							return "green";
						} else if (Playerstatspergame.getBoxscore().getGameid() == playerpropPointsList.get(i).getId()
								.getGameid()
								&& Playerstatspergame.getPoints() < playerpropPointsList.get(i).getOverunder()) {
							return "red";
						} else if (Playerstatspergame.getBoxscore().getGameid() == playerpropPointsList.get(i).getId()
								.getGameid()
								&& Playerstatspergame.getPoints() == playerpropPointsList.get(i).getOverunder()) {
							return "grey";
						}
					}
					return null;
				}).setExpandRatio(1).setWidth(114.5);

		/***** IMPORT COMPONENTS IN GRIDLAYOUT ******/
		gridHeaderPlayerStats.setHeight("100%");
		gridHeaderPlayerStats.setWidth("100%");
		gridHeaderPlayerStats.setSpacing(false);
		gridHeaderPlayerStats.addStyleName("GridHeaderPlayerStats");
		playerHeader.setSizeFull();
		playerSeasonStatsTable.setSizeFull();
		playerHeader.addStyleName("PlayerStatsName");
		gridHeaderPlayerStats.addComponent(playerHeader, 0, 0, 6, 1);
		gridHeaderPlayerStats.addComponent(playerSeasonStatsTable, 7, 1, 11, 1);
		gridHeaderPlayerStats.addComponent(playerHeaderTeamImage, 7, 0, 11, 0);
		gridHeaderPlayerStats.setRowExpandRatio(0, 1);
		gridHeaderPlayerStats.setComponentAlignment(playerHeader, Alignment.MIDDLE_LEFT);
		gridHeaderPlayerStats.setComponentAlignment(playerSeasonStatsTable, Alignment.BOTTOM_RIGHT);
		gridHeaderPlayerStats.setComponentAlignment(playerHeaderTeamImage, Alignment.MIDDLE_CENTER);

		htmlLabelHomeLineChart.setSizeFull();
		gridLayoutPlayerStatsPoints.setSizeFull();
		gridLayoutPlayerStatsPoints.setHeaderRowHeight(38);
		gridLayoutPlayerStatsPoints.setCaptionAsHtml(true);
		gridLayoutPlayerStatsPoints.getHeaderRow(0).setStyleName("GridHeaderCategoryPlayerStats");
		gridLayoutPlayerStatsPoints.setStyleGenerator(Playerstatspergame -> {
			return "GridPlayerStatsPoints";
		});
		gridLayoutPlayerStatsPoints.setHeightMode(HeightMode.CSS);
		gridLayoutPlayerStatsPoints.setSelectionMode(SelectionMode.NONE);
		gridLineChartPlayerStats.setHeight("300px");
		gridLineChartPlayerStats.setWidth("100%");
		gridLineChartPlayerStats.addStyleName("GridLineChartPlayerstats");
		gridLineChartPlayerStats.addComponent(htmlLabelHomeLineChart, 0, 0, 5, 0);
		gridLineChartPlayerStats.addComponent(gridLayoutPlayerStatsPoints, 6, 0, 11, 0);
		gridLineChartPlayerStats.setComponentAlignment(htmlLabelHomeLineChart, Alignment.MIDDLE_CENTER);

		addComponents(gridHeaderPlayerStats, gridLineChartPlayerStats);

		/***** JAVASCRIPT FOR CHARTS ******/
		JavaScript.getCurrent().execute(
				playerStatsChart.LineChartScript(playerpropPointsList_LastX, playerstatspergameList, playerid, teamid));
		/***************************************/
	}
}
