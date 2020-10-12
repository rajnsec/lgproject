package com.wellsfargo.fsd.its.dto;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.wellsfargo.fsd.its.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {

	
	@NotNull(message = "User ID is required")
	private Integer userId;
	
	@NotNull(message = "First Name is required")
	@Size(min=5, max=30, message="First Name should have 5 to 30 characters")
	private String firstName;
	
	@NotNull(message = "Last Name is required")
	@Size(min=3, max=25, message="Last Name should have 3 to 25 characters")
	private String lastName;
	
	@NotNull(message = "Email is required")
	@Email(message = "Valid Email is required")
	private String email;
	
	@NotNull(message = "Mobile Number is required")
	@Size(min=10, max=10, message="Mobile Number should have 10 digits")
	private String mobile;

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
	
	}
