package com.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ecommerce.config.JwtProvider;
import com.ecommerce.exception.UserException;
import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;

@Component
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private JwtProvider jwtprovider;
	@Override
	public User findUserById(Long userId) throws UserException {
		Optional<User> user=userRepo.findById(userId);
		if(user.isPresent()) {
			return user.get();
		}
		throw new UserException("user not found with Id.."+userId);
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
		String email=jwtprovider.getEmailFromToken(jwt);
		
		User user=userRepo.findByEmail(email);
		if(user==null) {
			throw new UserException("user not found with email.."+email);
		}
		return user;
	}

}
