package Shop_INT_Packages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.utils.WebdriverUtils;

public class IntPackages {
	
	public static WebElement Checkavailabity() throws Exception{
		return WebdriverUtils.findElement(By.id("centralQualButton"));
		
	}
	
	public static WebElement Enteraddress() throws Exception{
		return WebdriverUtils.findElement(By.id("ValidationAddressHowtoBuy"));
		
		
	}
	
	public static WebElement selectaddress() throws Exception{
		WebdriverUtils.waitForVisibilityOfElementLocated(By.xpath("//div[@class='pcaautocomplete pcatext']/div[2]/div[1]"));
		return WebdriverUtils.findElement(By.xpath("//div[@class='pcaautocomplete pcatext']/div[2]/div[1]"));
		
	}
	
	public static WebElement  frameswitch() throws Exception{
		return WebdriverUtils.findElement(By.id("rufous-sandbox"));

		
	}
	
	public static WebElement Selectdropdownaddres() throws Exception{
		return WebdriverUtils.findElement(By.xpath("//div[@title='1 Rockwood Dr']") );
	
	}
	public static WebElement SeeavalbleCTA() throws Exception{
		return WebdriverUtils.findElement(By.xpath("(//button[@class='qual-button'and contains(text(),'See available packages')])[2]") );
		
	}

	public static WebElement OrdernowClick() throws Exception{
		return WebdriverUtils.findElement(By.xpath("(//a[@class='rsx-button rsx-button_small' and contains(text(),'Order now')])[1]"));
		
		
	}
	
	public static WebElement OrderInBundle() throws Exception{
		return WebdriverUtils.findElement(By.xpath("(//a[@class='qual-button qual-button-o' and contains(text(),'Order in a bundle')])[1]]"));
		
	}
	
	public static WebElement OrdernoeBetterBundle() throws Exception{
		return WebdriverUtils.findElement(By.xpath("(//a[@class='qual-button qual-button-o' and contains(text(),'Order in a bundle')])[1]]"));
		
	}	
	
	public static WebElement AddMobility() throws Exception{
		return WebdriverUtils.findElement(By.xpath("//span[@class='rsx-sb-lob-picker-button-label' and contains(text(),'  Add Mobility')]"));
		
	}
	
	public static WebElement CheckoutCTA() throws Exception{
		return WebdriverUtils.findElement(By.id("btnSBCheckout"));
	}
	
}
