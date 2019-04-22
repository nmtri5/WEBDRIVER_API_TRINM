package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_08_Iframe {
	WebDriver driver;
  @Test
  public void TC_01_Iframe() {
//	  	  Step 01 - Truy cập vào trang: http://www.hdfcbank.com/
	  driver.get("http://www.hdfcbank.com/");
//		  Step 02 - Close popup nếu có hiển thị (switch qua iframe nếu có)  - F5 (refresh page) nhiều lần thì sẽ xuất 
	  
	  WebElement closePopup = driver.findElement(By.xpath("//div[@id = 'parentdiv']//img[@class='popupCloseButton']']"));
	  WebElement popup = driver.findElement(By.xpath("//div[@id = 'parentdiv']//img[@class='popupbanner at-element-click-tracking']"));
	  if(popup.isDisplayed()) {
		  closePopup.click();
	  }
	  
//	  	  Step 03 - Verify đoạn text được hiển thị:  What are you looking for? (switch qua iframe nếu có)
	  driver.switchTo().frame("viz_iframe36255cbced723a5198cb1213c31ONR");
	  String message = driver.findElement(By.id("messageText")).getText();
  }
  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();	
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
