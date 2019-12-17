package webdriver_api;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_WebdriverWait_Part3_ExplicitWait {
	WebDriver driver;
	WebDriverWait explicitWait;
	By today = By.xpath("//a[text()='16']");
	By selectedDay= By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']");
	By todaySelected= By.xpath("//a[text()='16']/parent::td[@class='rcSelected']");
	By loadingIcon= By.xpath("//div[@class='raDiv']");
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		
	}
/*
	@Test
	public void TC_03_ExplicitWait_Invis_3s () throws Exception{
		explicitWait = new WebDriverWait(driver,3);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated((By.xpath("//div[@id='loading']"))));
			
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed());
	}
	*/
	@Test
	public void TC_03_ExplicitWait_Invis_5s() {
		explicitWait = new WebDriverWait(driver,5);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated((By.xpath("//div[@id='loading']"))));
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed());
		

	}
	
	/*
	@Test
	public void TC_04_ExplicitWait_vis_3s  () throws Exception{
		explicitWait = new WebDriverWait(driver,3);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//div[@id='finish']/h4[text()='Hello World!']"))));
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed());
	}
	*/
	@Test
	public void TC_04_ExplicitWait_vis_5s() {
		explicitWait = new WebDriverWait(driver,5);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//div[@id='finish']/h4[text()='Hello World!']"))));
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed());
	}
	
	@Test
	public void TC_05_ExplicitWait() {
		explicitWait = new WebDriverWait(driver,30);
		driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated((today)));
		
		Assert.assertEquals(driver.findElement(selectedDay).getText(),"No Selected Dates to display.");
		
		driver.findElement(today).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated((todaySelected)));
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated((loadingIcon)));
		
		Assert.assertEquals(driver.findElement(selectedDay).getText(),"Monday, December 16, 2019");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}