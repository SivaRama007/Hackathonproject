package com.selenium.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFile {
	
		public static Properties prop = new Properties();// properties object created
		InputStream readFile = null;//initialize read file to null
		public PropertiesFile()throws Exception{
			//Obtain data from property file
			 try {
				 readFile = new FileInputStream("input.properties");
				 prop.load(readFile);
			} 
			 catch(IOException io){
			io.printStackTrace();
			 }
			 /*finally block to close the input.Properties file*/
			 finally {
			if(readFile!=null){
				try {
					readFile.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				}
			 }
		}
		
		 //To retrieve url from property file
		 public static String getUrl()throws Exception{
		 String url = prop.getProperty("URL");
		 return url;
		 }
		 
		//To obtain choice of Browser - Chrome or FireFox
		 public static String getbrowser()throws Exception{
			 String browser = prop.getProperty("Browser");
			 return browser;
		 }
		 
		
}
