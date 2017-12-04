package com.smpuos.ftn.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.smpuos.ftn.models.User;
import com.smpuos.ftn.repositories.UserRepository;

@Service
public class UserService extends AbstractCRUDService<User, String>{
	
	private UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository){
		super(userRepository);
		this.userRepository = userRepository;
	}
	
	public List<User> findByFirstName(String firstName){
		return userRepository.findByFirstName(firstName);
	}
	
	public Page<User> findByFirstName(String firstName, Pageable pageable){
		return userRepository.findByFirstName(firstName, pageable);
	}

}
