package com.lambdatest.TestCases;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class TC_Lambdatest_01 {

	String username = "adithedaddy90";
	String accessKey = "baYJAP8XDL7wHQdYFYlWFbIdrfQNXza4hgqJmwX0QkfJJAoLsZ";
	public RemoteWebDriver driver = null;
	public String gridURL = "@hub.lambdatest.com/wd/hub";
	boolean status = false;

	@BeforeMethod
	@Parameters(value = { "browser", "version", "platform" })
	public void setUp(String browser, String version, String platform) throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", browser);
		capabilities.setCapability("version", version);
		capabilities.setCapability("platform", platform); 										
		capabilities.setCapability("build", "TC_Lambdatest_01");
		capabilities.setCapability("name", "TC_Lambdatest_01");
		capabilities.setCapability("network", true); 
		capabilities.setCapability("visual", true); 
		capabilities.setCapability("video", true); 
		capabilities.setCapability("console", true); 
		try {
			driver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + gridURL), capabilities);
		} catch (MalformedURLException e) {
			System.out.println("Invalid grid URL");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	@Test(description="Test Scenario 1",priority=1)
	public void Lambdatest1() 
	{
		SoftAssert softassert= new SoftAssert();
		
		//1.Opening Lambda Test's Selenium Playground form
		driver.get("https://www.lambdatest.com/selenium-playground");
		
		//2.Click “Simple Form Demo” under Input Forms.
		driver.findElement(By.linkText("Simple Form Demo")).click();
		
		//3.Validate that the URL contains “simple-form-demo”.
		boolean check1= driver.getCurrentUrl().contains("simple-form-demo");
		if(check1==true)
		{
			softassert.assertTrue(true);
			System.out.println("Validation Successful!! URL contains simple-form-demo");
		}
		else 
		{
			softassert.assertTrue(false);
			System.out.println("Validation Failed!!! URL do not contains simple-form-demo");
		}
		
		//4.Create a variable for a string value E.g: “Welcome to LambdaTest”.
		String text="Welcome to LambdaTest";
		
		//5.Use this variable to enter values in the “Enter Message” text box.
		driver.findElement(By.xpath("//input[@id='user-message']")).sendKeys(text);
		
		//6.Click “Get Checked Value”
		driver.findElement(By.cssSelector("#showInput")).click();
		
		//7.Validate whether the same text message is displayed in the right-hand panel under the “Your Message:” section.
		boolean check2=driver.findElement(By.xpath("//p[contains(text(),'Welcome to LambdaTest')]")).getText().contains(text);
		
		if(check2==true)
		{
			softassert.assertTrue(true);
			System.out.println("The text is present at the right side of the Panel");
		}
		else 
		{
			softassert.assertTrue(false);
			System.out.println("The text is not present at the right side of the Panel");
		}
		
	}
	

	@AfterMethod
	public void tearDown() throws Exception 
	{
			driver.quit();
		
	}
}