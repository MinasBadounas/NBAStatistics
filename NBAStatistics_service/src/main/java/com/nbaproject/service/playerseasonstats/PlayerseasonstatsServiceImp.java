package com.nbaproject.service.playerseasonstats;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbaproject.entities.Playerseasonstat;
import com.nbaproject.repository.playerseasonstats.PlayerseasonstatsRepository;

@Service
public class PlayerseasonstatsServiceImp implements PlayerseasonstatsService {

	@Autowired
	private PlayerseasonstatsRepository playerseasonstatsRepository;

	@Override
	public ArrayList<Playerseasonstat> findPlayerseasonstatsByPlayerId(int playerid) {

		ArrayList<Playerseasonstat> playerseasonstatList = playerseasonstatsRepository
				.findPlayerseasonstatsByPlayerId(playerid);

		return playerseasonstatList;
	}

	@Override
	public Playerseasonstat findLastPlayerseasonstatsByPlayerId(int playerid) {

		Playerseasonstat playerseasonstat = playerseasonstatsRepository.findLastPlayerseasonstatsByPlayerId(playerid);

		return playerseasonstat;
	}

	@Override
	public void savePlayerseasonstats(Playerseasonstat playerseasonstat) {

		playerseasonstatsRepository.save(playerseasonstat);
		
	}

}
