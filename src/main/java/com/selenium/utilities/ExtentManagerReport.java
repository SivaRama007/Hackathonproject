package com.selenium.utilities;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManagerReport {
	
	public static ExtentReports report;
	
	public static ExtentReports getReportInstance(){
		
		//To obtain report in html format
		if(report == null){
			String reportName = DateUtil.getTimeStamp() + ".html";;
			report = new ExtentReports(System.getProperty("user.dir") + "\\Project Output\\" + reportName, false);
		}
		return report;
	}
}



    

