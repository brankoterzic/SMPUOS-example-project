package rs.uns.acs.ftn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@Configuration
class MyConfiguration {

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class RibbonApplication {

	@Autowired
	private LoadBalancerClient lb;
	
	@Autowired
	private RestTemplate rest;
	
	@RequestMapping("/choose")// Do load-balancing among the user-service instances
	public ServiceInstance choose(){
		return lb.choose("user-service");
	}
	
	@RequestMapping(value = "/", produces = "text/html")
	public String produce(){
		String s = rest.getForObject("http://user-service/hello/?name={name}", String.class, "IGIS");
		return String.format("<html>"
								+ "<head>"
									+ "<title>TEST</title>"
								+ "</head>"
									+ "<body>"
										+ "<h1>%s</h1>"
									+ "</body>"
								+ "</html>", s);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(RibbonApplication.class, args);
	}
}
