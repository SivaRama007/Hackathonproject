package com.selenium.pageClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.baseClasses.PageBaseclassUI;

public class CarInsurancePage extends PageBaseclassUI {

	public CarInsurancePage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);// To call superclass constructor
	}

	// To proceed without car number
	public void proceedWithoutCarNumber() {


		WebElement proceedWithoutNumber = driver.findElement(By.xpath("//*[@id='frmCar']/div[1]/div/div/div[5]/a[1]"));
		highlight(proceedWithoutNumber);
		

		click(proceedWithoutNumber, "Clicking the Proceed Without CarNumber button","Clicked the Proceed Without CarNumber button");
        unhighlight(proceedWithoutNumber);
	}

	// Method to select RTO and City
	public void searchCity(String RTOValue) {
		try {
			setWait(2);
	
			WebElement searchKey = driver.findElement(By.className("react-autosuggest__input"));
			highlight(searchKey);
					


			// To delete previous values
			searchKey.sendKeys(Keys.CONTROL + "a");
			searchKey.sendKeys(Keys.DELETE);
			setWait(1);

			// sending the RTOValue by using excel

			WebElement citySerach = driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]"));
			citySerach.sendKeys(RTOValue);
			logger.log(LogStatus.INFO, "Selecting RTO and City as: " + RTOValue);
			citySerach.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
			
			unhighlight(searchKey);

			logger.log(LogStatus.PASS, "RTO and City selection: Successful");

		} catch (Exception e) {
			reportFail("Failed to Select the RTO and City");
			System.out.println("Failed to Select the RTO and City");
		}

	}

	// Select Company Brand
	public void searchBrand(String carName) {
		try {
			setWait(1);

			List<WebElement> carBrands = driver.findElements(By.xpath("//*[@id='dvMake']/div/ul/div/li/span/b"));
			logger.log(LogStatus.INFO, "Selecting Brand as: " + carName);
			for (WebElement carbrand : carBrands) {
				
				if (carbrand.getText().equals(carName)) {
					Actions action = new Actions(driver);
					action.moveToElement(carbrand).click().build().perform();
					break;
				}	
			}
			logger.log(LogStatus.PASS, "Car Brand selection: Successful");
			
		} catch (Exception e) {
			reportFail("Failed to select the car brands Type");
		}

	}

	// Function to select fuel type
	public void typeFuel(String fType) {
		try {
			logger.log(LogStatus.INFO, "Selecting the Fuel Type in List as: " + fType);
			setWait(1);
			// retrieving fuel-type data from excel file

			List<WebElement> fuelType = driver.findElements(By.xpath("//*[@id=\"dvFuelType\"]/ul/div/li"));
			for (WebElement Fuel : fuelType) {
				if (Fuel.getText().equals(fType)) {
					
					highlight(Fuel);
					
					Actions action = new Actions(driver);
					action.moveToElement(Fuel).click().build().perform();
					break;
				}
			}
			// report for selecting the appropriate fuel type
			logger.log(LogStatus.PASS, "Fuel Type selection: Successful");
		} catch (Exception e) {
			reportFail("Failed to select the Fuel Type");
		}

	}
	public void typeModel(String modelname) {
		try {
			logger.log(LogStatus.INFO, "Selecting the Model in List as: " + modelname);
			setWait(1);
			// retrieving fuel-type data from excel file

			modelname="//*[contains(text(),'" + modelname +"')]";
			driver.findElement(By.xpath(modelname)).click();
			// report for selecting the appropriate fuel type
			logger.log(LogStatus.PASS, "Model Variant selection: Successful");
		} catch (Exception e) {
			reportFail("Failed to select the model Type");
		}

	}

	// Method to select the car Brand variant
	public void selectCarBrand(String Variant) {

		logger.log(LogStatus.INFO, "Selecting the car variant as: " + Variant);
		setWait(2);

		WebElement searchKey = driver.findElement(By.xpath("//*[@id='searchInput']/div/input"));
		highlight(searchKey);
		

		searchKey.click();

		searchKey.sendKeys(Keys.CONTROL + "a");
		searchKey.sendKeys(Keys.DELETE);
		searchKey.sendKeys(Variant);
		searchKey.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
		logger.log(LogStatus.PASS, "Selected the CAR variant in suggestion list by passing the Value in Search");

	}

	// Method to select a particular year
	public void carYear(String yearData) {

		List<WebElement> selectYear = driver.findElements(By.xpath("//*[@id='dvRegYear']/ul/div/li/span"));
		logger.log(LogStatus.INFO, "Selecting the year as: " + yearData);
		for (WebElement year : selectYear) {
			if (year.getText().equals(yearData)) {
				highlight(year);
				Actions action = new Actions(driver);
				action.moveToElement(year).click().build().perform();
				break;
			}
			logger.log(LogStatus.PASS, "Selected year");
		}
	}

	// Method to enter full name
	public void cName(String Na) {
		setWait(2);
		logger.log(LogStatus.INFO, "Entering name as: " + Na);

		WebElement Name = driver.findElement(By.xpath("//input[@id='name']"));
		highlight(Name);

		Name.sendKeys(Keys.CONTROL + "a");
		Name.sendKeys(Keys.DELETE);
		Name.sendKeys(Na);
		unhighlight(Name);
		logger.log(LogStatus.PASS, "Entered year");
	}

	// Method to enter invalid email
	public void eEmail(String mail) { // To clear the previous data
		logger.log(LogStatus.INFO, "Entering invalid email as: " + mail);

		WebElement errorEmail = driver.findElement(By.xpath("//input[@id='email']"));
		highlight(errorEmail);

		errorEmail.clear(); // sending mail id by using excelfile
		errorEmail.sendKeys(mail);
		unhighlight(errorEmail);

		logger.log(LogStatus.PASS, "Entered invalid email");
	}

	// Method to enter invalid phone number
	public void ePnumber(String mno) { // To clear the previous data
		logger.log(LogStatus.INFO, "Entering invalid mobile number as: " + mno);

		WebElement errorMobileno = driver.findElement(By.xpath("//*[@id='mobileNo']"));
		highlight(errorMobileno);

		errorMobileno.clear(); // sending mobile no by using excel file
		errorMobileno.sendKeys(mno);
		unhighlight(errorMobileno);

		logger.log(LogStatus.PASS, "Entered invalid mobile number");
	}

	// Method to click on view prices
	public void proceedClick() {

		WebElement clickProceed = driver.findElement(By.xpath("//span[contains(text(),'View Prices')]"));
		highlight(clickProceed);
	
		click(clickProceed, "Clicking 'Proceed' Button", "Clicked 'Proceed' Button");


		unhighlight(clickProceed);
		setWait(2);

		WebElement errorMessage1 = driver.findElement(By.xpath("//*[@id='dvVariant']/div[2]/div[1]/div[2]/div[2]"));
		setWait(2);

		WebElement errorMessage2 = driver.findElement(By.xpath("//*[@id='dvVariant']/div[2]/div[1]/div[3]/div[1]"));
		System.out.println("message"+errorMessage2);
		logger.log(LogStatus.PASS,
				"Error Message Displayed " + errorMessage1.getText() + " " + errorMessage2.getText());
		takeScreenShotOnFailure();
	}

}
