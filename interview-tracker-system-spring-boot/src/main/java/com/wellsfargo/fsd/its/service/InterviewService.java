package com.wellsfargo.fsd.its.service;

import java.util.List;

import com.wellsfargo.fsd.its.dto.InterviewDTO;
import com.wellsfargo.fsd.its.dto.UserDTO;
import com.wellsfargo.fsd.its.entity.Interview;
import com.wellsfargo.fsd.its.entity.User;
import com.wellsfargo.fsd.its.exception.ITSException;

public interface InterviewService {
	
	Interview addInterview(Interview interview) throws ITSException;
	Interview saveInterview(Interview interview) throws ITSException;
	
	boolean deleteInterview(int interviewId) throws ITSException;
	
	Interview getInterview(int interviewId) throws ITSException;
	List<Interview> getAllInterviews() throws ITSException;
	
	void removeUserFromInterviews(User user) throws ITSException;
	
	InterviewDTO entityToDto(Interview interview) throws ITSException;
	Interview dtoToEntity(InterviewDTO userDTO) throws ITSException;
	List<InterviewDTO> entityToDto(List<Interview> interviews) throws ITSException;

}
