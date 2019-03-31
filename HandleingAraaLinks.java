import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HandleingAraaLinks {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ChromeOptions options= new ChromeOptions();
		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		
System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
WebDriver driver = new ChromeDriver();
driver.get("https://www.bbc.com/news/world");
Thread.sleep(3000);
WebElement Area = driver.findElement(By.xpath("//div[@class='macaw']"));

List<WebElement> aralinks = Area.findElements(By.xpath("//div[@class='macaw-item faux-block-link']"));

System.out.println(aralinks.size());
for(int i=0;i< aralinks.size();i++)
{
	aralinks.get(i).findElement(By.tagName("a")).click();
	System.out.println(driver.getTitle());
	driver.get("https://www.bbc.com/news/world");
	
	Thread.sleep(3000);
	 Area = driver.findElement(By.xpath("//div[@class='macaw']"));

	aralinks = Area.findElements(By.xpath("//div[@class='macaw-item faux-block-link']"));
	
}


//for(WebElement li:aralinks)
//{
//	if(li.isDisplayed())
//	{
//		li.click();
//	}
//		
//}


Thread.sleep(2000);
driver.close();

	}

}
