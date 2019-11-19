package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Part02_WebElement {
	WebDriver driver;
	
	By email =By.xpath("//input[@id='mail']");
	
	By passWord=By.xpath("//input[@id='password']");
	
	By ageUnder18 =By.xpath("//input[@id='under_18']");
	
	By education = By.xpath("//textarea[@id='edu']");
	
	By jobRole1 =By.xpath("//select[@id='job1']");
	
	By jobRole2 = By.xpath("//select[@id='job2']");
	
	By interest = By.xpath("//input[@id='development']");
	
	By interestDisabled =By.xpath("//input[@id='check-disbaled']");
	
	By biography = By.xpath("//textarea[@id='bio']");
	
	By slider01 = By.xpath("//input[@id='slider-1']");
	
	By slider02 = By.xpath("//input[@id='slider-2']");
	
	By ageDisabled = By.xpath("//input[@id='radio-disabled']");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();	
	}

	@Test
	public void TC_01_isDisplayed() throws Exception{
		
		//Step 1: Access page 
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
	/*	if(driver.findElement(email).isDisplayed())
		{
			driver.findElement(email).sendKeys("Automation Testing");
		}
		
		if(driver.findElement(education).isDisplayed())
		{
			driver.findElement(education).sendKeys("Automation Testing");
		}
		
		if(driver.findElement(ageUnder18).isDisplayed())
		{
			driver.findElement(ageUnder18).click();
		}
		*/
		
		//Step 2&3: Check element is displayed and input data
		if(isElementDisplayed(email))
		{
			sendkeyToElement(email,"Automation Testing");
		}
		
		Assert.assertTrue(isElementDisplayed(email));
		
		if(isElementDisplayed(education))
		{
			sendkeyToElement(education,"Automation Testing");
		}
		
		Assert.assertTrue(isElementDisplayed(education));
		
		if(isElementDisplayed(ageUnder18))
		{
			clickElement(ageUnder18);
		}

		Assert.assertTrue(isElementDisplayed(ageUnder18));
	}

	@Test
	public void TC_02_isEnabled() {
		//step 1 Access page
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//step 2 check element is enabled

		Assert.assertTrue(isElementEnabled(email));
		
		Assert.assertTrue(isElementEnabled(ageUnder18));
		
		Assert.assertTrue(isElementEnabled(education));
		
		Assert.assertTrue(isElementEnabled(jobRole1));
		
		Assert.assertTrue(isElementEnabled(interest));
		
		Assert.assertTrue(isElementEnabled(slider01));
		
		//Step 3 check element is disabled
		
		Assert.assertFalse(isElementEnabled(passWord));
		
		Assert.assertFalse(isElementEnabled(ageDisabled));
		
		Assert.assertFalse(isElementEnabled(biography));
		
		Assert.assertFalse(isElementEnabled(jobRole2));
		
		Assert.assertFalse(isElementEnabled(interestDisabled));
		
		Assert.assertFalse(isElementEnabled(slider02));
	}

	@Test
	public void TC_03_isSelected() {
		
		//Step 1 Access page
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Step 2 select these fields
		
		clickElement(ageUnder18);
		
		clickElement(interest);
		
		
		//Step 3 Check Element is selected 
		
		Assert.assertTrue(isElementSelected(ageUnder18));
		
		Assert.assertTrue(isElementSelected(interest));
		
		// Step 4 Un-select interest field
		
		clickElement(interest);
		
		//Step 5: Recheck Element is selected or not
		
		Assert.assertTrue(isElementSelected(ageUnder18));
		
		Assert.assertFalse(isElementSelected(interest));
			
	}
	
	public void sendkeyToElement (By by,String value)
	{
		driver.findElement(by).sendKeys(value);
	}
	
	public void clickElement (By by)
	{
		driver.findElement(by).click();
	}
	
	public boolean isElementDisplayed(By by){
		if(driver.findElement(by).isDisplayed())
		{
			System.out.println("Element with locator["+ by +"] is displayed");
			return true;
		}
		else {
			System.out.println("Element with locator["+ by +"] is not displayed");
			return false;
		}
	}
	
	public boolean isElementEnabled(By by){
		if(driver.findElement(by).isEnabled())
		{
			System.out.println("Element with locator["+ by +"] is enabled" );
			return true;
		}
		else {
			System.out.println("Element with locator["+ by +"] is disabled" );
			return false;
		}
	}
	
	public boolean isElementSelected(By by) {
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
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}