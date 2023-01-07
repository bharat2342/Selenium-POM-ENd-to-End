package com.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.pageobjects.LandingPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest 
{
	//here we need to write all the driver details
	//Like set the settings of all the browsers
	WebDriver driver;
	public WebDriver initializeDriver() throws IOException
	{
		//We can read the data by using Properties class to read from globalData
		Properties prop =new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "//src//test//resources//GlobalData.properties");//Give the path location by converting into input stream object
		//As the path is large we can get it by System.getProperty the other way of writing
		//FileInputStream fi= new FileInputStream(System.setProperty("user.dir")+
		prop.load(fis);//We need to load inputSTream as the argument
		String browserName=prop.getProperty("browser");//We get the property  name
		
		
		
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			//For Chrome Browser
		WebDriverManager.chromedriver().setup();
		 driver= new ChromeDriver();
	
	}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			//For Firefox
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
		}
		
		else if(browserName.equalsIgnoreCase("edge"))
		{
			//For Edge
			WebDriverManager.edgedriver().setup();
			driver= new EdgeDriver();
		}
	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	public LandingPage launchApplication() throws IOException
	{
		 driver= initializeDriver();
		 LandingPage landingpage=new LandingPage(driver);
		landingpage.gotoLandingPage();
		return landingpage;
		
		
	}
	

}
