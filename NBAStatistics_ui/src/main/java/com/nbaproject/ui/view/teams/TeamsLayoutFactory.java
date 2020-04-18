package com.nbaproject.ui.view.teams;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nbaproject.entities.Team;
import com.nbaproject.service.team.TeamService;
import com.nbaproject.ui.view.mainview.MainView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = TeamsLayoutFactory.NAME, ui = MainView.class)
public class TeamsLayoutFactory extends VerticalLayout implements View {

	public static final String NAME = "teams";
	private Button saveButton;

	@Autowired
	private TeamService teamService;

	public void enter(ViewChangeEvent event) {

		HorizontalLayout horizontalLayout = new HorizontalLayout();

		GridLayout gridLayoutEastTeams = new GridLayout(3, 6);

		List<Team> teamList = new ArrayList<Team>();
		teamList = teamService.findAll();

		int countEast = 0;

		for (int y = 1; y < 6; y++) {
			for (int i = 0; i < 3; i++) {
				Button btn = new Button();
				btn.setStyleName("btn" + teamList.get(countEast).getTeamkey());
				btn.setWidth("110px");
				btn.setHeight("110px");
				gridLayoutEastTeams.addComponent(btn, i, y, i, y);
				gridLayoutEastTeams.setComponentAlignment(btn, Alignment.MIDDLE_CENTER);
				int teamid=teamList.get(countEast).getTeamid();
				countEast++;
				
				btn.addClickListener(team->{
					UI.getCurrent().getNavigator()
					.navigateTo("teamstats/" + "teamid=" +teamid);
				});
			}
		}
		

		Label eastLabel = new Label("EAST", ContentMode.TEXT);
		gridLayoutEastTeams.addComponent(eastLabel, 0, 0, 2, 0);
		gridLayoutEastTeams.setComponentAlignment(eastLabel, Alignment.MIDDLE_CENTER);
		gridLayoutEastTeams.setSizeFull();

		GridLayout gridLayoutWestTeams = new GridLayout(3, 6);

		int countWest = 15;

		for (int y = 1; y < 6; y++) {
			for (int i = 0; i < 3; i++) {
				Button btn = new Button();
				btn.setStyleName("btn" + teamList.get(countWest).getTeamkey());
				btn.setWidth("110px");
				btn.setHeight("110px");
				gridLayoutWestTeams.addComponent(btn, i, y, i, y);
				gridLayoutWestTeams.setComponentAlignment(btn, Alignment.MIDDLE_CENTER);
				int teamid=teamList.get(countWest).getTeamid();
				countWest++;
				
				btn.addClickListener(team->{
					UI.getCurrent().getNavigator()
					.navigateTo("teamstats/" + "teamid=" +teamid);
				});
			}
		}
		
		Label westLabel = new Label("WEST", ContentMode.TEXT);
		gridLayoutWestTeams.addComponent(westLabel, 0, 0, 2, 0);
		gridLayoutWestTeams.setComponentAlignment(westLabel, Alignment.MIDDLE_CENTER);
		gridLayoutWestTeams.setSizeFull();
		

		eastLabel.setSizeFull();
		westLabel.setSizeFull();
		eastLabel.addStyleName("EastWestLabel");
		westLabel.addStyleName("EastWestLabel");
		gridLayoutWestTeams.setSizeFull();

		horizontalLayout.setSizeFull();
		horizontalLayout.addComponents(gridLayoutEastTeams, gridLayoutWestTeams);
		addComponent(horizontalLayout);
		
		

//		List<Team> teamList = new ArrayList<Team>();

//		try {
//			teamList = JsonNBATeams.JsonNBATeamsRequest();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		teamList=teamService.findAll();

//		Grid<Team> grid = new Grid<Team>();
//		grid.setItems(teamList);
//		grid.addColumn(Team::getTeamid).setCaption("TeamID");
//		grid.addComponentColumn(Team -> {
//			Image img = new Image();
//			img.setSource( new ExternalResource(Team.getWikipedialogourl()));
//			img.setHeight("30px");
//			img.setWidth("30px");
//			return img;
//		}).setCaption("Photo");
//		grid.addColumn(Team::getTeamkey).setCaption("Key");
//		grid.addColumn(Team::getTeamname).setCaption("Name");
//		grid.addColumn(Team::getCity).setCaption("City");
//		grid.addColumn(Team::getConference).setCaption("Conference");
//		grid.addColumn(Team::getDivision).setCaption("Division");
//		grid.setColumnReorderingAllowed(true);
//		grid.setSizeFull();
//
//		addComponent(grid);

	}

}

// **********ADD ALL TEAMS IN DATABASE *********//
//saveButton = new Button("SAVE");
//saveButton.addClickListener(new Button.ClickListener() {
//    public void buttonClick(ClickEvent event) {			
//		for(Team team:teamList) {
//			addTeamService.saveTeam(team);
//		}
//    }
//});
//
//saveButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
//addComponent(saveButton);
