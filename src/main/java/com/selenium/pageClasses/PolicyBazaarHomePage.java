package com.selenium.pageClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.baseClasses.PageBaseclassUI;

public class PolicyBazaarHomePage extends PageBaseclassUI{
	
	public PolicyBazaarHomePage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);//To call superclass constructor
	}
	
	//List of web elements in PoilcyBazaar home page
	@FindBy(xpath="/html/body/cclink/div[4]/div[1]/div/ul/li[2]/div/div/div[2]/ul")
	public List<WebElement> healthInsuranceList;
	
	// Method to Click the travel Insurance option in Insurance Product drop-down list
	public TravelInsurancePage clickTravelInsurance(ExtentTest newLogger) {
		try {
			logger= newLogger;

			WebElement insuranceProducts = driver.findElement(By.xpath("/html/body/cclink/div[4]/div[1]/div/ul/li[2]/a"));

			WebElement travelInsurance = driver.findElement(By.xpath("/html/body/cclink/div[4]/div[1]/div/ul/li[2]/div/div/div[4]/ul/li[1]/a/span"));

			driver.manage().deleteAllCookies();

			// To move cursor on to the element Insurance products
			Actions action = new Actions(driver);
			action.moveToElement(insuranceProducts);
			highlight(insuranceProducts);
			action.build().perform();
			unhighlight(insuranceProducts);
			
			// To move cursor on to the element Travel Insurance
			action.moveToElement(travelInsurance);
			logger.log(LogStatus.INFO, "Clicking the Travel Insurance Link");
			highlight(travelInsurance);
			// To click on Travel Insurance option
			travelInsurance.click();
			
			// report for selecting travel insurance option
			logger.log(LogStatus.PASS, "The Travel Insurance Link is clicked and opened");
			
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

		// To initialize Travel insurance web elements
		TravelInsurancePage travelinsurancepage = new TravelInsurancePage(driver, logger);
		return travelinsurancepage;
	}
	
	public HealthInsurancePage hoverInsuranceList(ExtentTest log)
	{	

		WebElement insuranceProducts = driver.findElement(By.xpath("/html/body/cclink/div[4]/div[1]/div/ul/li[2]/a"));

		WebElement healthInsurance = driver.findElement(By.xpath("/html/body/cclink/div[4]/div[1]/div/ul/li[2]/div/div/div[2]/h3/a"));
    	logger = log;
    	
		setWait(1);
		//Initializing action
		Actions action=new Actions(driver);
		highlight(insuranceProducts);
		//To click on insurance products element
		action.moveToElement(insuranceProducts).build().perform();
		setWait(1);
		unhighlight(healthInsurance);
		
		//To click on health insurance element
	
		action.moveToElement(healthInsurance).build().perform();
		setWait(1);

		
		//report for retrieving the Health Insurance list 
		logger.log(LogStatus.INFO, "Obtaining the Health Insurance List from the Home Page");
	    HealthInsurancePage healthinsurance = new HealthInsurancePage(driver,logger);
	    return healthinsurance;
	} 
	
	public CarInsurancePage clickCarInsurance(ExtentTest log) {
		try {
			

			WebElement insuranceProducts = driver.findElement(By.xpath("/html/body/cclink/div[4]/div[1]/div/ul/li[2]/a"));

			WebElement carInsurance = driver.findElement(By.xpath("/html/body/cclink/div[4]/div[1]/div/ul/li[2]/div/div/div[3]/h3/a"));
			logger =  log;
			setWait(1);
			
			//Initializing action
			Actions action = new Actions(driver);
			//To click on insurance products element
			action.moveToElement(insuranceProducts).build().perform();
			//To move cursor on to the element car insurance
			action.moveToElement(carInsurance);
			//To click on car insurance element
			action.build().perform();
			
			//report for clicking the insurance link on insurance product
			logger.log(LogStatus.INFO, "Clicking the Car Insurance Link ");
			carInsurance.click();
			logger.log(LogStatus.PASS, "Clicked the Car Insurance Link");
			
		}catch(Exception e) {
			reportFail(e.getMessage());
		}
		
		//To initialize carInsurance web elements
		CarInsurancePage carinsurancepage = new CarInsurancePage(driver,logger);
		PageFactory.initElements(driver, carinsurancepage);
		return carinsurancepage;
	}
	
}
