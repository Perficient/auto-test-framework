package com.perficient.test.google.basepages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.perficient.test.util.Page;
import com.perficient.test.util.TestCaseBase;
import com.perficient.test.util.Waiting;

public class GoogleMapPage extends Page {

	public static String TITLE = "Google Maps";
	
	//it can be located by xpath
	@FindBy(xpath = "//div[@class='dir-altroute-inner']/div[last()-1]")
	public WebElement suggestedRoutes;

	@FindBy(id = "d_daddr")
	public WebElement destinationResult;

	@FindBy(xpath = "//span[@class='pp-place-title']")
	public WebElement searchResultTitle;

	@FindBy(id = "panel_A_2")
	public WebElement desA2;
	@FindBy(name = "q")
	public WebElement searchInput;

	@FindBy(id = "gbqfb")
	public WebElement searchSubmit;

	@FindBy(id = "d_d")
	public WebElement startPoint;

	
	@FindBy(id = "d_daddr")
	public WebElement endPoint;
	
	@FindBy(id = "d_sub")
	public WebElement getDirections;

	@FindBy(xpath = "//div[@title='Zoom In']")
	public WebElement zoomIn;

	@FindBy(xpath = "//div[@title='Zoom Out']")
	public WebElement zoomOut;

	@FindBy(xpath = "//div[@title='Pan left']")
	public WebElement left;

	@FindBy(xpath = "//div[@title='Pan right']")
	public WebElement right;

	@FindBy(xpath = "//div[@title='Pan up']")
	public WebElement up;

	@FindBy(xpath = "//div[@title='Pan down']")
	public WebElement down;

	@FindBy(xpath = "//img[contains(@id,'mtgt_A')]")
	public WebElement areaA;// on chrome & ie

	@FindBy(xpath = "//img[contains(@id,'mtgt_J')]")
	public WebElement areaJ;// on chrome & ie

	@FindBy(xpath = "//area[contains(@id,'mtgt_A')]")
	public WebElement areaA_FF;// only on firefox

	@FindBy(xpath = "//area[contains(@id,'mtgt_J')]")
	public WebElement areaJ_FF;// only on firefox
	
	@FindBy(xpath = "//div[@id='link_J_2']")
	public WebElement areaJ_left;
	
	@FindBy(xpath = "//area[contains(@id,'mtgt_A_2')]")
	public WebElement areaA2;
	@FindBy(xpath = "//*[@id='wizard']//li[@action='actbar-sn']")
	public WebElement searchNearby;

	@FindBy(xpath = "//*[@id='wizard']//li[@action='directions']")
	public WebElement directions;
	
	@FindBy(xpath="//a[@id='d_launch']")
	public WebElement directionPanel;
	
	@FindBy(xpath = "//*[@id='wizard']//input[@class='actbar-input']")
	public WebElement searchNearbyInput;

	@FindBy(xpath = "//*[@id='wizard']//input[@name='Search']")
	public WebElement searchNearbySearchButton;


	public GoogleMapPage search(String searchTerm) throws Exception {
		searchInput.sendKeys(searchTerm);
		searchInput.submit();
		return this;
	}

	// to zoonIn in google map
	public GoogleMapPage zoomIn() throws InterruptedException {
		String browserFlag = TestCaseBase.browserFlag;
		if (browserFlag.equals("firefox")||browserFlag.equals("ie")) {
			zoomOut.click();//first zoom out then zoom in
			zoomOut.click();
			zoomIn.click();
			zoomIn.click();
			zoomIn.click();
			zoomIn.click();
		} else if (browserFlag.equals("chrome"))
		{
			// a hard wait? yes, we need to avoid this kind of waiting
			// but there is some special case that the soft wait not works.
			// and the google map is one of them, if you use chrome
			Thread.sleep(9000);
			zoomIn.click();
		} 
			
		return this;
	}

	//find some place
	public GoogleMapPage findResturant() throws InterruptedException {

		String browserFlag = TestCaseBase.browserFlag;

		if (browserFlag.equals("firefox")) {
			Waiting.until(areaA_FF);
			areaA_FF.click();
		} else if (browserFlag.equals("ie"))  {
			Waiting.until(areaA);
		    areaA.click();
		} else if (browserFlag.equals("chrome"))
		{
			Thread.sleep(9000);
			areaA.click();
		}
		
		
		Waiting.until(searchNearby);
		if (browserFlag.equals("chrome")){Thread.sleep(9000);}
		searchNearby.click();
		searchNearbyInput.sendKeys("restaurant");
		searchNearbySearchButton.click();

		if (browserFlag.equals("firefox")) {
			Waiting.until(areaJ_FF);
			areaJ_FF.click();
		} else if (browserFlag.equals("ie")) {
			Waiting.until(areaJ);
			areaJ.click();
		}else if (browserFlag.equals("chrome"))
		{
			Thread.sleep(9000);
			//what to do when some element can't be located?
			//here is an example which use java script to locate and click on element
			TestCaseBase.driver.executeScript("document.getElementById('link_J_2').click()");
		}
				
		if (browserFlag.equals("chrome")){
			Thread.sleep(3000);
			String endpoint=areaJ_left.getText();
			log.info ("here the endpoint is: "+endpoint);
			directionPanel.click();
			startPoint.sendKeys("Perficient");
			startPoint.sendKeys(Keys.ESCAPE);
			endPoint.clear();
			endPoint.sendKeys(endpoint);
			endPoint.sendKeys(Keys.ESCAPE);
			getDirections.click();
		}
		else{
		Waiting.until(directions);
		directions.click();
		startPoint.sendKeys("Perficient");
		startPoint.sendKeys(Keys.ESCAPE);
		getDirections.click();
		}
		return this;
	}

	public GoogleMapPage waitPageLoad() throws InterruptedException {
		Waiting.until(zoomOut);
		return this;
	}

	public boolean searchResultTitleContains(String mapSearchResultPerficient) {
		log.info("expected title="+mapSearchResultPerficient);
		log.info("actual title="+searchResultTitle.getText());
		return searchResultTitle.getText().contains(mapSearchResultPerficient);
	}

	public boolean suggestedRoutesContains(String suggestedRoutesExp) {
		
		log.info("expected results="+suggestedRoutesExp);
		log.info("actual results="+suggestedRoutes.getText());
		return suggestedRoutes.getText().contains(suggestedRoutesExp);
	}

}
