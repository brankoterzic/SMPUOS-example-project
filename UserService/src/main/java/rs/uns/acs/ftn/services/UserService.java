package rs.uns.acs.ftn.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import rs.uns.acs.ftn.models.User;
import rs.uns.acs.ftn.repositories.UserRepository;

@Service
public class UserService extends AbstractCRUDService<User, String>{
	
	private UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository){
		super(userRepository);
		this.userRepository = userRepository;
	}
	
	public User login(String userName, String password){
		User user = userRepository.findByUserNameAndActive(userName, true);
		
		if(user != null){
			if(user.getPassword().equals(password)){
				return user;
			}
		}
		
		return null;
	}
	
	public List<User> findByFirstName(String firstName){
		return userRepository.findByFirstName(firstName);
	}
	
	public Page<User> findByFirstName(String firstName, Pageable pageable){
		return userRepository.findByFirstName(firstName, pageable);
	}

	public User findByIdAndActive(String userId, Boolean isActive) {
		return userRepository.findByIdAndActive(userId, isActive);
	}

}
