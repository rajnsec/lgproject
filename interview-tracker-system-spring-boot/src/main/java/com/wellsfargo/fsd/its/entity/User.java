package com.wellsfargo.fsd.its.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="users")
public class User implements Serializable,Comparable<User> {
	
	@Id
	private Integer userId;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String mobile;
	
	@ManyToOne
	@JoinColumn(name="interview")
	private Interview interview;
	
	
	public User() {
		//left unimplemented.
	}


	public User(Integer userId, String firstName, String lastName, String email, String mobile, Interview interview) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.interview = interview;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public Interview getInterview() {
		return interview;
	}


	public void setInterview(Interview interview) {
		this.interview = interview;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", mobile=" + mobile + ", interview=" + interview + "]";
	}

	public int compareTo(User o) {
		return this.userId.compareTo(o.userId);
	}
		
}
