package week4.day1.HomeAssignments;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FrameInteractionWithTextVerification {
	public static void main(String[] args) throws InterruptedException {
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://www.w3schools.com/js/tryit.asp?filename=tryjs_prompt");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		
		//Switch to frame window
		WebElement frame=driver.findElement(By.xpath("//iframe[@id='iframeResult']"));
		driver.switchTo().frame(frame);	
		
		//Click the "Try It" button inside the frame
		driver.findElement(By.xpath("//button[text()='Try it']")).click();
		
		//Click OK/Cancel in the alert that appears
		Alert alt=driver.switchTo().alert();
		String enteredtext="Ashok Adla";
		alt.sendKeys(enteredtext);
		alt.accept();
		Thread.sleep(4000);
		
		//Confirm the action is performed correctly by verifying the text displayed
		String text=driver.findElement(By.xpath("//p[@id='demo']")).getText();
		if(text.contains(enteredtext)) {
			System.out.println(text);
		}else {
			System.out.println("Invalid text");
		}
		
		driver.close();
		
	}

}
