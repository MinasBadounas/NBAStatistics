package com.nbaproject.utils.staticInitializer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nbaproject.entities.Playerseasonstat;
import com.nbaproject.service.playerseasonstats.PlayerseasonstatsService;

@Component
public class PlayerseasonstatsServiceStaticInitializer {

	@Autowired
	private PlayerseasonstatsService playerseasonstatsService;
	
	private static PlayerseasonstatsServiceStaticInitializer playerseasonstatsServiceStaticInitializer;
	
	@PostConstruct
	public void init() {
		playerseasonstatsServiceStaticInitializer = this;
		playerseasonstatsServiceStaticInitializer.playerseasonstatsService = this.playerseasonstatsService;
	}

	public PlayerseasonstatsService getPlayerseasonstatsService() {
		return playerseasonstatsService;
	}

	public void setPlayerseasonstatsService(PlayerseasonstatsService playerseasonstatsService) {
		playerseasonstatsService = playerseasonstatsService;
	}
	
	public static void addPlayerseasonstat(Playerseasonstat playerseasonstat) {

		playerseasonstatsServiceStaticInitializer.playerseasonstatsService.savePlayerseasonstats(playerseasonstat);

	}

	
}
