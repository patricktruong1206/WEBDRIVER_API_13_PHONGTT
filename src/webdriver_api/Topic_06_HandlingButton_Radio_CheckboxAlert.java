package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

public class Topic_06_HandlingButton_Radio_CheckboxAlert {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	
	//TC 1 
	By myAccountLink = By.xpath("//div[@class='footer-container']//a[@title='My Account']");
	By createAccountLink= By.xpath("//a[@title='Create an Account' and @class='button']");
	
	//TC2
	By checkBox= By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
	
	//TC3
	By radioButton = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
	
	//TC4 
	By resultAlert= By.xpath("//p[@id='result']");
	

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
/*
	@Test
	public void TC_01_Button() {
		//Step 1: Access page 
		driver.get("http://live.guru99.com/");
		
		//Step 2: Click on link "My Account" using  Java script Executor code code
		
		clickElementjs(myAccountLink);
		
		//Step 3: Verify http://live.guru99.com/index.php/customer/account/login/
		
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/login/");
		
		//Step 4: Click on button "Create an account" Java script Executor code
		
		clickElementjs(createAccountLink);
		
		//Step 5: check http://live.guru99.com/index.php/customer/account/create/
		
		Assert.assertEquals( driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/create/");
	}

	@Test
	public void TC_02_CheckBox() {
		
		//Step 1 Access page
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		
		//Step 2 Click on check box Dual-Zone air conditioning
		
		clickElementjs(checkBox);
		
		//Step 3 Verify check box is selected
		
		Assert.assertTrue(isElemnentSelected(checkBox));
		
		//Step 4  not selected and verified
		
		clickElementjs(checkBox);
		Assert.assertFalse(isElemnentSelected(checkBox));		
	}

	@Test
	public void TC_03_RadioButton() {
		//Step 1 Access page
		
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		
		//Step 2 click on Radio button 
		
		driver.findElement(radioButton).click();
		
		//Step 3 Verify that button is checked or not if no reselect
		
		
		Boolean checkingResult = isElemnentSelected(radioButton);
		
		if(checkingResult == false) {
			clickElementjs(radioButton);
			Assert.assertTrue(isElemnentSelected(radioButton));
		}
		else
		{
			Assert.assertTrue(isElemnentSelected(radioButton));
		}	
	}
	*/
	@Test
	public void TC_04_AcceptAlert() {
		//Step 1  Access page  https://automationfc.github.io/basic-form/index.html
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Step 2 click button "Click for JS Alert
		
		driver.findElement( By.xpath("//button[text()='Click for JS Alert']")).click();
		
		//Step 3 Verify messsage "I am a JS Alert"
		
		Alert alert= driver.switchTo().alert();
		String textOnAlert = alert.getText();
		Assert.assertEquals(textOnAlert, "I am a JS Alert");
		
		//Step 4 Accept Alert and verify message "you clicked an alert successfully"
		alert.accept();
		String textAfterAccept = driver.findElement(resultAlert).getText();
		Assert.assertEquals(textAfterAccept, "You clicked an alert successfully");
	}
	
	
	@Test
	public void TC_05_ConfirmAlert() {
		//Step 1  Access page  https://automationfc.github.io/basic-form/index.html
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Step 2 Click button "I am a JS Confirm"
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		
		//Step 3 Verify message: "I am a JS confirm"
		Alert alert= driver.switchTo().alert();
		String textOnAlert = alert.getText();
		Assert.assertEquals(textOnAlert, "I am a JS Confirm");
		
		//Step 4 Cancel alert  & verify message "You clicked: Cancel"
		alert.dismiss();
		String textAfterCancel = driver.findElement(resultAlert).getText();
		Assert.assertEquals(textAfterCancel, "You clicked: Cancel");
	}
	
	@Test
	public void TC_06_PromptAlert() {
		//Step 1  Access page https://automationfc.github.io/basic-form/index.html
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Step 2 Click button "Click for JS prompt"
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		
		//Step 3 Verify message "I am  a JS prompt"
		Alert alert= driver.switchTo().alert();
		String textOnAlert = alert.getText();
		Assert.assertEquals(textOnAlert, "I am a JS prompt");
		
		//Step 4 Input random text & verify message " You entered: inputext"
		alert.sendKeys("Merry Xmas");
		alert.accept();
		String textAfterSubmit= driver.findElement(resultAlert).getText();
		Assert.assertEquals(textAfterSubmit, "You entered: Merry Xmas");
	}
	
	@Test
	public void TC_07_AuthenticationAlert () {
		String username="admin";
		String password="admin";
		String url ="http://the-internet.herokuapp.com/basic_auth";
		//Step 1  Access page http://the-internet.herokuapp.com/basic_auth
		//Step 2 Handle Authentication alert vs user/pass:admin/admin

		driver.get(byPassAuthenticationAlert(url,username,password));
		driver.get(url);
		
		//Step 3 Verify message after login successful: "Congratulations! You must have the proper credentials"
		
	
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='content']//p")).getText(), "Congratulations! You must have the proper credentials.");
		
	}
	
	public void clickElementjs(By by)
	{
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
	}
	
	public boolean isElemnentSelected(By by) {
		if(driver.findElement(by).isSelected())
		{
			System.out.println("Element with locator ["+ by +"] is seleted");
			return true;	
		}
		else {
			System.out.println("Element with locator [" + by +"] is not selected");
			return false;
		}
	}
	
	public String byPassAuthenticationAlert(String url, String username, String password) {
		System.out.println("Old Url= "+ url);
		String[] splitUrl = url.split("//");
		url = splitUrl[0] +"//" + username+":"+password+"@"+splitUrl[1];
		System.out.println("Custom Url= "+ url);
		return url;
	}
		
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}