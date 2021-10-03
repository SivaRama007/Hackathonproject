package com.selenium.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelwriteDataFile {
	static XSSFWorkbook wb;
	static XSSFSheet sheet;
	static int rowNo = 1;
	static String path = System.getProperty("user.dir") + "\\Project Output\\ExcelOutput.xlsx";
	
	
	public static void writeTestOutput(String data) {
		try {
			FileInputStream fis = new FileInputStream(path);
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheetAt(0);
			fis.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		sheet.getRow(rowNo).createCell(0).setCellValue(data);
		rowNo++;
		try {
			FileOutputStream fos = new FileOutputStream(path);
			wb.write(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
		
}
