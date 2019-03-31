import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HandleTables {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChromeOptions options = new ChromeOptions();
		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

		System.setProperty("Webdriver.chrome.driver", "./chromedriver.exe");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://business.bell.ca/Support/enterprise/satellite-tv-channel-list-starter");
		WebElement table= driver.findElement(By.xpath("(//table)[3]"));
		

	}

}
