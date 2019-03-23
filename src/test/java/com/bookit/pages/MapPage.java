package com.bookit.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bookit.utils.CommonSeleniumUtils;
import com.bookit.utils.ConfigurationReader;
import com.bookit.utils.Driver;

public class MapPage {
	CommonSeleniumUtils utils = new CommonSeleniumUtils();
	private final String lightsidetitle = "light-side today";
	private final String darksidetitle = "dark-side today";
	private final String iltitle = "dark-side today";

	@FindBy(linkText = "map")
	private WebElement map;

	@FindBy(linkText = "schedule")
	private WebElement schedule;

	@FindBy(linkText = "general")
	private WebElement general;

	@FindBy(linkText = "my")
	private WebElement my;

	@FindBy(linkText = "hunt")
	private WebElement hunt;

	@FindBy(linkText = "self")
	private WebElement self;

	@FindBy(linkText = "team")
	private WebElement team;

	@FindBy(linkText = "sign-out")
	private WebElement signout;

	@FindBy(css = "div.hero-body h2")
	private WebElement generalschedulesubtitle;

	public MapPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	public void goToGeneralSchedule() {
		schedule.click();
		utils.waitForVisibility(general, 20);
		general.click();
		new WebDriverWait(Driver.getDriver(), Integer.valueOf(ConfigurationReader.getProperty("timeout")))
				.until(ExpectedConditions.textToBePresentInElement(generalschedulesubtitle, lightsidetitle));
		System.out.println(generalschedulesubtitle.getText());
		Assert.assertTrue(generalschedulesubtitle.getText().equals(lightsidetitle));
	}
	public void goToGeneralSchedule(String location) {
		schedule.click();
		utils.waitForVisibility(general, 20);
		general.click();
		new WebDriverWait(Driver.getDriver(), Integer.valueOf(ConfigurationReader.getProperty("timeout")))
				.until(ExpectedConditions.textToBePresentInElement(generalschedulesubtitle, location));
		System.out.println(generalschedulesubtitle.getText());
		Assert.assertTrue(generalschedulesubtitle.getText().equals(location));
	}

	public void goToHunt() {
		utils.waitForVisibility(hunt, 20);
		hunt.click();
	}

}
