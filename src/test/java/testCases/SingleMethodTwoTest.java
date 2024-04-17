package testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.DashboardPage;
import pageObjects.LoginPage;
import resources.Base;

public class SingleMethodTwoTest extends Base {
	public WebDriver driver;
	@Test(dataProvider ="getLoginData",priority=1 )
public void loginwithValidandInvalidCredentials(String userName, String password, String status) throws InterruptedException {
		
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.sendUsername(userName);
		loginPage.sendPassword(password);
		loginPage.clickLogin();
		Thread.sleep(3000);
		DashboardPage dbPage = new DashboardPage(driver);
		
		String acTualStatus = "null";
		try {
			if(dbPage.dashboardDisplayed()) {
				acTualStatus = "Success";
			}
			
		}catch (Exception e){
			acTualStatus = "Fail";
		}
		
		Assert.assertEquals(acTualStatus, status);
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
