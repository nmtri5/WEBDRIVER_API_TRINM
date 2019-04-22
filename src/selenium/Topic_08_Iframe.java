package selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
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
	  
	  WebElement popup = driver.findElement(By.xpath("//div[@id = 'parentdiv']//img[@class='popupbanner at-element-click-tracking']"));
	  if(popup.isDisplayed()) {
		  WebElement closePopup = driver.findElement(By.xpath("//div[@id = 'parentdiv']//img[@class='popupCloseButton']"));
		  closePopup.click();
	  }
	  
//	  	  Step 03 - Verify đoạn text được hiển thị:  What are you looking for? (switch qua iframe nếu có)
	  driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe")));
	  String message = driver.findElement(By.id("messageText")).getText();
	  Assert.assertEquals(message,"What are you looking for?");
	  
//	  	  Step 04 - Verify banner có đúng 6 images (switch qua iframe nếu có)
	  driver.switchTo().defaultContent();
	  List<WebElement> listImage = driver.findElements(By.xpath("//div[@class='owl-stage']//div[contains(@class,'owl-item')]//img"));
	  Assert.assertEquals(listImage.size(), 11);

	  for(WebElement image : listImage) {
		  Assert.assertTrue(isImageLoadedSucceeded(image));
		  //Assert.assertTrue(image.isDisplayed());
		  
	  }
	  
//	  	  Step 05 - Verify flipper banner được hiển thị và có 8 items
	  List<WebElement> listProduct = driver.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
	  Assert.assertEquals(listProduct.size(), 8);
		  
	  //Verify image load succesfully
	  for(WebElement image : listProduct) {
		  Assert.assertTrue(isImageLoadedSucceeded(image));
		  Assert.assertTrue(image.isDisplayed());
	  }
  }
  
  
  public boolean isImageLoadedSucceeded(WebElement image) {
	  JavascriptExecutor js =(JavascriptExecutor) driver;
	  return (Boolean) js.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", image);
  }
  
  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();	
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
