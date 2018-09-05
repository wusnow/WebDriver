package com.crazy.webdriver.base;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.crazy.webdriver.util.GetByLocator;
import com.crazy.webdriver.util.Log;
import com.crazy.webdriver.util.SendMail;

public class WebDriverBase{
	public static Log logger=Log.getLogger(WebDriverBase.class);
	public static WebDriver driver;
	
	@DataProvider(name = "Browser")
	public Object[][] getBrowser(){
		return new Object[][] {{"http://192.168.20.1:4444","firefox"},{"http://192.168.20.1:4444","chrome"}};
	}
	
	

	//启动68及以上的chrome
	public static WebDriver openChrome() {
//		String downloadFilepath = "D:\\soft";
//		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
//		chromePrefs.put("profile.default_content_settings.popups", 0);
//		chromePrefs.put("download.default_directory", downloadFilepath);
//		ChromeOptions options = new ChromeOptions();
//		options.setExperimentalOption("prefs", chromePrefs);
		System.setProperty("webdriver.chrome.driver", "D:\\Github\\WebDriver\\drivers\\chromedriver.exe");
//		driver=new ChromeDriver(options);
		WebDriver driver = new ChromeDriver();
//		waitDefalt(driver);
		logger.debug("浏览器启动成功");
		return driver;
	}
	
