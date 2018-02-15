package assignment.ebay.settings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import assignment.ebay.interfaces.IConfigurationProvider;

public class Configuration implements IConfigurationProvider{
	
	private Properties mProperties;
	
	public Configuration() {
		mProperties = new Properties();
		initConfiguration("./resources/data.properties");
	}
	
	private void initConfiguration(String path) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(path);
			mProperties.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getAPK() {
		return mProperties.getProperty(TestConfigKeys.APK);
	}

	public String getDeviceName() {
		return mProperties.getProperty(TestConfigKeys.DeviceName);
	}

	public String getTimeOut() {
		return mProperties.getProperty(TestConfigKeys.TimeOut);
	}

	public String getPlatformName() {
		return mProperties.getProperty(TestConfigKeys.PlatformName);
	}

	public String getTestDataFile() {
		return mProperties.getProperty(TestConfigKeys.TestDataFile);
	}

	public String getScreenshotFile() {
		return mProperties.getProperty(TestConfigKeys.ScreenshotFile);
	}

}
