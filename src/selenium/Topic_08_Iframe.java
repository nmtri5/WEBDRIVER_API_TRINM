package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_08_Iframe {
	WebDriver driver;
  @Test
  public void TC_01_Iframe() {
	  driver.get("http://www.hdfcbank.com/");
	  
	  //WebElement iframe = driver.findElement(By.xpath(""));
	  
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
