package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_13_WaitPart2 {
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	
  @Test
  public void TC_01_FluentWait() {
//	  Step 01 - Truy cập vào trang: 
//		  https://daominhdam.github.io/fluent-wait/
	  driver.get("https://daominhdam.github.io/fluent-wait/");	
	  
//	  Step 02 - Wait cho đến khi countdown time được visible (visibility)
	  waitExplicit = new WebDriverWait(driver, 15);
	  WebElement countdount =  driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
	  waitExplicit.until(ExpectedConditions.visibilityOf(countdount));
	  
//	  Step 03 - Sử dụng Fluent wait để:
//		  Mỗi 1s kiểm tra countdount= 00 được xuất hiện trên page hay chưa (giây đếm ngược về 00)
//		  Tức là trong vòng 15s (tổng thời gian), cứ mỗi 1 giây verify xem nó đã đếm ngược về giây 00 hay chưa
	 
	  
	// Khởi tạo Fluent wait
	  new FluentWait<WebElement>(countdount)
	             // Tổng time wait là 15s
	             .withTimeout(15, TimeUnit.SECONDS)
	              // Tần số mỗi 1s check 1 lần
	              .pollingEvery(1, TimeUnit.SECONDS)
	             // Nếu gặp exception là find ko thấy element sẽ bỏ  qua
	              .ignoring(NoSuchElementException.class)
	              // Kiểm tra điều kiện
	              .until(new Function<WebElement, Boolean>() {
	                  public Boolean apply(WebElement element) {
	                             // Kiểm tra điều kiện countdount = 00
	                             boolean flag =  element.getText().endsWith("00");
	                             System.out.println("Time = " +  element.getText());
	                             // return giá trị cho function apply
	                             return flag;
	                  }
	             });
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

}
