package com.perficient.test.google.basepages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.perficient.test.util.Page;
import com.perficient.test.util.TestCaseBase;
import com.perficient.test.util.Waiting;

public class GoogleSignInPage extends Page{
	public static String TITLE = "Google Accounts";
	
	@FindBy(id = "Email")
	public WebElement email;

	@FindBy(id = "Passwd")
	public WebElement password;

	@FindBy(id = "signIn")
	public WebElement signIn;

	@FindBy(id = "PersistentCookie")
	public WebElement staySignIn;



	public boolean titleContains(String title) {
		
		return (TestCaseBase.threadDriver.get().getTitle().contains(title));
	}

	public GoogleHomePage signIn(String usr, String pwd) {
		email.sendKeys(usr);
		password.sendKeys(pwd);
		if (staySignIn.isSelected()) {
			staySignIn.click();
		}
		signIn.click();
		
		return new GoogleHomePage();
	}

	public GoogleSignInPage waitPageLoad() throws InterruptedException {
		Waiting.until(email);
		return this;
	}

}
