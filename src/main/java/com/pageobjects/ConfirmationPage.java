package com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abstractcomponents.CommonUtils;

public class ConfirmationPage extends CommonUtils
{

	WebDriver driver;
	public ConfirmationPage(WebDriver driver) 
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css = ".hero-primary")
	WebElement confirmMessage;
	
	
	
	public String getConfiramtionMessage()
	{
		CheckOutPage cp= new CheckOutPage(driver);
		return confirmMessage.getText();
		
	}
	
	

}
