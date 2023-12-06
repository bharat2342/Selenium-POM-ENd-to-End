package com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abstractcomponents.CommonUtils;

public class CheckOutPage extends CommonUtils

{

	WebDriver driver;
	
	
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//Driver.findelement we can give the pagefactory steps
	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css = ".action__submit")
	private WebElement submit;
	
	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	
	By results= By.cssSelector(".ta-results");//By statements cant write in pagefactory because it is not webelement
	
	
	public void selectCountry(String countryName)
	{
		 Actions act= new Actions(driver);
		 act.sendKeys(country,countryName).build().perform();
		 waitForElementToAppear(results);
		 selectCountry.click();
		 
	}
	
	public ConfirmationPage submitOrder() throws InterruptedException
	{
		//Actions act= new Actions(driver);
		 //act.moveToElement(submit).click().build().perform();
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("scroll(1050, 700)");
		Thread.sleep(3000);
		submit.click();
		return new ConfirmationPage(driver);
		
	}
	
	
	
	

}
