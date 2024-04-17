package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(name="username")
	private WebElement userNameField;
	
	public void sendUsername(String userName) {
		userNameField.sendKeys(userName);
	}
	public WebElement userNameField() {
		return userNameField;
	}
	
	@FindBy(name="password")
	private WebElement passwordField;
	
	public void sendPassword(String password) {
		passwordField.sendKeys(password);
	}
	@FindBy(tagName="button")
	private WebElement loginInButton;
	
	public void clickLogin() {
		loginInButton.click();
	}
	@FindBy(xpath="//p[text()='Invalid credentials']") private WebElement alert;
	public String alertMessageText() {
		return alert.getText();
	}
	@FindBy(xpath="//p[@class='oxd-text oxd-text--p orangehrm-login-forgot-header']") private WebElement forgotPasswordLink;
	public void clickForgotPassword() {
		forgotPasswordLink.click();
	}
	@FindBy(xpath="//button[@type='button']") private WebElement cancelButton;
	public void clickCancelButton() {
		cancelButton.click();
	}

}
