package com.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage extends HomePage {

	public WebDriver driver;
	
	String expectedError = "Login incorrect";
	
	// Define the Constructor and pass the WebDriver instance to it
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	// In Page Factory we use the @FindBy to store the locators.	
	@FindBy(name = "username")
	public WebElement userName;

	@FindBy(name = "password")
	public WebElement password;
	
	@FindBy(css = "div.alert.alert-danger")
	public WebElement errorElement;

	public  HomePage doLogin(String username, String password) {
		userName.clear();
		userName.sendKeys(username);
		this.password.clear();
		this.password.sendKeys(password);

		// To press the Enter Key
		this.password.sendKeys(Keys.ENTER);
		System.out.println("Login Successfully");
		return PageFactory.initElements(driver, HomePage.class);
	}
	
	public void validateLogin(String username, String password) {
		userName.clear();
		userName.sendKeys(username);
		this.password.clear();
		this.password.sendKeys(password);
		
		//Store username
    	String uname=userName.getAttribute("value");
    	//Store password
    	String pass=this.password.getAttribute("value");
		// To press the Enter Key
		this.password.sendKeys(Keys.ENTER);
		if(uname.equals("") && pass.equals("")){
			Assert.assertEquals(expectedError, errorElement.getText());
		}
		else if(uname.equals("")){
			Assert.assertEquals(expectedError, errorElement.getText());
		}
		else if (pass.equals("")) {
			Assert.assertEquals(expectedError, errorElement.getText());
		}
		else if(checkIfLogin()){
			logout_Link.click();
		}
		else {
			Assert.assertEquals(expectedError, errorElement.getText());
		}
	}

}

