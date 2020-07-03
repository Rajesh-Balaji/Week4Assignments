package week4.assignments;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.chrome.ChromeDriver;

public class MergeLeads {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.manage().window().maximize();
		
		driver.findElementByXPath("//input[@id='username']").sendKeys("Demosalesmanager");
		driver.findElementByXPath("//input[@id='password']").sendKeys("crmsfa");
		driver.findElementByXPath("//input[contains(@class,'decorative')]").click();
		
		driver.findElementByXPath("//a[contains(text(),'CRM/SF')]").click();
		driver.findElementByXPath("//a[text()='Contacts']").click();
		driver.findElementByXPath("//a[contains(text(),'Merge')]").click();
		
		driver.findElementByXPath("(//img[@alt='Lookup'])[1]").click();
		Set<String> windowHandle1 = driver.getWindowHandles();
		List<String> list = new ArrayList<String>(windowHandle1);
		driver.switchTo().window(list.get(1));
		driver.findElementByXPath("(//a[@class='linktext'])[1]").click();
		driver.switchTo().window(list.get(0));
		
		
		Thread.sleep(3000);
		driver.findElementByXPath("(//img[@alt='Lookup'])[2]").click();
		Set<String> windowHandle2 = driver.getWindowHandles();
		List<String> list2 = new ArrayList<String>(windowHandle2);
		driver.switchTo().window(list2.get(1));
		driver.findElementByXPath("(//a[@class='linktext'])[6]").click();
		driver.switchTo().window(list2.get(0));
		
		driver.findElementByXPath("//a[text()='Merge']").click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		Thread.sleep(5000);
		
		String expectedTitle = "Merge Contacts | opentaps CRM";
		String title = driver.getTitle();
		if(title.equals(expectedTitle)){
			System.out.println("Title verified and correct!");
		}
		else{
			System.out.println("Wrong title or on the wrong page!");
		}
		
		
		
		
	}

}
