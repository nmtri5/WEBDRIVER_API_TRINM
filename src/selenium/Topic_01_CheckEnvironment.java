package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_01_CheckEnvironment {
	
	WebDriver driver;
	
  @Test
  public void TC_01_CheckUrl() {
	  String homePageUrl = driver.getCurrentUrl();
	  Assert.assertEquals(homePageUrl, "http://live.guru99.com/");
  }
  
  @Test
  public void TC_02_CheckTitle(){
	  String homePageTitle = driver.getTitle();
	  Assert.assertEquals(homePageTitle, driver.getTitle());
  }
  @BeforeTest
  public void beforeTest() {
	  System.setProperty("webdriver.chrome.driver","D:\\Automation Test\\chromedriver\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  driver.get("http://live.guru99.com/");
	  //test3
  }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
