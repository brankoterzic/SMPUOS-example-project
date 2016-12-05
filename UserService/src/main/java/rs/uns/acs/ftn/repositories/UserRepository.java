package rs.uns.acs.ftn.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.repository.MongoRepository;

import rs.uns.acs.ftn.models.User;

public interface UserRepository extends MongoRepository<User, String>{

//	  @Query("{ 'firstname' : ?0 }")
//	  List<User> findByFirstName(String firstName);
	
	List<User> findByFirstName(String firstName);
	
	List<User> findByFirstName(String firstName, Sort sort);
	
	Page<User> findByFirstName(String firstName, Pageable pageable);
	
//	Stream<User> findByFirstName(String firstName);
	
	List<User> findByFirstNameAndLastName(String firsttName, String lastName);
	
	List<User> findByAgeGreaterThan(Integer age);
	
	List<User> findByAgeLessThan(Integer age);
	
	List<User> findByAgeBetween(Integer from, Integer to);
	
	List<User> findByFirstNameNotNull();
	
	List<User> findByFirstNameNull();
	
	List<User> findByUserLocationNear(Point point, Distance distance);
	
	List<User> findByUserLocationWithin(GeoJsonPolygon polygon);
	
	List<User> findByActiveIsTrue();
	
	List<User> findByActiveIsFalse();
	
	List<User> findByDateOfBirthBetween(Date start, Date end);

	User findByIdAndActive(String userId, Boolean isActive);
	
	User findByMailAndActive(String mail, Boolean isActive);
	
}
