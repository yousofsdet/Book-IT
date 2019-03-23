@smoke
Feature: Smoke test

Scenario: 1 Login as a student to the Light Side view
	Given user is on the sign-in page 
	And user logs in as a student with "jhelkin7u@hao123.com" and "stantonmatus" password 
	Then user navigates to general schedule and verifies "light-side today" subtitle
	
Scenario: 2 Login as a student to the Dark Side view
	Given user is on the sign-in page 
	And user logs in as a student with "mcossor8l@webmd.com" and "cecilnacey" password 
	Then user navigates to general schedule and verifies "dark-side today" subtitle
	
Scenario: 3 Login as a student to the Chicako view 
	Given user is on the sign-in page 
	And user logs in as a student with "htwinbrowb4@blogspot.com" and "kanyabang" password
	Then user navigates to general schedule and verifies "il today" subtitle
		