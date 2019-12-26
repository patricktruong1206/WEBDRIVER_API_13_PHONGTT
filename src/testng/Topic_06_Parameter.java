package testng;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_06_Parameter {
	WebDriver driver;
	By emailTextbox= By.xpath("//input[@id='email']");
	By passTextbox= By.xpath("//input[@id='pass']");
	By loginButton= By.xpath("//button[@id='send2']");
	
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		
		if(browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver.exe");
			driver=new ChromeDriver();
		} else if(browserName.equals("firefox")) {
			driver= new FirefoxDriver();
		} else if(browserName.equals("chrome_headless")) {
			System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1366x768");
			driver = new ChromeDriver(options);
		} else {
			System.out.println("Please choose your browser");
		}
		
	
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
	}
	@Test(invocationCount = 2)
	public void TC_01_Login() throws InterruptedException{
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		
		driver.findElement(emailTextbox).sendKeys("phong22@gmail.com");
		driver.findElement(passTextbox).sendKeys("phong1234");
		driver.findElement(loginButton).click();
	}
	

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}
	
	
  