package webdriver_api;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_UserInteractions {
	WebDriver driver;
	Actions action;
	String workingDirectory = System.getProperty("user.dir");
	String jsFilePath= workingDirectory + "\\dragAndDrop\\drag_and_drop_helper.js";
	String jqueryFilePath= workingDirectory + "\\dragAndDrop\\jquery_load_helper.js";
			

	@BeforeClass
	public void beforeClass() {
		FirefoxProfile profile= new FirefoxProfile();
		profile.setPreference("dom.notifications.enabled", false);
		driver = new FirefoxDriver(profile);
		action= new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_moveMouseToElement() {
		//Step 1 Access page http://www.myntra.com/
		driver.get("http://www.myntra.com/");
		
		//Step 2 Hover on Discover
		
		WebElement discoverNav= driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Discover']"));
		action.moveToElement(discoverNav).perform();
		
		//Step 3 Select American Eagele or anypage
		driver.findElement(By.xpath("//a[text()='American Eagle']")).click();
		
		//Step 4 Verify 
		
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='American Eagle']")).getText(),"American Eagle");
		Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='American Eagle']")).getText(),"American Eagle");
	
	}

	@Test
	public void TC_02_clickAndHoldElement() {
		//Step 1 Access page http://jqueryui.com/resources/demos/selectable/display-grid.html
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		//Step 2 click and hold 1->4
		List<WebElement> listitems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		System.out.println("All item = " +listitems.size());
		action.clickAndHold(listitems.get(0)).moveToElement(listitems.get(3)).release().perform();
	
		//Step 3 After select => verify 4 element is selected with xpath //li[@class='ui-state-default ui-selectee ui-selected']
		
		List<WebElement> listitemselected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		System.out.println("All selected item = " +listitemselected.size());
		Assert.assertEquals(listitemselected.size(), 4);
	}

	@Test
	public void TC_03_clickAndSelectElement() {
		//Step 1 Access page
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		//Step 2 click and select random item 1-3-6-11
		List<WebElement> listitems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		System.out.println("All item = " +listitems.size());
		
		action.keyDown(Keys.CONTROL).perform();
		action.click(listitems.get(0))
		.click(listitems.get(2))
		.click(listitems.get(5))
		.click(listitems.get(10)).perform();
		action.keyUp(Keys.CONTROL).perform();
		
		//Step 3 After select => verify 4 element is selected with xpath //li[@class='ui-state-default-ui-selectee ui-selected]
		List<WebElement> listitemselected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		System.out.println("All selected item = " +listitemselected.size());
		Assert.assertEquals(listitemselected.size(), 4);
	}
	
	@Test
	public void TC_04_doubleClick() {
		//Step 1: Access page
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//Step 2 double click at button: Double click me
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		//Step 3 Verify text Hello Automation Guys!
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");
	}
	
	@Test
	public void TC_05_rightClickToElement() throws InterruptedException {
		//Step 1:  Access page
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		//Step 2: Right click on Element:  right click me
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']")));
		//Step 3: Hover on Element: Quit
		WebElement quitNotHover= driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') and not (contains(@class,'context-menu-visible')) and not (contains(@class,'context-menu-hover'))]"));
		action.moveToElement(quitNotHover).perform();
		//Step 4:  Verify Element Quit *(visible + hover) with xpath //li[contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]/span[text()='Quit']
		WebElement quitHovered= driver.findElement(By.xpath("//li[contains(@class,'context-menu-visible') and (contains(@class,'context-menu-hover'))]/span[text()='Quit']"));
		Assert.assertEquals(quitHovered.getText(), "Quit");
		//Step 5: Click on Quit
		quitHovered.click();
		Alert alert= driver.switchTo().alert();
		alert.accept();
	}
	
	@Test
	public void TC_06_dragAndDropElement() {
		//Step 1 Access page http://demos.telerik.com/kendo-ui/dragdrop/angular
		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
		
		//Step 2 Pull small circle into big circle
		WebElement sourceCircle= driver.findElement(By.xpath("//div[@id='draggable']"));
		
		WebElement targetCircle= driver.findElement(By.xpath("//div[@id='droptarget']"));
		
		action.dragAndDrop(sourceCircle, targetCircle).perform();
		
		//Step 3 Verify message: You did great!
		Assert.assertEquals(targetCircle.getText(),"You did great!");
	} 
	
	@Test
	public void TC_07_dragAndDropHTML5() throws InterruptedException, IOException{
		//Step 1 Access page http://the-internet.herokuapp.com/drag_and_drop
		driver.get("http://the-internet.herokuapp.com/drag_and_drop");
		//Step 2  Use javasript/Jquery to drop A to B and reverse
		
		String sourceCss="#colum-a";
		String targetCss="#colum-b";
		
		String java_script= readFile(jsFilePath);
		JavascriptExecutor je= (JavascriptExecutor) driver;
		
		//A to B
		java_script = java_script + "$(\""+ sourceCss + "\").simulateDragDrop({dropTarget: \"" + targetCss +"\"});";
		je.executeScript(java_script);
		
		
		//B to A
		je.executeScript(java_script);
	}
	
	public String readFile(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}