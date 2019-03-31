
import java.util.List;

import org.apache.http.conn.ssl.BrowserCompatHostnameVerifier;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.SendKeysAction;

public class handlingInputFileds {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://register.rediff.com/register/register.php?FormName=user_details");
		driver.manage().window().maximize();
		//WebElement ele=driver.findElement(By.name("name4eda98ba"));
		//ele.sendKeys("siva");
		Thread.sleep(2000);
		//ele.sendKeys("prasad");
		WebElement area=driver.findElement(By.id("wrapper"));
		//List<WebElement> textfields = area.findElements(By.tagName("input"));
		
		List<WebElement> textfields = area.findElements(By.xpath("//input[@type='text']"));
		System.out.println(textfields.size());                     
		
//		for(int i=0;i< textfields.size();i++){
//			if(textfields.get(i).isDisplayed()){
//				textfields.get(i).sendKeys("siva");
//			}
//		}
		
		for(WebElement ele2 : textfields){
			if(ele2.isDisplayed()){
				ele2.sendKeys("prasad");
				
				driver.close();
			}
		}
			
	}

}
   