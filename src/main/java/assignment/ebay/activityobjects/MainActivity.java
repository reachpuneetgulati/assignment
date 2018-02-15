package assignment.ebay.activityobjects;

import assignment.ebay.componenthelper.UIComponentHelper;
import io.appium.java_client.android.AndroidDriver;

public class MainActivity extends UIComponentHelper {
	@SuppressWarnings("rawtypes")
	private AndroidDriver mDriver;
	
	@SuppressWarnings("rawtypes")
	public MainActivity(AndroidDriver driver) throws Exception {
		super("MainActivity");
		this.mDriver = driver;
	}
	
	public LoggedInActivity signInWithUserNameAndPassword(String username, String password) throws Exception {
		if(getElementXPath("sign-in") != null) {
			mDriver.findElementByXPath(getElementXPath("sign-in")).click();
			SignInActivity sia = new SignInActivity(mDriver);
			return sia.enterSignInDetails(username, password);
		}
		return null;
	}
}
