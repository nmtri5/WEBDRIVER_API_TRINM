package selenium;

import java.util.List;
import java.util.Set;
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

public class Topic_09_HandleWindowTabs {
	WebDriver driver;
	
	  @BeforeTest
	  public void beforeTest() {
		  driver = new FirefoxDriver();	
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  driver.manage().window().maximize();
		  
	  }
	  
  @Test (enabled = false)
  public void TC_01_OpenNewWindowTab() {
//	  	  Step 01 - Truy cập vào trang: https://daominhdam.github.io/basic-form/index.html
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  
//		  Step 02 - Click "Opening a new window: Click Here" link -> Switch qua tab mới
	  driver.findElement(By.xpath("//label[@for='open window']//following-sibling::a[text()='Click Here']")).click();
	  
//		  Step 03 - Kiểm tra title của window mới = Google
	  String parentID = driver.getWindowHandle();
	  switchToChildWindow(parentID);
	  Assert.assertEquals(driver.getTitle(), "Google");
	  
//		  Step 04 - Close window mới	  
//		  Step 05 - Switch về parent window
	  Assert.assertTrue(closeAllExceptParentWindows(parentID));	  
	  
//		  Step 06 - Kiểm tra đã quay về parent window thành công (title/ url)
	  Assert.assertEquals(driver.getTitle(),"SELENIUM WEBDRIVER FORM DEMO");
	  Assert.assertEquals(driver.getCurrentUrl(),"https://daominhdam.github.io/basic-form/index.html");
  }
  
  public void TC_02_OpenNewTab()
  {  
//	  	  Step 01 - Truy cập vào trang: http://www.hdfcbank.com/
	  driver.get("http://www.hdfcbank.com/");
	  
//		  Step 02 - Kiểm tra và close quảng cáo nếu có xuất hiện
	  List<WebElement> popup = driver.findElements(By.xpath("//div[@id = 'parentdiv']//img[@class='popupbanner at-element-click-tracking']"));
	  if(popup.size() > 0) {
		  
		  JavascriptExecutor js = (JavascriptExecutor) driver;
		  WebElement closePopup = driver.findElement(By.xpath("//div[@id = 'parentdiv']//img[@class='popupCloseButton']"));
		  js.executeScript("arguments[0].click();", closePopup);

	  }
	  
//		  Step 03 - Click Angri link -> Mở ra tab/window mới -> Switch qua tab mới
	  String parentID = driver.getWindowHandle();
	  driver.findElement(By.xpath("//div[@class='sectionnav']//li/a[text() = 'Agri']")).click();
	  switchToChildWindow(parentID);
	  
//	      Step 04 - Click Account Details link -> Mở ra tab/window mới -> Switch qua tab mới
	  	  
	  driver.findElement(By.xpath("//ul[@class='grid_list clearfix']//p[text()='Account Details']")).click();
	  switchToWindowByTitle("Welcome to HDFC Bank NetBanking");
	  
//	      Step 05- Click Privacy Policy link (nằm trong frame) -> Mở ra tab/window mới -> Switch qua tab mới
	  driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='footer']")));
	  WebElement linkPolicy = driver.findElement(By.xpath("//a[text() ='Privacy Policy']"));
	  linkPolicy.click();
	  
	  
//	  	  Step 06- Click CSR link on Privacy Policy page
	  driver.switchTo().defaultContent();
	  switchToWindowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
	  driver.findElement(By.xpath("//div[@class='hygeinenav']//a[text()='CSR']")).click();
	  
//	  Step 07 - Close tất cả windows/ tabs khác - chỉ giữ lại parent window (http://www.hdfcbank.com/)
//		  Back về parent windows
	  closeAllExceptParentWindows(parentID);
	  Assert.assertEquals(driver.getTitle(), "HDFC Bank: Personal Banking Services");
  }
 
@Test  
public void TC_03_AddToCompare() {
//		Step 01 - Truy cập vào trang: http://live.guru99.com/index.php/
	driver.get("http://live.guru99.com/index.php/");
	
//		Step 02 - Click vào Mobile tab
	driver.findElement(By.xpath("//div[@id='header-nav']//a[text()='Mobile']")).click();
	
//		Step 03 - Add sản phẩm Sony Xperia vào để Compare (Add to Compare)
	driver.findElement(By.xpath("//h2[@class='product-name']/a[text()='Sony Xperia']//parent::h2//following-sibling::div[@class='actions']/ul//a[text()='Add to Compare']")).click();
	Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(), "The product Sony Xperia has been added to comparison list.");
	
//		Step 04 - Add sản phẩm Samsung Galaxy vào để Compare (Add to Compare)
	driver.findElement(By.xpath("//h2[@class='product-name']/a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']/ul//a[text()='Add to Compare']")).click();
	Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(), "The product Samsung Galaxy has been added to comparison list.");
	List<WebElement> compareList = driver.findElements(By.xpath("//ol[@id='compare-items']/li"));
	Assert.assertEquals(compareList.size(), 2);
	
//		Step 05 - Click to Compare button
	driver.findElement(By.xpath("//div[@class='block-content']//div[@class='actions']/button[@title = 'Compare']")).click();

//		Step 06 - Switch qa cửa sổ mới (chứa 2 sản phẩm đã được Add vào để Compare)
	String parentID = driver.getWindowHandle();
	switchToChildWindow(parentID);
	String newTabTitle = driver.getTitle();
	System.out.println("Active tab title: " + newTabTitle);
	
//		Step 07 - Verify title của cửa sổ bằng: Products Comparison List - Magento Commerce
	Assert.assertEquals(newTabTitle, "Products Comparison List - Magento Commerce");
	
//		Step 08 - Close tab và chuyển về Parent Window
	closeAllExceptParentWindows(parentID);
	Assert.assertEquals(driver.getTitle(), "Mobile");
}
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }
  
  public void switchToChildWindow(String parent) {
	  Set<String> allWindows = driver.getWindowHandles();
	  for (String runWindow : allWindows) {
		  if (!runWindow.equals(parent)) {
			  driver.switchTo().window(runWindow);
			  break;
		  }
	  }
  }
  
  public void switchToWindowByTitle(String title) {
      Set<String> allWindows = driver.getWindowHandles();
      for (String runWindows : allWindows) {
                  driver.switchTo().window(runWindows);
                  String currentWin = driver.getTitle();
                  if (currentWin.equals(title)) {
                              break;
                  }
      }
}

  public boolean closeAllExceptParentWindows(String parentWindow) {
              Set<String> allWindows = driver.getWindowHandles();
              for (String runWindows : allWindows) {
                          if (!runWindows.equals(parentWindow)) {
                                      driver.switchTo().window(runWindows);
                                      driver.close();
                          }
              }
              driver.switchTo().window(parentWindow);
              if (driver.getWindowHandles().size() == 1)
                         return true;
              else
                         return false;
  }
  
}
