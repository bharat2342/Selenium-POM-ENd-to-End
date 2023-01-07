package com.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pageobjects.CartPage;

public class CommonUtils 
{
	WebDriver driver;
	public CommonUtils(WebDriver driver) 
	{
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement cartHeader;
	
	
	
	public void waitForElementToAppear(By findBy)
	{
	WebDriverWait explicitwait= new WebDriverWait(driver, Duration.ofSeconds(5));
	explicitwait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	
	public void waitForElementToDisappear(WebElement ele)
	{
		WebDriverWait explicitwait= new WebDriverWait(driver, Duration.ofSeconds(5));
		explicitwait.until(ExpectedConditions.invisibilityOf(ele));
		
	}
	public void waitForElementToClicked(WebElement ele)
	{
		
		
	}
	
	public CartPage goTOCartPage()
	{
		
		cartHeader.click();
		
		//This needs when objects redundancy
		CartPage cartPage= new CartPage(driver);
		return cartPage;
		
	}
	

}
