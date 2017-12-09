package com.pages;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends Page{
	public WebDriver driver;

	// Define the Constructor and pass the WebDriver instance to it
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(className = "glyphicon glyphicon-home")
	public WebElement home_Link;

	@FindBy(xpath = "//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/a")
	public WebElement sellanitem_Link;

	@FindBy(xpath = "//div[@id='bs-example-navbar-collapse-1']/ul/li[3]/a")
	public WebElement registernow_Link;

	@FindBy(linkText = "Login")
	public WebElement login_Link;

	@FindBy(xpath = "//div[@id='bs-example-navbar-collapse-1']/ul/li[4]/a")
	public WebElement logout_Link;

	@FindBy(linkText = "Help")
	public WebElement help_Link;

	@FindBy(linkText = "Advanced Search")
	public WebElement advanceSearch_Link;
	
	@FindBy(name="cat0")
	public WebElement select_category;

	@FindBy(name="cat1")
	public WebElement select_subcategory;
	
	@FindBy(name="submitit")
	public WebElement sbmt_btn;
	
	@FindBy(name="title")
	public WebElement txtTitle;
	
	@FindBy(name="subtitle")
	public WebElement txtSubtitle;
	
	@FindBy(xpath="//body")
	public WebElement txtitem_desc;
		
	@FindBy(linkText="Upload Pictures")
	public WebElement upload_pict_link;
	
	@FindBy(linkText="Add Files")
	public WebElement Add_Files_lnkbtn;
	
	@FindBy(linkText="Start Upload")
	public WebElement Start_Upload_lnkbtn;
	
	@FindBy(linkText="Close")
	public WebElement close_lnk;
	
	@FindBy(name="atype")
	public WebElement select_atype;
	
	@FindBy(name="minimum_bid")
	public WebElement txtminimum_bid;
	
	@FindBy(id="with_reserve_yes")
	public WebElement rdio_reserve_yes;
	
	@FindBy(name="reserve_price")
	public WebElement txtreserve_price;
	
	@FindBy(css="input[type='checkbox'][name='payment[]']")
	public WebElement chk_bank_trsfr;
	
	@FindBy(css="input[value='Submit Auction']")
	public WebElement sbmt_auc_btn;
	
	// check if the login is already happened or not
	public boolean checkIfLogin() {
		try {
			if (logout_Link.getText().equalsIgnoreCase("Login"))
				return false;
			else
				return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void sell_an_itm(String ttle,String subtitle,String desc,String min_bid) throws InterruptedException {
		Select ddl_cat=new Select(select_category);
		ddl_cat.selectByIndex(2);
		Select ddl_subcat=new Select(select_subcategory);
		ddl_subcat.selectByIndex(2);
		sbmt_btn.click();
		txtTitle.sendKeys(ttle);
		txtSubtitle.sendKeys(subtitle);
		driver.switchTo().frame(0);
		txtitem_desc.sendKeys(desc);
		driver.switchTo().defaultContent();
		upload_pict_link.click();
		String parentHandle = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		for (String wndw : windowHandles) {
			driver.switchTo().window(wndw);
		}
		Add_Files_lnkbtn.click();
		Thread.sleep(3000);
		uploadFile("C:\\Users\\Public\\Pictures\\Sample Pictures\\Hydrangeas.jpg");
		Thread.sleep(3000);
		Start_Upload_lnkbtn.click();
		Thread.sleep(2000);
		close_lnk.click();
		driver.switchTo().window(parentHandle);
		Select ddl_atype=new Select(select_atype);
		ddl_atype.selectByIndex(1);
		txtminimum_bid.sendKeys(min_bid);
		chk_bank_trsfr.click();
		sbmt_auc_btn.click();
	}
	
	public static void setClipboardData(String string) {
		//StringSelection is a class that can be used for copy and paste operations.
	StringSelection stringSelection = new StringSelection(string);
	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}
	public static void uploadFile(String fileLocation) {
			      try {
			             //Setting clipboard with file location
			            setClipboardData(fileLocation);
			           //native key strokes for CTRL, V and ENTER keys
			            Robot robot = new Robot();
			            //Hinting (ctrl + v) to past the path in the text box
			            robot.keyPress(KeyEvent.VK_CONTROL);
			            robot.keyPress(KeyEvent.VK_V);
			            //Hinting (ctrl + v)
			            robot.keyRelease(KeyEvent.VK_V);
			            robot.keyRelease(KeyEvent.VK_CONTROL);
			            //hitting enter key
			            robot.keyPress(KeyEvent.VK_ENTER);
			            robot.keyRelease(KeyEvent.VK_ENTER);
			        } 
			      catch (Exception exp) 
			      {
			    	  exp.printStackTrace();
			        }
			    }
			                
	public LoginPage clickOnLogin(){
		try {
			login_Link.click();			
		}
		catch (Exception e){
			System.err.println(e);
		}
		return PageFactory.initElements(driver, LoginPage.class);
	}
	
	public HelpPage clickOnHelp(){
		try {
			help_Link.click();			
		}
		catch (Exception e){
			System.err.println(e);
		}
		return PageFactory.initElements(driver, HelpPage.class);
	}
}
