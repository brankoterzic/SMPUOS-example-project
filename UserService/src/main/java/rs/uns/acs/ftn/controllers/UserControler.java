package rs.uns.acs.ftn.controllers;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import rs.uns.acs.ftn.models.User;
import rs.uns.acs.ftn.services.UserService;

@RestController
@RequestMapping("users")
public class UserControler extends AbstractRESTController<User, String>{
	
	private UserService userService;
	private RestTemplate restTemplate;

    private final Random random = new Random();

    private static final String[] NAMES = new String[] {
    		"Arnette Whitesides",
    		"Sherley Holifield ",
    		"Iva Mathias",
    		"Joellen Hatch",
    		"Harley Braziel",
    		"Oralee Thweatt",
    		"Mao Lammert",
    		"Dannette Peru",
    		"Sherell Service",
    		"Tamara Bratcher",
    		"Quintin Vankirk",
    		"Orval Tarter",
    		"Alysa Kesterson",
    		"Krissy Bothwell",
    		"Freeda Leicht",
    		"Gemma Crippen",
    		"Darci Caroll",
    		"Tarra Argento",
    		"Corinne Farah",
    		"Myrta Neuberger"

    };
    
	@Autowired
	public UserControler(UserService userService, RestTemplate restTemplate) {
		super(userService);
		this.userService = userService;
		this.restTemplate = restTemplate;
	}
	
	@RequestMapping(value = "/hello")
	public String hello(
			
			@RequestParam(name = "name") String name){

		return NAMES[random.nextInt(NAMES.length)] + " {PORT 8081}";
	}
	
	@RequestMapping(value = "/login")
	public User login(
			@RequestParam(name = "userName") String userName,
			@RequestParam(name = "password") String password){
		return userService.login(userName, password);
	}
	
	@RequestMapping(value = "/search/getAllProducts")
	public String getAllProducts(){
				
		String result = restTemplate.getForObject("http://localhost:8082/products", String.class);
		JsonObject obj = new JsonParser().parse(result).getAsJsonObject();
		JsonArray array = obj.get("elements").getAsJsonArray();
		
		return array.toString();
	}
	
	@RequestMapping(value = "search/findByFirstName", method = RequestMethod.GET)
	public List<User> findByAreaOfDanger(
			@RequestParam(name = "firstName") String firstName) {
		List<User> all = userService.findByFirstName(firstName);
		return all;
	}
	
	@RequestMapping(value = "search/findByFirstNamePageable", method = RequestMethod.GET)
	public Map<String, Object> findByAreaOfDanger(
			@RequestParam(name = "firstName") String firstName,
			@RequestParam(name = "page", defaultValue = "0") Integer page) {
		Page<User> all = userService.findByFirstName(firstName, new PageRequest(page, pageSize));
		return prepareListPage(all);
	}


}
