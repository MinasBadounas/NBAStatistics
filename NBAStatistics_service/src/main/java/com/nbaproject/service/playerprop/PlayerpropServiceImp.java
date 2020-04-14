package com.nbaproject.service.playerprop;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbaproject.entities.Playerprop;
import com.nbaproject.repository.playerprops.PlayerpropRepository;

@Service
public class PlayerpropServiceImp implements PlayerpropService {

	@Autowired
	private PlayerpropRepository playerpropRepository;

	@Override
	public ArrayList<Playerprop> findPlayerpropsByPlayerId(int playerid) {

		ArrayList<Playerprop> playerpropList = playerpropRepository.findPlayerpropsByPlayerId(playerid);

		return playerpropList;
	}

	@Override
	public ArrayList<Playerprop> findPlayerpropsByGameId(int gameid) {

		ArrayList<Playerprop> playerpropList = playerpropRepository.findPlayerpropsByGameId(gameid);

		return playerpropList;
	}

	@Override
	public ArrayList<Playerprop> findPlayerpropsByGameIdPlayerId(int gameid, int playerid) {

		ArrayList<Playerprop> playerpropList = playerpropRepository.findPlayerpropsByGameIdPlayerId(gameid, playerid);

		return playerpropList;
	}

	@Override
	public void savePlayerprops(Playerprop playerprop) {

		playerpropRepository.save(playerprop);

	}

	@Override
	public ArrayList<Playerprop> findPlayerpropsByPlayerIdAndDescription(int playerid, int description) {

		ArrayList<Playerprop> playerpropList = playerpropRepository.findPlayerpropsByPlayerIdAndDescription(playerid,
				description);

		return playerpropList;
	}

	@Override
	public ArrayList<Playerprop> findLastPlayerpropsByPlayerIdAndDescription(int playerid, int description, int lastX) {

		ArrayList<Playerprop> playerpropList = playerpropRepository
				.findLastPlayerpropsByPlayerIdAndDescription(playerid, description, lastX);

		return playerpropList;
	}

}
