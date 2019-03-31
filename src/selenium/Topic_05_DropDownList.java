package selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_05_DropDownList {
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
  @Test (enabled = false)
  public void TC_01_DefaultDropdownList() {
//	  Step 01 - Truy cập vào trang: https://daominhdam.github.io/basic-form/index.html
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
//	  Step 02 - Kiểm tra dropdown Job Role 01 không hỗ trợ thuộc tính multi-select
	  WebElement optionList = driver.findElement(By.xpath("//select[@id='job1']"));
	  Select defaultDropdown = new Select(optionList);
	  Assert.assertFalse(defaultDropdown.isMultiple());
	  	  
//	  Step 03 - Chọn giá trị Automation Tester trong dropdown bằng phương thức selectVisible
	  defaultDropdown.selectByVisibleText("Automation Tester");
	  
//	  Step 04 - Kiểm tra giá trị đã được chọn thành công
	  Assert.assertEquals(defaultDropdown.getFirstSelectedOption().getText(), "Automation Tester");
//	  Step 05 - Chọn giá trị Manual Tester trong dropdown bằng phương thức selectValue
	  defaultDropdown.selectByValue("manual");
	  
//	  Step 06 - Kiểm tra giá trị đã được chọn thành công
	  Assert.assertEquals(defaultDropdown.getFirstSelectedOption().getText(), "Manual Tester");

//	  Step 07 - Chọn giá trị Mobile Tester trong dropdown bằng phương thức selectIndex
	  defaultDropdown.selectByIndex(3);

//	  Step 08 - Kiểm tra giá trị đã được chọn thành công
	  Assert.assertEquals(defaultDropdown.getFirstSelectedOption().getText(), "Mobile Tester");

//	  Step 09 - Kiểm tra dropdown có đủ 5 giá trị
	  Assert.assertEquals(defaultDropdown.getOptions().size(), 5);
	  
  }
  @Test (enabled = false)
  public void TC_02_CustomDropdownList() throws Exception {
//	  Step 01 - Truy cập vào trang: http://jqueryui.com/resources/demos/selectmenu/default.html
	  driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
//	  Step 02 - Chọn item cuối cùng: số 19
	  selectDropdownListCustom("//span[@id='number-button']", "//div[@class='ui-selectmenu-menu ui-front ui-selectmenu-open']//li[@class='ui-menu-item']/div", "19");
//	  Step 03 - Kiểm tra item đã được chọn thành công
	  Assert.assertTrue(isElementDisplayedInDropdown("//span[@id='number-button']//span[@class='ui-selectmenu-text']", "19"));
  
	  selectDropdownListCustom("//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']//div", "1");
	  Assert.assertTrue(isElementDisplayedInDropdown("//span[@id='number-button']//span[@class='ui-selectmenu-text']", "1"));
	  
	  selectDropdownListCustom("//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']//div", "14");
	  Assert.assertTrue(isElementDisplayedInDropdown("//span[@id='number-button']//span[@class='ui-selectmenu-text']", "14"));
  
  }
  
  @Test (enabled = false)
  public void TC_03_angularDropdown() throws Exception {
	  driver.get("https://material.angular.io/components/select/examples");
	  
	  selectDropdownListCustom("//div[@class='mat-form-field-flex']//mat-select[@id='mat-select-5']//div[@class='mat-select-trigger']", "//div[@class='ng-tns-c21-18 ng-trigger ng-trigger-transformPanel mat-select-panel mat-primary']//mat-option", "Hawaii");
	  Assert.assertTrue(isElementDisplayedInDropdown("//div[@class='mat-form-field-flex']//mat-select[@id='mat-select-5']//div[@class='mat-select-trigger']", "Hawaii"));
  }
  
  @Test
  public void TC_04_kendoDropdown() throws Exception {
	  driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");
	  selectDropdownListCustom("//div[@id='cap-view']//label[contains(text(),'Cap Color')]/parent::h4/following-sibling::span[1]","//ul[@id='color_listbox']//li", "Orange");
	  Assert.assertTrue(isElementDisplayedInDropdown("//div[@id='cap-view']//label[contains(text(),'Cap Color')]/parent::h4/following-sibling::span[1]", "Orange"));
	  checkColorOfTheCap("Orange");
	  
	  selectDropdownListCustom("//div[@id='cap-view']//label[contains(text(),'Cap Color')]/parent::h4/following-sibling::span[1]","//ul[@id='color_listbox']//li", "Grey");
	  Assert.assertTrue(isElementDisplayedInDropdown("//div[@id='cap-view']//label[contains(text(),'Cap Color')]/parent::h4/following-sibling::span[1]", "Grey"));
	  checkColorOfTheCap("Grey");
	  
	  selectDropdownListCustom("//div[@id='cap-view']//label[contains(text(),'Cap Color')]/parent::h4/following-sibling::span[1]","//ul[@id='color_listbox']//li", "Black");
	  Assert.assertTrue(isElementDisplayedInDropdown("//div[@id='cap-view']//label[contains(text(),'Cap Color')]/parent::h4/following-sibling::span[1]", "Black"));
	  checkColorOfTheCap("Black");
	  
  }
  
  public boolean isElementDisplayedInDropdown(String xpathDropdown, String expectedValue) {
	  if(driver.findElement(By.xpath(xpathDropdown)).getText().equals(expectedValue)){
		  return true;
	  } else {
		  return false;
	  }
	  
  }
  
  public boolean checkColorOfTheCap(String expectedValue) {
	  if(driver.findElement(By.xpath("//div[@id='cap']")).getCssValue("class").contains(expectedValue)) {
		  return true;
	  } else {
		  return false;
	  }
  }
  
  public void selectDropdownListCustom(String xpathParent, String items, String expectedValue) throws Exception
  {
	  //locate the custom dropdown
	  WebElement dropdown = driver.findElement(By.xpath(xpathParent));	  
	  js.executeScript("arguments[0].click();", dropdown);
	  
	  //wait until all option are loaded
	  waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(items)));
	  
	  List<WebElement> listOptions = driver.findElements(By.xpath(items));

	  //find the desired element
	  for (WebElement a : listOptions) {
		  if(a.getText().equals(expectedValue)) {
			  js.executeScript("arguments[0].scrollIntoView(true);", a);
			  
			  Thread.sleep(1500);
			  if(a.isDisplayed()) {
				  a.click();
			  } else {
			  js.executeScript("arguments[0].click();", a);
			  }
			  break;
		  }
		
	}
	  
  }
  
  @BeforeTest
  public void beforeTest() {

	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  waitExplicit = new WebDriverWait(driver, 10);
	  js = (JavascriptExecutor) driver;
  }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
