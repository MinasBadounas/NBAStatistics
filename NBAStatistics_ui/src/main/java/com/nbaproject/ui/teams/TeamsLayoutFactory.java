package com.nbaproject.ui.teams;

import com.nbaproject.ui.common.MenuView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SpringView(name="Teams", ui=MenuView.class)
public class TeamsLayoutFactory extends VerticalLayout implements View {

	public void enter(ViewChangeEvent event) {
		
		addComponent(new Label ("Teamssssss"));
		
	}
	
}
