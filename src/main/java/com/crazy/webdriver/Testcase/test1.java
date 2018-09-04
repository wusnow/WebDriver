package com.crazy.webdriver.Testcase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
//		driver = WebDriverBase.openBrowser("chrome");
		System.setProperty("webdriver.chrome.driver", "D:\\Github\\WebDriver\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		myAssert = new Assertion(driver);
		
	}
	
	
	@Test
	public void case1() {
//		WebDriverBase.getURL(driver, "https://www.baidu.com");
		driver.get("https://www.baidu.com");
		
		myAssert.assertEquals(driver,WebDriverBase.findElement(driver,By.xpath("//*[@id=\"u1\"]/a[1]")).getText(),"加的", "news");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
