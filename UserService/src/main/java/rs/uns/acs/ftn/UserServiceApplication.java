package rs.uns.acs.ftn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableAutoConfiguration
@EnableDiscoveryClient
@SpringBootApplication
public class UserServiceApplication {
	
	public static void main(String[] args) {
		
    	SpringApplication app = new SpringApplication(UserServiceApplication.class);		
    	app.run(args);

	}
}
