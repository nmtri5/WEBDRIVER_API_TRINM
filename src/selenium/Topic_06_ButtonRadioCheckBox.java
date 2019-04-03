package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_06_ButtonRadioCheckBox {

	WebDriver driver;
	JavascriptExecutor js;
	
	@Test(enabled = false)
	public void TC_01_ClickByJavascript() {
		
//		Step 01 - Truy cập vào trang: http://live.guru99.com/
		driver.get("http://live.guru99.com/");
		
//		Step 02 - Click vào link My Account dưới footer (Sử dụng Javascript Executor code)
		WebElement myAccount = driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));
		js.executeScript("arguments[0].click();", myAccount);
		
//		Step 03 - Kiểm tra url của page sau khi click là: http://live.guru99.com/index.php/customer/account/login/
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
		
//		Step 04 - Click vào button CREATE AN ACCOUNT (Sử dụng Javascript Executor code)
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Create an Account']")));
		
//		Step 06 - Kiểm tra url của page sau khi click là: http://live.guru99.com/index.php/customer/account/create/
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");

		
	}
	@Test (enabled = false)
	public void TC_02_TererikCheckBox() {
		
//		Step 01 - Truy cập vào trang: http://demos.telerik.com/kendo-ui/styling/checkboxes
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		
//		Step 02 - Click vào checkbox: Dual-zone air conditioning (Thẻ input ko được sử dụng thuộc tính id)
		WebElement checkBox = driver.findElement(By.xpath("//label[text() = 'Dual-zone air conditioning']/preceding-sibling::input"));
		checkToCheckBox(checkBox);
		
//		Step 03 - Kiểm tra checkbox đó đã chọn
		Assert.assertTrue(checkBox.isSelected());
		
//		Step 04 - Sau khi checkbox đã được chọn - deselect nó và kiểm tra nó chưa được chọn
		checkToCheckBox(checkBox);
		Assert.assertFalse(checkBox.isSelected());
	}
	
	@Test (enabled = false)
	public void TC_03_TelerikRadioBox() {
		
//		Step 01 - Truy cập vào trang: http://demos.telerik.com/kendo-ui/styling/radios
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		
//		Step 02 - Click vào radiobutton:  2.0 Petrol, 147kW (Thẻ input ko được sử dụng thuộc tính id)		
		WebElement radioBox = driver.findElement(By.xpath("//label[@class='k-radio-label' and text() = '2.0 Petrol, 147kW']/preceding-sibling::input"));
		checkToCheckBox(radioBox); 
		
//		Step 03 - Kiểm tra radio button đó đã chọn hay chưa/ nếu chưa chọn lại
		if(!radioBox.isSelected()) {
			checkToCheckBox(radioBox);
		}
		
		Assert.assertTrue(radioBox.isSelected());
		
	}
	
	@Test (enabled = false)
	public void TC_04_JSAlert() {
		
//		Step 01 - Truy cập vào trang: https://daominhdam.github.io/basic-form/index.html
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		
//		Step 02 - Click vào button: Click for JS Alert		
		WebElement jsButton = driver.findElement(By.xpath("//div[@class='example']//button[text() = 'Click for JS Alert']"));
		jsButton.click();
		
//		Step 03 - Verify message hiển thị trong alert là: I am a JS Confirm
		Alert a = driver.switchTo().alert();
		Assert.assertEquals(a.getText(), "I am a JS Alert");
		
//		Step 04 - Accept alert và verify message hiển thị tại Result là:  You clicked an alert successfully
		a.accept();
		String success = driver.findElement(By.id("result")).getText();
		Assert.assertEquals(success, "You clicked an alert successfully");	

	}
	
	@Test (enabled = false)
	public void TC_05_JSConfirm() {
		
//		Step 01 - Truy cập vào trang: https://daominhdam.github.io/basic-form/index.html
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		
//		Step 02 - Click vào button: Click for JS Alert		
		WebElement jsButton = driver.findElement(By.xpath("//div[@class='example']//button[text() = 'Click for JS Confirm']"));
		jsButton.click();
		
//		Step 03 - Verify message hiển thị trong alert là: I am a JS Confirm
		Alert a = driver.switchTo().alert();
		Assert.assertEquals(a.getText(), "I am a JS Confirm");
		
//		Step 04 - Cancel alert và verify message hiển thị tại Result là:  You clicked: Cancel
		a.dismiss();
		String resultCancel = driver.findElement(By.id("result")).getText();
		Assert.assertEquals(resultCancel, "You clicked: Cancel");
		
	}
	@Test (enabled = false)
	public void TC_06_JSConfirm() {
		String inputText = "nguyenminhtri";
//		Step 01 - Truy cập vào trang: https://daominhdam.github.io/basic-form/index.html
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		
//		Step 02 - Click vào button: Click for JS Prompt	
		WebElement jsButton = driver.findElement(By.xpath("//div[@class='example']//button[text() = 'Click for JS Prompt']"));
		jsButton.click();
		
//		Step 02 - Click vào button: Click for JS Prompt
		Alert a = driver.switchTo().alert();
		Assert.assertEquals(a.getText(), "I am a JS prompt");
		
//		Step 04 - Nhập vào text bất kì (daominhdam) và verify message hiển thị tại Result là:  You entered: daominhdam
		a.sendKeys(inputText);
		a.accept();
		String result = driver.findElement(By.id("result")).getText();
		Assert.assertEquals(result, "You entered: " + inputText);
		
	}
	
	@Test
	public void TC_07_herokuAuthentication() {

//		Step 01 - Truy cập vào trang: http://the-internet.herokuapp.com/basic_auth
//		Step 02 - Handle authentication alert vs user/pass: admin/ admin	
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
				
//		Step 03 - Verify message hiển thị sau khi login thành công:
//		Congratulations! You must have the proper credentials.
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		
	}

	public void checkToCheckBox (WebElement expectedValue) {
			
		if (expectedValue.isDisplayed()) {
			expectedValue.click();
		} else {
			js.executeScript("arguments[0].click();", expectedValue);
		}
	}
	
	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		js = (JavascriptExecutor) driver;
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
