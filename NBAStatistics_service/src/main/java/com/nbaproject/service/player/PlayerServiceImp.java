package com.nbaproject.service.player;

import java.util.ArrayList;

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
	public Integer findPlayerByPlayerId(int playerid) {

		int playerID;

		if (playerRepository.findPlayerById(playerid) == null) {
			playerID = 0;
		} else {
			playerID = playerRepository.findPlayerById(playerid);
		}
		
		return playerID;
	}

}
