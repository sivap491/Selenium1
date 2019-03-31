package com.actions.Shop;

import java.sql.Driver;

import org.apache.poi.ss.formula.ThreeDEval;

import com.pages.login.loginPage;
import com.utils.WebdriverUtils;

import Shop_INT_Packages.IntPackages;
import Shop_navigation.NavigateI_nternet;

public class ShopIntAction {
	
	public static  void shopactions() throws Exception{
		
	WebdriverUtils.clickAction(NavigateI_nternet.PoplightboxClick());
	
	WebdriverUtils.clickAction(NavigateI_nternet.ShopClick());
	WebdriverUtils.clickAction(NavigateI_nternet.InternetClick());
	
	WebdriverUtils.clickAction(NavigateI_nternet.IntpackagesClick());
	WebdriverUtils.clickAction(IntPackages.Checkavailabity());
	WebdriverUtils.sendKeys(IntPackages.Enteraddress(), "m1m2m9 ");
			
	

	
	
	//WebdriverUtils.switchToFrame(IntPackages.frameswitch());
	WebdriverUtils.clickAction(IntPackages.selectaddress());
	//Thread.sleep(5000);
	//WebdriverUtils.clickAction(IntPackages.Selectdropdownaddres());
	Thread.sleep(5000);
	WebdriverUtils.clickAction(IntPackages.SeeavalbleCTA());
	Thread.sleep(5000);
	WebdriverUtils.clickAction(IntPackages.OrdernowClick());
	Thread.sleep(5000);
	WebdriverUtils.clickAction(IntPackages.OrderInBundle());

	Thread.sleep(5000);
	WebdriverUtils.clickAction(IntPackages.OrdernoeBetterBundle());
	Thread.sleep(5000);
	WebdriverUtils.scrollToElement(IntPackages.AddMobility());
	Thread.sleep(5000);
	WebdriverUtils.clickAction(IntPackages.CheckoutCTA());
	
	}
	
	
}
