package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNG_06_DependencyTest {
	
	@Test(groups = "customer", description = "Test login function")
	public void TC_01() {
		System.out.println("Run TC 03");
	}
	@Test(groups = "customer", description = "Test register function", dependsOnMethods="TC_01")
	public void TC_02() {
		System.out.println("Run TC 02");
	}
	@Test(groups = "payment", dependsOnMethods="TC_02")
	public void TC_03() {
		System.out.println("Run TC 03");
	}
	@Test(groups = "register", dependsOnMethods="TC_03")
	public void TC_04() {
		System.out.println("Run TC 04");
		Assert.assertTrue(false);
	}
	@Test(groups = "order", dependsOnMethods="TC_04")
	public void TC_05() {
		System.out.println("Run TC 05");
	}
}