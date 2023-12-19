package week4.day2.HomeAssignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowHandlingInteraction {
	public static void main(String[] args) throws InterruptedException {
		ChromeDriver driver = new ChromeDriver();
		// Launch browser and navigate to leaf taps page
		driver.get("http://leaftaps.com/opentaps/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		driver.manage().window().maximize();

		// Enter the user name and password
		driver.findElement(By.id("username")).sendKeys("DemoCSR");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));

		// Click on CRm/SFA link
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4000));

		// Click on the Contacts button.
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		Thread.sleep(3000);
		
		// Click on Merge Contacts.
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();
		Thread.sleep(4000);

		// Click on the widget of the "From Contact".
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[1]")).click();
		Thread.sleep(4000);
		
		Set<String> winds = driver.getWindowHandles();
		List<String> lst=new ArrayList<String>(winds);
		driver.switchTo().window(lst.get(1));
		
		Thread.sleep(3000);
		driver.manage().window().maximize();

		// Click on the first resulting contact.
		driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
		Thread.sleep(5000);
		
		//Switch back to parent window
		driver.switchTo().window(lst.get(0));
		
		// Click on the widget of the "To Contact".
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[2]")).click();
		Thread.sleep(4000);
		
		Set<String> winds1 = driver.getWindowHandles();
		List<String> lst1=new ArrayList<String>(winds1);
		driver.switchTo().window(lst1.get(1));
		
		Thread.sleep(3000);
		driver.manage().window().maximize();

		// Click on the second resulting contact.
		driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
		Thread.sleep(4000);
		
		//Switch back to parent window
		driver.switchTo().window(lst.get(0));
		
		// Click on the Merge button.
		driver.findElement(By.xpath("//a[text()='Merge']")).click();
		Thread.sleep(4000);
		
		// Accept the alert.
		Alert alt=driver.switchTo().alert();
		alt.accept();
		
		// Verify the title of the page.
		String title=driver.getTitle();
		System.out.println(title);
	}

}
