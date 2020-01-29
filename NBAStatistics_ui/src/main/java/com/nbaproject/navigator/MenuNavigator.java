package com.nbaproject.navigator;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.SingleComponentContainer;
import com.vaadin.ui.UI;

public class MenuNavigator extends Navigator {

	public MenuNavigator(UI ui, SingleComponentContainer container) {
		super(ui, container);
	}

	private static MenuNavigator getNavigator() {

		UI ui = UI.getCurrent();
		Navigator navigator = ui.getNavigator();

		return (MenuNavigator) navigator;

	}

	public static void navigate(String path) {

		try {

			MenuNavigator.getNavigator().navigateTo(path);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void navigateTo(String navigationState) {
		
		super.navigateTo(navigationState);
	}

}
