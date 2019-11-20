package webdriver_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Textbox_Textarea_Dropdownlist {
	WebDriver driver;
	String username, password,customerID, customerName, gender, dateOfBirth, address, city, state, pin, phone, email;
	String editAddress, editCity, editState, editPin, editPhone, editEmail;

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
		driver = new FirefoxDriver();

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
		editAddress = "12A LHP";
		editCity= "SaiGon";
		editState=  "BinhTan";
		editPin= "123456";
		editPhone= "01674559015";
		email="patricktruong" +randomNumber()+"@gmail.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_handlingTextBoxTextArea() {
		//Step 1 : Access page
		driver.get("http://demo.guru99.com/v4");
		
		//Step 2.1: login
		sendkeyElement(usernameTextbox,username);
		
		sendkeyElement(passwordTextbox,password);
		
		clickElement(loginButton);
		
		//Step 2.2: Verify homepage is displayed successful
		
		String titleHomepage= driver.getTitle();
		Assert.assertEquals(titleHomepage, "Guru99 Bank Manager HomePage");
		
		//Step 3: Click on New customer menu

		
		clickElement(newCustomerMenu);
		
		//Step 4 input data
		
		sendkeyElement(customerNameTextBox,customerName);
		
		clickElement(genderFemaleRadioButton);

		sendkeyElement(dobTextBox,dateOfBirth);
		
		sendkeyElement(addressTextBox,address);
		
		sendkeyElement(cityTextBox,city);
		
		sendkeyElement(stateTextBox,state);
		
		sendkeyElement(pinTextBox,pin);
		
		sendkeyElement(mobileNumberTextBox,phone);
		
		sendkeyElement(emailTextBox,email);
		
		sendkeyElement(passwordTextbox,password);
		
		clickElement(submitButton);
		
		//Step 5 & step 6 : After submit success, get information of new customer and then verify all information
		getTextElement(By.xpath("//td[text()='Customer Name']/following-sibling::td"));
		
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")), customerName);
		
		getTextElement(By.xpath("//td[text()='Gender']/following-sibling::td"));
		
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Gender']/following-sibling::td")), gender);
		
		getTextElement(By.xpath("//td[text()='Birthdate']/following-sibling::td"));
		
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")), dateOfBirth);
		
		getTextElement(By.xpath("//td[text()='Address']/following-sibling::td"));
		
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Address']/following-sibling::td")), address);
		
		getTextElement(By.xpath("//td[text()='City']/following-sibling::td"));
		
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='City']/following-sibling::td")), city);
		
		getTextElement(By.xpath("//td[text()='State']/following-sibling::td"));
		
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='State']/following-sibling::td")), state);
		
		getTextElement(By.xpath("//td[text()='Pin']/following-sibling::td"));
		
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Pin']/following-sibling::td")), pin);
		
		getTextElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td"));
		
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")), phone);
		
		getTextElement(By.xpath("//td[text()='Email']/following-sibling::td"));
		
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Email']/following-sibling::td")), email);
		
		//Step 7 Access edit page
		
		customerID = getTextElement(By.xpath("//td[text()='Customer ID']/following-sibling::td"));
		
		clickElement(By.xpath("//a[text()='Edit Customer']"));
		
		sendkeyElement(By.xpath("//input[@name='cusid']"), customerID);
		
		clickElement(By.xpath("//input[@name='AccSubmit']"));
		
		//Step 8 verify data
		
		Assert.assertEquals(getAttribute(customerNameTextBox,"value"), customerName);
		
		Assert.assertEquals(getTextElement(addressTextBox), address);
		
		//Step 9 Input edited data
		
		sendkeyElement(addressTextBox,editAddress);
		
		sendkeyElement(cityTextBox,editCity);
		
		sendkeyElement(stateTextBox,editState);
		
		sendkeyElement(pinTextBox,editPin);
		
		sendkeyElement(mobileNumberTextBox,editPhone);
		
		sendkeyElement(emailTextBox,email);
		
		clickElement(submitButton);
		
		//Step 10 Verify edited dataa
		
		getTextElement(By.xpath("//td[text()='Address']/following-sibling::td"));
		
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Address']/following-sibling::td")), editAddress);
		
		getTextElement(By.xpath("//td[text()='City']/following-sibling::td"));
		
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='City']/following-sibling::td")), editCity);
		
		getTextElement(By.xpath("//td[text()='State']/following-sibling::td"));
		
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='State']/following-sibling::td")), editState);
		
		getTextElement(By.xpath("//td[text()='Pin']/following-sibling::td"));
		
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Pin']/following-sibling::td")), editPin);
		
		getTextElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td"));
		
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")), editPhone);
		
		getTextElement(By.xpath("//td[text()='Email']/following-sibling::td"));
		
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Email']/following-sibling::td")), email);
		
	}

	@Test
	public void TC_02_() {
		driver.get("");
	}

	@Test
	public void TC_03_() {
		driver.get("");
	}

	
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
	
	public int randomNumber() {
		Random rand= new Random();
		int n =rand.nextInt(50);
		return n;
	}
	
	public String getAttribute(By by,String attributeName) {
		return driver.findElement(by).getAttribute(attributeName);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}