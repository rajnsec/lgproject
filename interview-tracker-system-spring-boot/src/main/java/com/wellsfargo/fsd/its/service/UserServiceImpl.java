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
	private UserRepository contactRepo;

	@Override
	@Transactional
	public User add(User contact) throws ITSException {
		if (contact != null) {
			if (contactRepo.existsById(contact.getContactId())) {
				throw new ITSException("Contact Id is already in use");
			}
			
			if (contactRepo.existsByMobile(contact.getMobile())) {
				throw new ITSException("Mobile is already in use");
			}

			contactRepo.save(contact);
		}
		return contact;
	}

	@Override
	@Transactional
	public User save(User contact) throws ITSException {
		if (contact != null) {
			if (!contactRepo.existsById(contact.getContactId())) {
				throw new ITSException("Contact Id is not found");
			}

			contactRepo.save(contact);
		}
		return contact;
	}

	@Override
	@Transactional
	public boolean deleteContact(int contactId) throws ITSException {
		if (!contactRepo.existsById(contactId)) {
			throw new ITSException("Contact Id is not found");
		}
		
		contactRepo.deleteById(contactId);
		return true;
	}

	@Override
	public User getContact(int contactId) throws ITSException {
		return contactRepo.findById(contactId).orElse(null);
	}

	@Override
	public List<User> getAllContacts() throws ITSException {
		return contactRepo.findAll();
	}

}
