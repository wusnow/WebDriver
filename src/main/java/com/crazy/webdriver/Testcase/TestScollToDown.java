package com.crazy.webdriver.Testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.crazy.webdriver.base.WebDriverBase;

public class TestScollToDown extends WebDriverBase{
	
	@BeforeClass
	public void beforeClass() {
		driver = openBrowser("chrome");
	}
	
	@Test
	public void test() {
		getURL(driver, "https://www.jd.com");
		driver.manage().window().maximize();
		ThreadSleep(10);
		scrollingToDown();
		ThreadSleep(5);
	}
	
	
	@AfterClass
	public void afterClass() {
		quit(driver);
	}
	
	
	
	
}
