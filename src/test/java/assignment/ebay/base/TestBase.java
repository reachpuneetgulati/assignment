package assignment.ebay.base;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import assignment.ebay.settings.System;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

/*
 * 
 * base class for TestClass -- provides some utility functions to all the test classes
 * 
 */
public abstract class TestBase {
	
	private String mTestDataFilePath;
	private String mScreenshotPath;
	
	@SuppressWarnings("rawtypes")
	public AppiumDriver initializeDriver() {
		
		mTestDataFilePath = System.Config.getTestDataFile();
		mScreenshotPath = System.Config.getScreenshotFile();
		
		File f = new File("./TestFiles");
		File fs = new File(f, System.Config.getAPK());
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, System.Config.getPlatformName());
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, System.Config.getDeviceName());
		dc.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
		dc.setCapability(MobileCapabilityType.TAKES_SCREENSHOT, true);
			
		//right now it is configured for Android only

		AppiumDriver driver;
		try {
			if(System.Config.getPlatformName().equals("Android")) {
				driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),dc);
			}else {
				driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),dc);
			}
		}catch(Exception e) {
			return null;
		}
		driver.manage().timeouts().implicitlyWait(Long.parseLong(System.Config.getTimeOut()), TimeUnit.SECONDS);
		return driver;
	}

	//default screenshot location
	public void takeScreenShot(@SuppressWarnings("rawtypes") AppiumDriver driver, String name) {
		//take screenshot on failure
		File f = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			org.apache.commons.io.FileUtils.copyFile(f, new File(mScreenshotPath.concat(name).concat(".png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//custom screenshot location
	public void takeScreenShot(@SuppressWarnings("rawtypes") AppiumDriver driver, String path, String name) {
		//take screenshot on failure
		File f = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			org.apache.commons.io.FileUtils.copyFile(f, new File(path.concat(name).concat(".png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private Object[][] processAndReturnTestData(Sheet sheet){
		int rowCount = sheet.getLastRowNum();
		int columnCount = sheet.getRow(0).getLastCellNum();
		
		if(rowCount == 1)
			return null;
		
		Object[][] dataobj = new Object[rowCount-1][columnCount]; 
		for(int i = 1; i<rowCount; i++) {
			for(int j = 0; j<columnCount; j++) {
				if (sheet.getRow(i).getCell(j).getCellTypeEnum() == CellType.STRING)
					dataobj[i-1][j] = sheet.getRow(i).getCell(j).getStringCellValue();
				else if (sheet.getRow(i).getCell(j).getCellTypeEnum() == CellType.NUMERIC)
					dataobj[i-1][j] = String.valueOf(sheet.getRow(i).getCell(j).getNumericCellValue());
				else if (sheet.getRow(i).getCell(j).getCellTypeEnum() == CellType.BOOLEAN)
					dataobj[i-1][j] = String.valueOf(sheet.getRow(i).getCell(j).getBooleanCellValue());
			}
		}
		return dataobj;
	}
	
	//test data file other than the default file
	public Object[][] getData(String path, String tcName) throws IOException{
		Workbook workbook = new XSSFWorkbook(path);
		Sheet sheet = workbook.getSheet(tcName);
		Object[][] dataobj =  processAndReturnTestData(sheet);
		workbook.close();
		return dataobj;
	}

	//default test data file
	public Object[][] getData(String tcName) throws IOException{
		Workbook workbook = new XSSFWorkbook(mTestDataFilePath);
		Sheet sheet = workbook.getSheet(tcName);
		Object[][] dataobj =  processAndReturnTestData(sheet);
		workbook.close();
		return dataobj;
	}
}
