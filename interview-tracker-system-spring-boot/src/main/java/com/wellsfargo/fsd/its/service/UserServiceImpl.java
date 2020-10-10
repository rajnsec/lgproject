package com.wellsfargo.fsd.its.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.fsd.its.dao.UserRepository;
import com.wellsfargo.fsd.its.entity.User;
import com.wellsfargo.fsd.its.exception.ITSException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	@Transactional
	public User add(User user) throws ITSException {
		if (user != null) {
			if (userRepo.existsById(user.getUserId())) {
				throw new ITSException("User Id is already in use");
			}

			userRepo.save(user);
		}
		return user;
	}

	@Override
	@Transactional
	public User save(User contact) throws ITSException {
		if (contact != null) {
			if (!userRepo.existsById(contact.getUserId())) {
				throw new ITSException("User Id is not found");
			}

			userRepo.save(contact);
		}
		return contact;
	}

	@Override
	@Transactional
	public boolean deleteUser(int userId) throws ITSException {
		if (!userRepo.existsById(userId)) {
			throw new ITSException("Contact Id is not found");
		}
		
		userRepo.deleteById(userId);
		return true;
	}

	@Override
	public User getUser(int userId) throws ITSException {
		return userRepo.findById(userId).orElse(null);
	}

	@Override
	public List<User> getAllUsers() throws ITSException {
		return userRepo.findAll();
	}

}
