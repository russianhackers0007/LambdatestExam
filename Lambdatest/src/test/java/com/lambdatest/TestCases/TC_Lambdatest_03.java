package com.lambdatest.TestCases;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class TC_Lambdatest_03 {

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
		capabilities.setCapability("build", "TC_Lambdatest_03");
		capabilities.setCapability("name", "TC_Lambdatest_03");
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

	@Test(description="Test Scenario 3",priority=3)
	public void Lambdatest3() throws InterruptedException 
	{
		SoftAssert softassert= new SoftAssert();
       
		//1.Opening Lambda Test's Selenium Playground form
		driver.get("https://www.lambdatest.com/selenium-playground/");
		
		//click “Input Form Submit” under “Input Forms”
		driver.findElement(By.xpath("//a[contains(text(),'Input Form Submit')]")).click();
		
		//2. Click “Submit” without filling in any information in the form 
		driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
		
		Thread.sleep(1000L);
	
		//3.Assert “Please fill in the fields” error message.
	   WebElement ele1= driver.findElement(By.xpath("//input[@name='name']"));
       System.out.println(ele1.getAttribute("validationMessage"));
        
       boolean valid=ele1.getAttribute("validationMessage").contains("Please fill out this field");
       
       if(valid==true)
       {
    	   softassert.assertTrue(true);
    	   System.out.println("Validation Success!! Please fill all the fields");
    	   softassert.assertAll();
       }
       else
       {
    	   softassert.assertTrue(false);
    	   System.out.println("Validation Failed!!");
    	   softassert.assertAll();
       }
       
       //4. Fill in Name, Email, and other fields
       System.out.println("filling all the details in the form");
       driver.findElement(By.cssSelector("input#name")).sendKeys("Aditya");
       driver.findElement(By.id("inputEmail4")).sendKeys("aditya.nadkarni@outlook.com");
       driver.findElement(By.name("password")).sendKeys("Lambdatest@123");
       driver.findElement(By.cssSelector("input#company")).sendKeys("Creative Information Technology India");
       driver.findElement(By.cssSelector("input#websitename")).sendKeys("https://citi-us.com/");
       
       //5.From the Country drop-down, select “United States” using the text property.
       WebElement country1= driver.findElement(By.xpath("//select[@name='country']"));
       Select sel= new Select(country1);
       sel.selectByVisibleText("United States");
       
       driver.findElement(By.xpath("//input[@id='inputCity']")).sendKeys("Mumbai");
       driver.findElement(By.xpath("//input[@id='inputAddress1']")).sendKeys("Airoli");
       driver.findElement(By.xpath("//input[@id='inputAddress2']")).sendKeys("Navi Mumbai");
       
       driver.findElement(By.xpath("//input[@id='inputState']")).sendKeys("Maharashtra");
       driver.findElement(By.xpath("//input[@id='inputZip']")).sendKeys("400708");
       System.out.println("Clicking on Submit Button");
       
       //6. Fill all fields and click “Submit”
       driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
       
       String finaltext="Thanks for contacting us, we will get back to you shortly.";
       
       WebDriverWait successwait= new WebDriverWait(driver, 30);
       successwait.until(ExpectedConditions.elementToBeClickable(By.linkText("Input Form Submit")));
       
       boolean finalsubmittext=driver.findElement(By.xpath("//*[contains(text(),'Thanks for contacting us')]")).getText().contains(finaltext);
       
       if(finalsubmittext==true)
       {
    	   softassert.assertTrue(true);
    	   System.out.println("Validation Success!! The Text is present on the screen");
    	   softassert.assertAll();
       }
       else
       {
    	   softassert.assertTrue(false);
    	   System.out.println("Validation Failed!! The Text is not present on the screen");
    	   softassert.assertAll();
       }
       
    }

	@AfterMethod
	public void tearDown() throws Exception 
	{
			driver.quit();
		
	}
}