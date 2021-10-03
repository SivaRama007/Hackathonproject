package com.selenium.baseClasses;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.pageClasses.PolicyBazaarHomePage;
import com.selenium.utilities.DateUtil;
import com.selenium.utilities.PropertiesFile;

public class PageBaseclassUI extends BasetestClass {
	public PageBaseclassUI() {
	}

	public PageBaseclassUI(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
	}

	/***************** OpenApplication @throws Exception ******************/

	public PolicyBazaarHomePage openWebsite() throws Exception {
		logger.log(LogStatus.INFO, "Opening the WebSite");// To open website
		driver.get(ReadPropertiesFile.getUrl());// Url of website is present in properties file
		logger.log(LogStatus.PASS, "Successfully Opened the Policy Bazaar Website");
		PolicyBazaarHomePage policybazaarhomepage = new PolicyBazaarHomePage(driver, logger);
		return policybazaarhomepage;
	}

	/****************** Reporting Functions ***********************/

	public void reportFail(String reportString) {
		logger.log(LogStatus.FAIL, reportString);
		takeScreenShotOnFailure();
		Assert.fail(reportString);
	}

	public void reportPass(String reportString) {
		logger.log(LogStatus.PASS, reportString);
	}

	/****************** Capture Screen Shot ***********************/

	public void takeScreenShotOnFailure() {
		logger.log(LogStatus.INFO, "Capturing the error messages for invalid input");
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);
		logger.log(LogStatus.PASS, "Successfully captured the error messages");
		String dest = System.getProperty("user.dir") + "\\ScreenShots\\" + DateUtil.getTimeStamp() + ".png";
		File destFile = new File(dest);
		try {
			FileUtils.copyFile(sourceFile, destFile);
			logger.log(LogStatus.PASS,logger.addScreenCapture(dest));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/****************************** clicking function *************/

	public void click(WebElement element, String info, String pass) {
		try {
			logger.log(LogStatus.INFO, info);
			element.click();
			logger.log(LogStatus.PASS, pass);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/******************************Retriving Element based on Locator *************/
	public WebElement getElement(String locatorKey) {
		WebElement element = null;

		try {
			if (locatorKey.endsWith("_Id")) {
				element = driver.findElement(By.id(ReadPropertiesFile.prop.getProperty(locatorKey)));
			} else if (locatorKey.endsWith("_Xpath")) {
				element = driver.findElement(By.xpath(ReadPropertiesFile.prop.getProperty(locatorKey)));
			} else if (locatorKey.endsWith("_ClassName")) {
				element = driver.findElement(By.className(ReadPropertiesFile.prop.getProperty(locatorKey)));
			} else if (locatorKey.endsWith("_CSS")) {
				element = driver.findElement(By.cssSelector(ReadPropertiesFile.prop.getProperty(locatorKey)));
			} else if (locatorKey.endsWith("_LinkText")) {
				element = driver.findElement(By.linkText(ReadPropertiesFile.prop.getProperty(locatorKey)));
			} else if (locatorKey.endsWith("_PartialLinkText")) {
				element = driver.findElement(By.partialLinkText(ReadPropertiesFile.prop.getProperty(locatorKey)));
			} else if (locatorKey.endsWith("_Name")) {
				element = driver.findElement(By.name(ReadPropertiesFile.prop.getProperty(locatorKey)));
			} else {
				reportFail("Failing the Testcase, Invalid Locator " + locatorKey);
			}
		} catch (Exception e) {

			// Fail the TestCase and Report the error
			reportFail(e.getMessage());
			e.printStackTrace();
		}

		return element;
	}
	public void highlight(WebElement element) {
		// JavascriptExecutor declared for Highlighting WebElement
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: aqua; border: 2px solid pink;');", element);
	}
	public void unhighlight(WebElement element) {
		// JavascriptExecutor declared for Highlighting WebElement
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].removeAttribute('style')", element);
	}
	
	/******************************Retriving List of Elements based on Xpath *************/
	public List<WebElement> getElements(String locatorKey) {
		List<WebElement>element = null;
		try {
			if(locatorKey.endsWith("_Xpath")) {
				element= driver.findElements(By.xpath(ReadPropertiesFile.prop.getProperty(locatorKey)));
			}else {
				reportFail("Failing the Testcase, Invalid Locator " + locatorKey);
			}
		}catch (Exception e) {

				// Fail the TestCase and Report the error
				reportFail(e.getMessage());
				e.printStackTrace();
			}

			return  element;
		
	}
	
	/****************** Enter Text ***********************/
	public void enterText(String xpathKey, String data) {
		try {
			getElement(xpathKey).sendKeys(data);
			reportPass(data + " - Entered successfully in locator Element : " + xpathKey);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	


	/****************** Verify Element ***********************/
	public boolean isElementPresent(String locatorKey) {
		try {
			if (getElement(locatorKey).isDisplayed()) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	/**************** close function ********************************/

	public void ClosePolicyBazzarPage() {
		driver.close();
	}

	/**************** quit function ********************************/

	public void quitPolicyBazzarPage() {
		driver.quit();
	}

}
