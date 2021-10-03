package com.selenium.pageClasses;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
//import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.baseClasses.BasetestClass;
import com.selenium.baseClasses.PageBaseclassUI;
import com.selenium.utilities.ExcelwriteDataFile;

public class TravelInsurancePage extends PageBaseclassUI {
	public TravelInsurancePage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}

	static int counter;

	public void selectCountry(String country) {
		WebElement desCountry = getElement("CountryTextBox_Id");

		WebElement nextBtn = driver.findElement(By.xpath("//*[@id='topForm']/section/div[2]/div/div[2]/button"));

		// Entering Country
		if(counter!=0) {

			WebElement deSelectCountry = driver.findElement(By.className("countryRemove"));
			deSelectCountry.click();
		}
		highlight(desCountry);

		enterText("CountryTextBox_Id", country);
		setWait(1);
		desCountry.sendKeys(Keys.ENTER);
		unhighlight(desCountry);


		// Clicking the 'Next' Button

		click(nextBtn, "Clicking 'Next' Button", "Clicked 'Next' Button");

	}

	public void selectTravellersAge(String age1, String age2) {

		WebElement SelectAgeTraveller1 = driver.findElement(By.id("travellerAge1"));

		WebElement addTravellerBtn = driver.findElement(By.xpath("//*[@id='secondStep']/a"));

		WebElement nextBtn = driver.findElement(By.xpath("//*[@id='topForm']/section/div[2]/div/div[2]/button"));
		highlight(SelectAgeTraveller1);

		// Selecting age for Traveller 1

		Select select1 = new Select(SelectAgeTraveller1);
		select1.selectByValue(age1);
		setWait(1);
		logger.log(LogStatus.INFO, "Selected " + age1 + " for Traveller 1");


		unhighlight(SelectAgeTraveller1);
		// Clicking 'Add another Traveller'
		if (counter == 0) {
			click(addTravellerBtn, "Clicking 'Add another traveller' button", "Clicked 'Add another traveller' button");
			counter++;
		}


		WebElement SelectAgeTraveller2 = driver.findElement(By.id("travellerAge2"));
		highlight(SelectAgeTraveller2);
		// Selecting age for Traveller 2
		Select select2 = new Select(SelectAgeTraveller2);
		select2.selectByValue(age2);
		setWait(1);
		logger.log(LogStatus.INFO, "Selected " + age2 + " for Traveller 2");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");
		setWait(2);
		unhighlight(SelectAgeTraveller2);
		
		// Clicking 'Next' button

		click(nextBtn, "Clicking 'Next' Button", "Clicked 'Next' Button");


	}

	public void selectStartDate(String date) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-350)", "");
		setWait(2);


		WebElement SelectStartDate = driver.findElement(By.id("startdate"));
		click(SelectStartDate, "Clicking on 'Start Date' calender", "Clicked on 'Start Date' calender");
		setWait(1);

		WebElement SelectYear = driver.findElement(By.xpath("//*[@id='navigatorType']/body/div[8]/div[2]/div[1]/table/thead/tr[1]/th[2]/select[2]"));

		WebElement SelectMonth = driver.findElement(By.xpath("//*[@id=\"navigatorType\"]/body/div[8]/div[2]/div[1]/table/thead/tr[1]/th[2]/select[1]"));

		// Retriving date, month and year component from date String
		String day = date.substring(0, 2);
		int month = Integer.parseInt(date.substring(3, 5));
		String year = date.substring(6, 10);

		// Selecting the year in drop-down calendar

		Select select = new Select(SelectYear);
		select.selectByVisibleText(year);
		setWait(1);


		// Selecting the month in drop-down calendar
	
		Select selectmonth = new Select(SelectMonth);
		selectmonth.selectByIndex(month - 1);
		setWait(1);

		// Selecting day
		List<WebElement> daysList = driver.findElements(By.xpath(ReadPropertiesFile.prop.getProperty("SelectDayStartDate_Xpath")));
		int index = -1;
		for (WebElement element : daysList) {
			if (element.getText().equals(day)) {
				index = daysList.indexOf(element);
				break;
			}
		}
		if (index == -1) {
			reportFail("The day providied is not available to select");
		}
		Actions action = new Actions(driver);
		action.moveToElement(daysList.get(index)).click().perform();
		logger.log(LogStatus.INFO, "Selected " + date + " for Start Date");

	}

	public void selectEndDate(String date) {


		WebElement SelectEndDate = driver.findElement(By.id("enddate"));

		WebElement SelectYear = driver.findElement(By.xpath("//*[@id='navigatorType']/body/div[9]/div[2]/div[1]/table/thead/tr[1]/th[2]/select[2]"));

		WebElement SelectMonth = driver.findElement(By.xpath("//*[@id='navigatorType']/body/div[9]/div[2]/div[1]/table/thead/tr[1]/th[2]/select[1]"));

		WebElement nextBtn = driver.findElement(By.xpath("//*[@id='topForm']/section/div[2]/div/div[2]/button"));

		// Retriving date, month and year component from date String
		String day = date.substring(0, 2);
		int month = Integer.parseInt(date.substring(3, 5));
		String year = date.substring(6, 10);

		// Selecting the year in drop-down calendar

		Select select = new Select(SelectYear);
		select.selectByVisibleText(year);
		setWait(1);


		// Selecting the month in drop-down calendar

		Select selectmonth = new Select(SelectMonth);
		selectmonth.selectByIndex(month - 1);
		setWait(1);

		// Selecting day
		List<WebElement> daysList = driver.findElements(By.xpath(ReadPropertiesFile.prop.getProperty("SelectDayEndDate_Xpath")));
		int index = -1;
		for (WebElement element : daysList) {
			if (element.getText().equals(day)) {
				index = daysList.indexOf(element);
				break;
			}
		}
		if (index == -1) {
			reportFail("The day providied is not available to select");
		}
		Actions action = new Actions(driver);
		action.moveToElement(daysList.get(index)).click().perform();
		logger.log(LogStatus.INFO, "Selected " + date + " for End Date");

		// Clicking the 'Next' Button

		click(nextBtn, "Clicking 'Next' Button", "Clicked 'Next' Button");

	}

	public void enterMobile(String countryCode, String mobile) {

		WebElement mobileBox = driver.findElement(By.xpath("//*[@id=\"travelmobile\"]"));

		WebElement countryBox = driver.findElement(By.id("countrycode"));

		WebElement ViewPlansBtn = driver.findElement(By.xpath("//*[@id='topForm']/section/div[2]/div/div[2]/button"));
		highlight(countryBox);

		Select select = new Select(countryBox);
		select.selectByVisibleText(countryCode);
		setWait(1);
		unhighlight(countryBox);
		highlight(mobileBox);



		enterText("MobileTextBox_Id", mobile);
		setWait(1);
		unhighlight(mobileBox);
		driver.manage().deleteAllCookies();


		highlight(ViewPlansBtn);
		click(ViewPlansBtn, "Clicking 'View Plan' Button", "Clicked 'View Plan' Button");
		ViewPlansBtn.click();
		setWait(2);

	}
	

	public void getErrorMessageMobile() {

		WebElement mobileBox = driver.findElement(By.xpath("//*[@id=\"travelmobile\"]"));

		WebElement countryBox = driver.findElement(By.id("countrycode"));

		WebElement ViewPlansBtn = driver.findElement(By.xpath("//*[@id='topForm']/section/div[2]/div/div[2]/button"));

		mobileBox.clear();
		driver.manage().deleteAllCookies();
		highlight(ViewPlansBtn);


		
		click(ViewPlansBtn, "Clicking 'View Plan' Button", "Clicked 'View Plan' Button");

		unhighlight(ViewPlansBtn);
		ViewPlansBtn.click();
		

		WebElement errorMessage = driver.findElement(By.xpath("//*[@id='number_f']/div[2]"));
		logger.log(LogStatus.PASS, "Error Message Displayed: "+errorMessage.getText());
		takeScreenShotOnFailure();
		
	}

	// Method to display three lowest travel insurance plans 
	public String[] getThreeLowTravelPlans() throws IOException {
		try {
			logger.log(LogStatus.INFO, "Obtaining the Lowest Three Travel Plans");

			WebElement selectSortPrice = driver.findElement(By.className("sort_select"));
			highlight(selectSortPrice);

			
			Select priceSort = new Select(selectSortPrice);	// To click on sortby drop down
			priceSort.selectByVisibleText("Price: Low to High");// To low to high in sortby drop down
			setWait(1);
			unhighlight(selectSortPrice);
			
			// To display lowest three international plans
			List<WebElement> insuraceProviders = driver.findElements(By.xpath(ReadPropertiesFile.prop.getProperty("InsuranceProvidersLogo_Xpath")));
			List<WebElement> insuranceCost = driver.findElements(By.xpath(ReadPropertiesFile.prop.getProperty("InsuranceCost_Xpath")));
			
			logger.log(LogStatus.PASS, "Obtained the Lowest Travel Plans"); // for insurance provider name
			String output[] = new String[3];
			for (int i = 0; i < 3; i++) { 
				// iterating through the list to obtain the Insurance provider name
				String insuranceName = insuraceProviders.get(i).getAttribute("class");
				String providerName = insuranceName.substring(4);
				System.out.println((i + 1) + ")" + " Insurance Provider: " + providerName); // Amount is retrieved and displayed
																							// using the insurance provider name
				String inscost = insuranceCost.get(i).getText();
				String[] providerCost = inscost.split("\\s+");
				String cost = "Rs. " + providerCost[1];
				System.out.println("Amount for the Plan: " + cost);
				System.out.println(" ");
				output[i] =(i + 1) + ")" + " Insurance Provider: " + providerName +", Amount for the Plan: " + cost;
			}
			return output;
		} catch (Exception e) {
			reportFail("Failed to Obtain the Travel Plans " +e.getMessage());
		}
		return null;
	}
	 

}