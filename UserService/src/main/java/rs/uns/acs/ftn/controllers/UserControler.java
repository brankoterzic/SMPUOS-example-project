package rs.uns.acs.ftn.controllers;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.uns.acs.ftn.models.User;
import rs.uns.acs.ftn.services.UserService;

@RestController
@RequestMapping("users")
public class UserControler extends AbstractRESTController<User, String>{
	
	@Autowired
	Environment environment;
	
	private UserService userService;
	
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
	public UserControler(UserService userService) {
		super(userService);
		this.userService = userService;
	}
	
	@RequestMapping(value = "/hello")
	public String hello(){

		return NAMES[random.nextInt(NAMES.length)] +"[PORT: "+ environment.getProperty("local.server.port") + "]";
	}
	
	@RequestMapping(value = "/checkUser", method = RequestMethod.GET)
	public Boolean checkUser(
			@RequestParam(name = "userId") String userId
	){
		User user = userService.findByIdAndActive(userId, true);
		return user != null;
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public User login(
			@RequestParam(name = "mail") String mail,
			@RequestParam(name = "password") String password){
		return userService.login(mail, password);
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
