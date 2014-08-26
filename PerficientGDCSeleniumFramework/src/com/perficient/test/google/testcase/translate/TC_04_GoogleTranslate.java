package com.perficient.test.google.testcase.translate;

import org.testng.annotations.Test;

import com.perficient.test.google.basepages.GoogleTranslatePage;
import com.perficient.test.util.TestCaseBase;
import com.perficient.test.util.TestData;

public class TC_04_GoogleTranslate extends TestCaseBase {
	// @Test(groups={"ChromeWin32"})
	// @Test(groups = { "firefox" })
	// @Test(groups={"IEWin32"})
	@Test(groups = { "firefox", "IEWin32", "ChromeWin32","random","browserPercentageSpecified" })
	public void testTranslate() throws Exception {

		String input = TestData.get("translate.input");
		String expectedResult = TestData.get("translate.result");

		GoogleTranslatePage googleTranslatePage = new GoogleTranslatePage();
		googleTranslatePage.open();
		googleTranslatePage.waitPageLoad();
		assert (googleTranslatePage.titleIs(GoogleTranslatePage.TITLE));
		
		googleTranslatePage.translateInput(input);
		googleTranslatePage.waitResultLoad();
	    assert(	googleTranslatePage.contain(expectedResult));

	}

}
