package com.bizflow.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bizflow.authorization.JwtProvider;
import com.bizflow.exceptions.UserException;
import com.bizflow.models.User;
import com.bizflow.repositories.UserRepository;
import com.bizflow.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class UserServiceImplementation implements UserService {
	
	private final UserRepository userRepository;
	private final JwtProvider jwtProvider;
	
	@Override
	public User getUserFromJwtToken(String token) throws UserException {
		String email = jwtProvider.getEmailFromToken(token);
		return  getUserByEmail(email);
		
		
	}

	
	
	@Override
	public User getCurrentUser() throws UserException {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return  getUserByEmail(email);
	}
	
	

	@Override
	public User getUserByEmail(String email) throws UserException {
		
		User user = userRepository.findByEmail(email);
		
		if(user==null) {
			throw new UserException("Invalid jwt token");
		}
		return user;
	}
	
	

	@Override
	public User getUserByid(Long id) throws UserException {
		Optional<User> user = userRepository.findById(id);
		
		if(user.isEmpty())  throw new UserException("User not found by given id "+ id);
		return user.get();
	}
	
	

	@Override
	public List<User> getAllUsers() throws UserException {
		List<User> allUsers = userRepository.findAll();
		
		if(allUsers.isEmpty()) throw new UserException("No user Found in database");
		
		return allUsers;
	}

	
	
}
