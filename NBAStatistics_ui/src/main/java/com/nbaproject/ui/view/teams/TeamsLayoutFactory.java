package com.nbaproject.ui.view.teams;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nbaproject.entities.Team;
import com.nbaproject.service.team.TeamService;
import com.nbaproject.ui.view.mainview.MainView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
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
		
		GridLayout gridLayoutTeams = new GridLayout(3,6);
		
		Button btnWAS = new Button();
		Button btnCHA = new Button();
		Button btnATL = new Button();
		Button btnMIA = new Button();
		Button btnORL = new Button();
		Button btnNY = new Button();
		Button btnPHI = new Button();
		Button btnBKN = new Button();
		Button btnBOS = new Button();
		Button btnTOR = new Button();
		Button btnCHI = new Button();
		Button btnCLE = new Button();
		Button btnIND = new Button();
		Button btnDET = new Button();
		Button btnMIL = new Button();

		btnWAS.setStyleName("btnWAS");
		btnCHA.setStyleName("btnCHA");
		btnATL.setStyleName("btnATL");
		btnMIA.setStyleName("btnMIA");
		btnORL.setStyleName("btnORL");
		btnNY.setStyleName("btnNY");
		btnPHI.setStyleName("btnPHI");
		btnBKN.setStyleName("btnBKN");
		btnBOS.setStyleName("btnBOS");
		btnTOR.setStyleName("btnTOR");
		btnCHI.setStyleName("btnCHI");
		btnCLE.setStyleName("btnCLE");
		btnIND.setStyleName("btnIND");
		btnDET.setStyleName("btnDET");
		btnMIL.setStyleName("btnMIL");
		
		btnWAS.setWidth("120px");
		btnCHA.setWidth("120px");
		btnATL.setWidth("120px");
		btnMIA.setWidth("120px");
		btnORL.setWidth("120px");
		btnNY.setWidth("120px");
		btnPHI.setWidth("120px");
		btnBKN.setWidth("120px");
		btnBOS.setWidth("120px");
		btnTOR.setWidth("120px");
		btnCHI.setWidth("120px");
		btnCLE.setWidth("120px");
		btnIND.setWidth("120px");
		btnDET.setWidth("120px");
		btnMIL.setWidth("120px");
		
		btnWAS.setHeight("120px");
		btnCHA.setHeight("120px");
		btnATL.setHeight("120px");
		btnMIA.setHeight("120px");
		btnORL.setHeight("120px");
		btnNY.setHeight("120px");
		btnPHI.setHeight("120px");
		btnBKN.setHeight("120px");
		btnBOS.setHeight("120px");
		btnTOR.setHeight("120px");
		btnCHI.setHeight("120px");
		btnCLE.setHeight("120px");
		btnIND.setHeight("120px");
		btnDET.setHeight("120px");
		btnMIL.setHeight("120px");
		
		gridLayoutTeams.addComponent(btnWAS,0,1,0,1);
		gridLayoutTeams.addComponent(btnCHA,1,1,1,1);
		gridLayoutTeams.addComponent(btnATL,2,1,2,1);
		gridLayoutTeams.addComponent(btnMIA,0,2,0,2);
		gridLayoutTeams.addComponent(btnORL,1,2,1,2);
		gridLayoutTeams.addComponent(btnNY,2,2,2,2);
		gridLayoutTeams.addComponent(btnPHI,0,3,0,3);
		gridLayoutTeams.addComponent(btnBKN,1,3,1,3);
		gridLayoutTeams.addComponent(btnBOS,2,3,2,3);
		gridLayoutTeams.addComponent(btnTOR,0,4,0,4);
		gridLayoutTeams.addComponent(btnCHI,1,4,1,4);
		gridLayoutTeams.addComponent(btnCLE,2,4,2,4);
		gridLayoutTeams.addComponent(btnIND,0,5,0,5);
		gridLayoutTeams.addComponent(btnDET,1,5,1,5);
		gridLayoutTeams.addComponent(btnMIL,2,5,2,5);

		gridLayoutTeams.setSizeFull();
		addComponent(gridLayoutTeams);

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
