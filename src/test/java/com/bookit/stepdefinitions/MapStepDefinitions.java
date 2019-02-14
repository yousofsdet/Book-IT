package com.bookit.stepdefinitions;

import com.bookit.pages.MapPage;

import cucumber.api.java.en.Then;

public class MapStepDefinitions {
	MapPage map = new MapPage();

	@Then("^user navigates to general schedule and verifies subtitle$")
	public void user_navigates_to_general_schedule_and_verifies_subtitle() throws Throwable {
		map.goToGeneralSchedule();
	}
}
