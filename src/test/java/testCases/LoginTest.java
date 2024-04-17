package testCases;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.DashboardPage;
import pageObjects.LoginPage;
import resources.Base;

public class LoginTest extends Base {
	public WebDriver driver;
	LoginPage loginPage;
	org.apache.logging.log4j.Logger log;
	
	
	
@BeforeMethod
	
	public void openBrowser() throws IOException {
	
		log = LogManager.getLogger(LoginTest.class.getName());
		driver = initilizeDriver();
		log.debug("Browser got launched");
		driver.get(prop.getProperty("url"));
		log.debug("Navigated to application URL");
	}
	
	
	@Test(dataProvider ="getLoginData",priority=1 )
	
	public void loginwithValidandInvalidCredentials(String userName, String password, String status){
		
		
		loginPage = new LoginPage(driver);
		loginPage.sendUsername(userName);
		loginPage.sendPassword(password);
		loginPage.clickLogin();
		log.debug("Clicked on login button");
		DashboardPage dbPage = new DashboardPage(driver);
		
		String acTualStatus = "null";
		try {
			if(dbPage.dashboardDisplayed()) {
				log.debug("User got logged in");
				acTualStatus = "Success";
			}
			
		}catch (Exception e){
			log.debug("User didn't log in");
			acTualStatus = "Fail";
		}
		
		Assert.assertEquals(acTualStatus, status);
	}
	
	@Test(dataProvider ="getIncorrectLoginData",priority=2)
	public void validateIncorrectUserName(String userName,String password ) {
		LoginPage loginPage = new LoginPage(driver);
		//String userName = "Suresh";
		//String password = "1234";
		loginPage.sendUsername(userName);
		loginPage.sendPassword(password);
		loginPage.clickLogin();
		String expectedMessage = "Invalid credentials";
		String actualMessage = loginPage.alertMessageText();
		Assert.assertEquals(expectedMessage, actualMessage, "Fail");
	}
	@Test(priority=3)
	public void accessForgotPasswordScreen() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickForgotPassword();
		loginPage.clickCancelButton();
		Assert.assertTrue(loginPage.userNameField().isDisplayed());
	}
	
	@DataProvider
	public Object[][] getLoginData() {
		return new Object [][] {{"Admin","admin123","Success"},{"Admin","admin1234","Fail"},{"Admin","Admin123","Fail"}};
	}
	
	@DataProvider
	public Object[][] getIncorrectLoginData() {
		return new Object [][] {{"suresh","1234"},{"Rakesh","1234"}};
	}
	
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	

}
