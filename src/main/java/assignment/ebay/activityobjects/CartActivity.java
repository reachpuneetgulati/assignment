package assignment.ebay.activityobjects;

import assignment.ebay.componenthelper.UIComponentHelper;
import io.appium.java_client.android.AndroidDriver;

/*
 * 
 * Represents the Cart of the user
 * 
 */
public class CartActivity extends UIComponentHelper{

	@SuppressWarnings("rawtypes")
	private AndroidDriver mDriver;
	
	@SuppressWarnings("rawtypes")
	public CartActivity(AndroidDriver driver) {
		super("CartActivity");
		this.mDriver = driver;
	}
	
	public CheckOutActivity proceedToCheckOut() {
		logMsg("Proceeding to checkout");
		if(getElementXPath("checkout") != null) {
			mDriver.findElementByXPath(getElementXPath("checkout")).click();
			return new CheckOutActivity(mDriver);
		}
		return null;
	}
	
	public void clearCartAndGoBack() {
		logMsg("Clearing cart and going back");
		if(getElementXPath("removefromcart") != null) {
			int count = mDriver.findElementsByXPath(getElementXPath("checkout")).size();
			try {
				while(count > 0) {
					mDriver.findElementByXPath(getElementXPath("removefromcart")).click();
					if(getElementXPath("removeconfirm") != null) {
						mDriver.findElementByXPath(getElementXPath("removeconfirm")).click();
					}
					count = mDriver.findElementsByXPath(getElementXPath("checkout")).size();
				}
			}catch(Exception e) {}
		}
		if(getElementXPath("close") != null) {
			mDriver.findElementByXPath(getElementXPath("close")).click();
		}
	}
}
