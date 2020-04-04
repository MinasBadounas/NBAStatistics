package com.nbaproject.ui.view.mainview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.nbaproject.ui.menubar.MenuLayoutFactory;
import com.nbaproject.ui.navigator.MainNavigator;
import com.nbaproject.ui.view.teams.TeamsLayoutFactory;
import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI(path = "/ui")
@Title("NBAStatistics")
@Theme("nbastatistics_uitheme")
@JavaScript({"vaadin://javascripts/Chart.js"})
public class MainView extends UI {

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
		rootLayout.setMargin(false);

		Panel menuPanel = new Panel();
		menuPanel.setWidth("80%");
		menuPanel.setHeightUndefined();

		Panel contentPanel = new Panel();
		contentPanel.setHeight("100%");
		contentPanel.setWidth("80%");

		HorizontalLayout uiLayout = new HorizontalLayout();
		uiLayout.setSizeFull();
		uiLayout.setMargin(false);

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
		
	}

	private void initNavigator() {

		MainNavigator menuNavigator = new MainNavigator(this, changeTab);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(menuNavigator);
		menuNavigator.addProvider(viewProvider);
		menuNavigator.navigateTo(TeamsLayoutFactory.NAME);
		
	}
	

}
