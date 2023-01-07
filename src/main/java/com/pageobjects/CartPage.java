package com.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v106.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abstractcomponents.CommonUtils;

public class CartPage extends CommonUtils 

{
	
	WebDriver driver;
	@FindBy(css =".cartSection h3" )
	private List<WebElement> productTitles;
	
	@FindBy(css = "li[class='totalRow'] button[type='button']")
	WebElement checkOutEle;

	

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	public boolean verifyProductDisplay(String productname)
	{
		Boolean match=	productTitles.stream().anyMatch
				(cartproduct -> cartproduct.getText().equalsIgnoreCase(productname));
		return match;
	}
	
	
	public CheckOutPage gotoCheckout()
	{
		checkOutEle.click();
		return new CheckOutPage(driver);
		//This is added when object are not necessary
		
		
	}
	
	
	
	

	
	

}
