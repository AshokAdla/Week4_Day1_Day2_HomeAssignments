package week4.day2.HomeAssignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.*;

public class SnapdealActionClass {
	public static void main(String[] args) throws InterruptedException, IOException{
		//Launch (https://www.snapdeal.com/)
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		
		driver.findElement(By.xpath("//button[@id='pushDenied']")).click();
		
		//Go to "Men's Fashion".
		WebElement elementToMouseOver=driver.findElement(By.xpath("(//span[@class=\"catText\"])[1]"));
		Actions actions=new Actions(driver);
		actions.moveToElement(elementToMouseOver).perform();
		Thread.sleep(3000);
		
		//Go to "Sports Shoes".
		driver.findElement(By.xpath("//span[text()='Sports Shoes']")).click();
		
		//Get the count of sports shoes.
		List<WebElement> sportsshoeslist=driver.findElements(By.xpath("//p[@class='product-title']"));
		System.out.println("Count of sports shoes: "+sportsshoeslist.size());
		
		//Click on "Training Shoes".
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		Thread.sleep(4000);	
		
		//Sort the products by "Low to High".
		driver.findElement(By.xpath("//i[@class='sd-icon sd-icon-expand-arrow sort-arrow']")).click();
		driver.findElement(By.xpath("//li[@data-sorttype='plth']")).click();
		Thread.sleep(3000);
		
		//Check if the displayed items are sorted correctly.
		List<WebElement> priceElements=driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		List<Double> originalPrices=new ArrayList<>();
		
		for(WebElement element:priceElements) {
			originalPrices.add(Double.parseDouble(element.getText().replaceAll("[^\\d.]", "")));
		}
		
		List<Double> sortedPrices=new ArrayList<>(originalPrices);
		
		Collections.sort(sortedPrices);
		
		//Select any price range ex:(500-700).
		WebElement from=driver.findElement(By.xpath("//input[@name='fromVal']"));
		from.clear();
		from.sendKeys("500");
		
		Thread.sleep(2000);
		WebElement to=driver.findElement(By.xpath("//input[@name='toVal']"));
		to.clear();
		to.sendKeys("700");
		driver.findElement(By.xpath("//div[@class='price-go-arrow btn btn-line btn-theme-secondary']")).click();
		Thread.sleep(2000);
		
		//Filter by any colour.
		driver.findElement(By.xpath("//label[@for='Color_s-White%20%26%20Blue']")).click();
		Thread.sleep(2000);
		
		//Verify all the applied filters.
		WebElement appliedFilters=driver.findElement(By.xpath("(//div[@class='filters'])[1]"));
		String filterstext=appliedFilters.getText();
		System.out.println("Applied Filters are: "+filterstext);
		
		//Mouse hover on the first resulting "Training Shoes".
		WebElement resultElement=driver.findElement(By.xpath("//img[@title='RICKENBAC Blue Training Shoes']"));
		Actions act=new Actions(driver);
		act.moveToElement(resultElement).perform();
		Thread.sleep(2000);
		
		//Click the "Quick View" button.
		driver.findElement(By.xpath("//div[contains(text(),'Quick View')]")).click();
		
		Thread.sleep(5000);
		//Print the cost and the discount percentage.
		WebElement cost=driver.findElement(By.xpath("//span[@class='payBlkBig']"));
		System.out.println("Cost of the product: "+cost.getText());
		
		WebElement discount=driver.findElement(By.xpath("//span[@class='percent-desc ']"));
		System.out.println("Discount percentage of product: "+discount.getText());
		
		//Take a snapshot of the shoes.
		TakesScreenshot scrshot=(TakesScreenshot)driver;
		File srcfile=scrshot.getScreenshotAs(OutputType.FILE);
		String fileWithPath="C:\\screenshots\\test.png";
		File destfile=new File(fileWithPath);
		FileUtils.copyFile(srcfile, destfile);
		
		driver.findElement(By.xpath("//div[@class='close close1 marR10']")).click();
		
		//Close the main window.
		driver.close();
	}

}
