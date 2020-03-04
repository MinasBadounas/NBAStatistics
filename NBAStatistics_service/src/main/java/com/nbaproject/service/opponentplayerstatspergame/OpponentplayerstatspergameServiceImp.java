package com.nbaproject.service.opponentplayerstatspergame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbaproject.repository.opponentplayerstatspergame.OpponentplayerstatspergameRepository;

@Service
public class OpponentplayerstatspergameServiceImp implements OpponentplayerstatspergameService {

	@Autowired
	private OpponentplayerstatspergameRepository opponentplayerstatspergameRepository;

	@Override
	public void saveOpponentPlayerStatsPerGame(int gameid, int playerid) {

		opponentplayerstatspergameRepository.InsertOpponentplayerstatspergame(gameid, playerid);

	}

	@Override
	public void saveNullOpponentPlayerStatsPerGame(int gameid, int playerid) {

		opponentplayerstatspergameRepository.InsertNullOpponentplayerstatspergame(gameid, playerid);

	}

}
