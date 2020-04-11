package com.nbaproject.service.player;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbaproject.entities.Player;
import com.nbaproject.repository.player.PlayerRepository;

@Service
public class PlayerServiceImp implements PlayerService {

	@Autowired
	private PlayerRepository playerRepository;

	@Override
	public void savePlayer(Player player) {

		playerRepository.save(player);

	}

	@Override
	public Integer findPlayerById(int playerid) {

		int playerID;

		if (playerRepository.findPlayerById(playerid) == null) {
			playerID = 0;
		} else {
			playerID = playerRepository.findPlayerById(playerid);
		}

		return playerID;
	}

	@Override
	public ArrayList<Player> findPlayersByName(String playername) {

		ArrayList<Player> playersList = playerRepository.findPlayersByName(playername);

		return playersList;
	}

	@Override
	public Player findPlayerByPlayerId(int playerid) {

		Player player = playerRepository.findPlayerByPlayerId(playerid);

		return player;
	}

	@Override
	public ArrayList<Player> findAll() {

		ArrayList<Player> playersList = playerRepository.findAll();
		
		return playersList;
	}

}
