package selenium;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Topic_02_Excercise {

	WebDriver driver;
	public String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//Step 01 - Truy cập vào trang: http://live.guru99.com/
		driver.get("http://live.guru99.com/");
	}

	@Test
	public void TC01_LoginEmpty() {
		
		//Step 02 - Click vào link "My Account" để tới trang đăng nhập
		driver.findElement(By.xpath(".//div[@class=\"footer\"]//a[@title=\"My Account\"]")).click();
		
		//Step 03 - Để trống Username/ Password
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("pass")).sendKeys("");
		
		//Step 04 - Click Login button
		driver.findElement(By.id("send2")).click();
		
		
		String password_error = driver.findElement(By.id("advice-required-entry-email")).getText();
		String email_error = driver.findElement(By.id("advice-required-entry-pass")).getText();
		
		//Step 05 - Verify error message xuất hiện tại 2 field:  This is a required field.
		Assert.assertEquals("This is a required field.", password_error);
		Assert.assertEquals("This is a required field.", email_error);
		
	}
	
	@Test
	public void TC02_LoginWithInvalidEmail() {
		
		//Step 02 - Click vào link "My Account" để tới trang đăng nhập
		driver.findElement(By.xpath(".//div[@class=\"footer\"]//a[@title=\"My Account\"]")).click();
		
		//Step 03 - Nhập email invalid: 123434234@12312.123123
		driver.findElement(By.id("email")).sendKeys("123434234@12312.123123");
		
		//Step 04 - Click Login button
		driver.findElement(By.id("send2")).click();
		
		String email_error = driver.findElement(By.id("advice-validate-email-email")).getText();
		
		//Step 05 - Verify error message xuất hiện:  Please enter a valid email address. For example johndoe@domain.com.
		Assert.assertEquals("Please enter a valid email address. For example johndoe@domain.com.", email_error);
		
	}
	
	@Test
	public void TC03_LoginWithPasswordLessThan6Character() {
		
		//Step 02 - Click vào link "My Account" để tới trang đăng nhập
		driver.findElement(By.xpath(".//div[@class=\"footer\"]//a[@title=\"My Account\"]")).click();
		
		//Step 03 - Nhập email correct and password incorrect: automation@gmail.com/ 123
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123");
		
		//Step 04 - Click Login button
		driver.findElement(By.id("send2")).click();
		
		String pass_error = driver.findElement(By.id("advice-validate-password-pass")).getText();
		
		//Step 05 - Verify error message xuất hiện: Please enter 6 or more characters without leading or trailing spaces.
		Assert.assertEquals("Please enter 6 or more characters without leading or trailing spaces.", pass_error);
		
	}
	
	@Test
	public void TC04_LoginWithPasswordIncorrect() {
		
		//Step 02 - Click vào link "My Account" để tới trang đăng nhập
		driver.findElement(By.xpath(".//div[@class=\"footer\"]//a[@title=\"My Account\"]")).click();
		
		//Step 03 - Nhập email correct and password incorrect: automation@gmail.com/ 123123123
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123123123");
		
		//Step 04 - Click Login button
		driver.findElement(By.id("send2")).click();
		
		String error_message = driver.findElement(By.xpath(".//li[@class=\"error-msg\"]")).getText();
		
		//Step 05 - Verify error message xuất hiện: Please enter 6 or more characters without leading or trailing spaces.
		Assert.assertEquals("Invalid login or password.", error_message);
		
	}
	@Test
	public void TC05_CreateAnAccount() throws Exception {
		
		//Step 02 - Click vào link "My Account" để tới trang đăng nhập
		driver.findElement(By.xpath(".//div[@class=\"footer\"]//a[@title=\"My Account\"]")).click();
		
		//Step 03 - Click CREATE AN ACCOUNT button để tới trang đăng kí tài khoản
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		//Step 04 - Nhập thông tin hợp lệ vào tất cả các field: First Name/ Last Name/ Email Address/ Password/ Confirm Password
		driver.findElement(By.id("firstname")).sendKeys("Tri");
		driver.findElement(By.id("middlename")).sendKeys("Minh");
		driver.findElement(By.id("lastname")).sendKeys("Nguyen");
		driver.findElement(By.id("email_address")).sendKeys(getSaltString() + "@gmail.com");
		driver.findElement(By.id("password")).sendKeys("trideptrai");
		driver.findElement(By.id("confirmation")).sendKeys("trideptrai");
		
		//Step 05 - Click REGISTER button
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		String success_message = driver.findElement(By.xpath("//li[@class='success-msg']")).getText();
		
		//Step 06 - Verify error message xuất hiện: Please enter 6 or more characters without leading or trailing spaces.
		Assert.assertEquals("Thank you for registering with Main Website Store.", success_message);
		
		//Step 07 - Logout khỏi hệ thống
		driver.findElement(By.xpath("//a[contains(@class,'skip-account')]")).click();
		driver.findElement(By.xpath("//*[@id='header-account']//ul//li[last()]")).click();
		
		//Step 08 - Kiểm tra hệ thống navigate về Home page sau khi logout thành công
		Thread.sleep(7000);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/");
			
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
