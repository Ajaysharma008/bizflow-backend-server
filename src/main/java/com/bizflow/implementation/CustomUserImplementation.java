package com.bizflow.implementation;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bizflow.domains.CustomUserDetails;
import com.bizflow.models.User;
import com.bizflow.repositories.UserRepository;

@Service
public class CustomUserImplementation implements UserDetailsService {
	
	private final UserRepository userRepository;

	public CustomUserImplementation(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		
		if(user==null) throw new UsernameNotFoundException("user not found");
		
		return new CustomUserDetails(user);
		
	}

}
