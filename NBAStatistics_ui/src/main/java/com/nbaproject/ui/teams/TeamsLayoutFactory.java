package com.nbaproject.ui.teams;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.nbaproject.entities.Team;
import com.nbaproject.repository.team.TeamRepository;
import com.nbaproject.service.team.TeamService;
import com.nbaproject.ui.common.MenuView;
import com.nbaproject.utils.JsonNBATeams;
import com.nbaproject.utils.StaticContextInitializer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ImageRenderer;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = TeamsLayoutFactory.NAME, ui = MenuView.class)
public class TeamsLayoutFactory extends VerticalLayout implements View {

	public static final String NAME = "teams";
	private Button saveButton;

	@Autowired
	private TeamRepository teamRepository;

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
		
		grid.addComponentColumn(Team -> {
			Image img = new Image();
			img.setSource( new ExternalResource(Team.getWikipedialogourl()));
			img.setHeight("60px");
			img.setWidth("60px");
			
		});
		
		Column<Team, ExternalResource> imageColumn = grid.addColumn(
			    Team -> new ExternalResource(Team.getWikipedialogourl()),
			    new ImageRenderer<>()).setCaption("Logo");
		
		grid.setColumnReorderingAllowed(true);
		grid.setSizeFull();

		// **********ADD ALL TEAMS IN DATABASE *********//
//		saveButton = new Button("SAVE");
//		saveButton.addClickListener(new Button.ClickListener() {
//		    public void buttonClick(ClickEvent event) {			
//				for(Team team:teamList) {
//					addTeamService.saveTeam(team);
//				}
//		    }
//		});
//
//		saveButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
//		addComponent(saveButton);

		addComponent(grid);

	}

}
