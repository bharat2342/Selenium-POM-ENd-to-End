package SeleniumEndtoEndFlowUsingPageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.pageobjects.CartPage;
import com.pageobjects.CheckOutPage;
import com.pageobjects.ConfirmationPage;
import com.pageobjects.LandingPage;
import com.pageobjects.ProductCatalogue;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EndToEndFlowReduce 
{
	//This is the way to create a multiple objects
	public static void main(String[] args) throws InterruptedException
	{
		
		WebDriverManager.chromedriver().setup();//with this step chrome driver automatically configured with updated version
		String productname="ZARA COAT 3";
		WebDriver driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		
		//So here we call all the methods by creating the objects
		
		//For Landing Page created page objects and then calling
		LandingPage landingpage=new LandingPage(driver);
		landingpage.gotoLandingPage();
		landingpage.loginApp("bkbharatkumar007@gmail.com", "Iambharat93@");	
		
		
		ProductCatalogue productCatalogue= new ProductCatalogue(driver);
		List<WebElement> Products=productCatalogue.getProductList();
		productCatalogue.addProductCart(productname);
		productCatalogue.goTOCartPage();
		
		CartPage cartpage= new CartPage(driver);
		Boolean match=cartpage.verifyProductDisplay(productname);
		Assert.assertTrue(match);
		cartpage.gotoCheckout();
		
		CheckOutPage checkout= new CheckOutPage(driver);
		checkout.selectCountry("India");
		checkout.submitOrder();
		
		ConfirmationPage confirmPage= new ConfirmationPage(driver);
		String confirmMessage=confirmPage.getConfiramtionMessage();
	    Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thanks you for the order."));
		driver.close();

	}

}
