package com.bookit.utils;

public class User {
	private String firstName;
	private String lastName;
	private String email;
	private String team;
	private String role;

	public User(String firstName, String lastName, String email, String team, String role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.team = team;
		this.role = role;
	}
	
	public User() {
		
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

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getRole() {
		if (role.contains("lead") || role.contains("lead") || role.contains("leader") || role.contains("Leader")) {
			return "student-team-leader";
		} else if (role.contains("teacher") || role.contains("Teacher")) {
			return "teacher";
		} else {
			return "student-team-member";
		}
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "main.java.app.Student{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\''
				+ ", email='" + email + '\'' + ", team='" + team + '\'' + ", role='" + role + '\'' + '}';
	}

}
