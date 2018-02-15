package assignment.ebay.activityobjects;

import assignment.ebay.componenthelper.UIComponentHelper;
import io.appium.java_client.android.AndroidDriver;

public class SignInActivity extends UIComponentHelper{

	@SuppressWarnings("rawtypes")
	private AndroidDriver mDriver;
	
	@SuppressWarnings("rawtypes")
	public SignInActivity(AndroidDriver driver) throws Exception {
		super("SignInActivity");
		this.mDriver = driver;
	}
	
	public LoggedInActivity enterSignInDetails(String username, String password) {
		if(getElementXPath("username") == null || getElementXPath("password") == null || getElementXPath("sign-in") == null)
			return null;
		
		mDriver.findElementByXPath(getElementXPath("username")).sendKeys(username);
		mDriver.findElementByXPath(getElementXPath("password")).sendKeys(password);
		mDriver.findElementByXPath(getElementXPath("sign-in")).click();
		try {
			mDriver.findElementByXPath(getElementXPath("maybelater")).click();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new LoggedInActivity(mDriver);
	}
}
