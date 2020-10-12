package com.wellsfargo.fsd.its.service;

import java.util.List;

import com.wellsfargo.fsd.its.dto.UserDTO;
import com.wellsfargo.fsd.its.entity.User;
import com.wellsfargo.fsd.its.exception.ITSException;

public interface UserService {

	User addUser(User user) throws ITSException;
	User saveUser(User user) throws ITSException;
	
	boolean deleteUser(int userId) throws ITSException;
	
	User getUser(int userId) throws ITSException;
	List<User> getAllUsers() throws ITSException;
	
	UserDTO entityToDto(User user) throws ITSException;
	User dtoToEntity(UserDTO userDTO) throws ITSException;
	List<UserDTO> entityToDto(List<User> users) throws ITSException;
}
