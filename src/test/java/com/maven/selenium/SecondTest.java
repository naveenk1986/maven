package com.maven.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SecondTest {
	static WebDriver driver;
	@Test
	public void test1()	
	 {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "D:\\Naveen_work\\selenium\\chromedriver.exe");
			driver=new ChromeDriver();
			driver.get("https://www.google.co.in/");
			driver.findElement(By.id("lst-ib")).sendKeys("maven");
			driver.quit();
	}
	@Test
public void test2()	
 {
	// TODO Auto-generated method stub
	System.setProperty("webdriver.chrome.driver", "D:\\Naveen_work\\selenium\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://www.google.co.in/");
		driver.findElement(By.id("lst-ib")).sendKeys("naveen");
		driver.quit();
}

}
