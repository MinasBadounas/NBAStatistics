package com.nbaproject.utils.staticInitializer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nbaproject.entities.Playerprop;
import com.nbaproject.service.playerprop.PlayerpropService;

@Component
public class PlayerpropServiceStaticInitializer {
	
	@Autowired
	private PlayerpropService playerpropService;
	
	private static PlayerpropServiceStaticInitializer playerpropServiceStaticInitializer;
	
	@PostConstruct
	public void init() {
		playerpropServiceStaticInitializer = this;
		playerpropServiceStaticInitializer.playerpropService = this.playerpropService;
	}

	public PlayerpropService getPlayerpropService() {
		return playerpropService;
	}

	public void setPlayerpropService(PlayerpropService playerpropService) {
		playerpropService = playerpropService;
	}
	
	public static void addPlayerprop(Playerprop playerprop) {

		playerpropServiceStaticInitializer.playerpropService.savePlayerprops(playerprop);

	}


}
