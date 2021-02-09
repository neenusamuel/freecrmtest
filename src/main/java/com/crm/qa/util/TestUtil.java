package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase {
	public static long Page_load_Timeout = 20;
	public static long Implicit_Wait = 20;
	static FileInputStream fis;
	static Workbook book;
	static Sheet sheet;
	
	

	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}
	public static void attachScreenshotAtEndOfTest() throws IOException {
		String scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		String st= "<img src=\"data:image/png;base64, " + scrFile + "\" height=\"600\" width=\"800\" />";
		Reporter.log(st);
//		String currentDir = System.getProperty("user.dir");
//		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}
	
//	public void switchToFrame() {
//		driver.switchTo().frame("main panel");
//	}
	public void switchToFrame(String frameName) {
		driver.switchTo().frame(frameName);
	}
	public static Object[][] getTestData(String sheetName){
		try {
			fis = new FileInputStream("C:\\Users\\neenu\\eclipse-workspace\\FreeCRMTest1\\src\\main\\java\\"
					+ "com\\crm\\qa\\testdata\\contacts.xlsx");
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(fis);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for(int i = 0; i<sheet.getLastRowNum(); i++) {
			for(int j=0; j<sheet. getRow(0).getLastCellNum(); j++){
				data[i][j] = sheet.getRow(i+1).getCell(j).toString();
			}
		}
		return data;
	}
	
}
