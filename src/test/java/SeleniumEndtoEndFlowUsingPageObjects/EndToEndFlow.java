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

import com.beust.jcommander.JCommander.Builder;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EndToEndFlow 
{
	public static void main(String[] args)
	{
		//So here we use the WebDriverManager to configure with Chrome
		//Since no need to give the path and all
		//Again we need to add the dependancy
		WebDriverManager.chromedriver().setup();//with this step chrome driver automatically configured with updated version
		
		String productname="ZARA COAT 3";
		WebDriver driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("bkbharatkumar007@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Iambharat93@");
		driver.findElement(By.id("login")).click();
		
		//Then it opens the e-commerce page and then we need to grab all the products and then validate
		List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));//if it is class we can give it directly
		int productsNumber=products.size();
		System.out.println(productsNumber);
		
		/*
		 * for(int i=0;i<productsNumber;i++) { String
		 * ProductName=products.get(i).getText(); System.out.println(ProductName); }
		 */
		
		//Now we do wrapping into single line using the streams
		// So this reduces the code
		
		//So this stores the Product having with Zara coat3
		WebElement desiredprod =products.stream().filter
				(product -> product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
		
		
		//So within the same product we will click on add-to-cart button
		//Again need to check in selectors hub
		desiredprod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
		//For Usage of CSS-selector and class we go with . for ID we go with #
		//check in selectors hub
		
		//Now toast message will get displayed and now we need to handle writing the X-path or Css
		WebDriverWait explicitwait= new WebDriverWait(driver, Duration.ofSeconds(5));
		explicitwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		explicitwait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		
		//Grab the items present in the cart section
		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		
		//Match type is being used
	Boolean match=	cartProducts.stream().anyMatch
		(cartproduct -> cartproduct.getText().equalsIgnoreCase(productname));
   Assert.assertTrue(match);
   
   driver.findElement(By.cssSelector("li[class='totalRow'] button[type='button']")).click();
   //or By using  CSS .totalRow 
		
   		
      
   Actions act= new Actions(driver);
   act.sendKeys(driver.findElement(By.cssSelector("[input[placeholder='Select Country']")),"india").build().perform();
   explicitwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
   driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
   driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();
   //or the same css will be
  // driver.findElement(By.cssSelector(".action_submit")).click();
   
   String confirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
   //Assert.assertEquals(confirmMessage, "Thankyou for the order");
   //Other way
   Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thanks you for the order."));
		//driver.close();
		
		
		
		
		
		
		

	}

}
