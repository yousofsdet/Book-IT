package com.bookit.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bookit.utils.CommonSeleniumUtils;
import com.bookit.utils.Driver;

public class MapPage {
	CommonSeleniumUtils utils = new CommonSeleniumUtils();
	private final String lightsidetitle = "light-side today";
	
	@FindBy(linkText="map")
	public WebElement map;
	
	@FindBy(linkText="schedule")
	public WebElement schedule;
	
	@FindBy(linkText="general")
	public WebElement general;
	
	@FindBy(linkText="my")
	public WebElement my;
	
	@FindBy(linkText="hunt")
	public WebElement hunt;
	
	@FindBy(linkText="self")
	public WebElement self;
	
	@FindBy(linkText="team")
	public WebElement team;
	
	@FindBy(linkText="sign-out")
	public WebElement signout;
	
	@FindBy(css="div.hero-body h2")
	public WebElement generalschedulesubtitle;
	
	public MapPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	public void goToGeneralSchedule() {
		schedule.click();
		utils.waitForVissibilityOfElement(general);
		general.click();
		utils.waitForPageLoaded();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		utils.waitForElement(generalschedulesubtitle);
		System.out.println(generalschedulesubtitle.getText());
		Assert.assertTrue(generalschedulesubtitle.getText().equals(lightsidetitle));
	}
	
	
}
