package com.testscripts;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pages.HomePage;
import com.pages.LoginPage;
import com.utilities.TestUtil;

import jxl.read.biff.BiffException;

public class LoginPageTest extends TestBase {
			
	@DataProvider
	public Object[] getLoginData() throws BiffException, IOException{
		return TestUtil.getData("LoginSheet");
	}
	@DataProvider
	public Object[] getHomeData() throws BiffException, IOException{
		return TestUtil.getData("DataSheet");
	}

	@Test(dataProvider="getLoginData")
	public void loginTest(Hashtable<String, String> data) {
		HomePage home = PageFactory.initElements(driver, HomePage.class);
		Assert.assertFalse(home.checkIfLogin(), "Login already happened please check it");
		LoginPage loginPage = home.clickOnLogin();
		if(data.get("Runmode").equalsIgnoreCase("NO"))
			throw new SkipException("Skipping the test case as rumode is set to false. TestcaseID:" + data.get("TCID"));
		loginPage.validateLogin(data.get("UserName"), data.get("Password"));
		System.out.println("successfully executed the first test case");
	}
	@Test(dataProvider="getHomeData")
	public void ScenarioTest(Hashtable<String, String> data) throws InterruptedException {
		HomePage home = PageFactory.initElements(driver, HomePage.class);
		home.sellanitem_Link.click();
		Assert.assertFalse(home.checkIfLogin(), "Login already happened please check it");
		LoginPage loginPage = home.clickOnLogin();
		if(data.get("Runmode").equalsIgnoreCase("NO"))
			throw new SkipException("Skipping the test case as rumode is set to false. TestcaseID:" + data.get("TCID"));
		loginPage.doLogin(data.get("UserName"), data.get("Password"));
		home.sell_an_itm(data.get("Title"), data.get("SubTitle"),data.get("Desc"), data.get("Min_Bid"));
		home.logout_Link.click();
		System.out.println("successfully executed the second test case");
	}
}

