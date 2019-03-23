package com.bookit.utils;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CommonSeleniumUtils {

	public void switchToWindowByTitle(String title) {
		Set<String> windows = Driver.getDriver().getWindowHandles();
		System.out.println("Amount of windows that are currently present :: " + windows.size());
		for (String window : windows) {
			Driver.getDriver().switchTo().window(window);
			if (Driver.getDriver().getTitle().startsWith(title)
					|| Driver.getDriver().getTitle().equalsIgnoreCase(title)) {
				break;
			} else {
				continue;
			}
		}
	}

	public void clickWithJS(WebElement elementtoclick) {
		WebDriverWait wait = new WebDriverWait(Driver.getDriver(),
				Long.parseLong(ConfigurationReader.getProperty("timeout")));
		wait.until(ExpectedConditions.elementToBeClickable(elementtoclick));
		((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", elementtoclick);
		((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", elementtoclick);
	}

	public void waitForPresenceOfElementByCss(String css) {
		WebDriverWait wait = new WebDriverWait(Driver.getDriver(),
				Long.parseLong(ConfigurationReader.getProperty("timeout")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(css)));
	}

	public void waitForVissibilityOfElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(Driver.getDriver(),
				Long.parseLong(ConfigurationReader.getProperty("timeout")));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElement(WebElement element) {
		int i = 0;
		while (i < 10) {
			try {
				element.isDisplayed();
				break;
			} catch (WebDriverException e) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
				i++;
			}
		}
	}

	public boolean verifyElementIsNotPresent(String xpath) {
		List<WebElement> elemetns = Driver.getDriver().findElements(By.xpath(xpath));
		return elemetns.size() == 0;
	}

	public boolean verifyElementIsNotPresent(By by) {
		List<WebElement> elemetns = Driver.getDriver().findElements(by);
		return elemetns.size() == 0;
	}

	public void scrollToElement(WebElement element) {
		((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void hitEnterUsingRobot() {
		Robot rb;
		try {
			rb = new Robot();
			rb.keyPress(KeyEvent.VK_ENTER);
			rb.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean verifyAlertPresent() {
		try {
			Driver.getDriver().switchTo().alert();
			return true;
		} catch (NoAlertPresentException Ex) {
			System.out.println("Alert is not presenet");
		}
		return false;
	}

	public static void takeSnapShot() {
		try {
			TakesScreenshot scrShot = ((TakesScreenshot) Driver.getDriver());
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir") + "\\screenshots.jpg";
			System.out.println(path);
			File DestFile = new File(path);
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void waitUntilPageLoad() {
		WebDriverWait wait = new WebDriverWait(Driver.getDriver(),
				Integer.valueOf(ConfigurationReader.getProperty("timeout")));
		wait.until((d) -> {
			Boolean isPageLoaded = (Boolean) ((JavascriptExecutor) Driver.getDriver())
					.executeScript("return document.readyState").equals("complete");
			if (!isPageLoaded)
				System.out.println("Document is loading");
			return isPageLoaded;
		});
	}

	public void waitForStaleElement(WebElement element) {
		int y = 0;
		while (y <= 15) {
			try {
				element.isDisplayed();
				break;
			} catch (StaleElementReferenceException st) {
				y++;
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} catch (WebDriverException we) {
				y++;
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
		WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeToWaitInSec);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public WebElement waitForVisibility(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement waitForClickablility(WebElement element, int timeout) {
		WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public WebElement waitForClickablility(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void waitForPageToLoad(long timeOutInSeconds) {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		try {
			System.out.println("Waiting for page to load...");
			WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeOutInSeconds);
			wait.until(expectation);
		} catch (Throwable error) {
			System.out.println(
					"Timeout waiting for Page Load Request to complete after " + timeOutInSeconds + " seconds");
		}
	}
	
	public String convertDateFormat(String OriginalFormat, String TargetFormat, String Date){            
        DateFormat original = new SimpleDateFormat(OriginalFormat, Locale.ENGLISH);
        DateFormat target = new SimpleDateFormat(TargetFormat);
        String formattedDate = null;
        try {
            Date date = original.parse(Date);
            formattedDate = target.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
    }
}
