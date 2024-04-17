package testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import resources.Base;

public class SingleMethodTest extends Base{
	public WebDriver driver;
	
	@Test
	public void accessForgotPasswordScreen() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickForgotPassword();
		loginPage.clickCancelButton();
		Assert.assertTrue(loginPage.userNameField().isSelected());
	}
	
	@DataProvider
	public Object[][] getLoginData() {
		return new Object [][] {{"Admin","admin123","Success"},{"Admin","admin1234","Fail"},{"Admin","Admin123","Fail"}};
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	@BeforeMethod
	public void openBrowser() throws IOException {
		driver = initilizeDriver();
		driver.get(prop.getProperty("url"));
	}

}
