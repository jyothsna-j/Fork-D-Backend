package com.forkd.forkd_backend.pojos;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

	private Long userId;
	private String username;
	private String email;
	private String name;
	private String password;
	private String role;
	private Long contactNumber;

	@JsonCreator
	public User(@JsonProperty("name") String name, @JsonProperty("username") String username,
			@JsonProperty("email") String email, @JsonProperty("password") String password,
			@JsonProperty("role") String role, @JsonProperty("contactNumber") Long contactNumber) {
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
		this.contactNumber = contactNumber;
	}

	public User(Long userId, String username, String email, String name, String password, String role,
			Long contactNumber) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.name = name;
		this.password = password;
		this.role = role;
		this.contactNumber = contactNumber;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(contactNumber, email, name, password, role, userId, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(contactNumber, other.contactNumber) && Objects.equals(email, other.email)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(role, other.role) && Objects.equals(userId, other.userId)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", email=" + email + ", name=" + name
				+ ", password=" + password + ", role=" + role + ", contactNumber=" + contactNumber + "]";
	}
	
	
}