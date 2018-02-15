package assignment.ebay.activityobjects;

import assignment.ebay.componenthelper.UIComponentHelper;
import io.appium.java_client.android.AndroidDriver;

/**
 * 
 * Checkout Activity represents the Checkout activity of the app
 *
 */

public class CheckOutActivity extends UIComponentHelper{

	@SuppressWarnings("rawtypes")
	private AndroidDriver mDriver;
	
	@SuppressWarnings("rawtypes")
	public CheckOutActivity(AndroidDriver driver) {
		super("CheckOutActivity");
		this.mDriver = driver;
	}
	
	public boolean purchaseProduct() {
		return true;
	}
}
