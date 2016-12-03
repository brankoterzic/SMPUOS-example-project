package rs.uns.acs.ftn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@PropertySources({
	@PropertySource("classpath:general.properties"),
	@PropertySource("classpath:development.properties")
})

@EnableAsync
@EnableScheduling
@Profile("development")
public class DevelopmentConfig {

}