	//启动chrome浏览器，通过grid
	@Test(dataProvider = "Browser")
	public static WebDriver openChromeByGrid(String url,String browser) {
		DesiredCapabilities caps;
		WebDriver driver;
		if(browser.toLowerCase().equals("chrome")) {
			caps = DesiredCapabilities.chrome();
		}else if (browser.toLowerCase().equals("firefox")) {
			caps = DesiredCapabilities.firefox();
		}else if (browser.toLowerCase().equals("ie")) {
			caps = DesiredCapabilities.internetExplorer();
		}else if (browser.toLowerCase().equals("safari")) {
			caps = DesiredCapabilities.safari();
		}else {
			caps = DesiredCapabilities.edge();
		}
		
		String URL1 = url+"/wd/hub";
		
		
		try {
			driver = new RemoteWebDriver(new URL(URL1),caps);
			return driver;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	public static WebDriver openBrowser(String Browser) {
		String path = System.getProperty("user.dir");
		
		try {
			if(Browser.toLowerCase().equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", path+"/drivers/chromedriver.exe");
				driver = new ChromeDriver();
				logger.debug("谷歌浏览器启动成功");
			}else if (Browser.toLowerCase().equals("firefox")) {
				System.setProperty("webdriver.firefox.driver", path+"/drivers/geckodriver.exe");
				driver = new FirefoxDriver();
				logger.debug("火狐浏览器启动成功");
			}else if (Browser.toLowerCase().equals("ie")) {
				System.setProperty("webdriver.ie.driver", path+"/drivers/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				logger.debug("IE浏览器启动成功");
			}else if (Browser.toLowerCase().equals("safari")) {
				System.setProperty("webdriver.safari.driver", path+"/drivers/safari.exe");
				driver = new SafariDriver();
				logger.debug("Safari浏览器启动成功");
			}else {
				logger.debug("输入的浏览器名称不准确，输入的名称是：    "+Browser);
				driver = null;
			}
			return driver;
		} catch (Exception e) {
			// TODO: handle exception
			SendMail.send("driver启动失败", e.getMessage());
			logger.error("driver启动失败");
			return driver;
		}

		
	}
	
	//启动不是在默认路径安装的火狐浏览器,支持57及以上
	public static WebDriver openFF() {
//		FirefoxProfile firefoxProfile = new FirefoxProfile();
//		//设置火狐的默认下载文件夹，0表示桌面，1表示我的下载，2表示自定义文件夹
//		firefoxProfile.setPreference("browser.download.folderList", "2");
//		//设置自定义文件夹位置
//		firefoxProfile.setPreference("browser.download.dir", "D:\\soft");
//		//设置无需确认下载的文件格式
//		firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-system,application/vnd.ms-sxcel,text/csv,application/zip,application/exe");
		System.setProperty("webdriver.gecko.driver", "E:\\EclipseOxygen\\WorkSpace\\Myself\\drivers\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		logger.debug("火狐浏览器启动成功");
		return driver;
	}
	
	//phantomJS启动方式
	public static WebDriver openPhantomJS(WebDriver driver) {
		System.setProperty("phantomjs.binary.path", "C:\\Users\\43776\\Desktop\\AutoUI\\Myself\\drivers\\phantomjs.exe");
		driver = new FirefoxDriver();
		return driver;
	}
	
	
	//启动IE浏览器需要先将IE选项中的启动保护模式关闭，才能正常的启动IE浏览器
	/**
	 * 
	* @Title: openIE  
	* @Description: TODO(启动IE浏览器)  
	* @param @param driver
	* @param @return    参数  
	* @return WebDriver    返回类型  
	* @throws  
	* @author duanhao
	 */
	public static WebDriver openIE(WebDriver driver) {
		System.setProperty("webdriver.ie.driver", "C:\\Users\\43776\\Desktop\\AutoUI\\Myself\\drivers\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		waitDefalt(driver);
		return driver;
	}
	
	/**
	 * 
	* @Title: openEdge  
	* @Description: TODO(启动edge浏览器)  
	* @param @param driver
	* @param @return    参数  
	* @return WebDriver    返回类型  
	* @throws  
	* @author duanhao
	 */
	public static WebDriver openEdge(WebDriver driver) {
		System.setProperty("webdriver.edge.driver", "C:\\Users\\43776\\Desktop\\AutoUI\\Myself\\drivers\\MicrosoftWebDriver.exe");
		driver = new EdgeDriver();
		waitDefalt(driver);
		return driver;
	}
	
	
	/**
	 * 
	* @Title: getURL  
	* @Description: TODO(获取URL时，添加了隐式等待)  
	* @param @param driver
	* @param @param URL    参数  
	* @return void    返回类型  
	* @throws  
	* @author duanhao
	 */
	public static void getURL(WebDriver driver,String URL) {
		waitDefalt(driver);
		driver.get(URL);
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		logger.debug("打开网页："+URL);
	}
	
	
	public static void close(WebDriver driver) {
		//关闭当前窗口页面
		
		driver.close();
		logger.debug("关闭当前窗口");
	}
	
	public static void quit(WebDriver driver) {
		//退出
		driver.quit();
		logger.debug("退出浏览器");
	}
	//隐式等待，默认是10s
	public static void waitDefalt(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		logger.debug("隐式等待30s");
	}
	//隐式等待，时间自己设定
	public static void wait(WebDriver driver,int time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		logger.debug("隐式等待"+time+"s");
	}
	
	
	/**
	 * 
	* @Title: xianshiWait  
	* @Description: TODO(显式等待的方法)  
	* @param @param driver
	* @param @param by    要等待的元素
	* @return void    返回类型  
	* @throws  
	* @author duanhao
	 */
	public static void xianshiWait(WebDriver driver,By by) {
		new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(by));
		logger.debug("显式等待30s");
	}
	
	public static void xianshiWaitByTime(WebDriver driver,By by,int time) {
		new WebDriverWait(driver, time).until(ExpectedConditions.presenceOfElementLocated(by));
		logger.debug("显式等待"+time+"s");
	}
	
	
	//判断元素是否选中
	public static boolean isSelected(WebDriver driver,By by) {
		logger.debug("选中元素");
		return driver.findElement(by).isSelected();
	}
	
	
	

	public static void scrollingToDown() {
		// 使用JavaScript的scrollTo方法和document.body.scrollHeight参数，将页面的滚动条滑动到页面的最下方
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
		ThreadSleep(5);
		logger.debug("将页面的滚动条滑动到页面的最下方,执行等待5s");
	}
	
	
	
	
	//判断元素是否显示
	
	public static boolean isDisplayed(WebDriver driver,By by) {
		logger.debug("元素是否显示：    "+driver.findElement(by).isDisplayed());
		return driver.findElement(by).isDisplayed();
	}
	//判断元素是否被激活
	public static boolean isEnabled(WebDriver driver,By by) {
		logger.debug("元素是否被激活：   "+driver.findElement(by).isEnabled());
		return driver.findElement(by).isEnabled();
	}
	//获取元素的属性，可以进行断言操作
	public static String geta(WebDriver driver,By by,String name) {
		logger.debug("获取元素的属性：     "+driver.findElement(by).getAttribute(name));
		return driver.findElement(by).getAttribute(name);
	}
	//Alert弹窗
	public static void AlertWindow(WebDriver driver) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//把控制权转交给alert
		Alert alert = driver.switchTo().alert();
		alert.accept();
		logger.debug("切换Alert弹窗");
	}
	
	//confirm弹窗的处理,弹窗出现后点击取消按钮
	public static void confirmWindow(WebDriver driver) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
		logger.debug("confirm弹窗的处理,弹窗出现后点击取消按钮");
	}
	
	//prompt弹出框的处理，可以输入值
	public static void promptWindow(WebDriver driver,String sendKeys) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(sendKeys);
		logger.debug("prompt弹出框的处理，输入：    "+sendKeys);
	}
	
	//iframe子框架的处理
	public static void iframe(WebDriver driver,String nameOrID) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.switchTo().frame(nameOrID);
		logger.debug("切换到"+nameOrID+"的iframe子框架");
//		driver.switchTo().iframe(nameOrID);
	}
	
