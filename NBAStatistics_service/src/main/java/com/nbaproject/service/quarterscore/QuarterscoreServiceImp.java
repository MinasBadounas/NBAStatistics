package com.nbaproject.service.quarterscore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbaproject.entities.Quarter;
import com.nbaproject.repository.quarterscore.QuarterscoreRepository;

@Service
public class QuarterscoreServiceImp implements QuarterscoreService {
	
	@Autowired
	private QuarterscoreRepository quarterscoreRepository;
	
	@Override
	public void saveQuarterscore(Quarter quarterscore) {
		
		quarterscoreRepository.save(quarterscore);
		
	}

}
