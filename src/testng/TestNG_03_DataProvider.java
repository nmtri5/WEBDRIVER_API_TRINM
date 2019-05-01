package testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNG_03_DataProvider {
	WebDriver driver;
	@BeforeTest
	public void preCondition() {
		System.setProperty("webdriver.gecko.driver", ".\\lib\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.guru99.com/index.php/customer/account/login/");
	}
	
	@Test(dataProvider = "userPassInfo")
	public void TC_01_LoginWithValidInformation(String user, String password) {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(user);
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
		
		driver.findElement(By.xpath("//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text() ='Log Out']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(), 'This is demo site for')]")).isDisplayed());
	}
	
	@DataProvider
	public Object[][] userPassInfo(){
		return new Object[][] {
			{ "auto_test_05@gmail.com" , "123123"},
			{ "auto_test_06@gmail.com" , "123123" },
			{ "auto_test_07@gmail.com" , "123123" }};		
			
	}
	
	
	@AfterTest
	public void postCondition() {
		driver.quit();
	}
	
}