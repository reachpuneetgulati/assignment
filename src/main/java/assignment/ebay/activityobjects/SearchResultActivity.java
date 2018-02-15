package assignment.ebay.activityobjects;

import assignment.ebay.componenthelper.UIComponentHelper;
import io.appium.java_client.android.AndroidDriver;

public class SearchResultActivity extends UIComponentHelper{
	@SuppressWarnings("rawtypes" )
	private AndroidDriver mDriver;
	
	@SuppressWarnings("rawtypes")
	public SearchResultActivity(AndroidDriver driver) {
		super("SearchResultActivity");
		this.mDriver = driver;
	}
	
	public ProductActivity selectFirstProduct(){
		if(getElementXPath("firstproduct") != null) {
			mDriver.findElementByXPath(getElementXPath("firstproduct")).click();
			return new ProductActivity(mDriver);
		}
		return null;
	}
}
