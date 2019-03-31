package Shop_navigation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.utils.WebdriverUtils;

public class NavigateI_nternet {
	
	public static WebElement PoplightboxClick() throws Exception{
		
		return WebdriverUtils.findElement(By.xpath("//a[@class='rsx-txt-white rsx-modal-close  rsx-pos-absolute rsx-pad-10 rsx-pad-30-top-xs rsx-pad-30-top-sm']"));
}


public static WebElement ShopClick() throws Exception{
	
	return WebdriverUtils.findElement(By.xpath("//ul[@class='rsx-connector-areas']/li[2]"));
}
	
public static WebElement InternetClick() throws Exception{
	return WebdriverUtils.findElement(By.xpath("//div[@class='menu-flyout menu-flyout-visible']/div[1]/ul[1]/li[4]"));
}

public static WebElement IntpackagesClick() throws Exception{
	return WebdriverUtils.findElement(By.xpath("(//a[@href='/Bell_Internet/Internet_access'])[1]"));

	
}

	
	
	



}

