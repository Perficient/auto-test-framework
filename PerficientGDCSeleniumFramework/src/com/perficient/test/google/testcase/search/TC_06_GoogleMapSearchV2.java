package com.perficient.test.google.testcase.search;

import org.testng.annotations.Test;

import com.perficient.test.google.basepages.GoogleHomePage;
import com.perficient.test.google.basepages.GoogleMapPage;
import com.perficient.test.util.TestCaseBase;
import com.perficient.test.util.TestData;

public class TC_06_GoogleMapSearchV2 extends TestCaseBase {
	 //@Test(groups={"ChromeWin32"})
	 @Test(groups = { "firefox" })
	// @Test(groups = { "IEWin32" })
//	@Test(groups = { "firefox", "IEWin32", "ChromeWin32","random","browserPercentageSpecified" })
	public void testSearchMap() throws Exception {
		//every test method, should have a expected result and to run the tests
		//to get some actual Result
		//If the expected result is equal to the actual result (or fit some other rule)
		//then the test passed
		//or the test failed.
		
		//read test data
		String mapSearchInput = TestData.get("input");
		String suggestedRoutes = TestData.get("result.suggestroutes");
		//set expected result
		expected.put("suggested_routes",suggestedRoutes);
		expected.put("title","Google Map");
		expected.put("directionPanelDisplayed", "true");
		//to get actual result, here at least we have one actual result
		//to get more than one actual result, first, we get the result page
		//then get the result we want to assert
		//then assert them
		GoogleMapPage page=new GoogleHomePage().open().waitPageLoad()
						.gotoGoogleMap().waitPageLoad()
						.search(mapSearchInput).waitPageLoad()
						.zoomIn().findResturant();

		//assert a web element text displayed
		//assertion is the only place where we can use a web element directly in test case
		//so define an actual Result like this is allowed:
		actualResult=page.suggestedRoutes.getText();
		assertion.equals(actualResult, expected, "suggested_routes");

		//assert title
		actualResult=page.getTitle();
		assertion.contains(actualResult, expected, "title");
		
		//assert if an element is displayed
		actualResult=page.directionPanel.isDisplayed()+"";
		assertion.equals(actualResult, expected, "directionPanelDisplayed");
		
		//assert more if you want, at least one assertion is required for each test case
	}
}
