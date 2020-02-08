package com.nbaproject.ui.players;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.nbaproject.entities.Player;
import com.nbaproject.entities.Team;
import com.nbaproject.service.player.PlayerService;
import com.nbaproject.ui.common.MenuView;
import com.nbaproject.utils.JsonNBAPlayers;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name=PlayersLayoutFactory.NAME, ui=MenuView.class)
public class PlayersLayoutFactory extends VerticalLayout implements View {

	public static final String NAME="players";
	
	private Button saveButton;
	
	@Autowired
	private PlayerService playerService;
	
	public void enter(ViewChangeEvent event) {
		
		ArrayList<Player> playerList = new ArrayList<Player>();

		try {
			playerList = JsonNBAPlayers.JsonNBAPlayersRequest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		Grid<Player> grid = new Grid<Player>();
		grid.setItems(playerList);
		grid.addColumn(Player::getPlayerid).setCaption("PlayerID");
		grid.addColumn(Player::getFirstname).setCaption("First Name");
		grid.addColumn(Player::getLastname).setCaption("Last Name");
		grid.addColumn(Player::getPosition).setCaption("Position");
		grid.addColumn(Player::getPositionCategory).setCaption("Position Category");
		grid.addColumn(Player::getTeam).setCaption("Team");
		grid.addColumn(Player::getPhotoUrl).setCaption("Photo URL");

		grid.setSizeFull();
		
//**********ADD ALL PLAYERS IN DATABASE *********//
//		saveButton = new Button("SAVE");
//		saveButton.addClickListener(new Button.ClickListener() {
//					
//		    public void buttonClick(ClickEvent event) {	
//		    	
//				ArrayList<Player> playerList1 = new ArrayList<Player>();
//
//				try {
//					playerList1 = JsonNBAPlayers.JsonNBAPlayersRequest();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				for(Player player:playerList1) {
//					playerService.savePlayer(player);
//				}
//		    }
//		});
//
//		saveButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
//		addComponent(saveButton);


		
		addComponent(grid);

		
	}
	
	
}
