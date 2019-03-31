package com.login.loginTestCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.actions.Shop.ShopIntAction;
import com.actions.login.loginActions;
import com.base.testBase;

import Shop_INT_Packages.IntPackages;
import Shop_navigation.NavigateI_nternet;

public class loginTest extends testBase{
	
	@Test
	public void verifyLogin() throws Exception{
		
		//driver.findElement(By.name("username")).sendKeys("admin");
		//driver.findElement(By.name("pwd")).sendKeys("manager");
		//driver.findElement(By.id("loginButton")).click();
		
		//String act=driver.findElement(By.xpath("//a[@class='userProfileLink username']")).getText();
		
		//Assert.assertEquals(act, "Robert Barber");
		
		ShopIntAction.shopactions();
		
	}

}
