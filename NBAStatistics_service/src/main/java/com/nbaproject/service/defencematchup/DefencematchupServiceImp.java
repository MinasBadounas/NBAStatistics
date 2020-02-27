package com.nbaproject.service.defencematchup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbaproject.entities.Defencematchup;
import com.nbaproject.repository.defencematchup.DefencematchupRepository;

@Service
public class DefencematchupServiceImp implements DefencematchupService {

	@Autowired
	private DefencematchupRepository defencematchupRepository;
	
	@Override
	public void saveDefenceMatchUp(Defencematchup defencematchup) {
		
		defencematchupRepository.save(defencematchup);
		
	}

}
