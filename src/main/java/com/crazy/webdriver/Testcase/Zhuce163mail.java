package com.crazy.webdriver.Testcase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import com.crazy.webdriver.MyAssert.Assertion;
import com.crazy.webdriver.base.WebDriverBase;
import com.crazy.webdriver.util.GetByLocator;

public class Zhuce163mail {
	
	WebDriver driver;
	Assertion myAssertion;
	
	@BeforeMethod
	public void beforeClass() {
		
		driver = WebDriverBase.openChrome();
		myAssertion = new Assertion(driver);
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\43776\\Desktop\\AutoUI\\Myself\\drivers\\chromedriver.exe");
////		driver=new ChromeDriver(options);
//		driver = new ChromeDriver();
	}
	
	@Test
	public void zhuce163() {
		driver.get("https://mail.163.com");
		driver.manage().window().maximize();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String handle = driver.getWindowHandle();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("==========================================================");
//		WebDriverBase.iframe(driver,"x-URS-iframe");
		driver.switchTo().frame(0);
		driver.findElement(By.id("changepage")).click();
		WebDriverBase.changeHandle(driver, handle);
		String otherhandle = driver.getWindowHandle();
		
		driver.findElement(By.id("nameIpt")).sendKeys("sdfasdfsadfk");
		driver.findElement(By.id("mainPwdIpt")).sendKeys("sdfjkajsdflkjl");
		driver.findElement(By.id("mainCfmPwdIpt")).sendKeys("sdfjkajsdflkjl");
		driver.findElement(By.id("mainMobileIpt")).sendKeys("13358067845");
		driver.findElement(By.id("vcodeIpt")).sendKeys("132132");
		driver.findElement(By.id("mainAcodeIpt")).sendKeys("132132");
//		driver.findElement(By.id("mainRegA")).click();
		driver.findElement(GetByLocator.getLocator("main")).click();
		
		
		WebDriverBase.xianshiWait(driver, By.xpath("//*[@id=\"m_mainAcode\"]/span1111111"));
		String Error = driver.findElement(By.xpath("//*[@id=\"m_mainAcode\"]/span111")).getText();
		
		
//		Assert.assertEquals(Error, "  手机验证码不正确，请重新填写");
		
		myAssertion.assertEquals(driver, Error, "手机验证码不正确，请重新填写", "signupError");
	}

	
	@AfterMethod
	public void afterClass() {
		driver.quit();
	}
	
}
