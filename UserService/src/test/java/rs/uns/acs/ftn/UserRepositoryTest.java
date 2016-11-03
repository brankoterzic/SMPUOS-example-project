package rs.uns.acs.ftn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import rs.uns.acs.ftn.models.User;
import rs.uns.acs.ftn.repositories.UserRepository;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UserServiceApplication.class)
@WebAppConfiguration
@ActiveProfiles("testing")
public class UserRepositoryTest {
	
	@Value("${general.dateFormat}")
	private String dateFormat;
	
	@Autowired
	private UserRepository userRepository;
	
	@Before
	public void insertInitialCollection() throws ParseException {
		User user1 = new User(
				null,
			    "Bill", 
			    "Miller",
			    "bm",
			    "pass123",
			    "b.m@mail.com",
			    23,
			    true,
			    new GeoJsonPoint(19.8334687,45.2527831),
			    new SimpleDateFormat(dateFormat).parse("1900-01-01T00:00:00.000Z"));
		
		User user2 = new User(
				null,
			    "Sam", 
			    "Huckerr",
			    "sh",
			    "pass1",
			    "s.m@mail.com",
			    25,
			    true,
			    new GeoJsonPoint(13.8334687,48.2527831),
			    new SimpleDateFormat(dateFormat).parse("1900-01-01T00:00:00.000Z"));
		
		User user3 = new User(
				null,
			    null, 
			    "Hass",
			    "jh",
			    "pass12",
			    "j.m@mail.com",
			    33,
			    true,
			    new GeoJsonPoint(16.8334687,54.2527831),
			    new SimpleDateFormat(dateFormat).parse("1900-01-01T00:00:00.000Z"));
		
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
	}
	
	@Test
	public void testUserRepository(){
		
		List<User> users = userRepository.findAll();
		Assert.assertEquals(3, users.size());

		Page<User> page = userRepository.findAll(new PageRequest(0,20));
		Assert.assertEquals(3, page.getNumberOfElements());
		Assert.assertEquals(3, page.getTotalElements());
		
		users = userRepository.findByFirstNameAndLastName("Sam", "Huckerr");
		Assert.assertEquals(1, users.size());	
		
		users = userRepository.findByAgeGreaterThan(23);
		Assert.assertEquals(2, users.size());	

		users = userRepository.findByAgeLessThan(25);
		Assert.assertEquals(1, users.size());
		
		users = userRepository.findByAgeBetween(20, 30);
		Assert.assertEquals(2, users.size());
		
		users = userRepository.findByFirstNameNotNull();
		Assert.assertEquals(2, users.size());
		
		users = userRepository.findByFirstNameNull();
		Assert.assertEquals(1, users.size());
		
		users = userRepository.findByUserLocationNear( 
				new Point(13.8334688,48.2527831),
				new Distance(1., Metrics.KILOMETERS));
		Assert.assertEquals(1, users.size());
		
		users = userRepository.findByUserLocationWithin( 
				new GeoJsonPolygon(
						new Point(0., 0.),
						new Point (0., 50.),
						new Point(30.0, 50.),
						new Point (30., 0.),
						new Point(0., 0.))
				);
		Assert.assertEquals(2, users.size());
		
		users = userRepository.findByActiveIsTrue();
		Assert.assertEquals(3, users.size());
	}
	
	
	@After
	public void cleanUp(){
		userRepository.deleteAll();
	}

}
