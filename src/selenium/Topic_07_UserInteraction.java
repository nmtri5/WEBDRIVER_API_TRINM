package selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_07_UserInteraction {
	WebDriver driver;
	JavascriptExecutor js;
	Actions action;

	@Test (enabled = false)
	public void TC_01_MoveToElement() {
//	  	Step 01 - Truy cập vào trang: http://www.myntra.com/
		driver.get("http://www.myntra.com/");

//		Step 02 - Hover chuột vào Menu để login
		WebElement userIcon = driver.findElement(By.xpath("//div[@class='desktop-actions']//span[contains(@class, 'desktop-iconUser')]"));
		action.moveToElement(userIcon).perform();;
		
//		Step 03 - Chọn Login button
		WebElement loginBtn = driver.findElement(By.xpath("//div[@class='desktop-userActions']//a[text() = 'log in']"));
		action.click(loginBtn).perform();;
		
//		Step 04 - Verify Login form được hiển thị
		WebElement loginBox = driver.findElement(By.xpath("//div[@class='login-box']"));
		Assert.assertTrue(loginBox.isDisplayed());
	}
	
	@Test (enabled = false)
	public void TC_02_ClickAndHoldSelectMultiple() {
		
//		Step 01 - Truy cập vào trang: http://jqueryui.com/resources/demos/selectable/display-grid.html
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		
//		Step 02 - Click and hold từ 1-> 4
		WebElement number1 = driver.findElement(By.xpath("//ol[@id='selectable']//li[text() = '1']"));		
		WebElement number4 = driver.findElement(By.xpath("//ol[@id='selectable']//li[text() = '4']"));	
		
		action.dragAndDrop(number1, number4).perform();
		
//		Step 03 - Sau khi chọn kiểm tra rằng có đúng 4 phần tử đã được chọn thành công với xpath:
//			//li[@class='ui-state-default ui-selectee ui-selected']
		List <WebElement> list = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		Assert.assertEquals(list.size(), 4);
		
	}
	
	@Test (enabled = false)
	public void TC_02_ClickAndHoldSelectRandomMultiple() {
		
//		Step 01 - Truy cập vào trang: http://jqueryui.com/resources/demos/selectable/display-grid.html
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		
//		Step 02 - Click random bất kì
		List<WebElement> listNumber = driver.findElements(By.xpath("//ol[@id='selectable']//li"));				

		action.keyDown(Keys.CONTROL).perform();
		action.click(listNumber.get(0));		
		action.click(listNumber.get(4));
		action.click(listNumber.get(2));
		action.click(listNumber.get(7));
		action.keyUp(Keys.CONTROL).perform();
		
		
//		Step 03 - Sau khi chọn kiểm tra rằng có đúng 4 phần tử đã được chọn thành công với xpath:
//			//li[@class='ui-state-default ui-selectee ui-selected']
		List <WebElement> list = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		Assert.assertEquals(list.size(), 4);
		
	}
	
	@Test (enabled = false)
	public void TC_03_DoubleClick() {
//		Step 01 - Truy cập vào trang: http://www.seleniumlearn.com/double-click
		driver.get("http://www.seleniumlearn.com/double-click");
		
//		Step 02 - Double click vào element: Double-Click Me!
		WebElement doubleClick = driver.findElement(By.xpath("//div[@class='field-items']//button[text() = 'Double-Click Me!']"));	
		action.doubleClick(doubleClick).perform();;
		
//		Step 03 - Verify text trong alert được hiển thị: 'The Button was double-clicked.'
		String verify = driver.switchTo().alert().getText();
		Assert.assertEquals(verify, "The Button was double-clicked.");
		
//		Step 04 - Accept Javascript alert
		driver.switchTo().alert().accept();
	}
	
	@Test (enabled = false)
	public void TC_04_RightClick() {
		
//		Step 01 - Truy cập vào trang: http://swisnl.github.io/jQuery-contextMenu/demo.html
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
//		Step 02 - Right click vào element: right click me
		WebElement rightClick = driver.findElement(By.xpath("//span[contains(@class, 'context-menu-one') and text() = 'right click me']"));
		action.contextClick(rightClick).perform();
		
//		Step 03 - Hover chuột vào element: Quit
		WebElement quitElement = driver.findElement(By.xpath("//ul[contains(@class, 'context-menu-list')]//span[text() = 'Quit']"));
		action.moveToElement(quitElement).perform();
		
//		Step 04 - Verify element Quit (visible + hover) với xpath:
		//li[contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]/span[text()='Quit']
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]/span[text()='Quit']")).isDisplayed());
		
//		Step 05 - Click chọn Quit
		action.click(quitElement).perform();
		
//		Step 06 - Accept Javascript alert
		driver.switchTo().alert().accept();

	}
	
	@Test
	public void TC_05_DragAndDrop() {
//		Step 01 - Truy cập vào trang: http://demos.telerik.com/kendo-ui/dragdrop/angular
		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
		
//		Step 02 - Kéo hình tròn nhỏ vào hình tròn lớn
		WebElement smallCircle = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement bigCircle = driver.findElement(By.xpath("//div[@id='droptarget']"));
		action.dragAndDrop(smallCircle, bigCircle).perform();
		
//		Step 03 - Verify message đã thay đổi: You did great!
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='droptarget' and text()='You did great!']")).getText(), "You did great!");
	}

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		js = (JavascriptExecutor) driver;
		action = new Actions(driver);

	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
