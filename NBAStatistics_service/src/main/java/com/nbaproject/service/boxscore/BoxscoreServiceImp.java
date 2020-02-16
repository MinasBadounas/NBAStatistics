package com.nbaproject.service.boxscore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbaproject.entities.Boxscore;
import com.nbaproject.entities.Player;
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
		
		if(boxscoreRepository.findMaxGameId()==null) {
			maxId=0;
		}
		else {
			maxId=boxscoreRepository.findMaxGameId();
		}
		
		return maxId;
	}

}
