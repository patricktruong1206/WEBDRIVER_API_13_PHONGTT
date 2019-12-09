package webdriver_api;

import static org.junit.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_JavascriptExecutor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebElement element;
	
	String username, password,customerID, customerName, gender, dateOfBirth, address, city, state, pin, phone, email;
	By usernameTextbox= By.xpath("//input[@name='uid']");
	
	By passwordTextbox= By.xpath("//input[@name='password']");
	
	By loginButton = By.xpath("//input[@name='btnLogin']");
	
	By newCustomerMenu= By.xpath("//a[text()='New Customer']");
	
	By customerNameTextBox= By.xpath("//input[@name='name']");
	
	By genderFemaleRadioButton= By.xpath("//input[@name='rad1' and @value= 'f']");
	
	By dobTextBox= By.xpath("//input[@id='dob']");
	
	By addressTextBox= By.xpath("//textarea[@name='addr']");
	
	By cityTextBox= By.xpath("//input[@name='city']");
	
	By stateTextBox= By.xpath("//input[@name='state']");
	
	By pinTextBox= By.xpath("//input[@name='pinno']");
	
	By mobileNumberTextBox= By.xpath("//input[@name='telephoneno']");
	
	By emailTextBox= By.xpath("//input[@name='emailid']");
	
	By submitButton= By.xpath("//input[@type='submit']");
	
	@BeforeClass
	public void beforeClass() {
		//driver = new FirefoxDriver();
		
		String projectFolder = System.getProperty("user.dir");
		System.out.println("Root folder = " +projectFolder	);
		System.setProperty("webdriver.chrome.driver", projectFolder + "\\libraries\\chromedriver.exe");
		driver = new ChromeDriver();
		
		
		jsExecutor = (JavascriptExecutor) driver; 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		username = "mngr233537";
		password= "vUvapen";
		customerName="Barakkas";
		gender="female";
		dateOfBirth="1994-12-06";
		address = "16A LHP";
		city= "HCM";
		state=  "GoVap";
		pin= "000000";
		phone= "0868460282";
		email="patricktruong" +randomNumber()+"@gmail.com";
		
	}

	
	@Test
	public void TC_01_() {

		//Step 1 Access page http://live.guru99.com/ using JE
		navigateToURL("http://live.guru99.com/");
		
		//Step 2 Use JE to get domain of page
		//Verify domain  = live.guru99.com
		
		Assert.assertEquals(getDomainPage(), "live.demoguru99.com");
		//Step 3 Use JE to get URL of page
		
		//Verify URL = http://live.guru99.com/
		Assert.assertEquals(getURL(), "http://live.demoguru99.com/");
		//Step 4 Open MOBILE page ( using JE) 
		clickToElementByJS("//a[text()='Mobile']");
		
		//Step 5 add product Samsung Galaxy to cart  by JE  (Tips: Using xpath following sibling)
		
		clickToElementByJS("//a[@title='Samsung Galaxy']//following-sibling::div//descendant::button");
		
		//Step 6 Verify message Samsung Galaxy was added  to your shopping cart (use JE to get inner text)
		
		verifyInnerText("Samsung Galaxy was added to your shopping cart.");
		//Step 7 Open Customer Service Page (Use Je)
		
		clickToElementByJS("//a[text()='Customer Service']");
			//Verify page title =  Customer Service (Use JE)
		Assert.assertEquals(getTitle(), "Customer Service");
		
		//Step 8 - Scroll to ELement Newsletter Textbox at bottom (Using JE)
		scrollToElement("//input[@id='newsletter']");
		//Step 9 - Verify text is displayed ( Use JE-Get innertext)
		
		verifyInnerText("Praesent ipsum libero, auctor ac, tempus nec, tempor nec, justo.");
		//Step 10- Navigate to domain: http://demo.guru99.com/v4/ (use JE)
		navigateToURL("http://demo.guru99.com/v4/");
		//Verify domain after navigated = demo.guru99.com
		Assert.assertEquals(getDomainPage(), "demo.guru99.com");
	}

	@Test
	public void TC_02_RemoveAttribut() {
	
		//Step 1 access to page http://demo.guru99.com/v4
		driver.get("http://demo.guru99.com/v4");
		//Step 2 login
		sendkeyElement(usernameTextbox,username);
		
		sendkeyElement(passwordTextbox,password);
		
		clickElement(loginButton);
		//Step 3 Select menu New Customer 
		clickElement(newCustomerMenu);
		//Step 4 Input  valid data -> Click Submit
		clickElement(genderFemaleRadioButton);
		
		sendkeyElement(customerNameTextBox,customerName);
		//	Remove attribute type = date of  field Date of Birth 
		removeElementAttribute("//input[@name='dob']","type");

		
		sendkeyElement(dobTextBox,dateOfBirth);
		
		sendkeyElement(addressTextBox,address);
		
		sendkeyElement(cityTextBox,city);
		
		sendkeyElement(stateTextBox,state);
		
		sendkeyElement(pinTextBox,pin);
		
		sendkeyElement(mobileNumberTextBox,phone);
		
		sendkeyElement(emailTextBox,email);
		
		sendkeyElement(passwordTextbox,password);
		
		clickElement(submitButton);
		By validateText = By.xpath("//p[text()='Customer Registered Successfully!!!']");
		//Verify that new customer successful
		Assert.assertTrue(isElementDisplayed(validateText));
	}

	@Test
	public void TC_03_CreateAnAccount() throws InterruptedException{
		//Step 1 Access page  http://live.guru99.com/ using JE
		
		navigateToURL("http://live.guru99.com/");
		
		//Step 2 click on link "My account" 
		
		clickToElementByJS("//div[@class='footer-container']//a[text()='My Account']");
		
		//Step 3 click on Create An Account button  to go to register page (usisng JE)
		
		clickToElementByJS("//a[@title='Create an Account']");

		//Step 4 input valid data
		sendkeyToElementByJS("//input[@id='firstname']","Jake");
		
		sendkeyToElementByJS("//input[@id='middlename']","Ace");
		
		sendkeyToElementByJS("//input[@id='lastname']","Briggs");
		
		sendkeyToElementByJS("//input[@id='email_address']",email);
		
		sendkeyToElementByJS("//input[@id='password']","admin@123");
		
		sendkeyToElementByJS("//input[@id='confirmation']","admin@123");
		
		//Step 5 click on Register button
		clickToElementByJS("//button[@title='Register']");
		
		//Step 6 Verify message
		verifyInnerText("Thank you for registering with Main Website Store.");
		
		//Step 7 log out
		clickToElementByJS("//div[@class='page-header-container']//span[text()='Account']");
		
		clickToElementByJS("//a[text()='Log Out']");
		// Verify navigation success 
		
		Thread.sleep(10000);
		
		Assert.assertEquals(getTitle(), "Home page");
		
		
		
		
		
		
		
	}
	
	//browser
	public String getTitle() {
		String title=jsExecutor.executeScript("return document.title").toString();
		return title;
	}
	public String getURL() {
		String url=(String)jsExecutor.executeScript("return document.URL");
		return url;
				
	}
	public String getDomainPage() {
		 String domain= (String)jsExecutor.executeScript("return document.domain");
		 return domain;
		
	}
	
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}
	public boolean verifyInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected +"')[0]");
		System.out.println("Text actual: " +textActual);
		return textActual.equals(textExpected);
	}
	public void navigateToURL(String url) {
		jsExecutor.executeScript("document.location ='" + url + "'");
	}
	
	//element
	public void highlightElement(String locator) {
		element = driver.findElement(By.xpath(locator));
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

	}
	
	public void clickToElementByJS(String locator) {
		element= driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void scrollToElement(String locator) {
		element= driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void sendkeyToElementByJS(String locator, String value) {
		element= driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public void removeElementAttribute(String locator, String attributeRemove) {
		element= driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}
	public int randomNumber() {
		Random rand= new Random();
		int n =rand.nextInt(1000);
		return n;
	}
	
	//selenium 
	public void sendkeyElement(By by,String value)
	{
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(value);
	}
	
	public void clickElement(By by)
	{
		driver.findElement(by).click();
	}
	
	public String getTextElement(By by)
	{
		return driver.findElement(by).getText();
	}
	
	public boolean isElementDisplayed(By by){
		if(driver.findElement(by).isDisplayed())
		{
			System.out.println("Element with locator["+ by +"] is displayed");
			return true;
		}
		else {
			System.out.println("Element with locator["+ by +"] is not displayed");
			return false;
		}
}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}