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

		if (playerStatsPerGameRepository.maxGameId() == null) {
			maxGameId = 0;
		} else {
			maxGameId = playerStatsPerGameRepository.maxGameId();
		}

		return maxGameId;
	}

	@Override
	public ArrayList<Playerstatspergame> findPlayerStatsPerGameByGameId(int gameid, int min_minutes) {

		ArrayList<Playerstatspergame> playerStatsPerGameList = playerStatsPerGameRepository
				.findPlayerStatsPerGameByGameId(gameid, min_minutes);

		return playerStatsPerGameList;
	}

	@Override
	public ArrayList<Playerstatspergame> findPlayerStatsPerGameWhichNotInDefenceMatchUpByGameId(int gameId, int minutes) {

		ArrayList<Playerstatspergame> playerStatsPerGameList = playerStatsPerGameRepository
				.findAllPlayerStatsPerGameWhereNotInDefencemMatchUpAndOverMinutesByGameId(gameId,minutes);

		return playerStatsPerGameList;
	}

	@Override
	public ArrayList<Playerstatspergame> findPlayerStatsPerGameByGameIdAndTeamId(int gameId, int teamid) {

		ArrayList<Playerstatspergame> playerStatsPerGameList = playerStatsPerGameRepository
				.findPlayerStatsPerGameByGameIdAndTeamId(gameId, teamid);

		return playerStatsPerGameList;
	}
}
