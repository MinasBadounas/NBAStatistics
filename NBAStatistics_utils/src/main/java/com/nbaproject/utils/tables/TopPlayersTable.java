package com.nbaproject.utils.tables;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.nbaproject.entities.Playerstatspergame;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

@Component
public class TopPlayersTable {

	public GridLayout componentTopPlayersTable(ArrayList<Playerstatspergame> gridTopPlayersList, String title) {

		GridLayout gridTopPlayers = new GridLayout(2, 6);

		gridTopPlayers.addComponent(new Label("<p class=\"HeaderTopPlayer\">" + title + "</p>", ContentMode.HTML), 0, 0,
				1, 0);
		gridTopPlayers.setSpacing(true);
		gridTopPlayers.setStyleName("gridTopPlayers");
		for (int i = 0; i < gridTopPlayersList.size(); i++) {
			Label labelName = new Label("<p class=\"RowTopPlayerName\">" + (i + 1) + ". "
					+ gridTopPlayersList.get(i).getPlayer().getFirstname() + " "
					+ gridTopPlayersList.get(i).getPlayer().getLastname() + "</p>", ContentMode.HTML);
			gridTopPlayers.addComponent(labelName, 0, i + 1);

		}

		switch (title) {

		case "Points":
			for (int i = 0; i < gridTopPlayersList.size(); i++) {
				Label labelPointsValue = new Label("<p class=\"RowTopPlayerValue\">"
						+ String.valueOf(gridTopPlayersList.get(i).getPoints()) + "</p>", ContentMode.HTML);
				gridTopPlayers.addComponent(labelPointsValue, 1, i + 1);
			}
			break;
		case "Rebounds":
			for (int i = 0; i < gridTopPlayersList.size(); i++) {
				Label labelReboundsValue = new Label("<p class=\"RowTopPlayerValue\">"
						+ String.valueOf(gridTopPlayersList.get(i).getRebounds()) + "</p>", ContentMode.HTML);
				gridTopPlayers.addComponent(labelReboundsValue, 1, i + 1);
			}
			break;
		case "Assists":
			for (int i = 0; i < gridTopPlayersList.size(); i++) {
				Label labelAssistsValue = new Label("<p class=\"RowTopPlayerValue\">"
						+ String.valueOf(gridTopPlayersList.get(i).getAssists()) + "</p>", ContentMode.HTML);
				gridTopPlayers.addComponent(labelAssistsValue, 1, i + 1);
			}
			break;
		}

		return gridTopPlayers;

	}

}
