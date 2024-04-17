package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
	WebDriver driver;
	public DashboardPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//h6[text()='Dashboard']")
	private WebElement text_Dashboard;
	
	public boolean dashboardDisplayed() {
		boolean b = text_Dashboard.isDisplayed();
		return b;
	}
	
}
