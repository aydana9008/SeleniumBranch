package com.sauceLabPractice;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class ZohoAppSauceLabTest {

	public static final String USERNAME = "msmith_batch9"; //aydana_9008
    public static final String ACCESS_KEY = "aef7c37f-f086-4a5d-ad2c-62c33a18b57b";  //
    public static final String URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";
	
    
    
	RemoteWebDriver driver = null;
	final static String excelLocation = "C:\\Users\\aydan\\OneDrive\\Desktop\\C_tek\\Selenium\\testingMaven\\mavenProject-TRUNK\\src\\test\\java\\com\\sauceLabPractice\\ZohoData.xlsx";
	Xls_Reader data = new Xls_Reader(excelLocation);
    private Logger log = null;
	final static String path = "C:\\Users\\aydan\\git\\SeleniumBranch\\src\\test\\java\\com\\sauceLabPractice\\zohoApp.properties"; 
	Properties prop = new Properties();
	
	
	
	@BeforeTest
	public void invokeBrowser() throws IOException {
		DesiredCapabilities caps = DesiredCapabilities.chrome();
		caps.setCapability("platform", "Windows 10");
		caps.setCapability("version", "61.0");
		driver = new RemoteWebDriver(new URL(URL), caps);
		
		FileInputStream ip = new FileInputStream(path);
		prop.load(ip);
		String url = prop.getProperty("url");
		
	
		log = (Logger) LogManager.getLogger(ZohoAppSauceLabTest.class.getName());
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		log.info("Opening the firefox on MAC");
	}
	
	@AfterTest
	public void closeBrowser() {
		log.debug("Closing browser succesfully");
		driver.quit();
	}
	
	@Test
	public void extendResults() {
	  Select select = new Select(driver.findElement(By.id("recPerPage")));
	  select.selectByVisibleText("100");
	  log.info("Choosing option 100 from dropdown");
	}
	
	@Test
	public void transferDataIntoExcelFile() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#reportTab>tbody>:nth-child(15)")));
		
		List<WebElement> keyValue = driver.findElements(By.cssSelector("#reportTab>tbody>tr>:nth-child(1)"));
		List<WebElement> fullName = driver.findElements(By.cssSelector("#reportTab>tbody>tr>:nth-child(2)"));
		List<WebElement> email = driver.findElements(By.cssSelector("#reportTab>tbody>tr>:nth-child(3)"));
		List<WebElement> phone = driver.findElements(By.cssSelector("#reportTab>tbody>tr>:nth-child(4)"));
		List<WebElement> annualSalary = driver.findElements(By.cssSelector("#reportTab>tbody>tr>:nth-child(5)"));
	for(int i = 0; i < keyValue.size(); i++) {
		ZipSecureFile.setMinInflateRatio(0.009);
        String key_code = keyValue.get(i).getText();
		data.setCellData("Sheet1", "Key code", i+2, key_code);
		String full_name = fullName.get(i).getText();
		data.setCellData("Sheet1", "Fullname", i+2, full_name);
		String email_address = email.get(i).getText();
		data.setCellData("Sheet1", "Email", i+2, email_address);
		String phone_number = phone.get(i).getText();
		data.setCellData("Sheet1", "Phone", i+2, phone_number);
		String annual_salary = annualSalary.get(i).getText();
		data.setCellData("Sheet1", "annual_salary", i+2, annual_salary);
		log.info("Key number: "+key_code+"; Fullname: "+full_name+"; email: "+email_address+"; phone: "+phone_number+"; salary: "+annual_salary);
	    }
	
	}
}
