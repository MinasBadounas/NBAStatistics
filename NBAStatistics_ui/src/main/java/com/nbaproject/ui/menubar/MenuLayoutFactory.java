	package com.nbaproject.ui.menubar;

import java.util.stream.Stream;

import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
public class MenuLayoutFactory implements UIComponentBuilder {

	private class MenuLayout extends VerticalLayout {
		
		private MenuBar menuBar;
		
		public MenuLayout init() {
			

			menuBar = new MenuBar();
			Stream.of("Home", "Dashboard", "Teams", "Players", "Appearance",
			        "Modules", "Users", "Configuration", "Reports", "Help")
			        .forEach(menuBar::addItem);
			
			menuBar.addListener(new Listener()
			{
				
				@Override
				public void componentEvent(Event event) {
					String selectedItemPath =(String) event.getClass().get
					
				}
			});
			
			
			return this;
		}
		
		public MenuLayout layout() {
			
			
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
