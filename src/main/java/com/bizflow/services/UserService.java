package com.bizflow.services;

import java.util.List;

import com.bizflow.exceptions.UserException;
import com.bizflow.models.User;

public interface UserService {
	
	User getUserFromJwtToken(String token) throws UserException;
	User getCurrentUser() throws UserException;
	User getUserByEmail(String email) throws UserException;
	User getUserByid(Long id) throws UserException;
	List<User> getAllUsers() throws UserException;

}
