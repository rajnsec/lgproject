package com.wellsfargo.fsd.its.service;

import java.util.List;

import com.wellsfargo.fsd.its.entity.User;
import com.wellsfargo.fsd.its.exception.ITSException;

public interface UserService {

	User add(User user) throws ITSException;
	User save(User user) throws ITSException;
	
	boolean deleteUser(int userId) throws ITSException;
	
	User getUser(int userId) throws ITSException;
	List<User> getAllUsers() throws ITSException;
}