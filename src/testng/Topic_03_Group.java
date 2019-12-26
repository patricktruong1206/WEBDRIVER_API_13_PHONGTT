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

public class Topic_03_Group {
	WebDriver driver;
	By emailTextbox= By.xpath("//input[@id='email']");
	By passTextbox= By.xpath("//input[@id='pass']");
	
	
	@Test(groups="alpha")
	public void TC_01_Login(){
		System.out.println("Run test case 01");
	
	}
	
	
	@Test(groups="alpha")
	public void TC_02_CreateClient(){
		System.out.println("Run test case 02");
		
	}
		
		
	@Test(groups="beta")
	public void TC_03_EditClient(){
		System.out.println("Run test case 03");
		
	}
		
		
	@Test(groups="beta")
	public void TC_04_DeletClient(){
	System.out.println("Run test case 04");
		
	}
		
	@Test(groups="delta")
	public void TC_05_LogOut(){
	System.out.println("Run test case 05");
		
	}	
}

	
	
  