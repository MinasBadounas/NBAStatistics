package com.nbaproject.ui.view.boxscore;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

import com.nbaproject.entities.Boxscore;
import com.nbaproject.ui.view.mainview.MainView;
import com.nbaproject.utils.staticInitializer.BoxscoreServiceStaticInitializer;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.Position;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.SingleSelectionModel;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = BoxscoreLayoutFactory.NAME, ui = MainView.class)
public class BoxscoreLayoutFactory extends VerticalLayout implements View {

	public static final String NAME = "boxscore";

	public void enter(ViewChangeEvent event) {

		Grid<Boxscore> grid = new Grid<Boxscore>();
		HorizontalLayout horizontal = new HorizontalLayout();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateField date = new DateField();
		date.setDateFormat("yyyy-MMM-dd");
		date.setValue(LocalDate.now());

		Button forwardArrow = new Button();
		Button backwardArrow = new Button();
		Button showStatsButton = new Button("Show Stats");
		forwardArrow.setIcon(VaadinIcons.ARROW_FORWARD);
		backwardArrow.setIcon(VaadinIcons.ARROW_BACKWARD);
		showStatsButton.setStyleName(ValoTheme.BUTTON_PRIMARY);

		horizontal.addComponent(backwardArrow);
		horizontal.addComponent(date);
		horizontal.addComponent(forwardArrow);
		horizontal.addComponent(showStatsButton);
		addComponent(horizontal);

		forwardArrow.addClickListener(datefield -> {
			date.setValue(date.getValue().plusDays(1));
		});

		backwardArrow.addClickListener(datefield -> {
			date.setValue(date.getValue().minusDays(1));
		});

		date.addValueChangeListener(datefield -> {

			grid.removeAllColumns();

			ArrayList<Boxscore> boxscoreList = new ArrayList<Boxscore>();
//			try {
//				boxscoreList = JsonNBABoxscore.JsonNBABoxscoreRequest(datefield.getValue());
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

			boxscoreList = BoxscoreServiceStaticInitializer.findBoxscoresByDate(dtf.format(datefield.getValue()));

			grid.setItems(boxscoreList);
			grid.addColumn(Boxscore::getGameid).setCaption("Gameid");
			grid.addColumn(Boxscore::getSeason).setCaption("Season");
			grid.addColumn(Boxscore::getStatus).setCaption("Status");
			grid.addColumn(Boxscore::getDatetime).setCaption("Datetime");
			grid.addColumn(Boxscore::getAwayteam).setCaption("Awayteam");
			grid.addColumn(Boxscore::getHometeam).setCaption("Hometeam");
			grid.addColumn(Boxscore::getAwayteamscore).setCaption("Awayteamscore");
			grid.addColumn(Boxscore::getHometeamscore).setCaption("Hometeamscore");
			grid.addColumn(Boxscore::getPointspread).setCaption("Pointspread");
			grid.addColumn(Boxscore::getOverunder).setCaption("Overunder");

			grid.setSizeFull();

		});

		date.setValue(LocalDate.now().minusDays(1));

		showStatsButton.addClickListener(boxscore -> {

			if (((SingleSelectionModel) grid.getSelectionModel()).getSelectedItems().size() > 0) {

				Optional<Boxscore> selected = ((SingleSelectionModel) grid.getSelectionModel()).getSelectedItem();
				LocalDate localDateNow = LocalDate.now();
				LocalDate gameDate = (selected.get().getDatetime()).toInstant().atZone(ZoneId.systemDefault())
						.toLocalDate();
				if (localDateNow.isAfter(gameDate)) {
					UI.getCurrent().getNavigator()
							.navigateTo("stats/" + "gameid=" + selected.get().getGameid() + "&awayteam="
									+ selected.get().getTeam1().getTeamid() + "&hometeam="
									+ selected.get().getTeam2().getTeamid());
				} else {
					Notification notification = new Notification("You should select game");
					notification.setDelayMsec(3000);
					notification.setPosition(Position.MIDDLE_CENTER);
					notification.show("Information: ", "The game isn't completed", Notification.Type.HUMANIZED_MESSAGE);
				}

			} else {
				Notification notification = new Notification("You should select game");
				notification.setDelayMsec(3000);
				notification.setPosition(Position.MIDDLE_CENTER);
				notification.show("Information: ", "You should select game", Notification.Type.HUMANIZED_MESSAGE);

			}

		});

		addComponent(grid);
	}

}
