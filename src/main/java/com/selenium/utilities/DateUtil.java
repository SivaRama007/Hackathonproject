package com.selenium.utilities;

import java.util.Date;

public class DateUtil {
	//Method to convert date into appropriate date variable format
	public static String getTimeStamp(){
		Date date = new Date();
		return date.toString().replaceAll(":", "_").replaceAll(" ", "_");
	}
}
