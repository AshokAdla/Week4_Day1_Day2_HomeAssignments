package week4.day2.HomeAssignments;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import java.util.*;
public class NykaaUsingActionClass {
	public static void main(String[] args) throws InterruptedException {
		
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver=new ChromeDriver(options);
		
		//Go to the Nykaa website.
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4000));
		
		//Mouseover on "Brands" and search for "L'Oreal Paris".
		WebElement brands=driver.findElement(By.xpath("//a[text()='brands']"));
		Actions act=new Actions(driver);
		act.moveToElement(brands).perform();
		Thread.sleep(3000);
		
		//Click on "L'Oreal Paris".
		driver.findElement(By.xpath("//li[5]//a[1]//img[1]")).click();
		Thread.sleep(4000);
		
		//Check if the title contains "L'Oreal Paris".
		String title=driver.getTitle();
		if(title.contains("L'Oreal Paris")) {
			System.out.println("Title contains: "+title);
		}
		
		//Click on "Sort By" and select "Customer Top Rated".
		driver.findElement(By.cssSelector("svg.arrow-icon")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='customer top rated']")).click();		
		//driver.findElement(By.xpath("(//div[@class=\"control-indicator radio \"])[3]")).click();
		Thread.sleep(3000);
		
		//Click on "Category" and select "Hair" -> "Haircare" -> "Shampoo".
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'Shampoo')]")).click();	
		
		driver.findElement(By.xpath("(//span[text()='Shampoos'])[1]")).click();
		
		//Click on "Concern" -> "Color Protection".
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'Colour Protection')]")).click();
		
		//Check if the filter is applied with "Shampoo".
		String shampoofiltertext=driver.findElement(By.xpath("(//span[@class='filter-value'])[1]")).getText();
		System.out.println(shampoofiltertext);
		
		//Click on "L'Oreal Paris Colour Protect Shampoo".
		driver.findElement(By.xpath("//div[@class='css-xrzmfa']")).click();
		
		//Go to the new window and select the size as "180 ml".
		Set<String> handles=driver.getWindowHandles();
		List<String> lst=new ArrayList<String>(handles);
		driver.switchTo().window(lst.get(1));
		driver.findElement(By.xpath("//button[@class='active css-1r0ze8m']")).click();
		
		//Print the MRP of the product.
		String MRP=driver.findElement(By.xpath("//div[@class='css-1hbcpku']")).getText();
		String splitval[]=MRP.split(":");
		System.out.println("MRP of product :" +splitval[1].trim());
		
		//Click on "ADD to BAG".
		driver.findElement(By.xpath("(//button[@class=' css-13zjqg6'])[1]"));
		
		//Go to the Shopping Bag.
		driver.findElement(By.xpath("//span[@class='cart-count']")).click();		
		
		//Print the Grand Total amount.
		String GrandTotal=driver.findElement(By.xpath("//span[@class='css-1um1mkq e171rb9k3']")).getText();
		System.out.println("Grand total amount: "+GrandTotal);
		
		//Click "Proceed".
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();		
		Thread.sleep(5000);
		
		//Click on "Continue as Guest".
		driver.findElement(By.xpath("//button[text()='Continue as guest']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[@class='css-1ld034w ehes2bo0']")).click();
		
		//Check if the Grand Total is the same as in step 14.
		String obtprice=driver.findElement(By.xpath("//p[@class='css-1e59vjt eka6zu20']")).getText();
		if(GrandTotal.equals(obtprice)) {
			System.out.println("Prices are matching");
		}else {
			System.out.println("Prices are not matching");
		}
		
		//Close all windows.
		Set<String> windowhandles=driver.getWindowHandles();
		for(String handle:windowhandles) {
			driver.switchTo().window(handle);
			String windowtitle=driver.getTitle();
			if(windowtitle!=null) {
				driver.close();
			}
		}

	}
}
