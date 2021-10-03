package com.selenium.testCases;

import java.util.Map;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.baseClasses.BasetestClass;
import com.selenium.baseClasses.PageBaseclassUI;
import com.selenium.pageClasses.CarInsurancePage;
import com.selenium.pageClasses.HealthInsurancePage;
import com.selenium.pageClasses.PolicyBazaarHomePage;
import com.selenium.pageClasses.TravelInsurancePage;
import com.selenium.utilities.ExtentManagerReport;
import com.selenium.utilities.PropertiesFile;
import com.selenium.utilities.TestDataProvider;
import com.selenium.utilities.ExcelwriteDataFile;

public class PolicyBazaarTestCases extends BasetestClass{
	PolicyBazaarHomePage policybazaarhomepage;
	TravelInsurancePage travelinsurancepage;
	CarInsurancePage carinsurancepage;
	HealthInsurancePage healthInsurancePage;
	
	@BeforeTest
	public void startReport() {
		// Initializing ExtentReports object ExtentReports(String filePath,Boolean
		// replaceExisting)
		report = ExtentManagerReport.getReportInstance();
	}
	
	
	@Test(priority = 0, groups = "Smoke Test")
	public void openWebSite() throws Exception {
		logger = report.startTest("Policybazaar : Opening Website");
		invokeBrowser();// opening of the browser
		PageBaseclassUI pageBaseUI = new PageBaseclassUI(driver, logger);
		PageFactory.initElements(driver, pageBaseUI);// To initialize web elements
		policybazaarhomepage = pageBaseUI.openWebsite();
		report.endTest(logger);
	}
	
	
	@Test(dataProvider = "getThreeLowestTravelQuotes", priority = 1, groups = { "Regression Test", "Smoke Test" })
	public void displayLowestTravelPlans(Map<String, String> testData) throws Exception {
		
		logger = report.startTest("Travel Insurance: Display Lowest Travel Plans " + testData.get("Comment"));
		driver.manage().deleteAllCookies();
		
		// Redirecting the browser to travel insurance page
		travelinsurancepage = policybazaarhomepage.clickTravelInsurance(logger);
		Thread.sleep(2000);

		// Entering country and proceeding next
		travelinsurancepage.selectCountry(testData.get("Country"));

		// Selecting age for Traveller 1 and Traveller 2
		travelinsurancepage.selectTravellersAge(testData.get("Age 1"), testData.get("Age 2"));

		// Selecting Start date from calender
		travelinsurancepage.selectStartDate(testData.get("Start Date"));

		// Selecting Start date from calender
		travelinsurancepage.selectEndDate(testData.get("End Date"));

		// Entering mobile number
		travelinsurancepage.enterMobile(testData.get("CountryCode"), testData.get("MobileNumber"));
		
		String output[] = travelinsurancepage.getThreeLowTravelPlans();
		setWait(3);
		for(int i=0;i<output.length;++i) {
		  ExcelwriteDataFile.writeTestOutput(output[i]);
		 }
		 
		driver.navigate().to(ReadPropertiesFile.getUrl());
	}
	
	 
	  @Test(dataProvider ="getAgeError",priority = 2, groups = { "Regression Test",
	  "Smoke Test" }) public void TravelInsuranceInvalidMobile(Map<String, String>
	  testData) throws Exception {
	  
	  logger =
	  report.startTest("Travel Insurance:Show Error message-Invalid Mobile No");
	  driver.manage().deleteAllCookies();
	  
	  // Redirecting the browser to travel insurance page travelinsurancepage =
	  policybazaarhomepage.clickTravelInsurance(logger);
	  
	  // Entering Invalid country and proceeding next
	  travelinsurancepage.selectCountry(testData.get("Country"));
	  
	  // Selecting age for Traveller 1 and Traveller 2
	  travelinsurancepage.selectTravellersAge(testData.get("Age 1"),
	  testData.get("Age 2"));
	  
	  // Selecting Start date from calender
	  travelinsurancepage.selectStartDate(testData.get("Start Date"));
	  
	  // Selecting Start date from calender
	  travelinsurancepage.selectEndDate(testData.get("End Date"));
	  travelinsurancepage.getErrorMessageMobile();
	  
	  driver.manage().deleteAllCookies();
	  driver.navigate().to(ReadPropertiesFile.getUrl());
	  
	  }
	 
	
	@Test(priority=3,groups={"Regression Test","Smoke Test"})
	 public void  HealthInsuranceMenuList() throws Exception { 
		
		driver.manage().deleteAllCookies();
		logger = report.startTest("Health Insurance: Display all the categories from the website PolicyBazar");
		
        //Retrieving the health insurance list
		healthInsurancePage = policybazaarhomepage.hoverInsuranceList(logger);
		String output[] = healthInsurancePage.healthInsuranceList();
		for(int i=0;i<output.length;++i) {
			  ExcelwriteDataFile.writeTestOutput(output[i]);
		}
		
		driver.manage().deleteAllCookies();
		driver.navigate().to(ReadPropertiesFile.getUrl());
      }
	
