package com.bookit.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bookit.utils.CommonSeleniumUtils;
import com.bookit.utils.Driver;



public class HuntPage {
	@FindBy(css="input[id*='mat']")
	public WebElement dateelement;
	
	@FindBy(xpath="//*[contains(@placeholder,'from')]")
	public WebElement fromelement;
	
	@FindBy(xpath="//*[contains(@placeholder,'to')]")
	public WebElement toelement;
	
	@FindBy(css="button[type='submit']")
	public WebElement searchbtn;
	
	@FindBy(css="div[class='mat-select-arrow']")
	public List<WebElement> dropdowns;
	
	@FindBy(css="button[type='submit']")
	public WebElement confirmConference;
	
	CommonSeleniumUtils utils = new CommonSeleniumUtils();
	
	
	public HuntPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	public void findAvailabeRooms(String date, String from, String to) {
		String locatorFrom = "//span[contains(text(),'"+from+"')]";
		String locatorto = "//span[contains(text(),'"+to+"')]";
		utils.waitForStaleElement(dateelement);
		dateelement.clear();
		dateelement.sendKeys(date);	
		utils.waitForStaleElement(dropdowns.get(0));
		utils.waitForClickablility(dropdowns.get(0), 20);
		dropdowns.get(0).click();
		List<WebElement> fromElements = Driver.getDriver().findElements(By.xpath(locatorFrom));
		new WebDriverWait(Driver.getDriver(), 20).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(locatorFrom)));
		new WebDriverWait(Driver.getDriver(), 20).until(ExpectedConditions.visibilityOfAllElements(fromElements));
		for(WebElement e: fromElements) {
			if(!e.isDisplayed()) {
				continue;
			}else {
				utils.clickWithJS(e);
				break;
			}	
		}
		new WebDriverWait(Driver.getDriver(), 20).until(ExpectedConditions.invisibilityOfAllElements(fromElements));
		utils.waitForStaleElement(dropdowns.get(1));
		utils.waitForClickablility(dropdowns.get(1), 20);
		dropdowns.get(1).click();
		new WebDriverWait(Driver.getDriver(), 20).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(locatorto)));
		List<WebElement> toElements = Driver.getDriver().findElements(By.xpath(locatorto));
		new WebDriverWait(Driver.getDriver(), 20).until(ExpectedConditions.visibilityOfAllElements(toElements));
		for(WebElement e: toElements) {
			if(!e.isDisplayed()) {
				continue;
			}else {
				utils.clickWithJS(e);
				break;
			}
		}
		new WebDriverWait(Driver.getDriver(), 20).until(ExpectedConditions.invisibilityOfAllElements(toElements));
		utils.waitForPageToLoad(10);
		searchbtn.click();
		
		
	}
	public void reserveConference(String roomName) {
		String locator = "//p[contains(text(),'"+roomName+"')]/../..//button[contains(text(),'book')]";
		WebElement roomToBook = Driver.getDriver().findElement(By.xpath(locator));
		try {
			utils.waitForStaleElement(roomToBook);
			utils.waitForClickablility(roomToBook, 10);
			utils.clickWithJS(roomToBook);
		}catch(WebDriverException e) {
			roomToBook.click();
		}
		new WebDriverWait(Driver.getDriver(), 20).until(ExpectedConditions.invisibilityOf(roomToBook));
		utils.waitForStaleElement(confirmConference);
		utils.clickWithJS(confirmConference);
	}
}
