import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Handlinglinks01 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ChromeOptions options=new ChromeOptions();
		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		
		System.setProperty("webdriver.chrme.driverr","./chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.bbc.com/news/world");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println(links.size());
		//String[] alllinks = new String[links.size()];
		
		//for(int i=0;i<links.size();i++)
//		{
//			System.out.println(links.get(i).getText());
//			
//			}
		for(int i=0;i<links.size();i++)
		{
			if(links.get(i).isDisplayed())
			links.get(i).click();
			Thread.sleep(5000);
			driver.navigate().back();
			Thread.sleep(2000);
		}
		
		
	

	}

}
