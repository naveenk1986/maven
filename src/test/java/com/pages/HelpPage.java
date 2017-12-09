package com.pages;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelpPage extends Page {
	
	public WebDriver driver;

	// Define the Constructor and pass the WebDriver instance to it
	public HelpPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean clickOnBuyingLink() {
		return clickOnElement(driver, By.linkText("Buying"));
	}
	
	public boolean clickOnClose() {
		return clickOnElement(driver, By.xpath("//a[contains(@href, 'javascript:window.close()')]"));
	}
	/**
	 * switch to the help window if exist and returns true else returns false
	 * @return
	 */
	public boolean switchFocusToHelpWindow() {
		boolean isSwitchedToHelp = false; 
		String currentHandel = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		for (String handel : windowHandles) {
			driver.switchTo().window(handel);
			if (isElementPresent(driver, By.cssSelector("a.list-group-item")) && driver.findElement(By.cssSelector("a.list-group-item")).getText().equals("Buying") ) {
				System.out.println("Switching to help Window");
				isSwitchedToHelp = true;
				break;				
			}
		}
		if (!isSwitchedToHelp && windowHandles.contains(currentHandel)) {
			System.out.println("Help Window is not shown up or closed so switching the driver to default handle");
			driver.switchTo().window(currentHandel);
		}
		
		return isSwitchedToHelp;
	}

}
