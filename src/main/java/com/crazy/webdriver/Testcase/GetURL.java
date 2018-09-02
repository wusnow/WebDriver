package com.crazy.webdriver.Testcase;


import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.crazy.webdriver.base.WebDriverBase;

public class GetURL {
	WebDriver driver;
	@BeforeClass
	public void beforeClass() {
		
		driver = WebDriverBase.openChrome();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getBaidu() throws Exception {
		
		driver.get("https://www.jd.com");
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		//后退
		driver.navigate().back();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//前进
		driver.navigate().forward();
		
		driver.navigate().refresh();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String oldURL = driver.getCurrentUrl();
		
		System.out.println("获取到的URL是:     "+oldURL);
		
		//设置页面窗口大小
		Dimension dimension = new Dimension(500,500);
		driver.manage().window().setSize(dimension);
		
		
		
		//最大化窗口
		driver.manage().window().maximize();
		System.out.println(driver.getTitle());
		driver.findElement(By.className("link-login")).click();
		driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/div/div[3]/a")).click();
//		driver.findElement(By.linkText("《京东隐私政策》")).click();
//		driver.navigate().back();
//		driver.findElement(By.partialLinkText("京东隐私")).click();
		driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/div/div[3]/a")).click();
		driver.findElement(By.name("loginname")).sendKeys("437765664@qq.com");
		driver.findElement(By.name("nloginpwd")).sendKeys("dvan-ha0dhqianli");
		driver.findElement(By.id("loginsubmit")).click();
			
		Thread.sleep(5000);
		String newURL = driver.getCurrentUrl();
		System.out.println("获取到新的界面的URL是：   "+newURL);
		Assert.assertEquals(newURL.toLowerCase().contains("jd.com"), true);
		
		
		
		
	}
	
	
	@Test
	public void clearTest() {
		WebDriverBase.waitDefalt(driver);
		
		driver.get("http://www.baidu.com");
		driver.findElement(By.id("kw")).sendKeys("selenium");
		driver.findElement(By.id("su")).click();
		System.out.println(driver.getTitle());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String title = driver.getTitle();
		System.out.println(driver.findElement(By.id("su")).getAttribute("value"));
		driver.findElement(By.id("kw")).clear();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assert.assertEquals(title, "selenium_百度搜索");
		
		
	}
	
	
	@Test
	public void changeHandle() {
		WebDriverBase.waitDefalt(driver);
		driver.get("https://www.jd.com");
		driver.manage().window().maximize();
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		driver.findElement(By.className("link-login")).click();
		driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/div/div[3]/a")).click();
		String handle = driver.getWindowHandle();
		WebDriverBase.changeHandle(driver, handle);
		driver.findElement(By.linkText("《京东隐私政策》")).click();
//		driver.close();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		driver.switchTo().window(handle);
		driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/div/div[3]/a")).click();
		driver.findElement(By.name("loginname")).sendKeys("437765664@qq.com");
		driver.findElement(By.name("nloginpwd")).sendKeys("dvan-ha0dhqianli");
		driver.findElement(By.id("loginsubmit")).click();
			
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@AfterClass
	public void quit() {
		
		
		driver.quit();
		
		
	}
	
}
