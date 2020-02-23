package com.nbaproject.service.playerstatspergame;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbaproject.entities.Playerstatspergame;
import com.nbaproject.repository.playerstatspergame.PlayerStatsPerGameRepository;

@Service
public class PlayerstatspergameServiceImp implements PlayerstatspergameService {

	@Autowired
	private PlayerStatsPerGameRepository playerStatsPerGameRepository;

	@Override
	public void savePlayerstatspergame(Playerstatspergame playerstatspergame) {

		playerStatsPerGameRepository.save(playerstatspergame);

	}

	@Override
	public ArrayList<Integer> findAllGameIdInPlayerStatsPerGame() {

		ArrayList<Integer> listAllGameIdInPlayerStatsPerGame = new ArrayList<Integer>();
		listAllGameIdInPlayerStatsPerGame = playerStatsPerGameRepository.findAllGameId();

		return listAllGameIdInPlayerStatsPerGame;
	}

	@Override
	public int maxGameIdInPlayerStatsPerGame() {

		int maxGameId;
		
		if(playerStatsPerGameRepository.maxGameId()==null) {
			maxGameId = 0;
		}
		else {
			maxGameId=playerStatsPerGameRepository.maxGameId();
		}

		return maxGameId;
	}
}
