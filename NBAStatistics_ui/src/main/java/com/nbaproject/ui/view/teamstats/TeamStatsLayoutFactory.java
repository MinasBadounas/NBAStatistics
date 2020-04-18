package com.nbaproject.ui.view.teamstats;

import com.nbaproject.ui.view.mainview.MainView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = TeamStatsLayoutFactory.NAME, ui = MainView.class)
public class TeamStatsLayoutFactory extends VerticalLayout implements View {
	
	public static final String NAME = "teamstats";
	
	public void enter(ViewChangeEvent event) {
		int teamid = Integer.parseInt(event.getParameterMap().get("teamid"));
		
		Label label = new Label("teamid", ContentMode.TEXT);
		
		addComponent(label);
	}


}
