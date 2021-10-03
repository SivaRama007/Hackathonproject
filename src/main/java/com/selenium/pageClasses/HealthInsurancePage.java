package com.selenium.pageClasses;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.baseClasses.PageBaseclassUI;
import com.selenium.utilities.ExcelwriteDataFile;


public class HealthInsurancePage extends PageBaseclassUI {
	public HealthInsurancePage (WebDriver driver,ExtentTest logger) {
		super(driver,logger);
	}
	
	
	public String[] healthInsuranceList() 
	{	List<WebElement> healthInsuranceList = driver.findElements(By.xpath(ReadPropertiesFile.prop.getProperty("HeathInsuranceList_Xpath")));
	
		String[] healthInsuranceTypes=new String[healthInsuranceList.size()];
		for(int i=0;i<healthInsuranceList.size();i++)
		{
			highlight(healthInsuranceList.get(i));
			//Storing the data in the string array 

			healthInsuranceTypes[i]=healthInsuranceList.get(i).getText();
			//To display the List 
			System.out.println(healthInsuranceTypes[i]);
			unhighlight(healthInsuranceList.get(i));

		}
		logger.log(LogStatus.PASS, "List of Health Insurance Names is acquired");
		return healthInsuranceTypes;
		
	}
}
