package webdriver_api;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Topic_05_Textbox_Textarea_Dropdownlist {
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor jsExecutor;	
	Actions action;
	
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
	
	By angularValue= By.xpath("//div[@class='mat-select-value']//span[text()='Alaska']");
	
	By reactJsValue=  By.xpath("//div[@class='visible menu transition']//span[text()='Christian']");
	
	By vueJsValue= By.xpath("//li[contains(.,'First Option')]");
	
	By editableValue= By.xpath("//div[@id='default-place']/descendant::li[contains(@class,'es-visible')]");
	
	By iframe= By.xpath("//iframe");
	
	Select select;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		waitExplicit= new WebDriverWait(driver,30);
		action = new Actions(driver);
		
		
		jsExecutor=(JavascriptExecutor) driver;
		
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
	public void TC_02_HandlinghtmlDropDownList() {
		//Step 1 Access page
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Step 2 check dropdown list is multipled or not
		
		WebElement jobDropdown = driver.findElement(By.xpath("//select[@id='job1']"));
		
	    select = new Select(jobDropdown);
		
		Assert.assertFalse(select.isMultiple());
		
		//Step 3 select value "Automation Tester" in dropdown list by method selectByVisibleText
		
		select.selectByVisibleText("Automation Tester");
		
		//Step 4 Verify value
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Automation Tester");
		
		//Step 5 select value	"Manual Tester" in dropdown list by method selectByValue
		
		select.selectByValue("manual");
		
		//Step 6 Verify value
		
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Tester");
		
		//Step 7 Select value "Mobile Tester" in  dropdown list by method selectByIndex
		
		select.selectByIndex(4);
		
		//Step 8  Verify Value
		
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Tester");
		
		//Step 9 Verify dropdown list have 5 values
		
		Assert.assertEquals(5, select.getOptions().size());
	}

	@Test
	public void TC_03_HandlingCustomDropDownList() throws Exception{
		
		//step 1 access page
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
		//step 2 select final item (item 19)
		
		selectItemDropdown("//span[@id='number-button']","//ul[@id='number-menu']/li/div","19");
		
		//step 3 Verify
		
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "19");
	}

	@Test
	public void TC_04_HandlingAngularDropDownList() throws Exception{
		
		//step 1 access page
		driver.get("https://material.angular.io/components/select/examples");
		
		//step 2 select final item (item 19)
		
		selectItemDropdown("//label/child::mat-label[text()='State']","//div[contains(@class,'mat-primary')]/mat-option","Alaska");
		
		//step 3 Verify
		
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='ng-tns-c100-18 ng-star-inserted']")).getText(), "Alaska");
	}
	
	@Test
	public void TC_05_HandlingReactJsDropDownList() throws Exception{
		
		//step 1 access page
		driver.get("https://react.semantic-ui.com/modules/dropdown/");
		
		//step 2 select final item (item 19)
		
		selectItemDropdown("//div[text()='Select Friend']","//div[@class='visible menu transition']","Christian");
		
		//step 3 Verify
		
		Assert.assertTrue(isElementDisplayed(reactJsValue));
	}
	
	@Test
	public void TC_06_HandlingVueJsDropDownList() throws Exception{
		
		//step 1 access page
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		//step 2 select final item (item 19)
		
		selectItemDropdown("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']","First Option");
		
		//step 3 Verify
		
		Assert.assertTrue(isElementDisplayed(vueJsValue));
	}
	
	@Test
	public void TC_07_HandlingEditableDropDownList() throws Exception{
		
		//step 1 access page
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		
		//step 2 select final item (item 19)
		
		selectItemEditableDropdown("//div[@id='default-place']/input","//div[@id='default-place']/descendant::li","Fiat");
		
		//step 3 Verify
		
		Assert.assertEquals(getTextElement(editableValue), "Fiat");
	}	
	
	@Test
	public void TC_07_HandlingMultipleDropDownList() throws Exception{
		
		//step 1 access page
		driver.get("http://multiple-select.wenzhixin.net.cn/examples#basic.html");
		
		
		driver.switchTo().frame(driver.findElement((iframe)));
		//step 2 select final item (item 19)
		
		selectItemEditableDropdown("//button[@class='ms-choice']","//div[@class='ms-drop bottom']/descendant::li","Fiat");
		
		//step 3 Verify
		
		Assert.assertEquals(getTextElement(editableValue), "Fiat");
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
	public void selectItemDropdown(String parentLocator, String allItemLocator, String expectedItem)throws InterruptedException{
		
		//click dropdown de hien thi cac item
		WebElement parentDropdown= driver.findElement(By.xpath(parentLocator));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", parentDropdown);
		jsExecutor.executeScript("arguments[0].click();", parentDropdown);
		Thread.sleep(2000);
	
		
		//cho cho cac element load thanh cong	
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemLocator)));
		
		//define ra 1 list element de store lai cac item duoc load ra
		List <WebElement> allItems= driver.findElements(By.xpath(allItemLocator));
			
		//dung vong lap de duyet qua cac item minh can
		for (WebElement item:allItems) {
			if(item.getText().equals(expectedItem)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				Thread.sleep(1000); 
				item.click();
				break;
			}
		}
		
	}
	
public void selectItemEditableDropdown(String parentLocator, String allItemLocator, String expectedItem)throws InterruptedException{
		
		//click dropdown de hien thi cac item
		WebElement parentDropdown= driver.findElement(By.xpath(parentLocator));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", parentDropdown);
		jsExecutor.executeScript("arguments[0].click();", parentDropdown);
		Thread.sleep(2000);
		parentDropdown.sendKeys(expectedItem);
	
		
		//cho cho cac element load thanh cong	
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemLocator)));
		
		//define ra 1 list element de store lai cac item duoc load ra
		List <WebElement> allItems= driver.findElements(By.xpath(allItemLocator));
			
		//dung vong lap de duyet qua cac item minh can
		for (WebElement item:allItems) {
			if(item.getText().equals(expectedItem)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				Thread.sleep(1000); 
				item.click();
				break;
			}
		}
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}