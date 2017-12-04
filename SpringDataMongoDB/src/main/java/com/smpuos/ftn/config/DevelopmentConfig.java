package com.smpuos.ftn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
	@PropertySource("classpath:general.properties"),
	@PropertySource("classpath:development.properties")
})
@Profile("development")
public class DevelopmentConfig {

}
