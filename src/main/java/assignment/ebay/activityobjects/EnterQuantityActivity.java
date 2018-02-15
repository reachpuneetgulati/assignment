package assignment.ebay.activityobjects;

import assignment.ebay.componenthelper.UIComponentHelper;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

/**
 * 
 * Checkout Activity represents the Checkout activity of the app
 *
 */

public class EnterQuantityActivity extends UIComponentHelper {

	@SuppressWarnings("rawtypes")
	AndroidDriver mDriver;
	
	@SuppressWarnings("rawtypes")
	public EnterQuantityActivity(AndroidDriver driver) {
		super("EnterQuantityActivity");
		this.mDriver = driver;
	}
	
	@SuppressWarnings("deprecation")
	public void increaseQuantity() {
		logMsg("Updating quantity");
		if(getElementXPath("numberpicker") != null) {
			@SuppressWarnings("rawtypes")
			TouchAction scroll = new TouchAction(mDriver);
			scroll.press(mDriver.findElementByXPath(getElementXPath("numberpicker"))).moveTo(mDriver.findElementByXPath(getElementXPath("target")), 10, 10).perform();
		}
	}
	
	public CheckOutActivity proceedToCheckout() {
		logMsg("proceeding to checkout");
		if(getElementXPath("review") != null) {
			mDriver.findElementByXPath(getElementXPath("review")).click();
			return new CheckOutActivity(mDriver);
		}
		return null;
	}
}
