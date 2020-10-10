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

import com.wellsfargo.fsd.its.entity.Interview;
import com.wellsfargo.fsd.its.exception.ITSException;
import com.wellsfargo.fsd.its.service.InterviewService;

@RestController
@RequestMapping("/interviews")
public class InterviewRestController {
	@Autowired
	private InterviewService interviewService;

	@GetMapping
	public ResponseEntity<List<Interview>> getAllInterviews() throws ITSException{
		return new ResponseEntity<List<Interview>>(interviewService.getAllInterviews(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Interview> getInterviews(@PathVariable("id") int interviewId) throws ITSException{
		ResponseEntity<Interview> resp=null;
		
		Interview interview = interviewService.getInterview(interviewId);
		
		if(interview != null) {
			resp = new ResponseEntity<Interview>(interview,HttpStatus.OK);
		}else {
			resp = new ResponseEntity<Interview>(HttpStatus.NOT_FOUND);
		}
		return resp;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteInterviews(@PathVariable("id") int interviewId) throws ITSException{
		interviewService.deleteInterview(interviewId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Interview> addInterviews(@RequestBody Interview interview) throws ITSException{
		return new ResponseEntity<Interview>(interviewService.add(interview),HttpStatus.OK);
	} 
	
	@PutMapping
	public ResponseEntity<Interview> updateUser(@RequestBody Interview interview) throws ITSException{
		return new ResponseEntity<Interview>(interviewService.save(interview),HttpStatus.OK);
	} 
}
