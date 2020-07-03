package week4.assignments;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Acme {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://acme-test.uipath.com/account/login"); 
		driver.manage().window().maximize(); //Maximizing the browser
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		driver.findElementById("email").sendKeys("kumar.testleaf@gmail.com", Keys.TAB);
		driver.findElementById("password").sendKeys("leaf@12");
		driver.findElementById("buttonLogin").click();

		Actions obj1 = new Actions(driver);
		obj1.moveToElement(driver.findElementByXPath("(//button[@Type='button'])[6]")).click(driver.findElementByXPath("(//a[contains(text(),'Search')])[2]")).perform();
		
		driver.findElementById("vendorName").sendKeys("Blue Lagoon");
		driver.findElementById("buttonSearch").click();
		
		WebElement resultsTable = driver.findElementByXPath("//table[@class='table']");
		List<WebElement> tableRows = resultsTable.findElements(By.tagName("tr"));
		WebElement firstRow = tableRows.get(1);
		List<WebElement> tabColumns = firstRow.findElements(By.tagName("td"));
		

		if(tabColumns.get(0).getText().equals("Blue Lagoon")){
			System.out.println("The Country Name is : " + tabColumns.get(4).getText());
		}
		
		driver.findElementByXPath("//a[contains(text(),'Out')]").click();
		driver.close();
		
	}

}
