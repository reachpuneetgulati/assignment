package assignment.ebay.activityobjects;

import assignment.ebay.componenthelper.UIComponentHelper;
import io.appium.java_client.android.AndroidDriver;

public class ProductActivity extends UIComponentHelper{
	@SuppressWarnings("rawtypes")
	private AndroidDriver mDriver;
	
	@SuppressWarnings("rawtypes")
	public ProductActivity(AndroidDriver driver) {
		super("ProductActivity");
		this.mDriver = driver;
	}
	
	public EnterQuantityActivity proceedToBuy() {
		if(getElementXPath("buyitnow") != null) {
			mDriver.findElementByXPath(getElementXPath("buyitnow")).click();
			return new EnterQuantityActivity(mDriver);
		}
		return null;
	}

	public CartActivity addToCart() {
		if(getElementXPath("addtocart") != null && getElementXPath("viewincart") != null) {
			mDriver.findElementByXPath(getElementXPath("addtocart")).click();
			mDriver.findElementByXPath(getElementXPath("viewincart")).click();
			return new CartActivity(mDriver);
		}
		return null;
	}
}
