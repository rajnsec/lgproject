package com.wellsfargo.fsd.its.service;

import java.util.List;

import com.wellsfargo.fsd.its.entity.Interview;
import com.wellsfargo.fsd.its.exception.ITSException;

public interface InterviewService {
	
	Interview add(Interview interview) throws ITSException;
	Interview save(Interview interview) throws ITSException;
	
	boolean deleteInterview(int interviewId) throws ITSException;
	
	Interview getInterview(int interviewId) throws ITSException;
	List<Interview> getAllInterviews() throws ITSException;

}
