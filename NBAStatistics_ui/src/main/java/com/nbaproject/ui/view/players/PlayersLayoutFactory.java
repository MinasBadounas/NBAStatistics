package com.nbaproject.ui.view.players;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.nbaproject.entities.Boxscore;
import com.nbaproject.entities.Player;
import com.nbaproject.entities.Team;
import com.nbaproject.service.player.PlayerService;
import com.nbaproject.ui.view.mainview.MainView;
import com.nbaproject.utils.JsonNBAPlayers;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.components.grid.SingleSelectionModel;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name=PlayersLayoutFactory.NAME, ui=MainView.class)
public class PlayersLayoutFactory extends VerticalLayout implements View {

	public static final String NAME="players";
	
	TextField filterText = new TextField();
	Grid<Player> grid = new Grid<Player>();
	ArrayList<Player> playerList = new ArrayList<Player>();
	
	@Autowired
	private PlayerService playerService;
	
	public void enter(ViewChangeEvent event) {
		
		CssLayout filtering = new CssLayout();
		
		filterText.setPlaceholder("Filter by players name...");
		filterText.addValueChangeListener(e->updatelist());
		filterText.setValueChangeMode(ValueChangeMode.LAZY);
		
		Button clearFilterTextBtn = new Button(VaadinIcons.CLOSE);
		clearFilterTextBtn.setDescription("Clear the current filter");
		clearFilterTextBtn.addClickListener(e->filterText.clear());
		
		Button showStatsButton = new Button("Show Stats");
		showStatsButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
		
		filtering.addComponents(filterText,clearFilterTextBtn,showStatsButton);
		filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
				
		addComponent(filtering);

		/*** JSON REQUEST ***/
//		try {
//			playerList = JsonNBAPlayers.JsonNBAPlayersRequest();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
		playerList= playerService.findAll();
		
		grid.setItems(playerList);
//		grid.addColumn(Player::getPlayerid).setCaption("PlayerID");
//		grid.addComponentColumn(Player -> {
//			Image img = new Image();
//			img.setSource( new ExternalResource(Player.getPhotoUrl()));
//			img.setHeight("30px");
//			img.setWidth("30px");
//			return img;
//		}).setCaption("Photo");
		grid.addColumn(Player::getFirstname).setCaption("First Name");
		grid.addColumn(Player::getLastname).setCaption("Last Name");
		grid.addColumn(Player::getPosition).setCaption("Position");
		grid.addColumn(Player::getPositionCategory).setCaption("Position Category");	
		grid.addColumn(Player->{
			return Player.getTeam().getTeamname();
		}).setCaption("Team");

		grid.setSizeFull();	
		
		
		addComponent(grid);
		updatelist();
	
		showStatsButton.addClickListener(player -> {

			if (((SingleSelectionModel) grid.getSelectionModel()).getSelectedItems().size() > 0) {

				Optional<Player> selectedPlayer = ((SingleSelectionModel) grid.getSelectionModel()).getSelectedItem();

					UI.getCurrent().getNavigator()
							.navigateTo("playerstats/" + "playerid=" + selectedPlayer.get().getPlayerid());
			}
			else {
				Notification notification = new Notification("You should select player");
				notification.setDelayMsec(3000);
				notification.setPosition(Position.MIDDLE_CENTER);
				notification.show("Information: ", "You should select player", Notification.Type.HUMANIZED_MESSAGE);
			}
		});
	}

	private void updatelist() {
		
		playerList = playerService.findPlayersByName(filterText.getValue());
		System.out.println("size list"+ playerList.size());
		grid.setItems(playerList);
	}
	
	
}
