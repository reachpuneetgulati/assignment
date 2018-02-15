package assignment.ebay.activityobjects;

import io.appium.java_client.android.AndroidDriver;

public class PaymentActivity {
	@SuppressWarnings("rawtypes")
	private AndroidDriver mDriver;
	
	@SuppressWarnings("rawtypes")
	public PaymentActivity(AndroidDriver driver) {
		this.mDriver = driver;
	}
}
