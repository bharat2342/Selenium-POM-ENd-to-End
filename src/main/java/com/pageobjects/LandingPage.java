package com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abstractcomponents.CommonUtils;

public class LandingPage extends CommonUtils
{
		//To make sure it comes from the end-to-end flow we use constructor
		WebDriver driver;
		public LandingPage(WebDriver driver)
		{
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		
	   //WebElement userEmailID=  driver.findElement(By.id("userEmail"));
	   //Page factory(Instead of writing the above statement we use below)
		//Page object class should focus on only Elements and action but not data
	   @FindBy(id="userEmail")
	   WebElement userEmailID;
	   
	   
	   @FindBy(id="userPassword")
	   WebElement userPassword;
	   
	   @FindBy(id="login")
	   WebElement submit;
	   
	   public ProductCatalogue loginApp(String email,String pwd)
	   {
		   userEmailID.sendKeys(email);
		   userPassword.sendKeys(pwd);
		   submit.click();
		  
		   //This two extra needs to be added when dont create the multiple objects
		   ProductCatalogue productCatalogue= new ProductCatalogue(driver);
		   return productCatalogue;
		   
	   }
	   
	   public void gotoLandingPage()
	   {
		   
		   driver.get("https://rahulshettyacademy.com/client");
		   
		   
	   }

}
