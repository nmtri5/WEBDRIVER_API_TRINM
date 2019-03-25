package selenium;

import java.sql.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_04_TextArea {
	WebDriver driver;
	String a;
	//data
	String username = "mngr181358 ";
	String password = "berydUp";
	String customerName = "Selenium Online";
	String gender = "male";
	String dob = "10/01/2000";
	String address = "123 Address";
	String city = "Ho Chi Minh";
	String state = "Thu Duc";
	String pin = "123456";
	String mobileNumber = "01268887473";
	String email = "tringuyen" + randomNumber() + "@gmail.com";
	String pass = "123456";
	
	//edited data
	String editedAddress = "234 Edit Address";
	String editedCity = "Edit Ho Chi Minh";
	String editedState = "Edit Thu Duc";
	String editedPin = "654321";
	String editedMobile = "0987654321";
	String editedEmail = "tringuyen" + randomNumber() + "@gmail.com";
		
	//Login page elements
	By usernameTextBox = By.xpath("//input[@name='uid']");
	By passwordTextBox = By.xpath("//input[@name='password']");
	By btnLogin = By.xpath("//input[@name='btnLogin']");
	
	//Main page element
	By welcomeText = By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]");
	By newCustomerNav = By.xpath("//ul[@class='menusubnav']//a[text()=\"New Customer\"]");
	By editCustomerNav = By.xpath("//ul[@class='menusubnav']//a[text()=\"Edit Customer\"]");
	
	//Create new customer element
	By customerNameTextBox = By.xpath("//input[@name='name']");
	By genderRadioBox = By.xpath("//input[@name='rad1'and @value='m']");
	By dobTextBox = By.xpath("//input[@id='dob']");
	By addressTextArea = By.xpath("//textarea[@name='addr']");
	By cityTextBox = By.xpath("//input[@name='city']");
	By stateTextBox = By.xpath("//input[@name='state']");
	By pinTextBox = By.xpath("//input[@name='pinno']");
	By phoneTextBox = By.xpath("//input[@name='telephoneno']");
	By emailTextBox = By.xpath("//input[@name='emailid']");
	By passTextBox = By.xpath("//input[@name='password']");
	By submitBtn = By.xpath("//input[@name='sub']");
	
	//Edit customer page element
	By cusIDTextBox = By.xpath("//input[@name='cusid']");
	By cusNameTextBox = By.xpath("//input[@name='name']");
	By addrTextArea = By.xpath("//textarea[@name='addr']");
	By submitBtn2 = By.xpath("//input[@name='AccSubmit']");
	

  @Test
  public void TC_01_checkTextBox() throws Exception{
	  //Step 02 - Đăng nhập với thông tin: User =  mngr181358 | Pass = berydUp
	  driver.findElement(usernameTextBox).sendKeys(username);
	  driver.findElement(passwordTextBox).sendKeys(password);
	 
	  //Click login button
	  driver.findElement(btnLogin).click();
	  
	  //Verify HomePage được hiển thị thành công
	  Assert.assertTrue(driver.findElement(welcomeText).isDisplayed());
	  
	  //Step 03 - Chọn menu New Customer
	  driver.findElement(newCustomerNav).click();
	  
	  //Step 04 - Nhập toàn bộ dữ liệu đúng > Click Submit
	  driver.findElement(customerNameTextBox).sendKeys(customerName);
	  driver.findElement(genderRadioBox).click();
	  driver.findElement(dobTextBox).sendKeys(dob);
	  driver.findElement(addressTextArea).sendKeys(address);
	  driver.findElement(cityTextBox).sendKeys(city);
	  driver.findElement(stateTextBox).sendKeys(state);
	  driver.findElement(pinTextBox).sendKeys(pin);
	  driver.findElement(phoneTextBox).sendKeys(mobileNumber);
	  driver.findElement(emailTextBox).sendKeys(email);
	  driver.findElement(passTextBox).sendKeys(password);
	  driver.findElement(submitBtn).click();
	 
	  //Step 05 - Sau khi hệ thống tạo mới Customer thành công > Get ra thông tin của Customer ID
	  a = driver.findElement(By.xpath("//table[@id='customer']//td[text() = 'Customer ID']/following-sibling::td")).getText();
		
	  //Step 06 - Verify tất cả thông tin được tạo mới thành công
	  Assert.assertEquals(customerName,driver.findElement(By.xpath("//table[@id='customer']//td[text() = 'Customer Name']/following-sibling::td")).getText());
	  Assert.assertEquals(gender ,driver.findElement(By.xpath("//table[@id='customer']//td[text() = 'Gender']/following-sibling::td")).getText());
	  Assert.assertEquals(convertDate(dob),driver.findElement(By.xpath("//table[@id='customer']//td[text() = 'Birthdate']/following-sibling::td")).getText());
	  Assert.assertEquals(address,driver.findElement(By.xpath("//table[@id='customer']//td[text() = 'Address']/following-sibling::td")).getText());
	  Assert.assertEquals(city,driver.findElement(By.xpath("//table[@id='customer']//td[text() = 'City']/following-sibling::td")).getText());
	  Assert.assertEquals(state,driver.findElement(By.xpath("//table[@id='customer']//td[text() = 'State']/following-sibling::td")).getText());
	  Assert.assertEquals(pin,driver.findElement(By.xpath("//table[@id='customer']//td[text() = 'Pin']/following-sibling::td")).getText());
	  Assert.assertEquals(mobileNumber,driver.findElement(By.xpath("//table[@id='customer']//td[text() = 'Mobile No.']/following-sibling::td")).getText());
	  Assert.assertEquals(email,driver.findElement(By.xpath("//table[@id='customer']//td[text() = 'Email']/following-sibling::td")).getText());
	    
  }
  @Test
  public void TC_02_EditCustomer() {
	//Step 07 - Chọn menu Edit Customer > Nhập Customer ID > Submit
	  driver.findElement(editCustomerNav).click();
	  driver.findElement(cusIDTextBox).sendKeys(a);
	  driver.findElement(submitBtn2).click();
	  
	//Step 08 - Verify giá trị tại 2 field: Customer Name và Address đúng với dữ liệu khi tạo mới New Customer tại Step 04
	  Assert.assertEquals(customerName, driver.findElement(By.xpath("//input[@name='name']")).getAttribute("value"));
	  Assert.assertEquals(address, driver.findElement(By.xpath("//textarea[@name='addr']")).getText());
	  
	//Step 09 - Nhập giá trị mới tại tất cả các field (ngoại trừ những field bị disable) > Submit
	  driver.findElement(addressTextArea).clear();
	  driver.findElement(addressTextArea).sendKeys(editedAddress);
	  driver.findElement(cityTextBox).clear();
	  driver.findElement(cityTextBox).sendKeys(editedCity);
	  driver.findElement(stateTextBox).clear();
	  driver.findElement(stateTextBox).sendKeys(editedState);
	  driver.findElement(pinTextBox).clear();
	  driver.findElement(pinTextBox).sendKeys(editedPin);
	  driver.findElement(phoneTextBox).clear();
	  driver.findElement(phoneTextBox).sendKeys(editedMobile);
	  driver.findElement(emailTextBox).clear();
	  driver.findElement(emailTextBox).sendKeys(editedEmail);
	  
	  driver.findElement(submitBtn).click();
	  
	  //Step 10 - Verify giá trị tất cả các field đúng với dữ liệu sau khi đã Edit thành công
	  Assert.assertEquals(editedAddress, driver.findElement(By.xpath("//table[@id='customer']//td[text() = 'Address']/following-sibling::td")).getText());
	  Assert.assertEquals(editedCity,  driver.findElement(By.xpath("//table[@id='customer']//td[text() = 'City']/following-sibling::td")).getText());
	  Assert.assertEquals(editedState, driver.findElement(By.xpath("//table[@id='customer']//td[text() = 'State']/following-sibling::td")).getText());
	  Assert.assertEquals(editedPin, driver.findElement(By.xpath("//table[@id='customer']//td[text() = 'Pin']/following-sibling::td")).getText());
	  Assert.assertEquals(editedMobile, driver.findElement(By.xpath("//table[@id='customer']//td[text() = 'Mobile No.']/following-sibling::td")).getText());
	  Assert.assertEquals(editedEmail, driver.findElement(By.xpath("//table[@id='customer']//td[text() = 'Email']/following-sibling::td")).getText());
	
  }
  
  private int randomNumber() {
	Random ran = new Random();
	int n = ran.nextInt(9999);
	return n;
}
  
  public String convertDate(String date) {
	  String day, month, year;
	  String [] a = date.split("/");	
	  day = a[0];
	  month = a[1];
	  year = a[2];
	  String newdate = year + "-" + day + "-" + month;
	  return newdate;
  }
  
@BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.get("http://demo.guru99.com/v4/");
	  driver.manage().window().maximize();
  }

 @AfterTest
  public void afterTest() {
	  //driver.quit();
  }

}
