package com.crazy.webdriver.Page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crazy.webdriver.base.WebDriverBase;
import com.crazy.webdriver.util.GetByLocator;
import com.crazy.webdriver.util.Log;


public class BasePage extends WebDriverBase{
	private Log logger=Log.getLogger(BasePage.class);
	public String pageSource;
	public BasePage(WebDriver driver){
		super.driver=driver;
		this.pageSource=getPageSource();
	}

	public String getPageSource(){
		logger.debug(driver.getPageSource());
		return driver.getPageSource();
	}
	//输入
	public void sendkeys(WebElement element,String value){
		if(element!=null){
			element.sendKeys(value);
		}else{
			logger.error("元素没有定位到，是null");
		}
	}

	//点击
	public void click(WebElement element){
		if(element!=null){
			element.click();
		}else{
			logger.error("元素没有定位到，是null");
		}
	}
	
	//清除
	public void clear(WebElement element){
		if(element!=null){
			element.clear();
		}else{
			logger.error("元素没有定位到，是null");
		}
	}
	//点击元素，根据元素表中定位
	public void clickByKey(String key) {
		WebDriverBase.xianshiWait(driver, GetByLocator.getLocator(key));
		try {
			driver.findElement(GetByLocator.getLocator(key)).click();
			logger.debug(driver.findElement(GetByLocator.getLocator(key)).getText());
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
			logger.error("没有定位到元素");
		}

	}
	
	//点击元素，根据by进行定位
	public void clickByElement(By by) {

		try {
			WebDriverBase.xianshiWait(driver, by);
			driver.findElement(by).click();
			logger.debug(driver.findElement(by).getText());
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
			logger.error("没有定位到元素");
		}
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
			logger.debug(driver.findElement(by).getText());
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("元素:    "+by+"未找到！");
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
			logger.error("元素：      "+by+"未找到！");
			e.printStackTrace();
		}
		return webElements;
	}
	
	/**
	 * 
	* @Title: sendkeys  
	* @Description: TODO(元素框内先清空，后输入)  
	* @param @param by
	* @param @param text    参数  
	* @return void    返回类型  
	* @throws  
	* @author duanhao
	 */
	public void sendkeys(By by,String text) {
		findElement(by).clear();
		findElement(by).sendKeys(text);
		logger.debug("输入的元素是：   "+text);
		
	}
	
	/**
	 * 
	* @Title: getText  
	* @Description: TODO(获取元素的text)  
	* @param @param by
	* @param @return    参数  
	* @return String    返回类型  
	* @throws  
	* @author duanhao
	 */
	public String getText(By by) {
		try {
			logger.debug(findElement(by).getText());
			return findElement(by).getText();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("没有找到元素");
		}
		return null;
		
	}
	
	
	/**
	 * 
	* @Title: getTexts  
	* @Description: TODO(获取多个元素的文本值)  
	* @param @param by
	* @param @return    参数  
	* @return List<String>    返回类型  
	* @throws  
	* @author duanhao
	 */
	public List<String> getTexts(By by){
		ArrayList arrayList = new ArrayList();
		List<WebElement> lists = findElements(by);
//		for(int i = 0;i<lists.size();i++) {
//			String text = lists.get(i).getText();
//			arrayList.add(text);
//		}
		
		
		for(WebElement ae:lists) {
			arrayList.add(ae.getText());
			logger.debug(ae.getText());
		}
		return arrayList;
	}
	
	
}