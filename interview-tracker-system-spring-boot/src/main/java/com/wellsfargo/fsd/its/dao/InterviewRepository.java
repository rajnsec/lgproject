package com.wellsfargo.fsd.its.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.wellsfargo.fsd.its.entity.Interview;

public interface InterviewRepository extends JpaRepository<Interview, Integer> {
	
	Interview findByInterviewName(String interviewName);
	
	Interview findByInterviewerName(String interviewerName);
	
	 @Modifying
	@Query("UPDATE Interview i set i.interviewStatus=:status WHERE i.interviewId=:id")
	void updateStatus(String status,int id);

}
