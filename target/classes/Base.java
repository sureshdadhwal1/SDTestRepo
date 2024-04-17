package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	public WebDriver driver;
	public Properties prop;
	
	
	
	
	
	
	
	public WebDriver initilizeDriver() throws IOException {
		prop = new Properties();
		String propPath = System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties";
		FileInputStream fis = new FileInputStream(propPath);
		prop.load(fis);
		
		String browser = prop.getProperty("browser");
		
		if(browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else if(browser.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
		
		
		
		
	}
	public String takeScreenshot(String testName, WebDriver driver) throws IOException {
		TakesScreenshot srcShot = ((TakesScreenshot)driver);
		File sourceFile = srcShot.getScreenshotAs(OutputType.FILE);
		String destinationFilePath = System.getProperty("user.dir")+"\\screenshots\\"+testName+".png";
		FileUtils.copyFile(sourceFile, new File(destinationFilePath));
		return destinationFilePath;
	}
	

}