	//切换到初始的框架
	public static void swithToDefault(WebDriver driver) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.switchTo().defaultContent();
		logger.debug("切换到初始的框架");
	}
	//下拉框的处理，标签一般是select标签,通过Index进行定位
	public static void selectByIndex(WebDriver driver,WebElement element,int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
		logger.debug("下拉框选择select标签：  "+index);
	}
	//通过value进行定位
	public static void selectByValue(WebDriver driver,WebElement element,String value) {
		Select select = new Select(element);
		select.selectByValue(value);
		logger.debug("下拉框选择select标签："+value);
	}
	//通过文本值进行定位
	public static void selectByVisibleText(WebDriver driver,WebElement element,String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
		logger.debug("下拉框选择select标签："+text);
	}
	
	//切换窗口
	public static void changeHandle(WebDriver driver,String handle) {
//		String handle = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		for(String ae:handles) {
			if(ae.equals(handle)) {
				logger.debug("获取到的handle是：      "+ae);
				continue;
			}else {
				driver.switchTo().window(ae);
				logger.debug("跳转到的handle是：      "+ae);
			}
		}
	}
	
	
	
	//Actions方法的封装
	//右键点击
	public static void rightClick(WebDriver driver,By by) {
		WebElement element = driver.findElement(by);
		//实例化actions
		Actions actions = new Actions(driver);
		actions.contextClick(element).perform();
		logger.debug("右键点击");
	}
	
	//双击操作
		public static void doubleClick(WebDriver driver,By by) {
			WebElement element = driver.findElement(by);
			//实例化actions
			Actions actions = new Actions(driver);
			actions.doubleClick(element).perform();
			logger.debug("双击操作");
		}
		//移动光标到某个元素
		public static void moveElement(WebDriver driver,By by) {
			WebElement element = driver.findElement(by);
			//实例化actions
			Actions actions = new Actions(driver);
			actions.moveToElement(element).perform();
			logger.debug("移动光标到：    "+element.getText());
		}
		
		//拖拽元素到某个元素操作
		public static void dragAndDropByElement(WebDriver driver,By by,WebElement target) {
			WebElement element = driver.findElement(by);
			//实例化actions
			Actions actions = new Actions(driver);
			actions.dragAndDrop(element, target).perform();
			logger.debug("拖拽元素到：    "+element.getText());
		}
		
		//拖拽元素到某个元素操作
		public static void dragAndDropByElementToElement(WebDriver driver,By startBy,By endBy) {
			WebElement elementstart = driver.findElement(startBy);
			WebElement elementend = driver.findElement(endBy);
			//实例化actions
			Actions actions = new Actions(driver);
			actions.clickAndHold(elementstart).moveToElement(elementend).release(elementstart).perform();
			logger.debug("拖拽元素到：    "+elementend.getText());
		}
		
		//推拽元素到某个坐标点
		public static void dragAndDropByPoint(WebDriver driver,By by,int xOffset,int yOffset) {
			WebElement element = driver.findElement(by);
			//实例化actions
			Actions actions = new Actions(driver);
			actions.dragAndDropBy(element, xOffset, yOffset).perform();
			logger.debug("拖拽元素到：       "+xOffset+":"+yOffset);
		}
		
		//下拉框多选操作
		public static void moreSelected(WebDriver driver,By listElement,int index) {
			List<WebElement> list = driver.findElements(listElement);
			Actions actions = new Actions(driver);
			actions.keyDown(Keys.CONTROL).click(list.get(index)).release().perform();
			logger.debug("下拉框多选");
		}
		
		
		
		//Robot模拟键盘操作
		public static void robotCtrlAddS() {
			try {
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_S);
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.debug("键盘模拟Ctrl+S操作");
		}
		
		//上传文件操作,但是需要元素的标签是input
		public static void uploadFile(WebDriver driver,By by,String Location) {
			driver.findElement(by).sendKeys(Location);
			logger.debug("上传文件地址：           "+Location);
		}
	
		
		//执行js脚本,document.getElementById("kw").setAttribute("value","");
		public static void execJS(WebDriver driver,String key,String value) {
			JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
			javascriptExecutor.executeScript(key, value);
			logger.debug("执行js脚本");
		}
		
		//针对元素进行截图
		public void takeScreenForElement(WebElement element,String path,String fileName) throws Exception{
			 // 获得element的位置和大小
			 Point location = element.getLocation();
			 Dimension size = element.getSize();
			 byte[] imageByte=element.getScreenshotAs(OutputType.BYTES);
			 // 创建全屏截图
			 BufferedImage originalImage =ImageIO.read(new ByteArrayInputStream(imageByte));
			 // 截取element所在位置的子图。
			 BufferedImage croppedImage = originalImage.getSubimage(
			  location.getX(),
			  location.getY(),
			  size.getWidth(),
			  size.getHeight());
			 try {
				  ImageIO.write(croppedImage, "png", new File(path+fileName+".png"));
					//ImageIO.write(im, formatName, output)
			 }catch (IOException e) {
					// TODO Auto-generated catch block
				  e.printStackTrace();
			 }
			 logger.debug("对元素进行截图，保存路径是：           "+path);
		}
		
		//针对元素进行截图
		public void takeScreenForElement(WebDriver driver,By by,String path,String fileName) throws Exception{
			 // 获得element的位置和大小
			 WebElement element=(WebElement) driver.findElement(by);
			 this.takeScreenForElement(element, path, fileName);
			 logger.debug("对元素进行截图，保存路径是：           "+path);
		}
		
		//截图全屏
		public void takeScreen(WebElement element,String path,String fileName) throws Exception{
			File srcFile=element.getScreenshotAs(OutputType.FILE);
			System.out.println(path+fileName);
			FileUtils.copyFile(srcFile,new File(path+"/"+fileName+".png"));
			logger.debug("截取全屏，路径是：          "+path);
		}
		
		//截图全屏
