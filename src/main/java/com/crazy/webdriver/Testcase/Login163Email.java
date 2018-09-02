package com.crazy.webdriver.Testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crazy.webdriver.base.WebDriverBase;

public class Login163Email {
	WebDriver driver;
//	
//	@DataProvider(name = "Browser")
//	public Object[][] getBrowser(){
//		return new Object[][] {{"http://192.168.20.1:4444","firefox"},{"http://192.168.20.1:4444","chrome"}};
//	}
//	
//	@Test(dataProvider = "Browser")
//	public  void openChromeByGrid(String url,String browser) throws Exception {
//		DesiredCapabilities caps;
//		WebDriver driver;
//		if(browser.toLowerCase().equals("chrome")) {
//			caps = DesiredCapabilities.chrome();
//		}else if (browser.toLowerCase().equals("firefox")) {
//			caps = DesiredCapabilities.firefox();
//		}else if (browser.toLowerCase().equals("ie")) {
//			caps = DesiredCapabilities.internetExplorer();
//		}else if (browser.toLowerCase().equals("safari")) {
//			caps = DesiredCapabilities.safari();
//		}else {
//			caps = DesiredCapabilities.edge();
//		}
//		
//		String URL1 = url+"/wd/hub";
//
//		driver = new RemoteWebDriver(new URL(URL1),caps);
//	
//		driver.get("http://www.baidu.com");
//		driver.quit();
//	}
	
	
	
	@BeforeClass
	public void beforeClass() {
		driver = WebDriverBase.openChrome();
//		driver = WebDriverBase.openChromeByGrid(url, browser);
	}
	
	@Test
	public void login163() {
		driver.get("https://mail.163.com");
		driver.manage().window().maximize();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("lbNormal")).click();
		WebDriverBase.iframe(driver, "x-URS-iframe");
		
//		WebElement frameElement = driver.findElement(By.id("x-URS-iframe"));
//		driver.switchTo().frame(0);
		driver.findElement(By.name("email")).sendKeys("18025478840");
		driver.findElement(By.name("password")).sendKeys("62218852000duan");
		
		System.out.println(WebDriverBase.isSelected(driver, By.id("un-login")));
		
		driver.findElement(By.id("un-login")).click();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("dologin")).click();
		
		System.out.println(WebDriverBase.isSelected(driver, By.id("un-login")));
		
//		WebDriverBase.xianshiWait(driver, By.linkText("退出"));
//		String loginString = driver.findElement(By.linkText("退出")).getText();
//		System.out.println(loginString);
//		String titleString = driver.getTitle();
//		
//		Assert.assertEquals(titleString, "网易邮箱6.0版");
		
		WebDriverBase.xianshiWait(driver, By.xpath("/html/body/div[1]/nav[1]/div[1]/ul[1]/li[2]"));
		
		driver.findElement(By.xpath("/html/body/div[1]/nav[1]/div[1]/ul[1]/li[2]")).click();
		WebDriverBase.xianshiWait(driver, By.className("nui-editableAddr-ipt"));
		driver.findElement(By.className("nui-editableAddr-ipt")).sendKeys("437765664@qq.com");
		List<WebElement> listElements = driver.findElements(By.className("nui-ipt-input"));
		listElements.get(2).sendKeys("这是一个测试主题，请忽略");
		WebElement iframeElement = driver.findElement(By.className("APP-editor-iframe"));
//		WebDriverBase.iframe(driver, "APP-editor-iframe");
		driver.switchTo().frame(iframeElement);
		driver.findElement(By.className("nui-scroll")).sendKeys("这是一个测试内容，请忽略");
		driver.switchTo().defaultContent();
		WebDriverBase.xianshiWait(driver, By.xpath("//*[@id=\"dvContentContainer\"]/div[1]/div[2]/div[1]/section[1]/header[1]/div[3]/div[1]/div[2]/input[1]"));
		driver.findElement(By.xpath("//*[@id=\"dvContentContainer\"]/div[1]/div[2]/div[1]/section[1]/header[1]/div[3]/div[1]/div[2]/input[1]")).sendKeys("C:\\Users\\43776\\Desktop\\360截图17860607111145145.png");;
		WebDriverBase.xianshiWait(driver, By.xpath("//*[@id=\"dvContentContainer\"]/div[1]/div[2]/div[1]/section[1]/footer[1]/div[1]/span[1]"));
		driver.findElement(By.xpath("//*[@id=\"dvContentContainer\"]/div[1]/div[2]/div[1]/section[1]/footer[1]/div[1]/span[1]")).click();
//		WebDriverBase.xianshiWait(driver, By.className("nui-btn-text"));
//		driver.findElements(By.className("nui-btn-text")).get(12).click();
		WebDriverBase.xianshiWait(driver, By.xpath("/html/body/div[9]/div[3]/div[2]/div[2]/span[1]"));
		driver.findElement(By.xpath("/html/body/div[9]/div[3]/div[2]/div[2]/span[1]")).click();
		
		WebDriverBase.xianshiWait(driver, By.xpath("//*[@id=\"dvContentContainer\"]/div/div[2]/div[2]/section/h1/span"));
		String Access = driver.findElement(By.xpath("//*[@id=\"dvContentContainer\"]/div/div[2]/div[2]/section/h1/span")).getText();
		AssertJUnit.assertEquals(Access, "可用手机接收回复");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	@Test
	public void testGrid() {
		driver.get("https://www.baidu.com");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
