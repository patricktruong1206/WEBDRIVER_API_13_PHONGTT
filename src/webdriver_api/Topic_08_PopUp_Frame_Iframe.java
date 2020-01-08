package webdriver_api;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_PopUp_Frame_Iframe {
	WebDriver driver;
	By popupBanner= By.xpath("//img[contains(@class,'popupbanner')]");
	WebDriverWait waitExplicit;
	WebElement element;
	JavascriptExecutor jsExecutor;
	String projectPath= System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		//System.setProperty("webdriver.chrome.driver", projectPath+ "\\libraries\\chromedriver.exe");
		//ChromeOptions options = new ChromeOptions();
		//options.addArguments("--lang=vi");
		//driver = new ChromeDriver(options);
		
		
		driver = new FirefoxDriver();
		jsExecutor =  (JavascriptExecutor) driver;
		
		waitExplicit = new WebDriverWait(driver,5);
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
	public void TC_01_New_HandlingPopUpIFrame() throws InterruptedException {
		driver.get("https://kyna.vn/");
		
		List<WebElement> fancyPopup= driver.findElements(By.xpath("//div[@class='fancybox-inner']"));
		
		if(fancyPopup.size() >0) {
			Assert.assertTrue(fancyPopup.get(0).isDisplayed());
			driver.findElement(By.cssSelector(".fancybox-close")).click();
		}
		
		Thread.sleep(3000);
		
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='fanpage']//iframe")));
		
		
		boolean facebookIframe = driver.findElement(By.cssSelector("#facebook")).isDisplayed();
		System.out.println("Facebook iframe is displayed" +facebookIframe);
		Assert.assertTrue(facebookIframe);
		
		
		
		String  facebookLikes= driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText();
		System.out.println("Facebook like is displayed" +facebookLikes);
		Assert.assertEquals(facebookLikes,"170K likes");
		
		driver.switchTo().parentFrame();
		
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li//a[@class='button-login']")));
		
		driver.findElement(By.xpath("//li//a[@class='button-login']")).click();
		
		driver.findElement(By.xpath("//input[@id='user-login']")).sendKeys("automationfc.vn@gmail.com");
		
		driver.findElement(By.xpath("//input[@id='user-password']")).sendKeys("automationfc.vn@gmail.com");
		
		Thread.sleep(3000);
		
		waitExplicit.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='btn-submit-login']")));
				
		driver.findElement(By.xpath("//button[@id='btn-submit-login']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Automation FC']")).isDisplayed());
		
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

	/*@Test
	public void TC_03_HandlingWindows_Tab_Frame() {
		driver.get("http://www.hdfcbank.com/");

		if(driver.findElement(popupBanner).isDisplayed()) {
			driver.findElement(By.cssSelector(".popupCloseButton")).click();
			Assert.assertFalse(driver.findElement(popupBanner).isDisplayed());
		}
	
		driver.findElement(By.xpath("//ul[contains(@class,'lp-navbar-advanced-root normal-main-header')]//a[text()='Agri']")).click();
		//This test case is out-dated. To be updated later
			
	}
	*/
	@Test
	public void TC_03_New_HandlingWindows_Tab() throws InterruptedException{
		driver.get("https://kyna.vn/");
		
		String parentWindowId= driver.getWindowHandle();
		
		List<WebElement> fancyPopup= driver.findElements(By.xpath("//div[@class='fancybox-inner']"));
		
		if(fancyPopup.size() >0) {
			Assert.assertTrue(fancyPopup.get(0).isDisplayed());
			driver.findElement(By.cssSelector(".fancybox-close")).click();
		}
		
		Thread.sleep(3000);
		
		
		clickToElementByJS("//div[@class='hotline']//a//img[@alt='facebook']");
		
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id: allWindowIDs) {
			System.out.println("ID" +id);
			
			if(!id.equals(parentWindowId)) {
				driver.switchTo().window(id);
			}
		}
		
		switchToChildWindowByTitle("Kyna.vn - Trang chủ | Facebook");
		
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Trang chủ | Facebook");
		
		
		driver.switchTo().window(parentWindowId);
			
		clickToElementByJS("//div[@class='hotline']//a//img[@alt='youtube']");
		
		switchToChildWindowByTitle("Kyna.vn - YouTube");
		
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - YouTube");
		
		driver.switchTo().window(parentWindowId);
		
		clickToElementByJS("//div[@class='hotline']//a//img[@alt='zalo']");
		switchToChildWindowByTitle("Kyna.vn");
		
		Assert.assertEquals(driver.getTitle(), "Kyna.vn");
		
		driver.switchTo().window(parentWindowId);
		
		//clickToElementByJS("//div[@class='app-col']//a//img[@alt='apple-app-icon']");
		
		//switchToChildWindowByTitle("‎KYNA on the App Store");
		
		//Assert.assertEquals(driver.getTitle(), "‎KYNA on the App Store");
		
		driver.switchTo().window(parentWindowId);
		
		clickToElementByJS("//div[@class='app-col']//a//img[@alt='android-app-icon']");
		
		switchToChildWindowByTitle("KYNA - Học online cùng chuyên gia - Apps on Google Play");
		
		Assert.assertEquals(driver.getTitle(), "KYNA - Học online cùng chuyên gia - Apps on Google Play");
		
		driver.switchTo().window(parentWindowId);
		
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='fanpage']//iframe")));
		
		clickToElementByJS("//a[@title='Kyna.vn']");
		
		switchToChildWindowByTitle("Kyna.vn - Trang chủ | Facebook");
		
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Trang chủ | Facebook");
			
		closeAllWindowsWithoutParent(parentWindowId);
		
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
	
	public void clickToElementByJS(String locator) {
		element= driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].click();", element);
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

}