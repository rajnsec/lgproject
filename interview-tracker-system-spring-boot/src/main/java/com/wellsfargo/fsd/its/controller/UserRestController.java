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

import com.wellsfargo.fsd.its.dto.UserDTO;
import com.wellsfargo.fsd.its.entity.Interview;
import com.wellsfargo.fsd.its.entity.User;
import com.wellsfargo.fsd.its.exception.ITSException;
import com.wellsfargo.fsd.its.service.InterviewService;
import com.wellsfargo.fsd.its.service.UserService;

@RestController
@RequestMapping("/users")
public class UserRestController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private InterviewService interviewService;

	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers() throws ITSException{
		List<UserDTO> resp=null;
		resp=userService.entityToDto(userService.getAllUsers());
		return new ResponseEntity<List<UserDTO>>(resp,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUsers(@PathVariable("id") int uid) throws ITSException{
		ResponseEntity<UserDTO> resp=null;
		
		User user = userService.getUser(uid);
		UserDTO userDTO=new UserDTO();
		userDTO=userService.entityToDto(user);
		if(userDTO != null) {
			resp = new ResponseEntity<UserDTO>(userDTO,HttpStatus.OK);
		}else {
			resp = new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}
		return resp;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUsers(@PathVariable("id") int uid) throws ITSException{
		User user =userService.getUser(uid);
		interviewService.removeUserFromInterviews(user);
		userService.deleteUser(uid);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) throws ITSException{
		User user=new User();
		user=userService.dtoToEntity(userDTO);		
		userDTO=userService.entityToDto(userService.addUser(user));
		return new ResponseEntity<UserDTO>(userDTO,HttpStatus.OK);
	} 
	
	@PutMapping
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) throws ITSException{
		User user=new User();
		user=userService.dtoToEntity(userDTO);		
		userDTO=userService.entityToDto(userService.saveUser(user));
		return new ResponseEntity<UserDTO>(userDTO,HttpStatus.OK);
	} 
}
