package rs.uns.acs.ftn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


//@SpringBootApplication
//@EnableDiscoveryClient
//@EnableCircuitBreaker
@EnableAutoConfiguration
@SpringCloudApplication
@EnableFeignClients
public class ShoppingCartServiceApplication {
	
	public static void main(String[] args) {
		
    	SpringApplication app = new SpringApplication(ShoppingCartServiceApplication.class);
    	app.run(args);

	}
}
