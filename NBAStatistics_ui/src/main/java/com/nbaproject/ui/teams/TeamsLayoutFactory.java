package com.nbaproject.ui.teams;

import java.io.IOException;
import java.util.ArrayList;
import com.nbaproject.entities.Team;
import com.nbaproject.ui.common.MenuView;
import com.nbaproject.utils.JsonNBATeams;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = TeamsLayoutFactory.NAME, ui = MenuView.class)
public class TeamsLayoutFactory extends VerticalLayout implements View {

	public static final String NAME = "teams";

	public void enter(ViewChangeEvent event) {

		ArrayList<Team> teamList = new ArrayList<Team>();

		try {
			teamList = JsonNBATeams.JsonNBATeamsRequest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Grid<Team> grid = new Grid<Team>();
		grid.setItems(teamList);
		grid.addColumn(Team::getTeamid).setCaption("TeamID");
		grid.addColumn(Team::getTeamkey).setCaption("Key");
		grid.addColumn(Team::getTeamname).setCaption("Name");
		grid.addColumn(Team::getCity).setCaption("City");
		grid.addColumn(Team::getConference).setCaption("Conference");
		grid.addColumn(Team::getDivision).setCaption("Division");
		grid.addColumn(Team::getWikipedialogourl).setCaption("WikipediaLogoUrl");

		grid.setSizeFull();

		addComponent(grid);

	}

}
