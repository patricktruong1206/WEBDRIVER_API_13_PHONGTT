package webdriver_api;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_PopUp_Frame_Iframe {
	WebDriver driver;
	By popupBanner= By.xpath("//img[contains(@class,'popupbanner')]");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_HandlingPopUpIFrame() {
		//Step 1 Access page http://www.hdfcbank.com/
		driver.get("http://www.hdfcbank.com/");
		
		//Step 2 Check pop up is displayed and close popup
		
		if(driver.findElement(popupBanner).isDisplayed()) {
			driver.findElement(By.cssSelector(".popupCloseButton")).click();
			Assert.assertFalse(driver.findElement(popupBanner).isDisplayed());
		}
	
		//Step 3 Verify banner have 6 images // 5/12/2019 page have updated to 7 images
		List <WebElement> imagesList = driver.findElements(By.xpath("//div[contains(@class, 'be-ex-hero-carousel' ) and @role='option']/descendant::img"));
		Assert.assertEquals(imagesList.size(), 7);
		
		
		//Step 4 Verify flipper banner  is displayed  and have 8 images  (Invalid step)
		
		//Step 5 open page https://netbanking.hdfcbank.com/netbanking
		
		driver.get("https://netbanking.hdfcbank.com/netbanking");
		//Switch  to  frame contain NetBankingLogin
		
		driver.switchTo().frame("login_page");
		
		//Login with user name: selenium_online
		
		driver.findElement(By.xpath("//input[@class='input_password']")).sendKeys("selenium_online");
		
		//Click "Continue" button
		driver.findElement(By.xpath("//table[@class='lForm']//img[@alt='continue']")).click();
		
		//Verify "IPIN (Password)" is displayed
		
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='fldPassword']")).isDisplayed());
		
		//Step 6 Check Privacy Policy link  below footer is displayed 
		
		driver.switchTo().parentFrame();
		
		driver.switchTo().frame("footer");
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Privacy Policy']")).isDisplayed());
			
	}

	@Test
	public void TC_02_HandlingWindowstab() throws InterruptedException  {
		
		//Access page
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		
		
		String parentWindowId= driver.getWindowHandle();
		
		System.out.println("ID window:" + parentWindowId);
		
		//Click on "Google" link 
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id: allWindowIDs) {
			System.out.println("ID" +id);
			
			if(!id.equals(parentWindowId)) {
				driver.switchTo().window(id);
			}
		}
		 
		switchToChildWindowById(parentWindowId);
		Assert.assertEquals(driver.getTitle(), "Google");
		
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='q']")).isDisplayed());
		
		driver.switchTo().window(parentWindowId);
		
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		
		switchToChildWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");
		
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
		
		driver.switchTo().window(parentWindowId);
		
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		
		switchToChildWindowByTitle("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		
		Assert.assertEquals(driver.getTitle(), "Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		
		Assert.assertTrue(closeAllWindowsWithoutParent(parentWindowId));
	}

	@Test
	public void TC_03_HandlingWindows_Tab_Frame() {
		driver.get("http://www.hdfcbank.com/");

		if(driver.findElement(popupBanner).isDisplayed()) {
			driver.findElement(By.cssSelector(".popupCloseButton")).click();
			Assert.assertFalse(driver.findElement(popupBanner).isDisplayed());
		}
	
		driver.findElement(By.xpath("//ul[contains(@class,'lp-navbar-advanced-root normal-main-header')]//a[text()='Agri']")).click();
		//This test case is out-dated. To be updated later
			
	}

	@Test
	public void TC_04_HandlingWindows() {
		//Step 1 Access page
		driver.get("http://live.guru99.com/index.php/");
		
		//Step 2 Click on mobile tab
		
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();;
		
		//Step 3 add sony product to  compare (Add to compare)
		
		driver.findElement(By.xpath("//a[@title='Xperia']//following-sibling::div//descendant::a[text()='Add to Compare']")).click();
		
			//Verify text: "The product Sony Xperia  has been added to comparison list"
		
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='The product Sony Xperia has been added to comparison list.']")).getText(), "The product Sony Xperia has been added to comparison list.");
		
		
		//Step 4 add samsung product to compare
		
		driver.findElement(By.xpath("//a[@title='Samsung Galaxy']//following-sibling::div//descendant::a[text()='Add to Compare'] ")).click();
		
					//Verify text
		
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")).getText(), "The product Samsung Galaxy has been added to comparison list.");
		//Step 5 Click to compare button
		
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		
		//Step 6 Switch to new window (contains 2 added product for comparison)
		
		
		String parentWindowId= driver.getWindowHandle();
		
		System.out.println("ID window:" + parentWindowId);
		
		
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id: allWindowIDs) {
			System.out.println("ID" +id);
			
			if(!id.equals(parentWindowId)) {
				driver.switchTo().window(id);
			}
		}
		 
		switchToChildWindowById(parentWindowId);
		
		//Step 7 Verify title of windows by : product comparison list - magento commerce
		
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		
		//Step 8 Close tab and switch to Parent Window
		
		closeAllWindowsWithoutParent(parentWindowId);
	}

		
	public void  switchToChildWindowByTitle(String title) {
		//get all id
		Set<String> allWindows= driver.getWindowHandles();
		
		//use loop to browse all get id
		for(String runWindows:allWindows){
			driver.switchTo().window(runWindows);
			
			//Get title
			String currentWin= driver.getTitle();
			
			// if title equal to expected title then exit loop
			if(currentWin.equals(title)) {
				break;
			}
		}
	}
	
	public boolean closeAllWindowsWithoutParent(String parentWindow) {
		Set<String> allWindows=driver.getWindowHandles();
		for(String runWindows:allWindows) {
			if(!runWindows.equals(parentWindow)) {
			driver.switchTo().window(runWindows);
			driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
		if(driver.getWindowHandles().size()==1)
			return true;
		else
			return false;
	}
	
	public void switchToChildWindowById(String parent) {
		
		//get All id
			Set<String> allWindows= driver.getWindowHandles();
			
			//use loop to browse all id
			for(String runWindow:allWindows) {
				
				//if any id different with parent then switch to that id
				
				if(!runWindow.equals(parent)) {
					driver.switchTo().window(runWindow);
					
					//after switching success then break
					break;
				}
			}
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}