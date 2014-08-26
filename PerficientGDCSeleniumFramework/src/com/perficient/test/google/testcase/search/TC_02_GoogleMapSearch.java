package com.perficient.test.google.testcase.search;

import org.testng.annotations.Test;

import com.perficient.test.google.basepages.GoogleHomePage;
import com.perficient.test.google.basepages.GoogleMapPage;
import com.perficient.test.util.TestCaseBase;
import com.perficient.test.util.TestData;

public class TC_02_GoogleMapSearch extends TestCaseBase {
	

	 //@Test(groups={"ChromeWin32"})
	// @Test(groups = { "firefox" })
	// @Test(groups = { "IEWin32" })
	@Test(groups = { "firefox", "IEWin32", "ChromeWin32","random","browserPercentageSpecified" })
	public void testSearchMap() throws Exception {
		
		//The Test Data:
		//This is a shorter way of read test data,
		//Every test case has a default test data file which name is "testdata_classname.properties"
		//for example, this test case should have a data file"testdata_TC_02_GoogleMapSearch.properties"
		//then you could use TestData.get("name") to get your test data 
		
		//if you don't want to create a default test data file for each test case, you could
		//use the default way of get test data provided by the framework,
		//see detail in com.perficient.test.util.SystemUtil.java and Waiting.java
		//the default way allows your to put test data in specific file
		String mapSearchResultPerficient = TestData.get("result.perficient");
		String mapSearchInput = TestData.get("input");
		
		
		GoogleHomePage googleHomePage = new GoogleHomePage();
		googleHomePage.open();
		googleHomePage.waitPageLoad();
		assert (googleHomePage.titleIs(GoogleHomePage.TITLE));

		GoogleMapPage googleMapPage=googleHomePage.gotoGoogleMap();
		googleMapPage.waitPageLoad();
		assert (googleMapPage.titleIs(GoogleMapPage.TITLE));

		googleMapPage.search(mapSearchInput);
		googleMapPage.waitPageLoad();
		assert (googleMapPage.searchResultTitleContains(mapSearchResultPerficient));
		
		//the new assertion, see detail in TC_06_GoogleMapSearchV2.java
		googleMapPage.zoomIn();
		googleMapPage.findResturant();
		expected.put("suggested_routes",TestData.get("result.suggestroutes"));
		actualResult=googleMapPage.suggestedRoutes.getText();
		assertion.equals(actualResult, expected, "suggested_routes");

	}
}
