package com.nbaproject.utils.staticInitializer;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nbaproject.entities.Boxscore;
import com.nbaproject.service.boxscore.BoxscoreService;

@Component
public class BoxscoreServiceStaticInitializer {

	@Autowired
	private BoxscoreService boxscoreService;

	private static BoxscoreServiceStaticInitializer boxscoreStaticContextInitializer;

	@PostConstruct
	public void init() {
		boxscoreStaticContextInitializer = this;
		boxscoreStaticContextInitializer.boxscoreService = this.boxscoreService;
	}

	public BoxscoreService getBoxscoreService() {
		return boxscoreService;
	}

	public void setBoxscoreService(BoxscoreService boxscoreService) {
		boxscoreService = boxscoreService;
	}

	
	public static Boxscore findBoxscoreByGameId(int gameid) {

		return boxscoreStaticContextInitializer.boxscoreService.findBoxscorebyGameId(gameid);
	}

	public static ArrayList<Boxscore> findBoxscoresByDate(String date) {

		return boxscoreStaticContextInitializer.boxscoreService.findBoxscoresbyDate(date);
	}
}

