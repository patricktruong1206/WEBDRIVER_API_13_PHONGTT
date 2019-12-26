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
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_04_DataProvider {
	WebDriver driver;
	By emailTextbox= By.xpath("//input[@id='email']");
	By passTextbox= By.xpath("//input[@id='pass']");
	By loginButton= By.xpath("//button[@id='send2']");
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		
	}
	@Test(dataProvider="user_pass")
	public void TC_01_Login(String username, String password) throws InterruptedException{
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		System.out.println("Run test case 01");
		driver.findElement(emailTextbox).sendKeys(username);
		driver.findElement(passTextbox).sendKeys(password);
		driver.findElement(loginButton).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).isDisplayed());
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}
	
	@DataProvider (name="user_pass")
	public Object[][] UserAndPassData(){
		return new Object[][] {
			{"phong12@gmail.com","11111111"},
			{"phong13@gmail.com","11111111"},
			{"phong14@gmail.com","11111111"}
		};
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}
	
	
  