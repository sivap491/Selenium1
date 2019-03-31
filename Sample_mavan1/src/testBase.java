package com.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.utils.WebdriverUtils;
import com.utils.utilities;

public class testBase {

	@BeforeMethod(alwaysRun=true)
	public void openBrowser() throws Exception{
		/*ChromeOptions options=new ChromeOptions();
		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		driver=new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	*/
		WebdriverUtils.openBrowser();
		
		//driver.get("http://demo.actitime.com");
		WebdriverUtils.goToURL(utilities.getPropertyValue("url"));
		
	}
	@AfterMethod(alwaysRun=true)
	public void closeBrowser() throws Exception{
		WebdriverUtils.quitBrowser();
	}
	

}
