package com.bookit.stepdefinitions;

import java.util.List;
import java.util.Map;

import com.bookit.pages.SignInPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SignInStepDefinitions {
	SignInPage signin = new SignInPage();

	@Given("^user is on the sign-in page$")
	public void user_is_on_the_sign_in_page() throws Throwable {
		signin.openHomePage();
	}

	@Then("^user logs in as a student with \"([^\"]*)\" and \"([^\"]*)\" password$")
	public void user_logs_in_as_a_student_with_and_password(String arg1, String arg2) throws Throwable {
		signin.login(arg1, arg2);
	}

	@Then("^user logs in as a student with following credentials$")
	public void user_logs_in_as_a_student_with_following_credentials(List<Map<String, String>> records)
			throws Throwable {
		for (Map<String, String> record : records) {
			signin.login(record.get("email"), record.get("password"));
		}
	}

	@When("user logs in as a team-leader with {string} email and {string} password")
	public void user_logs_in_as_a_team_leader_with_email_and_password(String string, String string2) {
		signin.login(string, string2);
	}
}
