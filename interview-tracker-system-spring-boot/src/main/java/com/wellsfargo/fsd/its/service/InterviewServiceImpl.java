package com.wellsfargo.fsd.its.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.fsd.its.dao.InterviewRepository;
import com.wellsfargo.fsd.its.dao.UserRepository;
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
				throw new ITSException("Interview Id is not found");
			}

			interviewRepo.save(interview);
		}
		return interview;
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

}
