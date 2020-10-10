package com.wellsfargo.fsd.its.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.fsd.its.entity.User;
import com.wellsfargo.fsd.its.exception.ITSException;
import com.wellsfargo.fsd.its.service.UserService;

@RestController
@RequestMapping("/users")
public class UserRestController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() throws ITSException{
		return new ResponseEntity<List<User>>(userService.getAllUsers(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUsers(@PathVariable("id") int uid) throws ITSException{
		ResponseEntity<User> resp=null;
		
		User user = userService.getUser(uid);
		
		if(user != null) {
			resp = new ResponseEntity<User>(user,HttpStatus.OK);
		}else {
			resp = new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return resp;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUsers(@PathVariable("id") int uid) throws ITSException{
		userService.deleteUser(uid);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<User> addUser(@RequestBody User user) throws ITSException{
		return new ResponseEntity<User>(userService.add(user),HttpStatus.OK);
	} 
	
	@PutMapping
	public ResponseEntity<User> updateUser(@RequestBody User user) throws ITSException{
		return new ResponseEntity<User>(userService.save(user),HttpStatus.OK);
	} 
}
