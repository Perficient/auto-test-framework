package com.perficient.test.google.basepages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.perficient.test.util.Page;
import com.perficient.test.util.TestCaseBase;
import com.perficient.test.util.Waiting;

public class GoogleWebSearchResultPage extends Page {

	@FindBy(xpath = "//*[@id='rso']/li[1]//h3/a")
	public WebElement firstLink;

	@FindBy(id = "resultStats")
	public WebElement resultStats;

	// returns a boolean value for assertion
	public boolean titleIs(String title) {
		title = title + " - Google Search";
		log.info("expected title="+title);
		log.info("actual title="+getTitle());
		return (getTitle().equals(title));
	}

	public void search(String searchTerm) throws Exception {
		// FunctionUtil.input(searchInput, searchTerm);
		// searchInput.submit();
	}

	public PerficientHomePage clickFirstLink() throws Exception {
		Waiting.until(firstLink);		
		//log.info (firstLink.getText());
		if (TestCaseBase.browserFlag.equals("ie"))
		{firstLink.sendKeys("\n");}
		else
		{firstLink.click();}
		
		return new PerficientHomePage();
	}

	public GoogleWebSearchResultPage waitPageLoad() throws InterruptedException {

		Waiting.until(firstLink);
	//	WaitHelper.wait(resultStats,30);
		return this;
	}

}
