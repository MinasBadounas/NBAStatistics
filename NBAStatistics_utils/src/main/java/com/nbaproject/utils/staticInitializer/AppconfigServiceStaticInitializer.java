package com.nbaproject.utils.staticInitializer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.nbaproject.service.appconfig.AppconfigService;


@Component
@Order(1)
public class AppconfigServiceStaticInitializer {
	
	@Autowired
	private AppconfigService appconfigService;
	
	private static AppconfigServiceStaticInitializer appconfigServiceStaticInitializer;
	
	@PostConstruct
	public void init() {
		appconfigServiceStaticInitializer = this;
		appconfigServiceStaticInitializer.appconfigService = this.appconfigService;
	}

	public AppconfigService getAppconfigService() {
		return appconfigService;
	}

	public void setAppconfigService(AppconfigService appconfigService) {
		appconfigService = appconfigService;
	}
	
	public static String getKeyValuefromAppconfig(String keytitle) {

		return appconfigServiceStaticInitializer.appconfigService.findKeyValue(keytitle);

	}

}
