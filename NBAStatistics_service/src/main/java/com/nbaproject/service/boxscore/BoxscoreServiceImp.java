package com.nbaproject.service.boxscore;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbaproject.entities.Boxscore;
import com.nbaproject.repository.boxscore.BoxscoreRepository;

@Service
public class BoxscoreServiceImp implements BoxscoreService {

	@Autowired
	private BoxscoreRepository boxscoreRepository;

	@Override
	public void saveBoxscore(Boxscore boxscore) {

		boxscoreRepository.save(boxscore);
	}

	@Override
	public int findMaxGameId() {

		int maxId;

		if (boxscoreRepository.findMaxGameId() == null) {
			maxId = 0;
		} else {
			maxId = boxscoreRepository.findMaxGameId();
		}

		return maxId;
	}

	@Override
	public ArrayList<Integer> findAllGameIdBoxscore() {

		ArrayList<Integer> gameIdBoxscoreList = boxscoreRepository.findAllGameId();

		return gameIdBoxscoreList;
	}

	@Override
	public Boxscore findBoxscorebyGameId(int gameid) {

		Boxscore boxscore = boxscoreRepository.findBoxscorebyGameId(gameid);

		return boxscore;
	}

	@Override
	public ArrayList<Boxscore> findBoxscoresbyDate(String date) {
		
		ArrayList<Boxscore> boxScoreList=boxscoreRepository.findBoxscoresbyDate(date);
		
		return boxScoreList;
	}

}
