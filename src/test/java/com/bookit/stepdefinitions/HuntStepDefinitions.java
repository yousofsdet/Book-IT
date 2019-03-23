package com.bookit.stepdefinitions;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.bookit.pages.HuntPage;
import com.bookit.pages.MapPage;
import com.bookit.utils.DBUtils;

import cucumber.api.java.en.Then;

public class HuntStepDefinitions {
	HuntPage hunt = new HuntPage();
	MapPage mappage = new MapPage();
	DBUtils db = new DBUtils();

	@Then("user reservers the conference with following parameters")
	public void user_reservers_the_conference_with_following_parameters(List<Map<String, String>> data) {
		for (Map<String, String> map : data) {
			hunt.findAvailabeRooms(map.get("date"), map.get("from"), map.get("to"));
			hunt.reserveConference(map.get("room"));
		}
		mappage.goToGeneralSchedule();
	}

	@Then("user verifies that the reservation with following parameters was created")
	public void user_verifies_that_the_reservation_with_following_parameters_was_created(List<Map<String, String>> data) {
		for (Map<String, String> map : data) {
			Assert.assertTrue(db.verifyConferenceHasBeenCraeted(map.get("fist name"), map.get("last name"), map.get("date")));
		}
	}
	


}
