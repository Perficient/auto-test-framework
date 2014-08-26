package com.perficient.test.google.testcase.search;

import org.testng.annotations.Test;

import com.perficient.test.google.basepages.GoogleHomePage;
import com.perficient.test.google.basepages.PerficientHomePage;
import com.perficient.test.util.CustomAssertion;
import com.perficient.test.util.TestCaseBase;

//just for your reference - Another style of test case  
public class TC_05_GoogleWebSearchV2 extends TestCaseBase {
	// @Test(groups={"ChromeWin32"})
	 @Test(groups = { "firefox" })
	// @Test(groups={"IEWin32"})
	// @Test(groups = { "firefox", "IEWin32", "ChromeWin32" ,"random","browserPercentageSpecified"})
	
	public void testSearchWeb() throws Exception {
		//expected the title to be PerficientHomePage.Title
		//this can also be get from a data file 
		expected.put("title", PerficientHomePage.TITLE);
		//run the test to get actual result
		//method chaining,use an internal DSL to create tests
		actualResult=new GoogleHomePage().open()
						   .search("perficient")
					       .waitPageLoad()
					       .clickFirstLink()
					       .switchToNewWindow()
					       .waitPageLoad().getTitle();
		//assertion will print expected results, actual results, and do the assertion
		assertion.equals(actualResult,expected,"title");
		
	}

}
