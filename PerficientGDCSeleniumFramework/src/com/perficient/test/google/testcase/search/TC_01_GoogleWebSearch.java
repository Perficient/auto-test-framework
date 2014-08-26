package com.perficient.test.google.testcase.search;

import org.testng.annotations.Test;

import com.perficient.test.google.basepages.GoogleHomePage;
import com.perficient.test.google.basepages.GoogleWebSearchResultPage;
import com.perficient.test.google.basepages.PerficientHomePage;
import com.perficient.test.util.FunctionUtil;
import com.perficient.test.util.TestCaseBase;

public class TC_01_GoogleWebSearch extends TestCaseBase {
	
	// @Test(groups={"ChromeWin32"})
	// @Test(groups = { "firefox" })
	// @Test(groups={"IEWin32"})
	@Test(groups = { "firefox", "IEWin32", "ChromeWin32" ,"random","browserPercentageSpecified"})
	public void testSearchWeb() throws Exception {
		
		//new this page with page object factory
		//Simply new this object with its default constructor
		//it will calls the parent class's constructor which will init this page object
		GoogleHomePage googleHomePage = new GoogleHomePage();
		
		//call a test action defined in home page
		//actually we should only call these actions but not directly use page element in test case
		googleHomePage.open();
		
		// assertion only comes after the steps we want to test, if the assertion fails, tests will fails and exit(by default, can modify)
		// if you don't want the tests to exit when fail, either use a soft assert or separate the test logic in 2 test method
		// usually do assertions against something we could see, you could also do some other assertions according to the requriement
		assert (googleHomePage.titleIs(GoogleHomePage.TITLE));
		
		//do search on home page will direct to result page, 
		//so the search method will return a page object of search result page.
		//that means we don't need to init the search result page here
		//simply use the following style:
		GoogleWebSearchResultPage googleWebSearchResultPage = googleHomePage.search("perficient");
		
		//a wait method could be defined in the page
		//And a new and invisible way of waiting will be developed in future version
		googleWebSearchResultPage.waitPageLoad();
		assert (googleWebSearchResultPage.titleIs("perficient"));
	    
		//clickFirstLink is a test action which simply click a link,
		//even this, we don't use googleWebSearchResultPage.FirstLink().click()
		//why not use "googleWebSearchResultPage.FirstLink.click()"?
		//It is because this way is not easy to maintain.
		//For example, if the original logic: click first link, changed to click a button ,then click a link
		//in this way, every place we use "googleWebSearchResultPage.FirstLink.click()"
		//should be changed to "googleWebSearchResultPage.somebutton.click();googleWebSearchResultPage.FirstLink.click()"
		//but if we use the "googleWebSearchResultPage.clickFirstLink()", we need only modify one place:
		//modify the clickFirstLink() method in the result page.		
		PerficientHomePage perficientHomePage=googleWebSearchResultPage.clickFirstLink();
		FunctionUtil.switchToNewWindow();//switch to new window
		perficientHomePage.waitPageLoad();
		assert (perficientHomePage.titleIs(PerficientHomePage.TITLE));
			
	}

}
