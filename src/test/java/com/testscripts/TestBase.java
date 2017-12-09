package com.testscripts;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;



public class TestBase {

	// Create the Global Objects
	public static Properties CONFIG = null;
	public WebDriver driver = null;

	// Initialize Configuration
	public void initConfig() {

		CONFIG = new Properties();
		try {
			FileInputStream input = new FileInputStream("D:\\Naveen_Work\\Selenium_work\\MavenSelTest\\src\\test\\java\\Config.properties");
			// load a properties file
			CONFIG.load(input);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

	// To initialize the Driver
	public void initBrowser() {
		if (driver == null) {
			if (CONFIG.getProperty("browser").equalsIgnoreCase("Mozilla")) {
				System.setProperty("webdriver.gecko.driver", "D:\\Naveen_work\\selenium\\geckodriver.exe");
				driver = new FirefoxDriver();				
			}
			else if (CONFIG.getProperty("browser").equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver", "D:\\Naveen_work\\selenium\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();				
			}
			else if (CONFIG.getProperty("browser").equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver", "D:\\Naveen_work\\selenium\\chromedriver.exe");
				driver = new ChromeDriver();				
			}
			else {
				System.setProperty("webdriver.chrome.driver", "D:\\Naveen_work\\selenium\\chromedriver.exe");
				driver = new ChromeDriver();
			}			

			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			System.out.println("Browser Initialized");
		} else {
			System.out.println("WebDriver Instance was avaiable ");

		}
	}

	public void invokeApplication() {
		driver.get("http://eauctionkart.com/");
	}

	public void closeBrowser() {
		driver.close();
		System.out.println("Browser Closed");
	}

	public void quitDriver() {
		driver.quit();
		System.out.println("All Browser Instance are closed");
	}

	public void takeScreenShot(String fileName) {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "\\screenshots\\" + fileName + ".jpg"));
		} catch (Exception e) {
			System.err.println("Failed to create Screenshot with following exception" + e);
			e.printStackTrace();
		}
	}

	@BeforeClass
	public void init() {
		initConfig();
		initBrowser();
		invokeApplication();
	}

	@AfterClass
	public void tearDown() {
		quitDriver();
	}
}
