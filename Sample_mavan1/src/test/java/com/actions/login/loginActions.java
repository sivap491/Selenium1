package com.actions.login;

import com.pages.login.loginPage;
import com.utils.WebdriverUtils;

public class loginActions {
	
	public static void verifyLogin() throws Exception{
		/*loginPage.username().sendKeys("admin");
		loginPage.password().sendKeys("manager");
		loginPage.loginButton().click();*/
		
		WebdriverUtils.sendKeys(loginPage.username(), "admin");
		WebdriverUtils.sendKeys(loginPage.password(), "manager");
		WebdriverUtils.clickAction(loginPage.loginButton());

	}

}
