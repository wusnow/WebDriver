package com.crazy.webdriver.imageUtils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

import com.crazy.webdriver.base.CrazyPath;
import com.crazy.webdriver.base.WebDriverBase;
import com.crazy.webdriver.util.DateFormat;
import com.crazy.webdriver.util.Log;
import com.crazy.webdriver.util.MyFile;
import java.io.File;
import java.io.IOException;


/**
 * Created by lenovo on 2016/11/12.必须要继承SeleniumDrivers
 */
public class ScreenShort extends WebDriverBase {
    static final Log logger=Log.getLogger(ScreenShort.class);
    static String path = CrazyPath.path;
    static String tomcatPath = "D:/apache-tomcat-8.5.33/webapps/ROOT/";
    /**
     * 错误截图，通过日期命名的截图
     **/
    public static void screenShots() {
        WebDriver augmentedDriver = new Augmenter().augment(driver);
        File file = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
          //根据日期创建文件夹，CHECK_LOG_FORMAT = "yyyyMMdd";REPORT_CSV_FORMAT = "yyyyMMdd_HHmmss";
        String myPath=path+String.valueOf(System.currentTimeMillis());
        System.out.print(myPath);
        MyFile.createFile1(myPath);
        try {
            //FileUtils.copyFile(file,new File("D:\\testing\\webdriver_demo\\aa.png"));
            //FileUtils.copyFile(file,new File("D:\\testing\\webdriver_demo\\"+DateFormat.format(DateFormat.CHECK_LOG_FORMAT)+".png"));
            String times= String.valueOf(System.currentTimeMillis());
            FileUtils.copyFile(file,new File(myPath + "/" +times+".png"));
          // FileUtils.copyFile(file,new File(myPath + "/" + DateFormat.format(DateFormat.REPORT_CSV_FORMAT) + ".png"));
        } catch (IOException e) {
            logger.error("截图失败！！");
            e.printStackTrace();
        }
    }
    /**
     * 错误截图,通过传入name来给截图命名
     **/
    public static void screenShots1(String name) {
        WebDriver augmentedDriver = new Augmenter().augment(driver);
        File file = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
        try {
            MyFile.createFile1(path + DateFormat.format(DateFormat.CHECK_LOG_FORMAT));
            logger.info(DateFormat.format(DateFormat.ZH_DATE_FORMAT));
            FileUtils.copyFile(file,new File(path + DateFormat.format(DateFormat.CHECK_LOG_FORMAT) + "/" + name + ".png"));
        } catch (IOException e) {
            logger.error("截图失败！！");
            e.printStackTrace();
        }
    }
    
    
    public static void takeScreenShort(String FileName) {
    	// 调用截图方法
        File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Long date=System.currentTimeMillis();
     // 拷贝截图文件到我们项目./Screenshots
    	try {
//			FileUtils.copyFile(src, new File(".\\Screenshots\\screen.png"));
			FileUtils.copyFile(src, new File(tomcatPath+date+FileName+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    
    public static void takeScreen(String path,String FileName) {
    	// 调用截图方法
        File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Long date=System.currentTimeMillis();
     // 拷贝截图文件到我们项目./Screenshots
    	try {
//			FileUtils.copyFile(src, new File(".\\Screenshots\\screen.png"));
			FileUtils.copyFile(src, new File(path+date+FileName+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * 判断文件夹，没有就新建
     */
/*    public static void fileExists() {
        String fileName = DateUtil.format(DateUtil.CHECK_LOG_FORMAT);
        File file = new File(path + fileName);
        if (!file.exists()) {
            file.mkdirs();
        }
    }*/
    
    
    public static void main(String[] args) {
		screenShots1("1111");
	}
}
