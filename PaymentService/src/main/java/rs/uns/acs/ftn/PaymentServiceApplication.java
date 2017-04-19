package rs.uns.acs.ftn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableAutoConfiguration
@EnableDiscoveryClient
@SpringBootApplication
public class PaymentServiceApplication {

	public static void main(String[] args) {
    	SpringApplication app = new SpringApplication(PaymentServiceApplication.class);    	
    	app.run(args);	
    }
}
