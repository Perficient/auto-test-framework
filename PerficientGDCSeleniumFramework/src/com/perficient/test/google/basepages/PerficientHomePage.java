package com.perficient.test.google.basepages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.perficient.test.util.FunctionUtil;
import com.perficient.test.util.Page;
import com.perficient.test.util.TestCaseBase;
import com.perficient.test.util.Waiting;

public class PerficientHomePage extends Page {
	public static String TITLE = "Perficient, Inc. | Experts in Business Driven Technology Solutions";

	@FindBy(xpath = "//div")
	public WebElement anyDiv;
	
	public boolean titleIs(String title) {
		log.info ("expected result="+title);
		log.info ("actual result="+TestCaseBase.threadDriver.get().getTitle());
		return (TestCaseBase.threadDriver.get().getTitle().equals(title));
	}
	
	public PerficientHomePage waitPageLoad() {
		Waiting.until(anyDiv);
		return this;
	}
	public PerficientHomePage switchToNewWindow() {
		// Switch to new window opened
		FunctionUtil.switchToNewWindow();
		return new PerficientHomePage();
	}
}
