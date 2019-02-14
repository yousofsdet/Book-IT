package com.bookit.suitesetupandteardown;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

import com.bookit.utils.ConfigurationReader;
import com.bookit.utils.Driver;
import com.cucumber.listener.Reporter;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class SuiteSetupAndTearDown {
	
	@Before
	public void suiteSetup(Scenario scenario) {
		Reporter.assignAuthor("Cybertek Alpha");
		Driver.initializeDriver();
		Driver.getDriver().manage().window().maximize();
		Driver.getDriver().manage().timeouts().implicitlyWait(Integer.valueOf(ConfigurationReader.getProperty("timeout")),TimeUnit.SECONDS);
		System.out.println();
		System.out.println("...........START AUTOMATION.............");
		System.out.println();
	}
	
	@After
	public void suiteTearDown(Scenario scenario) {
		try {
			if (scenario.isFailed()) {
				TakesScreenshot takesScreenshot = (TakesScreenshot) Driver.getDriver();
				byte[] image = takesScreenshot.getScreenshotAs(OutputType.BYTES);
				scenario.embed(image, "image/png");
			}
		} catch (WebDriverException e) {
			System.out.println("WebDriverException found");
		}
		Driver.closeDriver();
		System.out.println();
		System.out.println("...........END AUTOMATION.............");
		System.out.println();
	}
}
