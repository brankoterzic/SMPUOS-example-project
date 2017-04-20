package rs.uns.acs.ftn;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


//@SpringBootApplication
//@EnableDiscoveryClient
//@EnableCircuitBreaker
@SpringCloudApplication
@EnableFeignClients
@SpringBootApplication
public class ShoppingCartServiceApplication {
	
	public static void main(String[] args) {
		
    	SpringApplication app = new SpringApplication(ShoppingCartServiceApplication.class);
    	
    	//run the application wtih the argument spring.profiles.active=nameOfTheProfile
		//profiles - development, testing, deployment
		Object[] argsObject = Arrays.asList(args).stream()
				.filter(x -> x.contains("spring.profiles.active")).toArray();
		String[] profileArg = Arrays.copyOf(argsObject, argsObject.length, String[].class);
		if(profileArg.length != 0){
			app.setAdditionalProfiles(profileArg[0].split("=")[1]);
		} else {
			//if there is no active profile set through args, set a default profile - development
			//Active profile for tests will be set using @ActiveProfile annotation
			app.setAdditionalProfiles("development");
		}
		
    	app.run(args);

	}
}
