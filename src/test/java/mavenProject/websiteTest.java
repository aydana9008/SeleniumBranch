package mavenProject;

import org.testng.annotations.Test;
import org.testng.Assert;
import java.util.concurrent.TimeUnit;




import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;



public class websiteTest {
	WebDriver driver = null;
	
	  @BeforeTest
		public void invokeBrowser() {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.get("https://www.dice.com");
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.manage().window().maximize();
				  String actualTitle = driver.getTitle();
				  String expectedTitle = "Find Jobs in Tech | Dice.com";
				Assert.assertEquals(expectedTitle, actualTitle);
				 
			  
		}
	 @AfterTest
	  public void closeBrowser() {
		  driver.quit();
	  }
	
 
  
  @Test(dataProvider="getData")
  public void findTechJobs(String jobName, String location) {
	  WebElement searchJob = driver.findElement(By.id("search-field-keyword"));
	  searchJob.clear();
	  searchJob.sendKeys(jobName);
	  WebElement searchLocation =  driver.findElement(By.id("search-field-location"));
	  searchLocation.clear();
	  searchLocation.sendKeys(location);
	  driver.findElement(By.id("findTechJobs")).click();
	  String resultText = driver.findElement(By.id("posiCountId")).getText();
	  resultText=resultText.replaceAll(",", "");
      int result = Integer.parseInt(resultText);
	  Assert.assertTrue(result>1000);
      driver.findElement(By.className("logo")).click();
      
  }
  
 @DataProvider
  public String[][] getData(){
 	String[][] data = new String[3][2];
 	data[0][0] = "Java Developer";
 	data[0][1] = "PA";
 	data[1][0] = "JavaScript Developer";
 	data[1][1] = "PA";
 	data[2][0] = "Ruby Developer";
 	data[2][1] = "PA";
 	return data;
  }

}


