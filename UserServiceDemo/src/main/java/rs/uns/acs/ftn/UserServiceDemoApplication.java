package rs.uns.acs.ftn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

 //This service is used to demonstrate how ribbon round robin works with load-balancing 

@EnableDiscoveryClient
@SpringBootApplication
@RestController
@RequestMapping("users")
public class UserServiceDemoApplication {

	@RequestMapping(value = "/hello")
	public String hello(
			
			@RequestParam(name = "name") String name){
		return "Hello " + name + " " + "PORT 8083";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(UserServiceDemoApplication.class, args);
	}
}
