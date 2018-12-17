package mavenProject;

import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


import io.github.bonigarcia.wdm.WebDriverManager;

public class OzzyTaskTest {
		WebDriver driver = null;
		Properties prop = new Properties();
        final String file = "C:\\Users\\aydan\\OneDrive\\Desktop\\C_tek\\Selenium\\testingMaven\\mavenProject\\src\\test\\java\\mavenProject\\task.properties";
		
		
	@BeforeTest
     public void invokeBrowser() throws IOException {
		FileInputStream is = new FileInputStream(file);
		prop.load(is);
         String url = prop.getProperty("url");
    	 WebDriverManager.chromedriver().setup();
    	 driver = new ChromeDriver();
    	 driver.get(url);
    	 driver.manage().window().maximize();
    	 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
     }
	
	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
	@Test
	public void Login() {
		String username = prop.getProperty("username");
		String pass = prop.getProperty("password");
		driver.findElement(By.name("userName")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(pass);
		driver.findElement(By.name("login")).click();
	}
}
