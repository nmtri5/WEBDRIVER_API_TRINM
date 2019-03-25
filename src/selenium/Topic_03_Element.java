package selenium;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_03_Element {
	WebDriver driver;
	
	
	//enabled elements
	By emailTextBox = By.xpath("//input[@id='mail']");
	By educationTextBox = By.xpath("//textarea[@id='edu']");
	By radioBox = By.xpath("//input[@id='under_18']");
	By jobSelection = By.xpath("//select[@id='job1']");
	By interestCheckBox = By.xpath("//input[@id='development']");
	By inputSlider = By.xpath("//input[@id='slider-1']");
	By buttonButton = By.xpath("//button[@id='button-enabled']");
	
	//disabled elements
	By passwordTextBox = By.xpath("//input[@id='password']");
	By ageRadioBox = By.xpath("//input[@id='radio-disabled']");
	By bioTextArea = By.xpath("//textarea[@id='bio']");
	By jobSelectionDisabled = By.xpath("//select[@id='job2']");
	By interestCheckBoxDisabled = By.xpath("//input[@id='check-disbaled']");
	By inputSliderDisabled = By.xpath("//input[@id='slider-2']");
	By buttonButtonDisabled = By.xpath("//button[@id='button-disabled']");
	
	
  @Test(enabled = false)
  public void TC_01_checkElementDisplayed(){
//	  Step 02 - Kiểm tra các phần tử sau hiển thị trên trang: Email/ Age (Under 18)/ Education	  
//	  Step 03 - Nếu có nhập giá trị: Automation Testing vào 2 field Email/ Education và chọn Age = Under 18
	  if(isDisplayed(emailTextBox)) {
		  driver.findElement(emailTextBox).sendKeys("Automation Testing");
	  }
	  if(isDisplayed(educationTextBox)) {
		  driver.findElement(educationTextBox).sendKeys("Automation Testing");
	  }
	  if(isDisplayed(radioBox)) {
		  driver.findElement(radioBox).click();
	  }
  }
  
  @Test(enabled = false)
  public void TC_02_checkElementDisplayed(){
//	  Step 02 -Kiểm tra các phần tử sau enable trên trang: Email/ Age (Under 18)/ 
//	  Education/ Job Role 01/ Interests (Development)/ Slider 01/ Button is enabled
//	  Step 04 - Nếu có in ra Element is enabled/ ngược lại Element is disabled
	  Assert.assertTrue(isEnabled(emailTextBox));
	  Assert.assertTrue(isEnabled(educationTextBox));
	  Assert.assertTrue(isEnabled(radioBox));
	  Assert.assertTrue(isEnabled(jobSelection));
	  Assert.assertTrue(isEnabled(interestCheckBox));
	  Assert.assertTrue(isEnabled(inputSlider));
	  Assert.assertTrue(isEnabled(buttonButton));
	  
//	  Step 03 - Kiểm tra các phần tử sau disable trên trang: Password / Age (Radiobutton is disabled)/ Biography/ Job Role 02/ Interests (Checkbox is disabled)/ Slider 02/ Button is disabled
//	  Step 04 - Nếu có in ra Element is enabled/ ngược lại Element is disabled
	  Assert.assertTrue(isDisabled(passwordTextBox));
	  Assert.assertTrue(isDisabled(ageRadioBox));
	  Assert.assertTrue(isDisabled(bioTextArea));
	  Assert.assertTrue(isDisabled(jobSelectionDisabled));
	  Assert.assertTrue(isDisabled(interestCheckBoxDisabled));
	  Assert.assertTrue(isDisabled(inputSliderDisabled));
	  Assert.assertTrue(isDisabled(buttonButtonDisabled));

  }
  
  @Test
  public void TC_03_checkSelectedElement() {
	  //Step 02 - Click chọn Age (Under 18)/ Interests (Development)
	  driver.findElement(radioBox).click();
	  driver.findElement(interestCheckBox).click();

	  //Step 03 - Kiểm tra các phần tử tại Step 02 đã được chọn
	  Assert.assertTrue(driver.findElement(radioBox).isSelected());
	  Assert.assertTrue(driver.findElement(interestCheckBox).isSelected());
	  //Step 04 - Click để bỏ chọn Interests (Development) checkbox
	  WebElement a = driver.findElement(interestCheckBox);
	  a.click();
	  //Step 05 - Kiểm tra các phần tử tại Step 05 đã được bỏ chọn
	  Assert.assertFalse(a.isSelected());
	  
  }
  
  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  //Step 01 - Truy cập vào trang: https://daominhdam.github.io/basic-form/index.html
	  driver.get("https://daominhdam.github.io/basic-form/index.html");	  
  }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

  public boolean isDisplayed(By byValue) {
	return driver.findElement(byValue).isDisplayed();
	  
  }
  
  public boolean isEnabled(By byValue) {
	  if(driver.findElement(byValue).isEnabled()) {
		  System.out.println("Element " + byValue + "is enabled");
		  return true;
	  } else {
		  return false;
	  }
  }
  public boolean isDisabled(By byValue) {
	  if(!driver.findElement(byValue).isEnabled()) {
		  System.out.println("Element " + byValue + "is not enabled");
		  return true;
	  } else {
		  return false;
	  }
  }
}
