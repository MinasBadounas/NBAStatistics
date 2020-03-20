package com.nbaproject.ui.boxscore;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.nbaproject.entities.Boxscore;
import com.nbaproject.navigator.MenuNavigator;
import com.nbaproject.repository.boxscore.BoxscoreRepository;
import com.nbaproject.ui.common.MenuView;
import com.nbaproject.ui.teams.TeamsLayoutFactory;
import com.nbaproject.utils.JsonNBABoxscore;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.SingleSelectionModel;

@SpringView(name = BoxscoreLayoutFactory.NAME, ui = MenuView.class)
public class BoxscoreLayoutFactory extends VerticalLayout implements View {
	
	
	public static final String NAME = "boxscore";
	
	public void enter(ViewChangeEvent event) {
		
		Grid<Boxscore> grid = new Grid<Boxscore>();
		HorizontalLayout horizontal = new HorizontalLayout ();
				
		DateField date = new DateField();
		date.setDateFormat("yyyy-MMM-dd");
		date.setValue(LocalDate.now());
		
		Button forwardArrow = new Button("|>");
		Button backwardArrow = new Button("<|");
		Button showStatsButton = new Button("Show Stats");

		horizontal.addComponent(backwardArrow);
		horizontal.addComponent(date);
		horizontal.addComponent(forwardArrow);
		horizontal.addComponent(showStatsButton);
		addComponent(horizontal);
		
		forwardArrow.addClickListener(datefield->{
			date.setValue(date.getValue().plusDays(1));
		});
		
		backwardArrow.addClickListener(datefield->{
			date.setValue(date.getValue().minusDays(1));
		});
		
		date.addValueChangeListener(datefield->{
			
			grid.removeAllColumns();

			ArrayList<Boxscore> boxscoreList= new ArrayList<Boxscore>();
			try {
				boxscoreList=JsonNBABoxscore.JsonNBABoxscoreRequest(datefield.getValue());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			grid.setItems(boxscoreList);
			grid.addColumn(Boxscore::getGameid).setCaption("Gameid");
			grid.addColumn(Boxscore::getSeason).setCaption("Season");
			grid.addColumn(Boxscore::getStatus).setCaption("Status");
			grid.addColumn(Boxscore::getDatetime).setCaption("Datetime");
			grid.addColumn(Boxscore::getAwayteam).setCaption("Awayteam");
			grid.addColumn(Boxscore::getHometeam).setCaption("Hometeam");
			grid.addColumn(Boxscore::getAwayteamscore).setCaption("Awayteamscore");
			grid.addColumn(Boxscore::getHometeamscore).setCaption("Hometeamscore");
			grid.addColumn(Boxscore::getPointspread).setCaption("Pointspread");
			grid.addColumn(Boxscore::getOverunder).setCaption("Overunder");
			
			grid.setSizeFull();

		});
		
		date.setValue(LocalDate.now().minusDays(1));
		
		showStatsButton.addClickListener(boxscore->{
			
			Optional<Boxscore> selected = ((SingleSelectionModel) grid.getSelectionModel()).getSelectedItem();
		
			UI.getCurrent().getNavigator().navigateTo("stats/gameid="+selected.get().getGameid());

		});
		

		addComponent(grid);
	}
	
	
	
}
