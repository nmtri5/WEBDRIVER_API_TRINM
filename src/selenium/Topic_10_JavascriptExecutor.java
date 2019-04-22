package selenium;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_10_JavascriptExecutor {
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	
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
	
	//data new user
	String username = "mngr181358";
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
	
	//data new account
	String firstName = "Tri";
	String middleName = "Minh";
	String lastName = "Nguyen";
	String emailAddress = "tringuyen" + randomNumber() + "@gmail.com";
	String Confirmpassword = "123456";
	
	//Login page elements
		By usernameTextBox = By.xpath("//input[@name='uid']");
		By passwordTextBox = By.xpath("//input[@name='password']");
		By btnLogin = By.xpath("//input[@name='btnLogin']");
		By newCustomerNav = By.xpath("//ul[@class='menusubnav']//a[text()=\"New Customer\"]");
	
	  public void highlightElement(WebElement element) {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].style.border='6px groove red'", element);
	    }

	    public Object executeForBrowser(String javaSript) {
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            return js.executeScript(javaSript);
	    }

	    public Object clickToElementByJS(WebElement element) {
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            return js.executeScript("arguments[0].click();", element);
	    }

	    public Object sendkeyToElementByJS(WebElement element, String value) {
	           JavascriptExecutor js = (JavascriptExecutor) driver;
	           return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	    }

	    public Object removeAttributeInDOM(WebElement element, String attribute) {
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
	    }

	    public Object scrollToBottomPage() {
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	    }

	    public Object navigateToUrlByJS(String url) {
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            return js.executeScript("window.location = '" + url + "'");
	    }
	
  @Test (enabled = false)
  public void TC_01_JavascriptExecutor() {
//	  Step 01 - Truy cập vào trang: http://live.guru99.com/
	  navigateToUrlByJS("http://live.guru99.com/");
	  
//		  Step 02 - Sử dụng JE để get domain của page
//		  Verify domain =  live.guru99.com
	  String domainName = (String) executeForBrowser("return document.domain");
	  Assert.assertEquals(domainName, "live.guru99.com");
//		  Step 03 - Sử dụng JE để get URL của page
//		  Verify URL =  http://live.guru99.com/
	  String URL = (String) executeForBrowser("return document.URL");
	  Assert.assertEquals(URL, "http://live.guru99.com/");
	  
//		  Step 04 - Open MOBILE page (Sử dụng JE)
	  highlightElement(driver.findElement(By.xpath("//li[@class='level0 nav-1 first']/a[text()='Mobile']")));
	  clickToElementByJS(driver.findElement(By.xpath("//li[@class='level0 nav-1 first']/a[text()='Mobile']")));
	  
//		  Step 05 - Add sản phẩm SAMSUNG GALAXY vào Cart (click vào ADD TO CART button bằng JE)
//		  Hướng dẫn: sử dụng following-sibling
	  highlightElement(driver.findElement(By.xpath("//h2[@class='product-name']/a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']/button")));
	  clickToElementByJS(driver.findElement(By.xpath("//h2[@class='product-name']/a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']/button")));
	  
//		  Step 06 - Verify message được hiển thị:  Samsung Galaxy was added to your shopping cart. (Sử dụng JE - Get innertext of the entire webpage )
	  Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Samsung Galaxy was added to your shopping cart.");
	  
//		  Step 07 - Open PRIVACY POLICY page (Sử dụng JE)
	  clickToElementByJS(driver.findElement(By.xpath("//a[text() = 'Privacy Policy']")));
	  
//		  Verify title của page = Privacy Policy (Sử dụng JE)
	  String policy = (String) executeForBrowser("return document.title");
	  Assert.assertEquals(policy, "Privacy Policy");
	  
//		  Step 08 - Srcoll xuống cuối page
	  scrollToBottomPage();
	  
//		  Step 09 - Verify dữ liệu có hiển thị với chỉ 1 xpath: 
	  Assert.assertTrue(driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']//following-sibling::td[text()='The number of items in your Wishlist.']")).isDisplayed());
	  
	  //Step 10 - Navigate tới domain: http://demo.guru99.com/v4/  (Sử dụng JE)
	  //Verify domain sau khi navigate = demo.guru99.com
	  navigateToUrlByJS("http://demo.guru99.com/v4/");
	  Assert.assertEquals(executeForBrowser("return document.domain"), "demo.guru99.com");
  }
  
  @Test (enabled = false)
  public void TC_02_RemoveAttribute() {
//	      Step 01 - Access vào trang: http://demo.guru99.com/v4
	  navigateToUrlByJS("http://demo.guru99.com/v4");
	  
//		  Step 02 - Đăng nhập với thông tin: User =  mngr181358 | Pass = berydUp	  
//		  Note: Manual test để lấy thông tin User/Pass nếu hết hạn - User chỉ tồn tại trong 20 ngày - http://demo.guru99.com/
	  driver.findElement(usernameTextBox).sendKeys(username);
	  driver.findElement(passwordTextBox).sendKeys(password);
	 
	  //Click login button
	  driver.findElement(btnLogin).click();
	  
	  
//		  Step 03 - Chọn menu New Customer
	  clickToElementByJS(driver.findElement(newCustomerNav));
	  
//		  Step 04 - Nhập toàn bộ dữ liệu đúng > Click Submit
	  driver.findElement(customerNameTextBox).sendKeys(customerName);
	  driver.findElement(genderRadioBox).click();
	  removeAttributeInDOM(driver.findElement(dobTextBox), "type");
	  driver.findElement(dobTextBox).sendKeys(dob);
	  driver.findElement(addressTextArea).sendKeys(address);
	  driver.findElement(cityTextBox).sendKeys(city);
	  driver.findElement(stateTextBox).sendKeys(state);
	  driver.findElement(pinTextBox).sendKeys(pin);
	  driver.findElement(phoneTextBox).sendKeys(mobileNumber);
	  driver.findElement(emailTextBox).sendKeys(email);
	  driver.findElement(passTextBox).sendKeys(password);
	  driver.findElement(submitBtn).click();
	  
	  //Step 05 - Verify tạo mới Customer thành công
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
  public void TC_03_CreateAnAccount() {
//	 	  Step 01 - Truy cập vào trang: http://live.guru99.com/
	  navigateToUrlByJS("http://live.guru99.com/");
	  
//		  Step 02 - Click vào link "My Account" để tới trang đăng nhập (Sử dụng JE)
	  WebElement myAccount = driver.findElement(By.xpath("//li[@class='first']/a[text() = 'My Account']"));
	  clickToElementByJS(myAccount);
	  
//		  Step 03 - Click CREATE AN ACCOUNT button để tới trang đăng kí tài khoản (Sử dụng JE)
	  WebElement createAccount = driver.findElement(By.xpath("//a[@title='Create an Account']"));
	  clickToElementByJS(createAccount);
	  
//		  Step 04 - Nhập thông tin hợp lệ vào tất cả các field: First Name/ Last Name/ Email Address/ Password/ Confirm Password (Sử dụng JE)
//		  (Lưu ý: Tạo random cho dữ liệu tại field Email Address)
	  sendkeyToElementByJS(driver.findElement(By.id("firstname")), firstName);
	  sendkeyToElementByJS(driver.findElement(By.id("middlename")), middleName);
	  sendkeyToElementByJS(driver.findElement(By.id("lastname")), lastName);
	  sendkeyToElementByJS(driver.findElement(By.id("email_address")), emailAddress);
	  sendkeyToElementByJS(driver.findElement(By.id("password")), Confirmpassword);
	  sendkeyToElementByJS(driver.findElement(By.id("confirmation")), Confirmpassword);
	  
//		  Step 05 - Click REGISTER button (Sử dụng JE)
	  clickToElementByJS(driver.findElement(By.xpath("//div[@class='buttons-set']//span[text() = 'Register']")));
	  
//		  Step 05 - Verify message xuất hiện khi đăng kí thành công: Thank you for registering with Main Website Store. (Sử dụng JE)
	  String verify = executeForBrowser("return document.documentElement.innerText").toString();
	  Assert.assertTrue(verify.contains("Thank you for registering with Main Website Store."));
	  
//		  Step 06 - Logout khỏi hệ thống (Sử dụng JE)
	  clickToElementByJS(driver.findElement(By.xpath("//li[@class=' last']//a[@title='Log Out']")));
//		  Step 07 - Kiểm tra hệ thống navigate về Home page sau khi logout thành công (Sử dụng JE)
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']//h2[contains(text(),'This is demo site for')]")).isDisplayed());
  }
  
  @BeforeTest
  public void beforeTest() {
	  System.setProperty("webdriver.chrome.driver","D:\\Automation Test\\chromedriver\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @AfterTest
  public void afterTest() {
	  driver.quit();
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
}
