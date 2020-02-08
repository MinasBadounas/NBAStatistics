package com.nbaproject.ui.common;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.nbaproject.navigator.MenuNavigator;
import com.nbaproject.ui.menubar.MenuLayoutFactory;
import com.nbaproject.ui.players.PlayersLayoutFactory;
import com.nbaproject.ui.teams.TeamsLayoutFactory;
import com.nbaproject.utils.JsonNBATeams;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI(path = "/ui")
@Title("This the title")
@Theme("valo")
public class MenuView extends UI {

	@Autowired
	private SpringViewProvider viewProvider;

	@Autowired
	private MenuLayoutFactory menuLayoutFactory;

	@Autowired
	private ApplicationContext applicationContext;

	private Panel changeTab = new Panel();

	@Override
	protected void init(VaadinRequest request) {

		changeTab.setHeight("100%");

		VerticalLayout rootLayout = new VerticalLayout();
		rootLayout.setSizeFull();
		rootLayout.setMargin(true);

		Panel menuPanel = new Panel();
		menuPanel.setWidth("80%");
		menuPanel.setHeightUndefined();

		Panel contentPanel = new Panel();
		contentPanel.setHeight("100%");
		contentPanel.setWidth("80%");

		HorizontalLayout uiLayout = new HorizontalLayout();
		uiLayout.setSizeFull();
		uiLayout.setMargin(true);

		uiLayout.addComponent(changeTab);
		uiLayout.setComponentAlignment(changeTab, Alignment.TOP_CENTER);

		menuPanel.setContent(menuLayoutFactory.createComponent());

		contentPanel.setContent(uiLayout);
		
		rootLayout.addComponent(menuPanel);
		rootLayout.addComponent(contentPanel);
		rootLayout.setComponentAlignment(menuPanel, Alignment.TOP_CENTER);
		rootLayout.setComponentAlignment(contentPanel, Alignment.TOP_CENTER);
		// The content will take space as much as possible
		rootLayout.setExpandRatio(contentPanel, 1);

		initNavigator();

		setContent(rootLayout);

//		final VerticalLayout verticalLayout= new VerticalLayout();
//		
//		try {
//			JsonNBATeams.JsonNBATeamsRequest();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//
//		
//		verticalLayout.addComponent(new Label("Welcome to springboot with Vaadin...."));
//		
//		Button button = new Button("Click me!!");
//		
//		verticalLayout.addComponent(button);
//		button.addClickListener(new Button.ClickListener(){
//			
//			public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
//				verticalLayout.addComponent(new Label("Button has been clicked"));
//				
//			}
//
//		
//		});
//		
//		setContent(verticalLayout);

	}

	private void initNavigator() {

		MenuNavigator menuNavigator = new MenuNavigator(this, changeTab);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(menuNavigator);
		menuNavigator.addProvider(viewProvider);
		menuNavigator.navigateTo(TeamsLayoutFactory.NAME);
	}

}
