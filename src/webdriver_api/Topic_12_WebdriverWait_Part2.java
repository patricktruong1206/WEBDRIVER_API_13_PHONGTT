package webdriver_api;

import java.util.List;
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

public class Topic_12_WebdriverWait_Part2 {
	WebDriver driver;
	WebDriverWait explicitWait;
	By btnLoginBy= By.xpath("//input[@name='btnLogin']");
	By FileUploadBy= By.xpath("//a[text()='File Upload']");
	By emailTextBoxBy= By.xpath("//input[@name='emailid']");
	
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		
	}
/*
	@Test
	public void TC_01_DemoC_findElement() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");	
		//case 1 : found no element
		//Every 0.5s retry to find element if not found
		// Time out and not found element yet throw exception: No such Element Exception
		//driver.findElement(By.xpath("//div[@class='alert alert-danger' and not (@id='create_account_error')]"));
		
		//Case 2 : found 1 element
		// Dont have to wait timeout
		//Interact with that element and proceed to next step
		driver.findElement(By.xpath("//button[@id='SubmitLogin']")).click();
		
		
		//Case 3: found more than 1 element
		//Always interact with 1st found element
		String errorMesss= driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();
		System.out.println(errorMesss); 
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Automation Testing");	
	}
	@Test
	public void TC_01_DemoD_findElements() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");	
		//case 1 : found no element
		//retry to find element every 0.5s
		// Time out ,return empty list, throw exception: No such Element Exception
		//List <WebElement>errorMess = driver.findElements(By.xpath("//div[@class='alert alert-danger' and not (@id='create_account_error')]"));
		//Assert.assertTrue(errorMess.isEmpty());
		//int errorMessNumber= errorMess.size();
		//System.out.println(errorMessNumber);
		
		//Case 2 : found 1 element
		// Dont have to wait timeout
		//Interact with that element and proceed to next step
		List<WebElement>submitBtn= driver.findElements(By.xpath("//button[@id='SubmitLogin']"));
		int submitButton= submitBtn.size();
		System.out.print(submitButton);
		submitBtn.get(0).click();
		
		
		//Case 3: found more than 1 element
		//Always interact with 1st found element
		List<WebElement>inputText= driver.findElements(By.xpath("//input[@type='text']"));
		int inputNumber= inputText.size();
		System.out.print(inputNumber);
		for(WebElement input: inputText) {
			input.sendKeys("Automation Testing");
		}
	}
*/
	
	
	@Test
	public void TC_02_ImplicitWait() {
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed());
	
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}