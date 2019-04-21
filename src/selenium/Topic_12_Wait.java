package selenium;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	
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
  
  @Test (enabled = false)
  public void TC_04_ExplicitWait3() {
//	  Step 01 - Truy cập vào trang: 
//	  http://the-internet.herokuapp.com/dynamic_loading/2
	  waitExplicit = new WebDriverWait(driver, 5);
	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	  
//	  Step 02 - Check Hello World text invisible -> hết bao nhiêu s?
	  System.out.println("Start wait time Hello world text: " + getDateTimeSecond());
	  waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(txtHello));
	  System.out.println("End wait time Hello world text: " + getDateTimeSecond());
	  
//	  Step 03 - Check Loading invisible -> hết bao nhiêu s?	  

	  System.out.println("Start wait time Loading icon: " + getDateTimeSecond());
	  waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(iconLoading));
	  System.out.println("End wait time Loading icon: " + getDateTimeSecond());
	  
//	  Step 04 - Click the Start button
	  driver.findElement(btnStart).click();

//	  Step 05 - Check Loading invisible -> hết bao nhiêu s?
	  System.out.println("Start wait time Loading icon: " + getDateTimeSecond());
	  waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(iconLoading));
	  System.out.println("End wait time Loading icon: " + getDateTimeSecond());
	  
//	  Step 06- Check Start button inivisible -> hết bao nhiêu s?
	  System.out.println("Start wait time Start button: " + getDateTimeSecond());
	  waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(btnStart));
	  System.out.println("End wait time Start button: " + getDateTimeSecond());  
	  
  }
  @Test 
  public void TC_05_ExplicitWait4() {
//	  Step 01 - Truy cập vào trang:
//		  http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx
	  driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

//	  Step 02 - Wait cho "Date Time Picker" được hiển thị (sử dụng: presence or visibility)
	  waitExplicit = new WebDriverWait(driver,1);
	  waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_Panel1")));
	  
//	  Step 03 - In ra ngày đã chọn (Before AJAX call) -> hiện tại chưa chọn nên in ra = "No Selected Dates to display."
	  Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(), "No Selected Dates to display.");
	  
//	  Step 04 - Chọn ngày hiện tại (VD: 23/09/2017) (hoặc 1 ngày bất kì tương ứng trong tháng/ năm hiện tại)
	  driver.findElement(By.xpath("//tr[@class='rcRow']//a[text() = '24']")).click();
	  
//	  Step 05 - Wait cho đến khi "loader ajax" không còn visible (sử dụng: invisibility)
//		  Xpath: //div[@class='raDiv']
	  waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));
	  	  
//	  Step 06 - Wait cho selected date = 23 được visible ((sử dụng: visibility)
//		  Xpath: //*[contains(@class,'rcSelected')]//a[text()='23']
	  waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'rcSelected')]//a[text()='23']")));
	  
//	  Step 07 - Verify ngày đã chọn bằng = Saturday, September 23, 2017
	  Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(), "Tuesday, April 23, 2019");
	  
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
  
  public Date getDateTimeSecond() {
		Date date = new Date();
		date = new java.sql.Timestamp(date.getTime());
		return date;

	}
  
  public void clickOnRandomElement(List<WebElement> a) {
	  Random ran = new Random();
	  int randomInt = ran.nextInt(a.size());
	  executor.executeScript("arguments[0].click();", a.get(randomInt));
	  
  }
}