//		public void takeScreen(String path,String fileName) throws Exception{
//			File srcFile=super.getScreenshotAs(OutputType.FILE);
//			logger.debug(path+fileName);
//			FileUtils.copyFile(srcFile,new File(path+"/"+fileName+".png"));
//		}
		
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
		public void click(By by) {

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
		
		
		public static WebElement findElement(WebDriver driver,By by) {

			try {
				WebDriverBase.xianshiWait(driver, by);
				driver.findElement(by);
				logger.debug(driver.findElement(by).getText());
				return driver.findElement(by);
			} catch (Exception e) {
				// TODO: handle exception
				e.getMessage();
				logger.error("没有定位到元素");
				return null;
			}
		}
		
		
		
		public String pageSource;
		public void BasePage(){
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

		
		public static void ThreadSleep(int Time) {
			try {
				Thread.sleep(Time*1000);
				logger.debug("线程死等时间是   "+Time+"   秒");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.debug("线程等待失败");
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
//			for(int i = 0;i<lists.size();i++) {
//				String text = lists.get(i).getText();
//				arrayList.add(text);
//			}
			
			
			for(WebElement ae:lists) {
				arrayList.add(ae.getText());
				logger.debug(ae.getText());
			}
			return arrayList;
		}
		
		public boolean fileExists(String filepath) {
			return new File(filepath).exists();
		}
		
		
		
	public static void main(String[] args) {
//		System.setProperty("webdriver.edge.driver", "C:\\Users\\43776\\Desktop\\AutoUI\\Myself\\drivers\\MicrosoftWebDriver.exe");
//		WebDriver driver = new EdgeDriver();
	}

}
