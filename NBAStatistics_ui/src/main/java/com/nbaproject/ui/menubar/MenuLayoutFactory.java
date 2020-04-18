package com.nbaproject.ui.menubar;

import java.util.stream.Stream;

import com.nbaproject.ui.navigator.MainNavigator;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
public class MenuLayoutFactory implements UIComponentBuilder {

	private class MenuLayout extends VerticalLayout {

		private MenuBar menuBar;

		public MenuLayout init() {

			menuBar = new MenuBar();
			return this;
		}

		public MenuLayout layout() {
			
			GridLayout menuBarLayout = new GridLayout(12,1);
			menuBarLayout.setSizeFull();
			
			Resource logoResource = new ThemeResource("images/NBAStatistics_logo_200x100.svg");
			Image logoImage = new Image(null, logoResource);

			MenuItem teams = menuBar.addItem("Teams", new Command() {
				private static final long serialVersionUID = 1L;

				@Override
				public void menuSelected(MenuItem selectedItem) {
					String selectedItemPath = selectedItem.getText();

					if (selectedItemPath == null)
						return;

					String path = selectedItemPath.toLowerCase().replaceAll("\\s+", "");
					MainNavigator.navigate(path);

					System.out.println(path);
				}
			});

			MenuItem players = menuBar.addItem("Players", new Command() {
				private static final long serialVersionUID = 1L;

				@Override
				public void menuSelected(MenuItem selectedItem) {
					String selectedItemPath = selectedItem.getText();

					if (selectedItemPath == null)
						return;

					String path = selectedItemPath.toLowerCase().replaceAll("\\s+", "");
					MainNavigator.navigate(path);

					System.out.println(path);
				}
			});

			MenuItem boxscore = menuBar.addItem("Boxscore", new Command() {
				private static final long serialVersionUID = 1L;

				@Override
				public void menuSelected(MenuItem selectedItem) {
					String selectedItemPath = selectedItem.getText();

					if (selectedItemPath == null)
						return;

					String path = selectedItemPath.toLowerCase().replaceAll("\\s+", "");
					MainNavigator.navigate(path);

					System.out.println(path);
				}
			});

			MenuItem stats = menuBar.addItem("Stats", new Command() {
				private static final long serialVersionUID = 1L;

				@Override
				public void menuSelected(MenuItem selectedItem) {
					String selectedItemPath = selectedItem.getText();

					if (selectedItemPath == null)
						return;

					String path = selectedItemPath.toLowerCase().replaceAll("\\s+", "");
					MainNavigator.navigate(path);

					System.out.println(path);
				}
			});
			
			MenuItem playerstats = menuBar.addItem("PlayerStats", new Command() {
				private static final long serialVersionUID = 1L;

				@Override
				public void menuSelected(MenuItem selectedItem) {
					String selectedItemPath = selectedItem.getText();

					if (selectedItemPath == null)
						return;

					String path = selectedItemPath.toLowerCase().replaceAll("\\s+", "");
					MainNavigator.navigate(path);

					System.out.println(path);
				}
			});
			
			MenuItem teamstats = menuBar.addItem("TeamStats", new Command() {
				private static final long serialVersionUID = 1L;

				@Override
				public void menuSelected(MenuItem selectedItem) {
					String selectedItemPath = selectedItem.getText();

					if (selectedItemPath == null)
						return;

					String path = selectedItemPath.toLowerCase().replaceAll("\\s+", "");
					MainNavigator.navigate(path);

					System.out.println(path);
				}
			});

			stats.setVisible(false);
			playerstats.setVisible(false);
			teamstats.setVisible(false);
			
			menuBar.addStyleName("MenuBarStyle");
			logoImage.setHeight("55px");
			menuBarLayout.addComponent(logoImage,0,0,0,0);
			menuBarLayout.addComponent(menuBar,1,0,9,0);
			menuBarLayout.setComponentAlignment(logoImage, Alignment.MIDDLE_LEFT);
			menuBarLayout.setComponentAlignment(menuBar, Alignment.MIDDLE_LEFT);
			menuBarLayout.setHeight("60px");
			menuBarLayout.addStyleName("MenuBarLayout");

			addComponent(menuBarLayout);
			setComponentAlignment(menuBarLayout, Alignment.TOP_CENTER);
			return this;
		}
	}

	@Override
	public Component createComponent() {

		return new MenuLayout().init().layout();
	}

}
