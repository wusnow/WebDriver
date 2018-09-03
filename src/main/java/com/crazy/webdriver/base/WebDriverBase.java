package com.crazy.webdriver.base;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crazy.webdriver.Page.BasePage;
import com.crazy.webdriver.util.Log;
import com.gargoylesoftware.htmlunit.WebConsole.Logger;

public class WebDriverBase{
	
	private Log logger=Log.getLogger(WebDriverBase.class);
	
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
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\43776\\Desktop\\AutoUI\\Myself\\drivers\\chromedriver.exe");
//		driver=new ChromeDriver(options);
		WebDriver driver = new ChromeDriver();
//		waitDefalt(driver);
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
	
	public static WebDriver driver;
	public static WebDriver openBrowser(String Browser) {
		String path = System.getProperty("user.dir");
		if(Browser.toLowerCase().equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", path+"/drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}else if (Browser.toLowerCase().equals("firefox")) {
			System.setProperty("webdriver.firefox.driver", path+"/drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}else if (Browser.toLowerCase().equals("ie")) {
			System.setProperty("webdriver.ie.driver", path+"/drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}else if (Browser.toLowerCase().equals("safari")) {
			System.setProperty("webdriver.safari.driver", path+"/drivers/safari.exe");
			driver = new SafariDriver();
		}else {
			System.out.println("输入的浏览器名称不准确，输入的名称是："+Browser);
		}
		return driver;
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
	}
	
	
	public static void close(WebDriver driver) {
		//关闭当前窗口页面
		driver.close();
	}
	
	public static void quit(WebDriver driver) {
		//退出
		driver.quit();
	}
	//隐式等待，默认是10s
	public static void waitDefalt(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	}
	//隐式等待，时间自己设定
	public static void wait(WebDriver driver,int time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
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
	}
	
	
	//判断元素是否选中
	public static boolean isSelected(WebDriver driver,By by) {
		return driver.findElement(by).isSelected();
	}
	
	//判断元素是否显示
	
	public static boolean isDisplayed(WebDriver driver,By by) {
		return driver.findElement(by).isDisplayed();
	}
	//判断元素是否被激活
	public static boolean isEnabled(WebDriver driver,By by) {
		return driver.findElement(by).isEnabled();
	}
	//获取元素的属性，可以进行断言操作
	public static String geta(WebDriver driver,By by,String name) {
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
	}
	
	//prompt弹出框的处理，可以输入值
	public static void promptWindow(WebDriver driver) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Alert alert = driver.switchTo().alert();
		alert.sendKeys("xxxxxxxx");
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
//		driver.switchTo().iframe(nameOrID);
	}
	
	//切换到初始的界面
	public static void swithtoDefault(WebDriver driver) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.switchTo().defaultContent();
	}
	//下拉框的处理，标签一般是select标签,通过Index进行定位
	public static void selectByIndex(WebDriver driver,WebElement element,int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}
	//通过value进行定位
	public static void selectByValue(WebDriver driver,WebElement element,String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}
	//通过文本值进行定位
	public static void selectByVisibleText(WebDriver driver,WebElement element,String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}
	
	//切换窗口
	public static void changeHandle(WebDriver driver,String handle) {
//		String handle = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		for(String ae:handles) {
			if(ae.equals(handle)) {
				System.out.println("获取到的handle是：      "+ae);
				continue;
			}else {
				driver.switchTo().window(ae);
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
		
	}
	
	//双击操作
		public static void doubleClick(WebDriver driver,By by) {
			WebElement element = driver.findElement(by);
			//实例化actions
			Actions actions = new Actions(driver);
			actions.doubleClick(element).perform();
		}
		//双击操作
		public static void moveElement(WebDriver driver,By by) {
			WebElement element = driver.findElement(by);
			//实例化actions
			Actions actions = new Actions(driver);
			actions.moveToElement(element).perform();
		}
		
		//拖拽元素到某个元素操作
		public static void dragAndDropByElement(WebDriver driver,By by,WebElement target) {
			WebElement element = driver.findElement(by);
			//实例化actions
			Actions actions = new Actions(driver);
			actions.dragAndDrop(element, target).perform();
		}
		
		//拖拽元素到某个元素操作
		public static void dragAndDropByElementToElement(WebDriver driver,By startBy,By endBy) {
			WebElement elementstart = driver.findElement(startBy);
			WebElement elementend = driver.findElement(endBy);
			//实例化actions
			Actions actions = new Actions(driver);
			actions.clickAndHold(elementstart).moveToElement(elementend).release(elementstart).perform();
		}
		
		//推拽元素到某个坐标点
		public static void dragAndDropByPoint(WebDriver driver,By by,int xOffset,int yOffset) {
			WebElement element = driver.findElement(by);
			//实例化actions
			Actions actions = new Actions(driver);
			actions.dragAndDropBy(element, xOffset, yOffset).perform();
		}
		
		//下拉框多选操作
		public static void moreSelected(WebDriver driver,By listElement,int index) {
			List<WebElement> list = driver.findElements(listElement);
			Actions actions = new Actions(driver);
			actions.keyDown(Keys.CONTROL).click(list.get(index)).release().perform();
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
			
		}
		
		//上传文件操作,但是需要元素的标签是input
		public static void uploadFile(WebDriver driver,By by,String Location) {
			driver.findElement(by).sendKeys(Location);
		}
	
		
		//执行js脚本,document.getElementById("kw").setAttribute("value","");
		public static void execJS(WebDriver driver,String key,String value) {
			JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
			javascriptExecutor.executeScript(key, value);
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
		}
		
		//针对元素进行截图
		public void takeScreenForElement(WebDriver driver,By by,String path,String fileName) throws Exception{
			 // 获得element的位置和大小
			 WebElement element=(WebElement) driver.findElement(by);
			 this.takeScreenForElement(element, path, fileName);
		}
		
		//截图全屏
		public void takeScreen(WebElement element,String path,String fileName) throws Exception{
			File srcFile=element.getScreenshotAs(OutputType.FILE);
			System.out.println(path+fileName);
			FileUtils.copyFile(srcFile,new File(path+"/"+fileName+".png"));
		}
		
		//截图全屏
//		public void takeScreen(String path,String fileName) throws Exception{
//			File srcFile=super.getScreenshotAs(OutputType.FILE);
//			logger.debug(path+fileName);
//			FileUtils.copyFile(srcFile,new File(path+"/"+fileName+".png"));
//		}
		
		
		
		
		
		
	public static void main(String[] args) {
//		System.setProperty("webdriver.edge.driver", "C:\\Users\\43776\\Desktop\\AutoUI\\Myself\\drivers\\MicrosoftWebDriver.exe");
//		WebDriver driver = new EdgeDriver();
	}

}
