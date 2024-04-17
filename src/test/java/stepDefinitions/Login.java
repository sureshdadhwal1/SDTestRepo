package stepDefinitions;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.DashboardPage;
import pageObjects.LoginPage;
import resources.Base;

public class Login extends Base{
	WebDriver driver;
	LoginPage loginPage;
	DashboardPage dbPage;
	
	@Given("I am on login page of application")
	public void i_am_on_login_page_of_application() throws IOException {
		driver = initilizeDriver();
		driver.get(prop.getProperty("url"));
	}

	@And("I enter username {string}")
	public void i_enter_username(String userName) {
		loginPage = new LoginPage(driver);
		loginPage.sendUsername("Admin");
	}

	@And("I enter password {string}")
	public void i_enter_password(String password) {
		loginPage.sendPassword("admin123");
	}

	@When("I click on login button")
	public void i_click_on_login_button() {
		loginPage.clickLogin();
	}

	@Then("Dashboard screen is displayed")
	public void dashboard_screen_is_displayed() {
		dbPage = new DashboardPage(driver);
		boolean b = dbPage.dashboardDisplayed();
		Assert.assertTrue(b);
	}
	@After
	public void teardown() {
		driver.close();
		driver.quit();
	}
	



}
