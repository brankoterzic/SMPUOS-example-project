package com.smpuos.ftn.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GlobalConfig {

	@Value("${general.dateFormat}")
	private String dateFormat;
	
	@Bean
	public RestTemplate restTemplate(){
		RestTemplate template = new RestTemplate();
		return template;
	}
}
