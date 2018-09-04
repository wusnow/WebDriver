package com.crazy.webdriver.Testcase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.crazy.webdriver.MyAssert.Assertion;
import com.crazy.webdriver.base.WebDriverBase;

public class test1{
	WebDriver driver;
	Assertion myAssert;
	@BeforeClass
	public void beforeClass() {
		driver = WebDriverBase.openBrowser("chrome",driver);
		myAssert = new Assertion(driver);
		
	}
	
	
	@Test
	public void case1() {
		WebDriverBase.getURL(driver, "https://www.baidu.com");
		
		myAssert.assertEquals(driver.findElement(By.xpath("//*[@id=\"u_sp\"]/a[1]")).getText(),"加的", "news");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
