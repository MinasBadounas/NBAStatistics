package com.nbaproject.service.appconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbaproject.repository.appconfig.AppconfigRepository;

@Service
public class AppconfigServiceImp implements AppconfigService {

	@Autowired
	private AppconfigRepository appconfigRepository;

	@Override
	public String findKeyValue(String keytitle) {

		String keyvalue = appconfigRepository.findKeyValue(keytitle);
		return keyvalue;
	}

}
