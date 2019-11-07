package webdriver_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath_Part1and2 {
	WebDriver driver;
	String validEmail = "patricktruong" +randomNumber()+"@gmail.com";
	String validPassword = "123123";		
	String firstName= "Patrick";
	String lastName= "Truong";
    String middleName="Ace";
    
 
    
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_LoginEmpty() {
		driver.get("http://live.guru99.com/");
		
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		
		driver.findElement(By.xpath("//*[@id='send2']")).click();;
		
		String reqTextEmail = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals(reqTextEmail,"This is a required field.");
		
		String reqTextPass = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(reqTextPass,"This is a required field.");
	}

	@Test
	public void TC_02_LoginWithEmailInvalid() {
		driver.get("http://live.guru99.com/");
		
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("12341234@1234.123123");
		
		driver.findElement(By.xpath("//*[@id='send2']")).click();;
		
		String valTextEmail = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals(valTextEmail,"Please enter a valid email address. For example johndoe@domain.com.");
		
	
	}

	@Test
	public void TC_03_LoginWithPasswordLessThan6Characters() {
		driver.get("http://live.guru99.com/");
		
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(validEmail);
		
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		
		driver.findElement(By.xpath("//*[@id='send2']")).click();;
		
		String valTextPass = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(valTextPass,"Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void TC_04_LoginWithInvalidPassword() {
		driver.get("http://live.guru99.com/");
		
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(validEmail);
		
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123123");
		
		driver.findElement(By.xpath("//*[@id='send2']")).click();;
		
		String invalidText = driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText();
		Assert.assertEquals(invalidText,"Invalid login or password.");
		
	}
	
	@Test
	public void TC_05_CreateNewAccount() {
		
		driver.findElement(By.xpath("//a[@class='button' and @title='Create an Account']")).click();
		
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);

		//driver.findElement(By.xpath("//input[@id='middlename']")).sendKeys(middleName);
		
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);
		
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(validEmail);;
		
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(validPassword);
		
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(validPassword);
		
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//a[@class='skip-link skip-account']")).click();
		
		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='Log Out']")).click();
	}
	
	@Test
	public void TC_06_LoginWithValidPasswordAndEmail() {
	
		
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(validEmail);
		
		
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(validPassword);
		
		driver.findElement(By.xpath("//*[@id='send2']")).click();
		
		//option 1: use assert true to check (condition) -> locator is displayed or not      
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']//h1")).isDisplayed());
		
		//String titlePage = driver.findElement(By.xpath("//div[@class='page-title']//h1")).getText();  //getText() only work with static text, Do not use for dynamic text
		//Assert.assertEquals(titlePage,"MY DASHBOARD");   // Option 2: compare equal value 1 and value 2: 
	 
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Hello, "+firstName+" "+lastName+"!']")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']//p[contains(text(),'"+firstName+" "+lastName+"')]")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']//p[contains(.,'"+validEmail+"')]")).isDisplayed());
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int randomNumber() {
		Random rand= new Random();
		int n =rand.nextInt(50);
		return n;
	}
}