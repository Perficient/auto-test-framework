package com.perficient.test.google.testcase.account;

import org.testng.annotations.Test;

import com.perficient.test.google.basepages.GoogleHomePage;
import com.perficient.test.google.basepages.GoogleSignInPage;
import com.perficient.test.util.TestCaseBase;
import com.perficient.test.util.TestData;

public class TC_03_GoogleUserSignInSignOut extends TestCaseBase {
	//@Test(groups={"ChromeWin32"})
	//@Test(groups = { "firefox" })
	//@Test(groups={"IEWin32"})
	@Test(groups = { "firefox", "IEWin32", "ChromeWin32" ,"random","browserPercentageSpecified"})
	public void testSignIn() throws Exception {

		// test data
		String email=TestData.get("email");
		String pwd=TestData.get("password");
		
		GoogleHomePage googleHomePage = new GoogleHomePage();
		googleHomePage.open();
		googleHomePage.waitPageLoad();
		assert (googleHomePage.titleIs(GoogleHomePage.TITLE));

		GoogleSignInPage googleSignInPage=googleHomePage.gotoSignPage();
		googleSignInPage.waitPageLoad();
		assert (googleSignInPage.titleContains(GoogleSignInPage.TITLE));

		googleHomePage=googleSignInPage.signIn(email, pwd);
		assert (googleHomePage.titleIs(GoogleHomePage.TITLE));
		assert (googleHomePage.isUserSignedIn());
		
		googleHomePage=googleHomePage.logout();
		googleHomePage.waitPageLoad();
		assert (googleHomePage.titleIs(GoogleHomePage.TITLE));
		log.info("expected Signed In=false");//expected the user is not signed in after the logout.
		assert (!googleHomePage.isUserSignedIn());

	}

}
