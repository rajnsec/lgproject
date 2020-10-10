package com.wellsfargo.fsd.its.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellsfargo.fsd.its.entity.Interview;

public interface InterviewRepository extends JpaRepository<Interview, Integer> {

}
