package com.TestComponents;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageobjects.CartPage;
import com.pageobjects.CheckOutPage;
import com.pageobjects.ConfirmationPage;
import com.pageobjects.LandingPage;
import com.pageobjects.ProductCatalogue;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EndTOEndFlowReduce2 extends BaseTest
{

	@Test
	public void submitOrder() throws InterruptedException, IOException
	{
		
		String productname="ZARA COAT 3";
		LandingPage landingPage= launchApplication();
		ProductCatalogue productCatalogue= landingPage.loginApp("bkbharatkumar007@gmail.com", "Iambharat93@");
		
		List<WebElement> Products=productCatalogue.getProductList();
		productCatalogue.addProductCart(productname);
		
		CartPage cartpage=productCatalogue.goTOCartPage();
		
		
		Boolean match=cartpage.verifyProductDisplay(productname);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage=cartpage.gotoCheckout();
		checkOutPage.selectCountry("India");
		
		Thread.sleep(3000);
		
		
		
		ConfirmationPage confirmPage= new ConfirmationPage(driver);
		CheckOutPage checkout= new CheckOutPage(driver);
		checkout.submitOrder();
		
		//checkOutPage.submitOrder();
		
		
		String confirmMessage=confirmPage.getConfiramtionMessage();
	    Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		driver.close();
		

	}

}
