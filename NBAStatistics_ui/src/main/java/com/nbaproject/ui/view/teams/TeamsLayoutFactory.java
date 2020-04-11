package com.nbaproject.ui.view.teams;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nbaproject.entities.Team;
import com.nbaproject.service.team.TeamService;
import com.nbaproject.ui.view.mainview.MainView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;


@SpringView(name = TeamsLayoutFactory.NAME, ui = MainView.class)
public class TeamsLayoutFactory extends VerticalLayout implements View {

	public static final String NAME = "teams";
	private Button saveButton;

	@Autowired
	private TeamService teamService;

	public void enter(ViewChangeEvent event) {

		List<Team> teamList = new ArrayList<Team>();

//		try {
//			teamList = JsonNBATeams.JsonNBATeamsRequest();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		teamList=teamService.findAll();

		Grid<Team> grid = new Grid<Team>();
		grid.setItems(teamList);
		grid.addColumn(Team::getTeamid).setCaption("TeamID");
		grid.addComponentColumn(Team -> {
			Image img = new Image();
			img.setSource( new ExternalResource(Team.getWikipedialogourl()));
			img.setHeight("30px");
			img.setWidth("30px");
			return img;
		}).setCaption("Photo");
		grid.addColumn(Team::getTeamkey).setCaption("Key");
		grid.addColumn(Team::getTeamname).setCaption("Name");
		grid.addColumn(Team::getCity).setCaption("City");
		grid.addColumn(Team::getConference).setCaption("Conference");
		grid.addColumn(Team::getDivision).setCaption("Division");
//		grid.addColumn(Team::getWikipedialogourl).setCaption("WikipediaLogoUrl");
		
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
