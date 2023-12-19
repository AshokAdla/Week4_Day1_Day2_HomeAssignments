package week4.day1.HomeAssignments;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.*;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertInteractionEcommerceApplcn {
	public static void main(String[] args) throws InterruptedException {
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://buythevalue.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));

		// Click on the first product (e.g., Hamdard Hing - 50GM).
		driver.findElement(By.xpath("(//input[@placeholder='Search product'])[1]")).sendKeys("Hamdard Hing - 50GM",Keys.ENTER);
		Thread.sleep(3000);

		WebElement product = driver.findElement(By.xpath("//a[contains(text(),'Hamdard Hing - 50GM')]"));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", product);
		Thread.sleep(2000);
		
		if (product.isDisplayed()) {
			product.click();
			Thread.sleep(4000);
		}
		
		Set<String> winds=driver.getWindowHandles();
		System.out.println("Count of windows: "+winds.size());
		
		String parentwindow=winds.iterator().next();
		System.out.println("current window: "+parentwindow);
		
		//Remove first/default/parent window
		winds.remove(winds.iterator().next());
		
		String childwindow=winds.iterator().next();
		System.out.println("child window: "+childwindow);
		
		//Switch to child window
		driver.switchTo().window(childwindow);
		
		// Enter the pincode.
		driver.findElement(By.xpath("//input[@name='wk_zipcode']")).click();
		driver.findElement(By.xpath("//input[@name='wk_zipcode']")).sendKeys("560086");

		// Click on "Check" to verify delivery availability.
		driver.findElement(By.xpath("//input[@value='Check']")).click();
		Thread.sleep(4000);

		// Click on "ADD TO CART".
		driver.findElement(By.xpath("(//span[text()='Add to Cart'])[1]")).click();
		Thread.sleep(8000);

		// Click on "View" to view the cart.		
		WebElement vc=driver.findElement(By.xpath("//span[@data-hover='View Cart']"));
		if(vc.isDisplayed()) {
			vc.click();
		}
		
		// Click on "CHECK OUT".
		driver.findElement(By.xpath("//input[@id='checkout']")).click();
		Thread.sleep(1000);
		
		// Handle the alert by accepting it
		Alert alt=driver.switchTo().alert();
		alt.accept();
		
		driver.close();

	}

}
