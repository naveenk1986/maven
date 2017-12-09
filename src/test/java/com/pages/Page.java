package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Page {
	
   // Create the Global Objects
   
   public boolean isElementPresent(WebDriver driver, By locator) {
	   try {
		   driver.findElement(locator);
		   return true;
	   }
	   catch(Exception e){
		   return false;
	   }
   }
   
   public boolean clickOnElement(WebDriver driver, By locator) {
	   try {
		   driver.findElement(locator).click();
		   return true;
	   }
	   catch(Exception e){
		  System.err.println( e);
		   return false;
	   }
   }
}
