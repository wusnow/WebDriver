package com.crazy.webdriver.Page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crazy.webdriver.base.WebDriverBase;
import com.crazy.webdriver.util.GetByLocator;

public class BasePage extends WebDriverBase{
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
	
	//清除
	public void clear(WebElement element){
		if(element!=null){
			element.clear();
		}else{
			System.out.println("元素没有定位到，是null");
		}
	}
	//点击元素，根据元素表中定位
	public void clickByKey(String key) {
		WebDriverBase.xianshiWait(driver, GetByLocator.getLocator(key));
		driver.findElement(GetByLocator.getLocator(key)).click();
	}
	
	//点击元素，根据by进行定位
	public void clickByElement(By by) {
		WebDriverBase.xianshiWait(driver, by);
		driver.findElement(by).click();
	}
	
	/**
	 * 
	* @Title: findElement  
	* @Description: TODO(显示等待一个元素出现，查找该元素)  
	* @param @param by
	* @param @return    参数  
	* @return WebElement    返回类型  
	* @throws  
	* @author duanhao
	 */
	public WebElement findElement(By by) {
		try {
			WebDriverBase.xianshiWait(driver, by);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("元素:    "+by+"未找到！");
			e.printStackTrace();
		}
		return driver.findElement(by);
		
		
	}
	
	
	/**
	 * 
	* @Title: findElements  
	* @Description: TODO(查找多个元素等待元素出现的封装)  
	* @param @param by
	* @param @return    参数  
	* @return List<WebElement>    返回类型  
	* @throws  
	* @author duanhao
	 */
	public List<WebElement> findElements(By by) {
		List<WebElement> webElements = null;
		try {
			webElements = new WebDriverWait(driver, 30).until(new ExpectedCondition<List<WebElement>>() {
				public List<WebElement> apply(WebDriver driver){
					return driver.findElements(by);
				}
			});
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("元素：      "+by+"未找到！");
			e.printStackTrace();
		}
		return webElements;
	}
	
}