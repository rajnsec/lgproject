package com.wellsfargo.fsd.its.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.wellsfargo.fsd.its.dto.AttendeeDTO;
import com.wellsfargo.fsd.its.dto.InterviewDTO;
import com.wellsfargo.fsd.its.entity.Interview;
import com.wellsfargo.fsd.its.entity.User;
import com.wellsfargo.fsd.its.exception.ITSException;
import com.wellsfargo.fsd.its.service.InterviewService;
import com.wellsfargo.fsd.its.service.UserService;

@RestController
@RequestMapping("/interviews")
public class InterviewRestController {
	@Autowired
	private InterviewService interviewService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<InterviewDTO> addInterviews(@Valid @RequestBody InterviewDTO interviewDTO) throws ITSException{
		Interview interview=null;
		interview=interviewService.dtoToEntity(interviewDTO);
		interviewDTO=interviewService.entityToDto(interviewService.addInterview(interview));
		return new ResponseEntity<InterviewDTO>(interviewDTO,HttpStatus.OK);
	} 
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteInterviews(@PathVariable("id") int interviewId) throws ITSException{
		interviewService.deleteInterview(interviewId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
		
	@PutMapping("/{id}/{status}")
	public ResponseEntity<Void> updateUser(@PathVariable("id") int interviewId, @PathVariable("status") String interviewStatus) throws ITSException{
		interviewService.changeInterviewStatus(interviewId, interviewStatus);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/interviewName/{interviewName}")
	public ResponseEntity<InterviewDTO> getInterviewByName(@PathVariable("interviewName") String interviewName) throws ITSException{
		ResponseEntity<InterviewDTO> resp=null;
		InterviewDTO interviewDTO=null;
		Interview interview = interviewService.getInterviewByName(interviewName);
		interviewDTO=interviewService.entityToDto(interview);
		if(interviewDTO != null) {
			resp = new ResponseEntity<InterviewDTO>(interviewDTO,HttpStatus.OK);
		}else {
			resp = new ResponseEntity<InterviewDTO>(HttpStatus.NOT_FOUND);
		}
		return resp;
	}
	@GetMapping("/interviewerName/{interviewerName}")
	public ResponseEntity<InterviewDTO> getInterviewByInterviewer(@PathVariable("interviewerName") String interviewerName) throws ITSException{
		ResponseEntity<InterviewDTO> resp=null;
		InterviewDTO interviewDTO=null;		
		Interview interview = interviewService.getInterviewerByName(interviewerName);
		interviewDTO=interviewService.entityToDto(interview);
		if(interviewDTO != null) {
			resp = new ResponseEntity<InterviewDTO>(interviewDTO,HttpStatus.OK);
		}else {
			resp = new ResponseEntity<InterviewDTO>(HttpStatus.NOT_FOUND);
		}
		return resp;
	}
	@GetMapping("/count")
	public ResponseEntity<Integer> getInterviewCount() throws ITSException{
		
		return new ResponseEntity<Integer>(interviewService.getInterviewCount(),HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<InterviewDTO>> getAllInterviews() throws ITSException{
		List<InterviewDTO> resp=null;
		resp=interviewService.entityToDto(interviewService.getAllInterviews());
		return new ResponseEntity<List<InterviewDTO>>(resp,HttpStatus.OK);
	}
	
	@PutMapping("addusertoInterview/{id}/{userId}")
	public ResponseEntity<Void> addUserToInterview(@PathVariable("id") int interviewId, @PathVariable("userId") int userId) throws ITSException{
		User user =userService.getUser(userId);
		interviewService.saveUserToInterview(interviewId,user);
		Interview interview=interviewService.getInterview(interviewId);
		interviewService.saveInterview(interview);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<AttendeeDTO> getInterviews(@PathVariable("id") int interviewId) throws ITSException{
		ResponseEntity<AttendeeDTO> resp=null;
		AttendeeDTO attendeeDTO=new AttendeeDTO();
		
		Interview interview = interviewService.getInterview(interviewId);
		attendeeDTO=interviewService.entityToAttendeeDto(interview);
		
		if(attendeeDTO != null) {
			resp = new ResponseEntity<AttendeeDTO>(attendeeDTO,HttpStatus.OK);
		}else {
			resp = new ResponseEntity<AttendeeDTO>(HttpStatus.NOT_FOUND);
		}
		return resp;
	}

	
	
	
	
}
