package com.nbaproject.launcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.nbaproject"})
//@EnableJpaRepositories({"com.nbaproject"})
//@EntityScan({"com.nbaproject"})
public class NBAStatisticsApplication extends SpringBootServletInitializer{
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(NBAStatisticsApplication.class);
	}
	

	public static void main(String[] args) {
		
		SpringApplication.run(NBAStatisticsApplication.class, args);
		
	}

}
