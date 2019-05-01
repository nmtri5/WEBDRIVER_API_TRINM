package selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_11_UploadFile {
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor js;

	// Get root folder
	String root = System.getProperty("user.dir");
	String file1 = "image01.JPG";
	String file2 = "image02.jpg";
	String file3 = "image03.JPG";

	String path1 = root + "\\files\\" + file1;
	String path2 = root + "\\files\\" + file2;
	String path3 = root + "\\files\\" + file3;

	String[] files = { path1, path2, path3 };

	@Test
	public void TC_01_uploadMultipleFileWithSendKeyQueue() throws InterruptedException {
		waitExplicit = new WebDriverWait(driver, 10);

		driver.get("http://blueimp.github.com/jQuery-File-Upload/");

		if (driver.toString().contains("firefox") || driver.toString().contains("chrome")) {
			for (String a : files) {
				System.out.println("Using firefox or Chrome");
				WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
				uploadFile.sendKeys(a);
				waitExplicit.until(ExpectedConditions
						.invisibilityOfElementLocated(By.xpath("//div[@class='progress-bar progress-bar-success']")));

			}
		} else {
			for (String a : files) {
				System.out.println("Using IE");
				WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
				uploadFile.sendKeys(a);
				waitExplicit.until(ExpectedConditions
						.invisibilityOfElementLocated(By.xpath("//div[@class='progress-bar progress-bar-success']")));
			}
		}
		
		driver.findElement(By.xpath("//span[text()='Start upload']")).click();
		waitExplicit.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[@class='progress-bar progress-bar-success']")));

		Thread.sleep(5000);
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + file1 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + file2 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + file3 + "']")).isDisplayed());

	}

	@Test
	public void TC_02_uploadMultipleFileWithSendKeyMultiple() throws InterruptedException {
		waitExplicit = new WebDriverWait(driver, 10);

		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		WebElement uploadFile = driver.findElement(By.cssSelector(".fileinput-button"));
		uploadFile.sendKeys(path1 + "\n" + path2 + "\n" + path3);

		List<WebElement> uploadBtn = driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));

		for (WebElement upload : uploadBtn) {
			waitExplicit.until(ExpectedConditions.elementToBeClickable(upload));
			clickToElementByJS(driver, upload);
			waitExplicit.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath("//div[@class='progress-bar progress-bar-success']")));
		}

		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + file1 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + file2 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + file3 + "']")).isDisplayed());

	}

	public void TC_03_UploadUsingAutoIT() {
		waitExplicit = new WebDriverWait(driver, 5);

		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(path1 + "\n" + path2 + "\n" + path3);

		List<WebElement> uploadBtn = driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));

		for (WebElement upload : uploadBtn) {
			waitExplicit.until(ExpectedConditions.elementToBeClickable(upload));
			upload.click();
			waitExplicit.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath("//div[@class='progress-bar progress-bar-success']")));
		}

		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + file1 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + file2 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + file3 + "']")).isDisplayed());
	}

	@BeforeTest
	public void beforeTest() {
	  System.setProperty("webdriver.chrome.driver",".\\lib\\chromedriver.exe");
	  driver = new ChromeDriver();

//	  System.setProperty("webdriver.gecko.driver",".\\lib\\geckodriver.exe");
//	  driver = new FirefoxDriver();

//		System.setProperty("webdriver.ie.driver", ".\\lib\\IEDriverServer.exe");
//		driver = new InternetExplorerDriver();
//		driver = new EdgeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

	public Object clickToElementByJS(WebDriver driver, WebElement element) {
		js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].click();", element);
	}
}
