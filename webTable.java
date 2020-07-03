package week4.day1.assignments;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class webTable {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/table.html"); 
		driver.manage().window().maximize(); //Maximizing the browser
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		//Get the count of number of columns
		WebElement tableValues = driver.findElementById("table_id");		
		List<WebElement> tableRows = tableValues.findElements(By.tagName("tr"));
		WebElement tableRow1 = tableRows.get(3);
		List<WebElement> tableColumns = tableRow1.findElements(By.tagName("td"));
		System.out.println("Count of number of columns: " + tableColumns.size());
		
		
		//Get the count of number of rows
		System.out.println("Count of number of rows: " + tableRows.size());
		
		
		//Get the Progress value
		String text = "Learn to interact with Elements";
		for(int i=1; i<tableRows.size();i++){
			WebElement rowIterator = tableRows.get(i);
			List<WebElement> columns = rowIterator.findElements(By.tagName("td"));
			String text2 = columns.get(0).getText();
			if(text2.equalsIgnoreCase(text)){
				System.out.println("The Maximun Progress value on the table is " + columns.get(1).getText());
				break;
			}
		}
		
		//Check the vital task for the least completed progress
		System.out.println("******************************");
		String finalValue = null;
		
		
		for(int i=1;i<tableRows.size()-1;i++){
			String progress1 = tableRows.get(i).findElements(By.tagName("td")).get(1).getText();
			int z = Integer.parseInt(progress1.replace("%", ""));
			int j=i+1;
			
			if(Integer.parseInt(tableRows.get(j).findElements(By.tagName("td")).get(1).getText().replace("%", "")) <= z){
				finalValue = tableRows.get(j).findElements(By.tagName("td")).get(1).getText();
				}
						
		}
		
		
		for (int k=1; k<tableRows.size(); k++) {
			String progressRow = tableRows.get(k).findElements(By.tagName("td")).get(1).getText();
			if(progressRow.equals(finalValue)){
				String detailName = tableRows.get(k).findElements(By.tagName("td")).get(0).getText();
				System.out.println("The Vital Task check box has been clicked for the following Selenium Learning Details: " + detailName );
				System.out.println("The Progress value of the above Learning Detail is : " + finalValue);
				driver.findElementByXPath("(//input[@type='checkbox'])["+k+"]").click();
			}
			
		}
		
		driver.close();
		
	}

}
