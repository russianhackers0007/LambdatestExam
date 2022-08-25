package com.lambdatest.TestCases;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class TC_Lambdatest_02 {

	String username = "aditya.nadkarni";
	String accessKey = "2h8LNvqBet1kmv5cW0oF3DzIZ0P7zTGIkRn6WHqCrDyrYEgFjV";
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
		capabilities.setCapability("build", "TC_Lambdatest_02");
		capabilities.setCapability("name", "TC_Lambdatest_02");
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

	@Test(description="Test Scenario 2",priority=2)
	public void Lambdatest2() throws InterruptedException 
	{
		SoftAssert softassert= new SoftAssert();
		
		//1.Opening Lambda Test's Selenium Playground form
		driver.get("https://www.lambdatest.com/selenium-playground");
		
		//click “Drag & Drop Sliders” under “Progress Bars & Sliders”
		driver.findElement(By.xpath("//a[contains(text(),'Drag & Drop Sliders')]")).click();
		
		//Select the slider “Default value 15” and drag the bar to make it 95. 
		WebElement ele=driver.findElement(By.xpath("//h4[contains(text(),'Default value 15')]/parent::div/div"));
		
		Actions act= new Actions(driver);
		act.dragAndDropBy(ele, 99, 0).perform();
		
		//validating whether the range value shows 95.
		boolean bool3= driver.findElement(By.id("rangeSuccess")).getText().contains("95");
		
		if(bool3==true)
		{
			softassert.assertTrue(true);
			System.out.println("The displayed Value is 95");
			softassert.assertAll();
		}
		else
		{
			softassert.assertTrue(false);
			System.out.println("Its displaying Different value in the slider");
			softassert.assertAll();
			
		}
		
		Thread.sleep(2000L);	
	}

	@AfterMethod
	public void tearDown() throws Exception 
	{
			driver.quit();
		
	}
}