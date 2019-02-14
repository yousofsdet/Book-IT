Feature: As user I want to be able to see general schedule 


Scenario: 1 Verify general schedule on the light side 
	Given user is on the sign-in page 
	Then user logs in as a student with "jhelkin7u@hao123.com" and "stantonmatus" password
	Then user navigates to general schedule and verifies subtitle 