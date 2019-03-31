package com.pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.utils.WebdriverUtils;

public class loginPage {

	//public static WebDriver driver;
	
	public static WebElement username() throws Exception{
		return WebdriverUtils.findElement(By.name("username"));
	}
	
	public static WebElement password() throws Exception{
		return WebdriverUtils.findElement(By.name("pwd"));

	}
	
	public static WebElement loginButton() throws Exception{
		return WebdriverUtils.findElement(By.id("loginButton"));
	}
	
}
