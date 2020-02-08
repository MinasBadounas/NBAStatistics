package com.nbaproject.service.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbaproject.entities.Player;
import com.nbaproject.repository.player.PlayerRepository;

@Service
public class PlayerServiceImp implements PlayerService{
	
	@Autowired
	private PlayerRepository playerRepository;
	
	
	@Override
	public void savePlayer(Player player) {
		
		playerRepository.save(player);
		
	}

}
