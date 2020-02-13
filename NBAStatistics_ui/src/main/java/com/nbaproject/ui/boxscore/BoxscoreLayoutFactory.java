package com.nbaproject.ui.boxscore;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.nbaproject.entities.Boxscore;
import com.nbaproject.repository.boxscore.BoxscoreRepository;
import com.nbaproject.repository.quarterscore.QuarterscoreRepository;
import com.nbaproject.ui.common.MenuView;
import com.nbaproject.utils.JsonNBABoxscore;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = BoxscoreLayoutFactory.NAME, ui = MenuView.class)
public class BoxscoreLayoutFactory extends VerticalLayout implements View {
	
	public static final String NAME = "boxscore";
	
	@Autowired
	private BoxscoreRepository boxscoreRepository;
	
	@Autowired
	private QuarterscoreRepository quarterscoreRepository;
	
	public void enter(ViewChangeEvent event) {
		
		DateField date = new DateField();
		date.setDateFormat("yyyy-MMM-dd");
		date.setValue(LocalDate.now());

		addComponent(date);
		
		date.addValueChangeListener(datefield->{

			ArrayList<Boxscore> boxscoreList= new ArrayList<Boxscore>();
			try {
				boxscoreList=JsonNBABoxscore.JsonNBABoxscoreRequest(datefield.getValue());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Grid<Boxscore> grid = new Grid<Boxscore>();
			grid.setItems(boxscoreList);
			grid.addColumn(Boxscore::getGameid).setCaption("Gameid");
			grid.addColumn(Boxscore::getSeason).setCaption("Season");
			grid.addColumn(Boxscore::getAwayteam).setCaption("Awayteam");
			grid.addColumn(Boxscore::getHometeam).setCaption("Hometeam");
			grid.addColumn(Boxscore::getAwayteamid).setCaption("Awayteamid");
			grid.addColumn(Boxscore::getHometeamid).setCaption("Hometeamid");
			grid.addColumn(Boxscore::getAwayteamscore).setCaption("Awayteamscore");
			grid.addColumn(Boxscore::getHometeamscore).setCaption("Hometeamscore");
			grid.addColumn(Boxscore::getPointspread).setCaption("Pointspread");
			grid.addColumn(Boxscore::getOverunder).setCaption("Overunder");
			grid.addColumn(Boxscore::getDatetime).setCaption("Datetime");
			
			grid.setSizeFull();
			addComponent(grid);

		});
	}
	
	
}
