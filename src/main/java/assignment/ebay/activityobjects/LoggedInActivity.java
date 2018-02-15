package assignment.ebay.activityobjects;

import assignment.ebay.componenthelper.UIComponentHelper;
import io.appium.java_client.android.AndroidDriver;

/*
 * 
 * LoggedIn Activity represents the LoggedIn activity of the app
 * 
 */
public class LoggedInActivity extends UIComponentHelper{
	@SuppressWarnings("rawtypes")
	private AndroidDriver mDriver;
	
	@SuppressWarnings("rawtypes")
	public LoggedInActivity(AndroidDriver driver) {
		super("LoggedInActivity");
		this.mDriver = driver;
	}
	
	public SearchResultActivity searchProduct(String product) {
		if(getElementXPath("searchbox") != null) {
			mDriver.findElementByXPath(getElementXPath("searchbox")).click();
			mDriver.findElementByXPath(getElementXPath("searchbox")).sendKeys(product.concat(" \n"));
			return new SearchResultActivity(mDriver);
		}
		return null;
	}
	
	public void clearCart() {
		if(getElementXPath("itemincart") != null) {
			try {
				mDriver.findElementByXPath(getElementXPath("itemincart")).click();
				CartActivity ca = new CartActivity(mDriver);
				ca.clearCartAndGoBack();
			}catch(Exception e) {}			
		}
	}
	
}