	@Test(dataProvider = "getErrorDetailsTestData", priority = 4, groups = { "Regression Test", "Smoke Test" })
	// dataProvider = "getErrorDetailsTestData",
	public void getErrorForInvalidDetails(Map<String, String> testData) throws Exception {
		driver.manage().deleteAllCookies();
		logger = report.startTest("Car Insurance: Display Error Message");
		
		// redirected to car insurance page
		carinsurancepage = policybazaarhomepage.clickCarInsurance(logger);
		
		//Click on 'Proceed without Car' option
		carinsurancepage.proceedWithoutCarNumber();
		
		// Next page is redirected and added other details
		carinsurancepage.searchCity(testData.get("RTO & City Name"));

		//To enter brand of car
		carinsurancepage.searchBrand(testData.get("Car Name"));
		
		//To enter model of car
		carinsurancepage.selectCarBrand(testData.get("MODEL"));
		
		//To enter type of fuel
		carinsurancepage.typeFuel(testData.get("Fuel Variant"));

		//To enter variant of model
		carinsurancepage.typeModel(testData.get("Model Variant"));

		//To select year
		carinsurancepage.carYear(testData.get("Year"));

		//Entering name
		carinsurancepage.cName(testData.get("Owner Name"));
		
		//Entering email id
		carinsurancepage.eEmail(testData.get("Owner Email"));
		
		//Entering mobile number
		carinsurancepage.ePnumber(testData.get("Owner Phone"));
		
		//Clicking proceed
		carinsurancepage.proceedClick();
		
		setWait(1);
		driver.manage().deleteAllCookies();
		driver.navigate().to(ReadPropertiesFile.getUrl());
		
		driver.quit();
	}
	
	
	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
		}else {
			logger.log(LogStatus.PASS, "Test Case Passed is " + result.getName());
		}
	}

	@AfterTest
	public void endReport() {
		report.flush();
	}
	
	@DataProvider
	public Object[][] getErrorDetailsTestData(){
		return TestDataProvider.getTestData("Policy Bazaar Tests.xlsx", "CarInsuranceData", "getErrorForInvalidDetails");
	}
	
	@DataProvider()
	public Object[][] getThreeLowestTravelQuotes(){
		return TestDataProvider.getTestData("Policy Bazaar Tests.xlsx", "Travel Plan Quotes", "displayLowestTravelPlans");
	}
	
	@DataProvider
	public Object[][] getCountryError(){
		return TestDataProvider.getTestData("Policy Bazaar Tests.xlsx", "Travel Plan Quotes", "InvalidDetailsCountry");
	}
	
	@DataProvider
	public Object[][] getAgeError(){
		return TestDataProvider.getTestData("Policy Bazaar Tests.xlsx", "Travel Plan Quotes", "InvalidDetails");
	}
	
}
