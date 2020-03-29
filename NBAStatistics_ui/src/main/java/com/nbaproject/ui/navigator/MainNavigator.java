package com.nbaproject.ui.navigator;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.SingleComponentContainer;
import com.vaadin.ui.UI;


public class MainNavigator extends Navigator {

	public MainNavigator(UI ui, SingleComponentContainer container) {
		super(ui, container);
	}

	private static MainNavigator getNavigator() {

		UI ui = UI.getCurrent();
		Navigator navigator = ui.getNavigator();

		return (MainNavigator) navigator;

	}

	public static void navigate(String path) {

		try {

			MainNavigator.getNavigator().navigateTo(path);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void navigateTo(String navigationState) {
		
		super.navigateTo(navigationState);
	}

}
