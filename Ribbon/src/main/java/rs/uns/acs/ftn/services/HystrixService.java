package rs.uns.acs.ftn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import rs.uns.acs.ftn.RibbonApplicationAndHystrix.Hello;

@Service
public class HystrixService {

	@Autowired
	private Hello hello;// feign client
	
	@HystrixCommand(fallbackMethod="defaultHi")// method that will be called if the hello method fails = if the user service is down
	public String hello(String name){
		return hello.hello(name);
	}
	
	public String defaultHi(String name){ // signature of failbackMethod must match hello method!
		return "DEFAULT HI";
	}
}
