package com.nbaproject.service.opponentplayerstatspergame;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbaproject.entities.Opponentplayerstatspergame;
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

	@Override
	public ArrayList<Opponentplayerstatspergame> findOpponentplayerstatspergameByPlayerId(int playerid) {
		
		ArrayList<Opponentplayerstatspergame> opponentplayerstatspergameList = opponentplayerstatspergameRepository.findOpponentplayerstatspergameByPlayerId(playerid);
		
		return opponentplayerstatspergameList;
	}

}
