package com.selenium.baseClasses;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.selenium.utilities.ExtentManagerReport;
import com.selenium.utilities.PropertiesFile;

public class BasetestClass {
	
	public WebDriver driver;
	public ExtentReports report = ExtentManagerReport.getReportInstance();//To generate html reports
	public ExtentTest logger;//To log steps onto the previously generated html report
	public PropertiesFile ReadPropertiesFile;
	
	
	public void invokeBrowser() throws Exception {
		
		ReadPropertiesFile=	new PropertiesFile();
		String userDir=System.getProperty("user.dir");//invoking browser name from getbrowser() in input.properties.file
		String webBrowser = com.selenium.utilities.PropertiesFile.getbrowser();
		if(webBrowser.equalsIgnoreCase("chrome")) 
		{
			//call getChromeDriver() to connect with chrome browser
			System.setProperty("webdriver.chrome.driver", userDir + "\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			
		}
		if (webBrowser.equalsIgnoreCase("Firefox")) 
		{
			//call getFireFoxDriver() to connect with firefox browser
			System.setProperty("webdriver.gecko.driver",userDir + "\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().deleteAllCookies();
		}
	
		driver.manage().window().maximize();  //Maximizes the window
		driver.manage().deleteAllCookies();   //Delete all the cookies
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);  //Defining Implicit Wait for the driver
	}
	
	public void setWait(int i) {
		try {
			TimeUnit.SECONDS.sleep(i);
			//To pause execution for particular amount of time
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	@AfterMethod
	public void flushReports() {
		report.flush();//To erase any previous data on the report and create new reports
	}
}
