package week4.day2.HomeAssignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonUsingActionClass {
	public static void main(String[] args) throws IOException, InterruptedException {
		ChromeDriver driver=new ChromeDriver();
		//Load the URL (https://www.amazon.in/)
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4000));
		
		//Search for "oneplus 9 pro".
		driver.findElement(By.xpath("//input[@placeholder='Search Amazon.in']")).sendKeys("oneplus 9 pro",Keys.ENTER);
		
		//Get the price of the first product.
		String pricefirstproduct=driver.findElement(By.xpath("(//span[@class='a-price-whole'])[1]")).getText();
		System.out.println("Price of first product :"+pricefirstproduct);
		
		//Print the number of customer ratings for the first displayed product.
		String custratings=driver.findElement(By.xpath("(//span[@class='a-size-base s-underline-text'])[1]")).getText();
		System.out.println("Number of customer ratings :"+custratings);
		
		//Click the first text link of the first image.
		driver.findElement(By.xpath("(//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal'])[1]")).click();
		
		//Take a screenshot of the product displayed.
		TakesScreenshot scrshot=(TakesScreenshot)driver;
		File srcfile=scrshot.getScreenshotAs(OutputType.FILE);
		String fileWithPath="C:\\screenshots\\test.png";
		File destfile=new File(fileWithPath);
		FileUtils.copyFile(srcfile, destfile);
		
		//Click the 'Add to Cart' button.
		Set<String> winds=driver.getWindowHandles();
		String parentwindow=winds.iterator().next();
		System.out.println("Parent window id :"+parentwindow);
		
		//Removing parent window
		winds.remove(winds.iterator().next());
		
		String childwindow=winds.iterator().next();
		System.out.println("Child window id :"+childwindow);
		
		//Thread.sleep(5000);
		//Switch to child window
		driver.switchTo().window(childwindow);
		Thread.sleep(5000);
		
		WebElement addtocart=driver.findElement(By.xpath("//input[@id='add-to-cart-button']"));
		//Scroll down until Add to cart button
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView()", addtocart);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
		Thread.sleep(3000);
		
		//Get the cart sub total and verify if it is correct.
		String subtotalprice=driver.findElement(By.xpath("//span[@id='attach-accessory-cart-subtotal']")).getText();
		System.out.println("Sub total price is :"+subtotalprice);
		
		String[] price=subtotalprice.split("₹");
		subtotalprice=price[1];
		pricefirstproduct=(pricefirstproduct+".00");
		
		if(pricefirstproduct.equals(subtotalprice)) {
			System.out.println("Price of first product: "+pricefirstproduct+" and Sub total price in cart is: "+subtotalprice+" are matching");
		}else {
			System.out.println("Price of first product: "+pricefirstproduct+" and Sub total price in cart is: "+subtotalprice+" are not matching");
		}
		
		//Close all the browser tabs.
		driver.quit();
	}
}





