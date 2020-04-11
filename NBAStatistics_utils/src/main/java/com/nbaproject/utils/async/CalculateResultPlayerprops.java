package com.nbaproject.utils.async;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.nbaproject.entities.Playerprop;
import com.nbaproject.entities.Playerstatspergame;
import com.nbaproject.service.playerprop.PlayerpropService;
import com.nbaproject.service.playerstatspergame.PlayerstatspergameService;

@Configuration
@EnableScheduling
@EnableAsync
public class CalculateResultPlayerprops {

	@Autowired
	private PlayerpropService playerpropService;

	@Autowired
	private PlayerstatspergameService playerstatspergameService;

	@Async("threadPoolTaskExecutor")
	@Scheduled(cron = "0 20 19 * * ?")
	public void CalculateResultPlayerpropSchedule() {

		ArrayList<Playerprop> playerpropList = playerpropService.findPlayerpropsByGameId(13905);

		for (int i = 0; i < playerpropList.size(); i++) {

			Playerstatspergame playerstatspergame = playerstatspergameService.findPlayerStatsPerGameByGameIdAndPlayerId(
					playerpropList.get(i).getId().getGameid(), playerpropList.get(i).getPlayer().getPlayerid());

			switch (playerpropList.get(i).getId().getDescription()) {

			case (1):

				break;

			case (2):
				if (playerstatspergame.getPoints() > playerpropList.get(i).getOverunder()) {
					playerpropList.get(i).setBetresult("over");
				} else if (playerstatspergame.getPoints() < playerpropList.get(i).getOverunder()) {
					playerpropList.get(i).setBetresult("under");
				} else {
					playerpropList.get(i).setBetresult("push");
				}
				break;
			case (3):
				if (playerstatspergame.getFieldgoalsmade() > playerpropList.get(i).getOverunder()) {
					playerpropList.get(i).setBetresult("over");
				} else if (playerstatspergame.getFieldgoalsmade() < playerpropList.get(i).getOverunder()) {
					playerpropList.get(i).setBetresult("under");
				} else {
					playerpropList.get(i).setBetresult("push");
				}
				break;
			case (4):
				if (playerstatspergame.getFieldgoalsattempted() > playerpropList.get(i).getOverunder()) {
					playerpropList.get(i).setBetresult("over");
				} else if (playerstatspergame.getFieldgoalsattempted() < playerpropList.get(i).getOverunder()) {
					playerpropList.get(i).setBetresult("under");
				} else {
					playerpropList.get(i).setBetresult("push");
				}
				break;
			case (5):
				if (playerstatspergame.getThreepointersmade() > playerpropList.get(i).getOverunder()) {
					playerpropList.get(i).setBetresult("over");
				} else if (playerstatspergame.getThreepointersmade() < playerpropList.get(i).getOverunder()) {
					playerpropList.get(i).setBetresult("under");
				} else {
					playerpropList.get(i).setBetresult("push");
				}
				break;
			case (6):
				if (playerstatspergame.getThreepointersattempted() > playerpropList.get(i).getOverunder()) {
					playerpropList.get(i).setBetresult("over");
				} else if (playerstatspergame.getThreepointersattempted() < playerpropList.get(i).getOverunder()) {
					playerpropList.get(i).setBetresult("under");
				} else {
					playerpropList.get(i).setBetresult("push");
				}
				break;
			case (7):
				if (playerstatspergame.getAssists() > playerpropList.get(i).getOverunder()) {
					playerpropList.get(i).setBetresult("over");
				} else if (playerstatspergame.getAssists() < playerpropList.get(i).getOverunder()) {
					playerpropList.get(i).setBetresult("under");
				} else {
					playerpropList.get(i).setBetresult("push");
				}
				break;
			case (8):
				if (playerstatspergame.getSteals() > playerpropList.get(i).getOverunder()) {
					playerpropList.get(i).setBetresult("over");
				} else if (playerstatspergame.getSteals() < playerpropList.get(i).getOverunder()) {
					playerpropList.get(i).setBetresult("under");
				} else {
					playerpropList.get(i).setBetresult("push");
				}
				break;
			case (9):
				if (playerstatspergame.getRebounds() > playerpropList.get(i).getOverunder()) {
					playerpropList.get(i).setBetresult("over");
				} else if (playerstatspergame.getRebounds() < playerpropList.get(i).getOverunder()) {
					playerpropList.get(i).setBetresult("under");
				} else {
					playerpropList.get(i).setBetresult("push");
				}
				break;
			case (10):
				if ((playerstatspergame.getPoints() + playerstatspergame.getRebounds()
						+ playerstatspergame.getAssists()) > playerpropList.get(i).getOverunder()) {
					playerpropList.get(i).setBetresult("over");
				} else if ((playerstatspergame.getPoints() + playerstatspergame.getRebounds()
						+ playerstatspergame.getAssists()) < playerpropList.get(i).getOverunder()) {
					playerpropList.get(i).setBetresult("under");
				} else {
					playerpropList.get(i).setBetresult("push");
				}
				break;
			case (11):
				if ((playerstatspergame.getPoints() + playerstatspergame.getRebounds()) > playerpropList.get(i)
						.getOverunder()) {
					playerpropList.get(i).setBetresult("over");
				} else if ((playerstatspergame.getPoints() + playerstatspergame.getRebounds()) < playerpropList.get(i)
						.getOverunder()) {
					playerpropList.get(i).setBetresult("under");
				} else {
					playerpropList.get(i).setBetresult("push");
				}
				break;
			case (12):
				if ((playerstatspergame.getPoints() + playerstatspergame.getAssists()) > playerpropList.get(i)
						.getOverunder()) {
					playerpropList.get(i).setBetresult("over");
				} else if ((playerstatspergame.getPoints() + playerstatspergame.getAssists()) < playerpropList.get(i)
						.getOverunder()) {
					playerpropList.get(i).setBetresult("under");
				} else {
					playerpropList.get(i).setBetresult("push");
				}
				break;

			}

			playerpropService.savePlayerprops(playerpropList.get(i));
			System.out.println("Save to Playerprop the Playerprop with GameID: "
					+ playerpropList.get(i).getBoxscore().getGameid() + " and PlayerId: "
					+ playerpropList.get(i).getPlayer().getPlayerid() + " and Description: "
					+ playerpropList.get(i).getId().getDescription() + " -> " + playerpropList.get(i).getBetresult());
		}
	}

}
