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
		ArrayList<Playerprop> playerpropReboundsList = playerpropService.findPlayerpropsByPlayerIdAndDescription(playerid,
				9);
		ArrayList<Playerprop> playerpropAssistsList = playerpropService.findPlayerpropsByPlayerIdAndDescription(playerid,
				7);
		ArrayList<Playerprop> playerpropPointsList_LastX = playerpropService
				.findLastPlayerpropsByPlayerIdAndDescription(playerid, 1, 20);
		ArrayList<Playerprop> playerpropReboundsList_LastX = playerpropService
				.findLastPlayerpropsByPlayerIdAndDescription(playerid, 9, 20);
		ArrayList<Playerprop> playerpropAssistsList_LastX = playerpropService
				.findLastPlayerpropsByPlayerIdAndDescription(playerid, 7, 20);
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
		GridLayout gridLineChartPlayerPointsStats = new GridLayout(12, 1);
		Label htmlLabelPointsLineChart = new Label(playerStatsChart.LineChartHtml("Points"), ContentMode.HTML);

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
		
		/**** GRIDLAYOUT LINE CHART (REBOUNDS) ****/
		GridLayout gridLineChartPlayerReboundsStats = new GridLayout(12, 1);
		Label htmlLabelReboundsLineChart = new Label(playerStatsChart.LineChartHtml("Rebounds"), ContentMode.HTML);

		Grid<Playerstatspergame> gridLayoutPlayerStatsRebounds = new Grid<Playerstatspergame>();
		gridLayoutPlayerStatsRebounds.setItems(playerstatspergameList);

		gridLayoutPlayerStatsRebounds.addColumn(Playerstatspergame -> {
			int oponentid = Playerstatspergame.getOpponentid();
			Team oponentTeam = teamService.findTeamById(oponentid);
			return oponentTeam.getTeamkey();
		}).setCaption("Opponent Name").setStyleGenerator(Playerstatspergame -> {
			for (int i = 0; i < playerpropReboundsList.size(); i++) {
				if (Playerstatspergame.getBoxscore().getGameid() == playerpropReboundsList.get(i).getId().getGameid()
						&& Playerstatspergame.getRebounds() > playerpropReboundsList.get(i).getOverunder()) {
					return "green";
				} else if (Playerstatspergame.getBoxscore().getGameid() == playerpropReboundsList.get(i).getId()
						.getGameid() && Playerstatspergame.getRebounds() < playerpropReboundsList.get(i).getOverunder()) {
					return "red";
				} else if (Playerstatspergame.getBoxscore().getGameid() == playerpropReboundsList.get(i).getId()
						.getGameid() && Playerstatspergame.getRebounds() == playerpropReboundsList.get(i).getOverunder()) {
					return "grey";
				}
			}
			return null;
		}).setExpandRatio(1).setWidth(114.5);
		gridLayoutPlayerStatsRebounds.addColumn(Playerstatspergame::getRebounds).setCaption("Rebounds")
				.setStyleGenerator(Playerstatspergame -> {
					for (int i = 0; i < playerpropReboundsList.size(); i++) {
						if (Playerstatspergame.getBoxscore().getGameid() == playerpropReboundsList.get(i).getId()
								.getGameid()
								&& Playerstatspergame.getRebounds() > playerpropReboundsList.get(i).getOverunder()) {
							return "green";
						} else if (Playerstatspergame.getBoxscore().getGameid() == playerpropReboundsList.get(i).getId()
								.getGameid()
								&& Playerstatspergame.getRebounds() < playerpropReboundsList.get(i).getOverunder()) {
							return "red";
						} else if (Playerstatspergame.getBoxscore().getGameid() == playerpropReboundsList.get(i).getId()
								.getGameid()
								&& Playerstatspergame.getRebounds() == playerpropReboundsList.get(i).getOverunder()) {
							return "grey";
						}
					}
					return null;
				}).setExpandRatio(1).setWidth(114.5);
		gridLayoutPlayerStatsRebounds.addColumn(Playerstatspergame -> {
			for (int i = 0; i < playerpropReboundsList.size(); i++) {
				if (Playerstatspergame.getBoxscore().getGameid() == playerpropReboundsList.get(i).getId().getGameid()) {
					return playerpropReboundsList.get(i).getOverunder();
				}
			}
			return 0;
		}).setCaption("OverUnder Limit").setStyleGenerator(Playerstatspergame -> {
			for (int i = 0; i < playerpropReboundsList.size(); i++) {
				if (Playerstatspergame.getBoxscore().getGameid() == playerpropReboundsList.get(i).getId().getGameid()
						&& Playerstatspergame.getRebounds() > playerpropReboundsList.get(i).getOverunder()) {
					return "green";
				} else if (Playerstatspergame.getBoxscore().getGameid() == playerpropReboundsList.get(i).getId()
						.getGameid() && Playerstatspergame.getRebounds() < playerpropReboundsList.get(i).getOverunder()) {
					return "red";
				} else if (Playerstatspergame.getBoxscore().getGameid() == playerpropReboundsList.get(i).getId()
						.getGameid() && Playerstatspergame.getRebounds() == playerpropReboundsList.get(i).getOverunder()) {
					return "grey";
				}
			}
			return null;
		}).setExpandRatio(1).setWidth(114.5);
		gridLayoutPlayerStatsRebounds.addColumn(Playerstatspergame::getOpponentrank).setCaption("Oponent Rank")
				.setStyleGenerator(Playerstatspergame -> {
					for (int i = 0; i < playerpropReboundsList.size(); i++) {
						if (Playerstatspergame.getBoxscore().getGameid() == playerpropReboundsList.get(i).getId()
								.getGameid()
								&& Playerstatspergame.getRebounds() > playerpropReboundsList.get(i).getOverunder()) {
							return "green";
						} else if (Playerstatspergame.getBoxscore().getGameid() == playerpropReboundsList.get(i).getId()
								.getGameid()
								&& Playerstatspergame.getRebounds() < playerpropReboundsList.get(i).getOverunder()) {
							return "red";
						} else if (Playerstatspergame.getBoxscore().getGameid() == playerpropReboundsList.get(i).getId()
								.getGameid()
								&& Playerstatspergame.getRebounds() == playerpropReboundsList.get(i).getOverunder()) {
							return "grey";
						}
					}
					return null;
				}).setExpandRatio(1).setWidth(114.5);
		gridLayoutPlayerStatsRebounds.addColumn(Playerstatspergame::getOpponentpositionrank)
				.setCaption("Oponent PositionRank").setStyleGenerator(Playerstatspergame -> {
					for (int i = 0; i < playerpropReboundsList.size(); i++) {
						if (Playerstatspergame.getBoxscore().getGameid() == playerpropReboundsList.get(i).getId()
								.getGameid()
								&& Playerstatspergame.getRebounds() > playerpropReboundsList.get(i).getOverunder()) {
							return "green";
						} else if (Playerstatspergame.getBoxscore().getGameid() == playerpropReboundsList.get(i).getId()
								.getGameid()
								&& Playerstatspergame.getRebounds() < playerpropReboundsList.get(i).getOverunder()) {
							return "red";
						} else if (Playerstatspergame.getBoxscore().getGameid() == playerpropReboundsList.get(i).getId()
								.getGameid()
								&& Playerstatspergame.getRebounds() == playerpropReboundsList.get(i).getOverunder()) {
							return "grey";
						}
					}
					return null;
				}).setExpandRatio(1).setWidth(114.5);

		/**** GRIDLAYOUT LINE CHART (ASSISTS) ****/
		GridLayout gridLineChartPlayerAssistsStats = new GridLayout(12, 1);
		Label htmlLabelAssistsLineChart = new Label(playerStatsChart.LineChartHtml("Assists"), ContentMode.HTML);

		Grid<Playerstatspergame> gridLayoutPlayerStatsAssists = new Grid<Playerstatspergame>();
		gridLayoutPlayerStatsAssists.setItems(playerstatspergameList);

		gridLayoutPlayerStatsAssists.addColumn(Playerstatspergame -> {
			int oponentid = Playerstatspergame.getOpponentid();
			Team oponentTeam = teamService.findTeamById(oponentid);
			return oponentTeam.getTeamkey();
		}).setCaption("Opponent Name").setStyleGenerator(Playerstatspergame -> {
			for (int i = 0; i < playerpropAssistsList.size(); i++) {
				if (Playerstatspergame.getBoxscore().getGameid() == playerpropAssistsList.get(i).getId().getGameid()
						&& Playerstatspergame.getAssists() > playerpropAssistsList.get(i).getOverunder()) {
					return "green";
				} else if (Playerstatspergame.getBoxscore().getGameid() == playerpropAssistsList.get(i).getId()
						.getGameid() && Playerstatspergame.getAssists() < playerpropAssistsList.get(i).getOverunder()) {
					return "red";
				} else if (Playerstatspergame.getBoxscore().getGameid() == playerpropAssistsList.get(i).getId()
						.getGameid() && Playerstatspergame.getAssists() == playerpropAssistsList.get(i).getOverunder()) {
					return "grey";
				}
			}
			return null;
		}).setExpandRatio(1).setWidth(114.5);
		gridLayoutPlayerStatsAssists.addColumn(Playerstatspergame::getAssists).setCaption("Assists")
				.setStyleGenerator(Playerstatspergame -> {
					for (int i = 0; i < playerpropAssistsList.size(); i++) {
						if (Playerstatspergame.getBoxscore().getGameid() == playerpropAssistsList.get(i).getId()
								.getGameid()
								&& Playerstatspergame.getAssists() > playerpropAssistsList.get(i).getOverunder()) {
							return "green";
						} else if (Playerstatspergame.getBoxscore().getGameid() == playerpropAssistsList.get(i).getId()
								.getGameid()
								&& Playerstatspergame.getAssists() < playerpropAssistsList.get(i).getOverunder()) {
							return "red";
						} else if (Playerstatspergame.getBoxscore().getGameid() == playerpropAssistsList.get(i).getId()
								.getGameid()
								&& Playerstatspergame.getAssists() == playerpropAssistsList.get(i).getOverunder()) {
							return "grey";
						}
					}
					return null;
				}).setExpandRatio(1).setWidth(114.5);
		gridLayoutPlayerStatsAssists.addColumn(Playerstatspergame -> {
			for (int i = 0; i < playerpropAssistsList.size(); i++) {
				if (Playerstatspergame.getBoxscore().getGameid() == playerpropAssistsList.get(i).getId().getGameid()) {
					return playerpropAssistsList.get(i).getOverunder();
				}
			}
			return 0;
		}).setCaption("OverUnder Limit").setStyleGenerator(Playerstatspergame -> {
			for (int i = 0; i < playerpropAssistsList.size(); i++) {
				if (Playerstatspergame.getBoxscore().getGameid() == playerpropAssistsList.get(i).getId().getGameid()
						&& Playerstatspergame.getAssists() > playerpropAssistsList.get(i).getOverunder()) {
					return "green";
				} else if (Playerstatspergame.getBoxscore().getGameid() == playerpropAssistsList.get(i).getId()
						.getGameid() && Playerstatspergame.getAssists() < playerpropAssistsList.get(i).getOverunder()) {
					return "red";
				} else if (Playerstatspergame.getBoxscore().getGameid() == playerpropAssistsList.get(i).getId()
						.getGameid() && Playerstatspergame.getAssists() == playerpropAssistsList.get(i).getOverunder()) {
					return "grey";
				}
			}
			return null;
		}).setExpandRatio(1).setWidth(114.5);
		gridLayoutPlayerStatsAssists.addColumn(Playerstatspergame::getOpponentrank).setCaption("Oponent Rank")
				.setStyleGenerator(Playerstatspergame -> {
					for (int i = 0; i < playerpropAssistsList.size(); i++) {
						if (Playerstatspergame.getBoxscore().getGameid() == playerpropAssistsList.get(i).getId()
								.getGameid()
								&& Playerstatspergame.getAssists() > playerpropAssistsList.get(i).getOverunder()) {
							return "green";
						} else if (Playerstatspergame.getBoxscore().getGameid() == playerpropAssistsList.get(i).getId()
								.getGameid()
								&& Playerstatspergame.getAssists() < playerpropAssistsList.get(i).getOverunder()) {
							return "red";
						} else if (Playerstatspergame.getBoxscore().getGameid() == playerpropAssistsList.get(i).getId()
								.getGameid()
								&& Playerstatspergame.getAssists() == playerpropAssistsList.get(i).getOverunder()) {
							return "grey";
						}
					}
					return null;
				}).setExpandRatio(1).setWidth(114.5);
		gridLayoutPlayerStatsAssists.addColumn(Playerstatspergame::getOpponentpositionrank)
				.setCaption("Oponent PositionRank").setStyleGenerator(Playerstatspergame -> {
					for (int i = 0; i < playerpropAssistsList.size(); i++) {
						if (Playerstatspergame.getBoxscore().getGameid() == playerpropAssistsList.get(i).getId()
								.getGameid()
								&& Playerstatspergame.getAssists() > playerpropAssistsList.get(i).getOverunder()) {
							return "green";
						} else if (Playerstatspergame.getBoxscore().getGameid() == playerpropAssistsList.get(i).getId()
								.getGameid()
								&& Playerstatspergame.getAssists() < playerpropAssistsList.get(i).getOverunder()) {
							return "red";
						} else if (Playerstatspergame.getBoxscore().getGameid() == playerpropAssistsList.get(i).getId()
								.getGameid()
								&& Playerstatspergame.getAssists() == playerpropAssistsList.get(i).getOverunder()) {
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

		/***** Points *****/
		htmlLabelPointsLineChart.setSizeFull();
		gridLayoutPlayerStatsPoints.setSizeFull();
		gridLayoutPlayerStatsPoints.setHeaderRowHeight(38);
		gridLayoutPlayerStatsPoints.setCaptionAsHtml(true);
		gridLayoutPlayerStatsPoints.getHeaderRow(0).setStyleName("GridHeaderCategoryPlayerStats");
		gridLayoutPlayerStatsPoints.setStyleGenerator(Playerstatspergame -> {
			return "GridPlayerStatsCategory";
		});
		gridLayoutPlayerStatsPoints.setHeightMode(HeightMode.CSS);
		gridLayoutPlayerStatsPoints.setSelectionMode(SelectionMode.NONE);
		gridLineChartPlayerPointsStats.setHeight("300px");
		gridLineChartPlayerPointsStats.setWidth("100%");
		gridLineChartPlayerPointsStats.addStyleName("GridLineChartPlayerstats");
		gridLineChartPlayerPointsStats.addComponent(htmlLabelPointsLineChart, 0, 0, 5, 0);
		gridLineChartPlayerPointsStats.addComponent(gridLayoutPlayerStatsPoints, 6, 0, 11, 0);
		gridLineChartPlayerPointsStats.setComponentAlignment(htmlLabelPointsLineChart, Alignment.MIDDLE_CENTER);
		
		/***** Rebounds *****/
		htmlLabelReboundsLineChart.setSizeFull();
		gridLayoutPlayerStatsRebounds.setSizeFull();
		gridLayoutPlayerStatsRebounds.setHeaderRowHeight(38);
		gridLayoutPlayerStatsRebounds.setCaptionAsHtml(true);
		gridLayoutPlayerStatsRebounds.getHeaderRow(0).setStyleName("GridHeaderCategoryPlayerStats");
		gridLayoutPlayerStatsRebounds.setStyleGenerator(Playerstatspergame -> {
			return "GridPlayerStatsCategory";
		});
		gridLayoutPlayerStatsRebounds.setHeightMode(HeightMode.CSS);
		gridLayoutPlayerStatsRebounds.setSelectionMode(SelectionMode.NONE);
		gridLineChartPlayerReboundsStats.setHeight("300px");
		gridLineChartPlayerReboundsStats.setWidth("100%");
		gridLineChartPlayerReboundsStats.addStyleName("GridLineChartPlayerstats");
		gridLineChartPlayerReboundsStats.addComponent(htmlLabelReboundsLineChart, 0, 0, 5, 0);
		gridLineChartPlayerReboundsStats.addComponent(gridLayoutPlayerStatsRebounds, 6, 0, 11, 0);
		gridLineChartPlayerReboundsStats.setComponentAlignment(htmlLabelReboundsLineChart, Alignment.MIDDLE_CENTER);
		
		/***** Assists *****/
		htmlLabelAssistsLineChart.setSizeFull();
		gridLayoutPlayerStatsAssists.setSizeFull();
		gridLayoutPlayerStatsAssists.setHeaderRowHeight(38);
		gridLayoutPlayerStatsAssists.setCaptionAsHtml(true);
		gridLayoutPlayerStatsAssists.getHeaderRow(0).setStyleName("GridHeaderCategoryPlayerStats");
		gridLayoutPlayerStatsAssists.setStyleGenerator(Playerstatspergame -> {
			return "GridPlayerStatsCategory";
		});
		gridLayoutPlayerStatsAssists.setHeightMode(HeightMode.CSS);
		gridLayoutPlayerStatsAssists.setSelectionMode(SelectionMode.NONE);
		gridLineChartPlayerAssistsStats.setHeight("300px");
		gridLineChartPlayerAssistsStats.setWidth("100%");
		gridLineChartPlayerAssistsStats.addStyleName("GridLineChartPlayerstats");
		gridLineChartPlayerAssistsStats.addComponent(htmlLabelAssistsLineChart, 0, 0, 5, 0);
		gridLineChartPlayerAssistsStats.addComponent(gridLayoutPlayerStatsAssists, 6, 0, 11, 0);
		gridLineChartPlayerAssistsStats.setComponentAlignment(htmlLabelAssistsLineChart, Alignment.MIDDLE_CENTER);

		addComponents(gridHeaderPlayerStats, gridLineChartPlayerPointsStats,gridLineChartPlayerReboundsStats,gridLineChartPlayerAssistsStats);

		/***** JAVASCRIPT FOR CHARTS ******/
		JavaScript.getCurrent().execute(
				playerStatsChart.LineChartScript(playerpropPointsList_LastX, playerstatspergameList, playerid, teamid,"Points"));
		JavaScript.getCurrent().execute(
				playerStatsChart.LineChartScript(playerpropReboundsList_LastX, playerstatspergameList, playerid, teamid,"Rebounds"));
		JavaScript.getCurrent().execute(
				playerStatsChart.LineChartScript(playerpropAssistsList_LastX, playerstatspergameList, playerid, teamid,"Assists"));
		/***************************************/
	}
}
