package com.crazy.webdriver.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
	public String pageSource;
	public WebDriver driver;
	public BasePage(WebDriver driver){
		this.driver=driver;
		this.pageSource=getPageSource();
	}

	public String getPageSource(){
		return driver.getPageSource();
	}
	//输入
	public void sendkeys(WebElement element,String value){
		if(element!=null){
			element.sendKeys(value);
		}else{
			System.out.println("元素没有定位到，是null");
		}
	}
	//直接定位并输入
	public void sendkeys(By by,String value){
		WebElement element=driver.findElement(by);
		sendkeys(element,value);
	}
	//点击
	public void click(WebElement element){
		if(element!=null){
			element.click();
		}else{
			System.out.println("元素没有定位到，是null");
		}
	}
	//定位并点击
	public void click(By by){
		WebElement element=driver.findElement(by);
		click(element);
	}
	//清除
	public void clear(WebElement element){
		if(element!=null){
			element.clear();
		}else{
			System.out.println("元素没有定位到，是null");
		}
	}
}