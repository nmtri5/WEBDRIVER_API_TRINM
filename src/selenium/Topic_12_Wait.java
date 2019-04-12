package selenium;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sun.jmx.snmp.Timestamp;

public class Topic_12_Wait {
	WebDriver driver;
	WebDriverWait waitExplicit;
	
	By btnStart = By.xpath("//div[@id='start']/button");
	By txtHello = By.id("finish");
	By iconLoading = By.xpath("//div[@id='loading']/img");
	
 
  public void TC_01_ImplicitWait() {
//	  	  Step 01 - Truy cập vào trang: 
//		  http://the-internet.herokuapp.com/dynamic_loading/2
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	  
//		  Step 02 - Define an implicit wait (If you set 2 seconds, test will fail)
	  driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
//		  Step 03 - Click the Start button
	  driver.findElement(btnStart).click();
//		  Step 04 - Wait result text will appear  
//		  Step 05 - Check result text is "Hello World!"
	  
	  Assert.assertEquals(driver.findElement(txtHello).getText(), "Hello World!");
	  
  }
  
  @Test (enabled = false)
  public void TC_02_ExplicitWait() {
//	  	  Step 01 - Truy cập vào trang: 
//		  http://the-internet.herokuapp.com/dynamic_loading/2
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	  waitExplicit = new WebDriverWait(driver, 4);
//	  Step 02 - Click the Start button

	  driver.findElement(btnStart).click();
//	  Step 03 - Wait Loading invisible
	  waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(iconLoading));
//	  Step 04 - Wait Hello World visible
	  waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(txtHello));
//	  Step 05 - Check result text is "Hello World!"
	  
	  Assert.assertEquals(driver.findElement(txtHello).getText(), "Hello World!");
	  
  }
  
  @Test (enabled = false)
  public void TC_03_ExplicitWait2() {
//	  Step 01 - Truy cập vào trang: 
//		  http://the-internet.herokuapp.com/dynamic_loading/2
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	  
//	  Step 02 - Click the Start button
	  driver.findElement(btnStart).click();
	  waitExplicit = new WebDriverWait(driver, 5);
	  
//	  Step 03 - Wait Hello World visible
	  waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(txtHello));
	  
//	  Step 04 - Check result text is "Hello World!"
	  Assert.assertEquals(driver.findElement(txtHello).getText(), "Hello World!");
  }
  
  @Test
  public void TC_04_ExplicitWait3() {
//	  Step 01 - Truy cập vào trang: 
//		  http://the-internet.herokuapp.com/dynamic_loading/2
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	  waitExplicit = new WebDriverWait(driver, 5);
	  
//	  Step 02 - Check Hello World text invisible -> hết bao nhiêu s?
	  driver.findElement(btnStart).click();
	  waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(txtHello));
	  getDateTimeSecond();
//	  Step 03 - Check Loading invisible -> hết bao nhiêu s?
	  waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(iconLoading));
	  getDateTimeSecond();
//	  Step 04 - Click the Start button
//	  Step 05 - Check Loading invisible -> hết bao nhiêu s?
//	  Step 06- Check Start button inivisible -> hết bao nhiêu s?
	  

	  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	  
//	  Step 02 - Click the Start button
	 
	  waitExplicit = new WebDriverWait(driver, 5);
	  
//	  Step 03 - Wait Hello World visible
	  
	  
//	  Step 04 - Check result text is "Hello World!"
	  Assert.assertEquals(driver.findElement(txtHello).getText(), "Hello World!");
  }
  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }
  
  public void getDateTimeSecond() {
      Date date = new Date();
      System.out.println(new Timestamp(date.getTime()));
}
}
