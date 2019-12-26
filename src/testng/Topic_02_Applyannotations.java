package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_02_Applyannotations {
	WebDriver driver;
	By emailTextbox= By.xpath("//input[@id='email']");
	By passTextbox= By.xpath("//input[@id='pass']");
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
	}

	@BeforeMethod
	public void beforeMethod() {
		//Auto refresh page for each test casse
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}
	
	
	
	@Test
	public void TC_01_LoginWithEmailInvalid(){
		System.out.println("Run test case 01");
		driver.findElement(emailTextbox).sendKeys("abc@mail.com");
		driver.findElement(passTextbox).sendKeys("admin@123");
	}
	
	@Test
	public void TC_02_LoginWithEmptyPassAndMail() {
		System.out.println("Run test case 02");
		driver.findElement(emailTextbox).sendKeys("null");
		driver.findElement(passTextbox).sendKeys("null");
	}
	
	@Test
	public void TC_03_LoginWithPassInvalid() {
		System.out.println("Run test case 03");
		driver.findElement(emailTextbox).sendKeys("phongtruong1206@gmail.com");
		driver.findElement(passTextbox).sendKeys("null");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}
	
	
  