package com.wellsfargo.fsd.its.service;

import java.util.List;

import com.wellsfargo.fsd.its.entity.User;
import com.wellsfargo.fsd.its.exception.ITSException;

public interface UserService {

	User add(User user) throws ITSException;
	User save(User user) throws ITSException;
	
	boolean deleteContact(int contactId) throws ITSException;
	
	User getContact(int contactId) throws ITSException;
	List<User> getAllContacts() throws ITSException;
}
