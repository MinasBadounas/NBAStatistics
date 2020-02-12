package com.nbaproject.ui.boxscore;

import java.io.IOException;
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
import com.vaadin.ui.VerticalLayout;

@SpringView(name = BoxscoreLayoutFactory.NAME, ui = MenuView.class)
public class BoxscoreLayoutFactory extends VerticalLayout implements View {
	
	public static final String NAME = "boxscore";
	
	@Autowired
	private BoxscoreRepository boxscoreRepository;
	
	@Autowired
	private QuarterscoreRepository quarterscoreRepository;
	
	public void enter(ViewChangeEvent event) {
		
		ArrayList<Boxscore> boxscoreList = new ArrayList<Boxscore>();
		
		try {
			boxscoreList=JsonNBABoxscore.JsonNBABoxscoreRequest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
