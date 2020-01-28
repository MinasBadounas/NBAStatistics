package com.nbaproject.ui;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.nbaproject.utils.JsonNBATeams;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI(path="/ui")
@Title("This the title")
@Theme("valo")
public class MenuView extends UI{

	@Autowired
    SpringViewProvider viewProvider;
	
	@Override
	protected void init(VaadinRequest request) {
		
		final VerticalLayout verticalLayout= new VerticalLayout();
		
		try {
			JsonNBATeams.JsonNBATeamsRequest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		verticalLayout.addComponent(new Label("Welcome to springboot with Vaadin...."));
		
		Button button = new Button("Click me!!");
		
		verticalLayout.addComponent(button);
		button.addClickListener(new Button.ClickListener(){
			
			public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
				verticalLayout.addComponent(new Label("Button has been clicked"));
				
			}

		
		});
		
		setContent(verticalLayout);
		
		
	}

}
