package webdriver_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_WebLocator {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_() {
		//practice get web element by different locator
		driver.get("http://live.demoguru99.com/index.php/customer/account/login");

		//ID 
		
		driver.findElement(By.id("email")).sendKeys("abc@gmail.com");
		
		driver.findElement(By.id("pass")).sendKeys("1234567");
		
		//Name
		
	    driver.findElement(By.name("send")).click();
		
		//Class
	    driver.findElement(By.className("validate-email")).clear();
		driver.findElement(By.className("validate-email")).sendKeys("cde@gmail.com");
		
		
		
		//Tag name (Find and print all links on this website
		//WebElement link =  driver.findElement(By.tagName("a"));  //single
		
		List <WebElement> links = driver.findElements(By.tagName("a"));    //mutiple
		
		int linkNumber= links.size();
		
		System.out.println("Total links = " + linkNumber); //only work with list webelement
		for (WebElement link: links) {
			System.out.println("Value = " +link.getAttribute("href"));
		}

	    //Linktext
		driver.findElement(By.linkText("ORDERS AND RETURNS")).click();
		
		//Partial linktext
		driver.findElement(By.partialLinkText("ORDERS")).click();
		
		//CSS
		//find css by id syntax #id  
		//find css by class  .classname
		//find css  by name tagname[attribute='value']
		
		driver.findElement(By.cssSelector("#oar_order_id")).sendKeys("55555");
		
		driver.findElement(By.cssSelector(".input-text.required-entry")).sendKeys("Roger");
		
		driver.findElement(By.cssSelector("input[name='oar_email']")).sendKeys("acb@gmail.com");;
		
		
		//count how many css a
		System.out.println("tag a use css" + driver.findElements(By.cssSelector("a")).size());
		
		
		
		driver.findElement(By.cssSelector("a[href='http://live.demoguru99.com/index.php/sales/guest/form/']")).click();
		
		
		//Xpath: 	//tagname[@attribute ='value']
		
		driver.findElement(By.xpath("//input[@id='oar_order_id']")).sendKeys("xpath_id");
		
		driver.findElement(By.xpath("//input[@class='input-text required-entry']")).sendKeys("xpath_class");
			
		driver.findElement(By.xpath("//input[@name='oar_email']")).sendKeys("xpath_name@gmail.com");
		
		System.out.println("tag a use css" + driver.findElements(By.xpath("//a")).size());
		
		driver.findElement(By.xpath("//a[text()='Advanced Search']")).click();
		
	}

	@Test
	public void TC_02_() {
		driver.get("");
	}

	@Test
	public void TC_03_() {
		driver.get("");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}