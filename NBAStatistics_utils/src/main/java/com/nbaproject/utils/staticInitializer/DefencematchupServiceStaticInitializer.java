package com.nbaproject.utils.staticInitializer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.nbaproject.entities.Defencematchup;
import com.nbaproject.service.defencematchup.DefencematchupService;
import com.nbaproject.service.opponentplayerstatspergame.OpponentplayerstatspergameService;


@Component
@Order(5)
public class DefencematchupServiceStaticInitializer {
	
	@Autowired
	private DefencematchupService defencematchupService;
	
	private static DefencematchupServiceStaticInitializer defencematchupServiceStaticInitializer;
	
	@PostConstruct
	public void init() {
		defencematchupServiceStaticInitializer = this;
		defencematchupServiceStaticInitializer.defencematchupService = this.defencematchupService;
	}

	public DefencematchupService getDefencematchupService() {
		return defencematchupService;
	}

	public void setDefencematchupService(DefencematchupService defencematchupService) {
		defencematchupService = defencematchupService;
	}
	
	public static void addDefencematchup(Defencematchup defencematchup) {

		defencematchupServiceStaticInitializer.defencematchupService.saveDefenceMatchUp(defencematchup);

	}


}
