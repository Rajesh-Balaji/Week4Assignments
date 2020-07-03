package week4.assignments;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ZoomCar {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.zoomcar.com/chennai"); 
		driver.manage().window().maximize(); //Maximizing the browser
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.findElementByXPath("//a[@class = 'search']").click();
		driver.findElementByXPath("(//div[contains(@class, 'popular')]/div)[3]").click();
		driver.findElementByXPath("//button[text()='Next']").click();
		//driver.findElementByXPath("//div[contains(text(), 'show')]").click(); //clicking show more
		Thread.sleep(3000);
		
		
		Date date = new Date();
		DateFormat sdf = new SimpleDateFormat("dd");
		String tdy = sdf.format(date);
		int tmrw = Integer.parseInt(tdy)+1;
		int nxtDay = tmrw+1;
		
		driver.findElementByXPath("//div[contains(text(),'"+tmrw+"')]").click();
		driver.findElementByXPath("//button[text()='Next']").click();
		Thread.sleep(3000);
		driver.findElementByXPath("//div[contains(text(),'"+nxtDay+"')]").click();
		driver.findElementByXPath("//button[text() = 'Done']").click();
		Thread.sleep(9000);
		List<WebElement> results = driver.findElementsByXPath("//div[contains(@class,'listing')]");
		System.out.println("Number of results displayed: " + results.size());
		
		
		List<WebElement> carName = driver.findElementsByXPath("//div[@class='details']/h3");
		List<WebElement> carPrice = driver.findElementsByXPath("//div[contains(@class,'km')]/strong");
			
		Map<String,Integer> map = new LinkedHashMap<String,Integer>();
		for(int i=0; i<results.size(); i++){
			String finalPrice = carPrice.get(i).getText().replace("Rs", "").replace("/km", "");
			map.put(carName.get(i).getText(), Integer.parseInt(finalPrice));
			}
			
		Integer maxEntry = Collections.max(map.values());
				
		for (Entry<String, Integer> eachMap : map.entrySet()) {
			if(eachMap.getValue().equals(maxEntry)){
				String maxKey = eachMap.getKey();
				System.out.println("The Car with Max value: " + maxKey);
				
				for(int j=0; j<results.size(); j++){
					if(carName.get(j).getText().equals(maxKey)){
						driver.findElementByXPath("(//button[@type='button'])["+j+"]").click();
					}
				}
							
			}
		}
		
		driver.close();
	
		
		

	}

}
