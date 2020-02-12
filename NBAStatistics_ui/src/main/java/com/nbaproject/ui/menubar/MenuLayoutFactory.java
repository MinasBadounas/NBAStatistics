package com.nbaproject.ui.menubar;

import java.util.stream.Stream;

import com.nbaproject.navigator.MenuNavigator;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
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

//			MenuItem home = menuBar.addItem("Home", new Command() {
//		        private static final long serialVersionUID = 1L;
//
//		        @Override
//		        public void menuSelected(MenuItem selectedItem) {
//		    		String selectedItemPath = selectedItem.getText();
//
//		    		if (selectedItemPath == null)
//		    			return;
//
//		    		String path = selectedItemPath.toLowerCase().replaceAll("\\s+", "");
//		    		MenuNavigator.navigate(path);
//
//		    		System.out.println(path);
//		        }
//		    });
//			MenuItem dashboard = menuBar.addItem("Dashboard", new Command() {
//		        private static final long serialVersionUID = 1L;
//
//		        @Override
//		        public void menuSelected(MenuItem selectedItem) {
//		    		String selectedItemPath = selectedItem.getText();
//
//		    		if (selectedItemPath == null)
//		    			return;
//
//		    		String path = selectedItemPath.toLowerCase().replaceAll("\\s+", "");
//		    		MenuNavigator.navigate(path);
//
//		    		System.out.println(path);
//		        }
//		    });
			MenuItem teams = menuBar.addItem("Teams", new Command() {
		        private static final long serialVersionUID = 1L;

		        @Override
		        public void menuSelected(MenuItem selectedItem) {
		    		String selectedItemPath = selectedItem.getText();

		    		if (selectedItemPath == null)
		    			return;

		    		String path = selectedItemPath.toLowerCase().replaceAll("\\s+", "");
		    		MenuNavigator.navigate(path);

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
		    		MenuNavigator.navigate(path);

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
		    		MenuNavigator.navigate(path);

		    		System.out.println(path);
		        }
		    });

			addComponent(menuBar);
			setComponentAlignment(menuBar, Alignment.TOP_CENTER);
			return this;
		}
	}

	@Override
	public Component createComponent() {

		return new MenuLayout().init().layout();
	}


}
