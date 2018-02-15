package assignment.ebay.testcases;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import assignment.ebay.activityobjects.CartActivity;
import assignment.ebay.activityobjects.CheckOutActivity;
import assignment.ebay.activityobjects.EnterQuantityActivity;
import assignment.ebay.activityobjects.LoggedInActivity;
import assignment.ebay.activityobjects.MainActivity;
import assignment.ebay.activityobjects.ProductActivity;
import assignment.ebay.activityobjects.SearchResultActivity;
import assignment.ebay.base.TestBase;
import io.appium.java_client.android.AndroidDriver;

/*
 * 
 * Class to test Logging in and Purchasing a product
 * 
 * Includes the following scenarios:
 * 1. Purchasing directly through Buy It Now
 * 2. Purchasing through Adding To Cart and then buying it
 * 
 */

public class LogInAndPurchase extends TestBase{
	

	@SuppressWarnings("rawtypes")
	
	private AndroidDriver mDriver;
	private Logger mLog = LogManager.getLogger(this.getClass().getName());

	@SuppressWarnings("rawtypes")
	@BeforeMethod
	public void init() {
		mLog.debug("Driver Initilization");
		mDriver = (AndroidDriver) initializeDriver();
	}
	
	@Test(dataProvider="LogInAndPurchaseData")
	public void LogInAndPurchaseAProductBuyItNow(String username, String password, String product, String creditcard, String expiry, String cvv) throws Exception {
		mLog.debug("Purchasing through Buy It Now");
		
		try {
			MainActivity mA = new MainActivity(mDriver);
			LoggedInActivity la =  mA.signInWithUserNameAndPassword(username,password);
			la.clearCart();
			SearchResultActivity sr = la.searchProduct(product);
			ProductActivity pa = sr.selectFirstProduct();
			EnterQuantityActivity eqa = pa.proceedToBuy();
			eqa.increaseQuantity();
			CheckOutActivity coa = eqa.proceedToCheckout();
			assertEquals(true, coa.purchaseProduct());
		}catch(Exception e) {
			mLog.error("Exception in LogInAndPurchaseAProductBuyItNow\n".concat(e.getMessage()));
			takeScreenShot(mDriver, "LogInAndPurchaseAProductBuyItNow");
			assertFalse(true);
		}
		mLog.debug("Finished Purchasing through Buy It Now");
	}

	@Test(dataProvider="LogInAndPurchaseData")
	public void LogInAndPurchaseAProductAddInCart(String username, String password, String product, String creditcard, String expiry, String cvv) throws Exception {
		mLog.debug("Purchasing through Adding to Cart");
		try {
			MainActivity mA = new MainActivity(mDriver);
			LoggedInActivity la =  mA.signInWithUserNameAndPassword(username,password);
			la.clearCart();
			SearchResultActivity sr = la.searchProduct(product);
			ProductActivity pa = sr.selectFirstProduct();
			CartActivity ca = pa.addToCart();
			CheckOutActivity coa = ca.proceedToCheckOut();
			assertEquals(true, coa.purchaseProduct());
		}catch(Exception e) {
			mLog.error("Exception in LogInAndPurchaseAProductAddInCart\n".concat(e.getMessage()));
			takeScreenShot(mDriver, "LogInAndPurchaseAProductAddInCart");
			assertFalse(true);
		}
		mLog.debug("Finished Purchasing through Adding to Cart");
	}

	@DataProvider
	public Object[][] LogInAndPurchaseData(){
		try {
			return getData("./TestFiles/TestData.xlsx", "logInAndPurchaseAProduct");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	@AfterMethod
	public void tearDown() {
		System.out.println("Running after test");
		mDriver.quit();
		mDriver = null;
	}
}
