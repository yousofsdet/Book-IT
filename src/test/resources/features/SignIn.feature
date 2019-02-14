Feature: As user I want to be able to login with different roles 


Scenario: 1 Login as a student to the Light Side 
	Given user is on the sign-in page 
	Then user logs in as a student with "jhelkin7u@hao123.com" and "stantonmatus" password 
	

Scenario: 2 Login as a team-leader to the Light Side 
	Given user is on the sign-in page 
	Then user logs in as a student with following credentials 
		|		email			  |  password   |
		|daldie7l@seattletimes.com|ruthannjohnes|
		
	