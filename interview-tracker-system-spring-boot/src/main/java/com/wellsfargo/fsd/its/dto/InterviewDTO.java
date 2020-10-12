package com.wellsfargo.fsd.its.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InterviewDTO {

	@NotNull(message = "Interview ID is required")
	private Integer interviewId;
	
	@NotNull(message = "Interviewer Name is required")
	@Size(min=5, max=30, message="Interviewer Name should have 5 to 30 characters")
	private String interviewerName;
	
	@NotNull(message = "Interview Name is required")
	@Size(min=3, max=30, message="Interview Name should have 3 to 30 characters")
	private String interviewName;
	
	@NotNull(message = "User Skills is required")
	@Size(min=5, max=30, message="User Skills should have 5 to 30 characters")
	private String usersSkills;
	
	private LocalTime time;
		
	private LocalDate date;
	
	@NotNull(message = "Interview Status is required")
	@Size(min=5, max=100, message="Interview Status should have 5 to 100 characters")
	private String interviewStatus;
	
	@NotNull(message = "Remarks is required")
	@Size(min=5, max=100, message="Remarks should have 5 to 100 characters")
	private String remarks;

	public Integer getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(Integer interviewId) {
		this.interviewId = interviewId;
	}

	public String getInterviewerName() {
		return interviewerName;
	}

	public void setInterviewerName(String interviewerName) {
		this.interviewerName = interviewerName;
	}

	public String getInterviewName() {
		return interviewName;
	}

	public void setInterviewName(String interviewName) {
		this.interviewName = interviewName;
	}

	public String getUsersSkills() {
		return usersSkills;
	}

	public void setUsersSkills(String usersSkills) {
		this.usersSkills = usersSkills;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getInterviewStatus() {
		return interviewStatus;
	}

	public void setInterviewStatus(String interviewStatus) {
		this.interviewStatus = interviewStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
