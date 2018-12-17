package com.sauceLab;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class sauceLabTest {

	public static final String USERNAME = "msmith_batch9"; //aydana_9008
    public static final String ACCESS_KEY = "aef7c37f-f086-4a5d-ad2c-62c33a18b57b";  //
    public static final String URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";
	
	
	@Test
	public void login() throws MalformedURLException {
		
		DesiredCapabilities caps = DesiredCapabilities.edge();
		caps.setCapability("platform", "Windows 10");
		caps.setCapability("version", "13.10586");
		RemoteWebDriver rdriver = new RemoteWebDriver(new URL(URL), caps);
		rdriver.get("https://www.cnn.com");
		System.out.println(rdriver.getTitle());
		rdriver.quit();
	}
}
