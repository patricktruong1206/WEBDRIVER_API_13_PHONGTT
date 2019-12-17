package webdriver_api;

import java.awt.datatransfer.StringSelection;
import java.awt.Robot;
import java.awt.Toolkit;
import java.util.List;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_UploadFile {
	WebDriver driver;
	String projectPath= System.getProperty("user.dir");
	String firstImage="1.jpg";
	String secondImage= "2.jpg";
	String thirdImage="3.jpg";
	String firstFilePath= projectPath + "\\fileUpload\\" + firstImage;
	String secondFilePath= projectPath + "\\fileUpload\\" + secondImage;
	String thirdFilePath= projectPath+ "\\fileUpload\\" + thirdImage;
	String chromeAutoITpath = projectPath + "\\fileUpload\\chrome.exe";
	String fireFoxAutoITpath = projectPath + "\\fileUpload\\firefox.exe";
	String ieAutoITpath = projectPath + "\\fileUpload\\ie.exe";
	
	@BeforeClass
	public void beforeClass() {
		//Neeed to set environment before use chrome
		//System.setProperty("webdriver.chrome.driver",".\\libraries\\chromedriver.exe");
		//driver= new ChromeDriver();
		
		//firefox 47.0.2
		//driver = new FirefoxDriver();
		
		
		//firefox (>=48) + selenium 3xx + set environment for geckodriver
		System.setProperty("webdriver.gecko.driver", ".\\libraries\\geckodriver 0.25.exe");
		driver= new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_UploadFileBySendkeys() throws Exception{
		 
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		WebElement fileInput= driver.findElement(By.xpath("//input[@type='file']"));
		fileInput.sendKeys(firstFilePath +"\n"+ secondFilePath +"\n"+ thirdFilePath);
		
		//verify 3 files have uloaded success
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'"+ firstImage +"')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'"+ secondImage +"')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'"+ thirdImage +"')]")).isDisplayed());
	
		//click on start button
		
		List <WebElement> startList= driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']")); 
		for (WebElement start:startList) {
			start.click();
			Thread.sleep(1500);
		}
		// Verify upload success
		Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'"+ firstImage +"')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'"+ secondImage +"')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'"+ thirdImage +"')]")).isDisplayed());
	}
	

	@Test
	public void TC_02_UploadFileByAutoIT() throws Exception {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		//handle select file to upload 
		WebElement uploadButton = driver.findElement(By.cssSelector(".fileinput-button"));
		uploadButton.click();
			
		//Runtime.getRuntime().exec(new String [] {chromeAutoITpath,firstFilePath});
		
		Runtime.getRuntime().exec(new String [] {fireFoxAutoITpath,firstFilePath});
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'"+ firstImage +"')]")).isDisplayed());
		
		//driver.findElement(By.xpath("//span[contains(text(),'Start upload')]")).click();
		//Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'"+ firstImage +"')]")).isDisplayed());
	
		
	}

	@Test
	public void TC_03_UploadFileByRobotClass() throws Exception{
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		WebElement uploadButton = driver.findElement(By.cssSelector(".fileinput-button"));
		uploadButton.click();
		
		StringSelection select = new StringSelection(firstFilePath);
		
		//copy to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
		
		Robot robot= new Robot();
		
		
		//press enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		//press crtl + v
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		
		//release crtl +v
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		
		
		//press enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);	
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'"+ firstImage +"')]")).isDisplayed());	
	}
	
	//page is not valid anny more
	@Test
	public void TC_04_UploadFile() throws Exception{
		driver.get("https://encodable.com/uploaddemo/");
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}