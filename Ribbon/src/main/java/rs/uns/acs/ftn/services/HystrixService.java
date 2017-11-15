package rs.uns.acs.ftn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class HystrixService {

	@Autowired
	private Hello hello;// feign client
	
	@HystrixCommand(fallbackMethod="defaultHi")// method that will be called if the hello(user-service: users/hello) method fails = if the user service is down
	public String hello(){
		return hello.hello();
	}
	
	public String defaultHi(){ // signature of failbackMethod must match hello method!
		return "DEFAULT HI";
	}
	
	@FeignClient("user-service")//the spring.application.name property value, for the service
	public interface Hello {
		@RequestMapping(value = "users/hello", method = RequestMethod.GET)// the endpoint which will be balanced over
		String hello();// the method specification must be the same as for users/hello
	}
}
