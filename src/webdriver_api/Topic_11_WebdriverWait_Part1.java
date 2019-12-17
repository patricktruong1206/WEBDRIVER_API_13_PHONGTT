package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_WebdriverWait_Part1 {
	WebDriver driver;
	WebDriverWait explicitWait;
	By btnLoginBy= By.xpath("//input[@name='btnLogin']");
	By FileUploadBy= By.xpath("//a[text()='File Upload']");
	By emailTextBoxBy= By.xpath("//input[@name='emailid']");
	
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver,10);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Demo_Visible() {
		//Visible
		driver.get("http://demo.guru99.com/v4/");
		
		
		//pass - Visbile in DOM and UI
		WebElement btnLogin = driver.findElement(btnLoginBy);
		boolean btnLoginStatus= btnLogin.isDisplayed();
		System.out.println("Status of button login is: "+btnLoginStatus);
		
		
		//Pass - Not visible in UI but visible in DOm 
		WebElement fileUploadLinks = driver.findElement(FileUploadBy);
		boolean fileUploadStatus = fileUploadLinks.isDisplayed();
		System.out.println("Status of file upload is: "+fileUploadStatus);
		
		
		/*  Fail - Not visbile both UI and DOm
		WebElement emailTextBox = driver.findElement(emailTextBoxBy);
		boolean emailTextBoxStatus = emailTextBox.isDisplayed();
		System.out.println("Status of email textbox is :" +emailTextBoxStatus);
		*/
	}
	@Test
	public void TC_01_DemoB_Invisible() {
		driver.get("http://demo.guru99.com/v4/");	
		//failed - Display on UI and DOM
		//explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(btnLoginBy));
		//true - Not displayed on UI but avaiable on DOOM
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(FileUploadBy));
		
		//pass - Not in Dom & UI
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(emailTextBoxBy));
		
	}
	
	@Test
	public void TC_01_DemoC_Presence() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");	
		
		//pass - condition 1 :  Element is display on UI and DOm
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='SubmitLogin']")));
		
		//pass - condition 2: Element is not displayed on UI but presence in DOm
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='alert alert-danger']")));
		
		//Fail = condition 3: Element is not displayed both UI and DOm
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='alert alert-danger' and not (@id='create_account_error')]")));
		
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}