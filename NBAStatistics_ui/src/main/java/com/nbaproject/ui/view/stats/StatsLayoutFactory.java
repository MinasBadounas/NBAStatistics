package com.nbaproject.ui.view.stats;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.nbaproject.entities.Playerstatspergame;
import com.nbaproject.service.boxscore.BoxscoreService;
import com.nbaproject.ui.view.mainview.MainView;
import com.nbaproject.utils.staticInitializer.PlayerstatspergameServiceStaticInitializer;
import com.nbaproject.utils.tools.EditPath;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = StatsLayoutFactory.NAME, ui = MainView.class)
public class StatsLayoutFactory extends VerticalLayout implements View {

	public static final String NAME = "stats";

	@Autowired
	BoxscoreService boxscoreService;

	public void enter(ViewChangeEvent event) {

		Grid<Playerstatspergame> grid = new Grid<Playerstatspergame>();
		int gameid = Integer.parseInt(event.getParameters());

		HorizontalLayout horizontal = new HorizontalLayout();

		Button awayButton = new Button("Away");
		Button homeButton = new Button("Home");

		horizontal.addComponent(awayButton);
		horizontal.addComponent(homeButton);
		addComponent(horizontal);

		int awayTeam = boxscoreService.findBoxscorebyGameId(gameid).getTeam1().getTeamid();
		int homeTeam = boxscoreService.findBoxscorebyGameId(gameid).getTeam2().getTeamid();

		awayButton.addClickListener(playerstatsList -> {
			
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

		homeButton.addClickListener(playerstatsList -> {
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

		awayButton.click();
	}

}
