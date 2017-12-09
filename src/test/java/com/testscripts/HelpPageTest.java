package com.testscripts;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pages.HelpPage;
import com.pages.HomePage;

public class HelpPageTest  extends TestBase{
	
	
	@Test
	public void helpTest() {
		initBrowser();
		HomePage home = PageFactory.initElements(driver, HomePage.class);
		HelpPage helpPage = home.clickOnHelp();
		Assert.assertTrue(helpPage.switchFocusToHelpWindow(), "Cannot switch to help window");
		Assert.assertTrue(helpPage.clickOnBuyingLink(),  "");
		Assert.assertTrue(helpPage.clickOnClose(), "");
		Assert.assertFalse(helpPage.switchFocusToHelpWindow(), "");
	}
}
