package com.wellsfargo.fsd.its.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.fsd.its.dao.InterviewRepository;
import com.wellsfargo.fsd.its.dao.UserRepository;
import com.wellsfargo.fsd.its.dto.AttendeeDTO;
import com.wellsfargo.fsd.its.dto.InterviewDTO;
import com.wellsfargo.fsd.its.dto.UserDTO;
import com.wellsfargo.fsd.its.entity.Interview;
import com.wellsfargo.fsd.its.entity.User;
import com.wellsfargo.fsd.its.exception.ITSException;

@Service
public class InterviewServiceImpl implements InterviewService {
	@Autowired
	private InterviewRepository interviewRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	@Transactional
	public Interview addInterview(Interview interview) throws ITSException {
		if (interview != null) {
			if (interviewRepo.existsById(interview.getInterviewId())) {
				throw new ITSException("Interview Id is already in use");
			}

			interviewRepo.save(interview);
		}
		return interview;
	}
	@Override
	@Transactional
	public Interview saveInterview(Interview interview) throws ITSException {
		if (interview != null) {
			if (!interviewRepo.existsById(interview.getInterviewId())) {
				throw new ITSException("Contact Id is not found");
			}

			interviewRepo.save(interview);
		}
		return interview;
	}


	@Override
	@Transactional
	public boolean changeInterviewStatus(int interviewId, String status) throws ITSException {
		
			if (!interviewRepo.existsById(interviewId)) {
				throw new ITSException("Interview Id is not found");
			}

			interviewRepo.updateStatus(status,interviewId);
		
		return true;
	}

	@Override
	@Transactional
	public boolean deleteInterview(int interviewId) throws ITSException {
		if (!interviewRepo.existsById(interviewId)) {
			throw new ITSException("Interview Id is not found");
		}
		
		interviewRepo.deleteById(interviewId);
		return true;
	}

	@Override
	public Interview getInterview(int interviewId) throws ITSException {
		return interviewRepo.findById(interviewId).orElse(null);
	}

	@Override
	public List<Interview> getAllInterviews() throws ITSException {
		return interviewRepo.findAll();
	}

	@Override
	public void removeUserFromInterviews(User user) throws ITSException {
		List<Interview> interviews=interviewRepo.findAll();
		for(Interview i:interviews)
		{
			Set<User> users=i.getUsers();
			users.remove(user);
		}
		
	}

	@Override
	public InterviewDTO entityToDto(Interview interview) {
		InterviewDTO interviewDTO=new InterviewDTO();
		interviewDTO.setInterviewId(interview.getInterviewId());
		interviewDTO.setInterviewName(interview.getInterviewName());
		interviewDTO.setInterviewerName(interview.getInterviewerName());
		interviewDTO.setInterviewStatus(interview.getInterviewStatus());
		interviewDTO.setDate(interview.getDate());
		interviewDTO.setTime(interview.getTime());
		interviewDTO.setUsersSkills(interview.getUsersSkills());
		interviewDTO.setRemarks(interview.getRemarks());
		return interviewDTO;
	}

	@Override
	public Interview dtoToEntity(InterviewDTO interviewDTO) throws ITSException {
		Interview interview=new Interview();
		interview.setInterviewId(interviewDTO.getInterviewId());
		interview.setInterviewName(interviewDTO.getInterviewName());
		interview.setInterviewerName(interviewDTO.getInterviewerName());
		interview.setInterviewStatus(interviewDTO.getInterviewStatus());
		interview.setDate(interviewDTO.getDate());
		interview.setUsersSkills(interviewDTO.getUsersSkills());
		interview.setTime(interviewDTO.getTime());
		interview.setRemarks(interviewDTO.getRemarks());
		return interview;
	}

	@Override
	public List<InterviewDTO> entityToDto(List<Interview> interviews) throws ITSException {
		return interviews.stream().map(x->entityToDto(x)).collect(Collectors.toList());
	}

	@Override
	public Interview getInterviewByName(String interviewName) throws ITSException {
		
		return interviewRepo.findByInterviewName(interviewName);
	}
	
	@Override
	public Interview getInterviewerByName(String interviewerName) throws ITSException {
		
		return interviewRepo.findByInterviewerName(interviewerName);
	}

	@Override
	public int getInterviewCount() throws ITSException {
		// TODO Auto-generated method stub
		return (int) interviewRepo.count();
	}

	@Override
	@Transactional
	public boolean saveUserToInterview(int interviewId, User user) throws ITSException {
		
			if (!interviewRepo.existsById(interviewId)) {
				throw new ITSException("Interview Id is not found");
			}

			List<Interview> interviews=interviewRepo.findAll();
			for(Interview i:interviews)
			{
				if(i.getInterviewId()==interviewId)
				{
				Set<User> users=i.getUsers();
				if(!users.isEmpty())
				{
				for(User u:users)
				{
					if(u.getUserId()==user.getUserId())
					{
						throw new ITSException("User is already an attendee");
					}
					else
					{
						users.add(user);
						i.setUsers(users);
					}
				}
				
				}				
				else
				{
					users= new HashSet<User>();
					users.add(user);
					i.setUsers(users);
					
				}
				
			}
				
			}
			return true;
	}
	@Override
	public AttendeeDTO entityToAttendeeDto(Interview interview) {
		AttendeeDTO attendeeDTO=new AttendeeDTO();
		attendeeDTO.setInterviewId(interview.getInterviewId());
		attendeeDTO.setInterviewName(interview.getInterviewName());
		attendeeDTO.setUsers(interview.getUsers());
		
		
				return attendeeDTO;
	}

	}
