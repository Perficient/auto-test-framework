package com.perficient.test.util;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
//this class contains many method could be reuse in tests
//it should always be re-factor to keep this class clean
//for example, the wait method is put in a separate class now, it was a part of this class
public class FunctionUtil {
	private static Log log = LogFactory.getLog("FunctionUtil");
	/**
	 * Objective: switch to frame from another frame
	 * 
	 * @param frameName
	 *            : frameName string
	 * @throws Exception
	 */
	static public void switchToFrame(WebElement frame) throws Exception {
		TestCaseBase.threadDriver.get().switchTo().defaultContent();
		TestCaseBase.threadDriver.get().switchTo().frame(frame);
	}

	static public void maximeBrowser() {
		TestCaseBase.threadDriver.get().manage().window().maximize();
	}

	// select a value from a selector 
	public static void select(WebElement element, String value)
			throws Exception {
		String browserFlag = TestCaseBase.browserFlag;

		if (browserFlag.equals("firefox")) {
			element.sendKeys(value); // firefox
		} else if (browserFlag.equals("ie") || browserFlag.equals("chrome")) {
			// element.click();
			List<WebElement> optionList = element.findElements(By
					.tagName("option"));
			for (WebElement option : optionList) {
				if (option.getText().trim().equalsIgnoreCase(value)) {
					option.click();
					break;
				}
			}

		} else {
			log.error("browserFlag is not set as \"ie\" or \"firefox\" in TestCaseBase class");

			throw new Exception(
					"browserFlag is not set as \"ie\" or \"firefox\" in TestCaseBase class");
		}
	}

	//to check if the element is exit, remember to wait before use this
	//see an example in googleHomePage.isUserSignedIn()
	public static boolean isExist(WebElement element) {
		try {
			// call any method on the element
			element.isDisplayed();
		} catch (Exception e) {
			log.warn("the element is not visible...");
			return false;
		}
		// if no exception thrown, it must be exists
		return true;
	}
	
	//to switch to a new window
	public static void switchToNewWindow()
	{
		// Switch to new window opened
		for (String winHandle : TestCaseBase.threadDriver.get().getWindowHandles()) {
			Waiting.implicitly(10);
			TestCaseBase.threadDriver.get().switchTo().window(winHandle);
		}	
	}

}
