package SeleniumEndtoEndFlowUsingPageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.pageobjects.CartPage;
import com.pageobjects.CheckOutPage;
import com.pageobjects.ConfirmationPage;
import com.pageobjects.LandingPage;
import com.pageobjects.ProductCatalogue;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EndTOEndFlowReduce1
{

	public static void main(String[] args) throws InterruptedException 
	{
		WebDriverManager.chromedriver().setup();//with this step chrome driver automatically configured with updated version
		String productname="ZARA COAT 3";
		WebDriver driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		
		//Here we dont use Multiple Objects as previous
		LandingPage landingpage=new LandingPage(driver);
		landingpage.gotoLandingPage();
		ProductCatalogue productCatalogue= landingpage.loginApp("bkbharatkumar007@gmail.com", "Iambharat93@");
		List<WebElement> Products=productCatalogue.getProductList();
		productCatalogue.addProductCart(productname);
		
		CartPage cartpage=productCatalogue.goTOCartPage();
		
		
		Boolean match=cartpage.verifyProductDisplay(productname);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage=cartpage.gotoCheckout();
		checkOutPage.selectCountry("India");
		
		Thread.sleep(3000);
		
		
		//At the end element is not clickable need to sort it out
		/*
		 * JavascriptExecutor js=(JavascriptExecutor) driver;
		 * js.executeScript("arguments[0].click()",checkOutPage.submitOrder());
		 */
		ConfirmationPage confirmPage= new ConfirmationPage(driver);
		CheckOutPage checkout= new CheckOutPage(driver);
		checkout.submitOrder();
		
		//checkOutPage.submitOrder();
		
		
		String confirmMessage=confirmPage.getConfiramtionMessage();
	    Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		driver.close();
		

	}

}
