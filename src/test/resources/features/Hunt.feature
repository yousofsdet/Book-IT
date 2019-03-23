Feature: As user I want to be able to reserve a conference 

Scenario: 1 Verify general schedule on the light side 
	Given user is on the sign-in page 
	When user logs in as a team-leader with "schin8js@lulu.com" email and "sharronchin" password 
	And user navigates to the hunt 
	Then user reservers the conference with following parameters 
		|fist name|last name|room|		date	 | from |   to  |
		| Sharron |  Chin   |yale|March 22, 2019 |9:30pm|10:00pm|
	And user verifies that the reservation with following parameters was created 
		|fist name|last name|room|		date	 | from |   to  |
		| Sharron |  Chin   |yale|March 22, 2019 |9:30pm|10:00pm|