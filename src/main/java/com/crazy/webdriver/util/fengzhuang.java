package com.crazy.webdriver.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import com.crazy.webdriver.base.CrazyPath;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


public class fengzhuang {

	WebDriver driver;
	public fengzhuang(WebDriver driver){
		this.driver=driver;
	}

	
	
	/**
	 * 
	* @Title: waitUtilElement  
	* @Description: TODO(自定义的显式等待)  
	* @param @param driver
	* @param @param element
	* @param @return    参数  
	* @return AndroidElement    返回类型  
	* @throws  
	* @author duanhao
	 */
	public static WebElement waitUtilElement(final WebDriver driver,WebElement element){
		WebDriverWait wait=new WebDriverWait(driver, 10);
		WebElement any = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(element));
		return any;
	}
	
	/**
	 * 
	* @Title: containText  
	* @Description: TODO(检查元素中是否包含某个文本)  
	* @param @param driver
	* @param @param by
	* @param @param text    是否包含的元素  
	* @return void    返回类型  
	* @throws  
	* @author duanhao
	 */
	public static Boolean containText(WebDriver driver,By by,String text) {
		return driver.findElement(by).getText().contains(text);
	}
	/**
	 * 
	* @Title: checkcheckable  
	* @Description: TODO(检查checkable的状态)  
	* @param @param driver
	* @param @param by
	* @param @param bool   字符串"true" or "false"
	* @param @return    参数  
	* @return boolean    返回类型  
	* @throws  
	* @author duanhao
	 */
	public static boolean checkcheckable(WebDriver driver,By by,String bool) {
		return driver.findElement(by).getAttribute("checkable").equals(bool);
    	//String status=Close.getAttribute("checked");
//    	String text=Close.getAttribute("text");
//    	String id=Close.getAttribute("resourceId");
//    	String className=Close.getAttribute("className");
//    	String content_desc=Close.getAttribute("content-desc");
//    	String clickable=Close.getAttribute("clickable");
//    	//对复选框进行勾选
//    	String checked=Close.getAttribute("checked");
//    	String checkable=Close.getAttribute("checkable");
//    	String long_clickable=Close.getAttribute("longClickable");
//    	//name参数优先获取cantent-desc属性的值，如果这个值为空字符串，那么会获取text属性的值
//    	String name=Close.getAttribute("name");
	}
	
	public static boolean checkchecked(WebDriver driver,By by,String bool) {
		return driver.findElement(by).getAttribute("checked").equals(bool);
	}
	public static boolean checktext(WebDriver driver,By by,String bool) {
		return driver.findElement(by).getAttribute("text").equals(bool);
	}
	public static boolean checkresourceId(WebDriver driver,By by,String bool) {
		return driver.findElement(by).getAttribute("resourceId").equals(bool);
	}
	public static boolean checkclassName(WebDriver driver,By by,String bool) {
		return driver.findElement(by).getAttribute("className").equals(bool);
	}
	public static boolean checkcontent_Desc(WebDriver driver,By by,String bool) {
		return driver.findElement(by).getAttribute("contentDescription").equals(bool);
	}
	public static boolean checkclickable(WebDriver driver,By by,String bool) {
		return driver.findElement(by).getAttribute("clickable").equals(bool);
	}
	public static boolean checklongClickable(WebDriver driver,By by,String bool) {
		return driver.findElement(by).getAttribute("longClickable").equals(bool);
	}
	public static boolean checkname(WebDriver driver,By by,String bool) {
		return driver.findElement(by).getAttribute("name").equals(bool);
	}
	public static boolean checkCheckable(WebDriver driver,By by,String bool) {
		return driver.findElement(by).getAttribute("enable").equals(bool);
	}
	
	
	/**
	 * 
	* @Title: ScreenShot  
	* @Description: TODO(截图方法)  
	* @param @param driver
	* @param @param filename   文件名
	* @param @throws Exception    参数  
	* @return void    返回类型  
	* @throws  
	* @author duanhao
	 */
	public static void ScreenShot(WebElement element,String filename){
		try {
			File file=element.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file,new File("images\\"+filename+".png"));
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}

	}
	/**
	 * 
	* @Title: Screenshot  
	* @Description: TODO(失败截图方法)  
	* @param @param driver
	* @param @param ScreenName
	* @param @throws Exception    参数  
	* @return void    返回类型  
	* @throws  
	* @author duanhao
	 */
	public static void Screenshot(WebElement element,String ScreenName){
		try {
			//设置时间格式
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
			//生成时间戳
			String dataString=formater.format(new Date());
			String dir_name=System.getProperty("user.dir")+"\\异常图片";
			System.out.println("异常图片目录"+dir_name);
			//由于可能存在异常图片的目录被删除的可能，所以这边先判断目录是否存在
			if (!(new File(dir_name).isDirectory())) 
			{
				//判断是否存在该目录
				new File(dir_name).mkdir();
			}
			//调用方法捕捉画面
			File screen=element.getScreenshotAs(OutputType.FILE);
			//复制文件到指定目录
			//图片最后存放的路径有 目录：dir_name+时间戳+测试套件+测试用例+测试步骤组合生成
			System.out.println("异常图片名称"+dir_name+"\\"+dataString+ScreenName+".png");
			FileUtils.copyFile(screen,new File(dir_name+"\\"+dataString+ScreenName+".png"));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	
	/**
	 * 
	* @Title: getMysqldata  查询数据库中的某个字段，已经处理，只返回一个数据
	* @Description: TODO(主要用作查询动态验证码用)  
	* @param @param database   要连接的数据库
	* @param @param selectdate    要查询的字段
	* @param @param selecttable			要查询的数据表
	* @param @param where			where条件，where已经提前加入了
	* @param @return    参数  
	* @return String    返回类型  
	* @throws  
	* @author duanhao
	 */
	public static String getMysqldata(String database,String selectdate,String selecttable,String where) {
        //数据库连接名称
        String username="root";
        //数据库连接密码
        String password="root";
        String driver="com.mysql.jdbc.Driver";
        //其中database为数据库名称
        String url="jdbc:mysql://localhost:3306/"+database+"?useSSL=false&useUnicode=true&characterEncoding=GBK";
        Connection conn=null;
        try{
            Class.forName(driver);
            conn=(Connection) DriverManager.getConnection(url,username,password);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        // sql语句
        String sql = "select "+selectdate+" from "+selecttable+" "+where; 
        PreparedStatement pst = null;
        // 定义一个list用于接受数据库查询到的内容
        List<String> list = new ArrayList<String>();
        try {
            pst = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                // 将查询出的内容添加到list中，其中userName为数据库中的字段名称
                list.add(rs.getString(selectdate));
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
        System.out.println("查询出来的数据是："+list.get(0));
        return list.get(0);
	}
	
	
	
	/**
	 * 
	* @Title: getdate  
	* @Description: TODO(获取当前设备的系统时间的方法)  
	* @param @return    参数  
	* @return String    返回类型    返回当前时间
	* @throws  
	* @author duanhao
	 */
	public static String getdate() {
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        
        
        SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMddHHmmss");
//        System.out.println(df1.format(System.currentTimeMillis()));  
        return df1.format(System.currentTimeMillis()).toString();
	}
	
	/**
	 * 
	* @Title: SplitString  
	* @Description: TODO(传入一个string，根据分隔符拆分string，得到所需要的索引的字符串)  
	* @param @param string   传入的字符串
	* @param @param splitSgin   分割符
	* @param @param index   分割后需要的索引的字符串
	* @param @return    参数  
	* @return Str    返回类型    返回拆分后的字符串
	* @throws  
	* @author duanhao
	 */
	public static String SplitString(String str,String splitSgin,int index) {
		return str.split(splitSgin)[index];
	}

	
	/**
	 * 从字符串里提取整数，索引是从0开始  dsd234dsf455h34h232h34545
	 * @param str  目标字符串
	 * @param index 提取第几组数字，从0开始
	 * @return 返回一个整数，如果返回-1则表明你提取的组数不存在
	 */
	public static int getIntFromString(String str,int index){
		Pattern p = Pattern.compile("\\d{1,}");//这个1是指连续数字的最少个数
        Matcher m = p.matcher(str);
        List<Integer> result=new ArrayList<Integer>();
        while (m.find()) {
           // System.out.println(m.group());
            result.add(Integer.valueOf(m.group()));
        }
        if(!result.isEmpty()&&index<result.size()){
        	System.out.println("你要找的数字在第"+index+"个索引");
        	return result.get(index);
        }else{
        	System.out.println("你要找的第"+(index+1)+"组数字不存在");
        	return -1;
        }
	}
	

	
	/**
	 * 
	* @Title: randomInt  
	* @Description: TODO(生成指定范围内的整数)  
	* @param @param min   最小数，包含生成的最小数
	* @param @param max		最大数，包含生成的最大数
	* @param @return    参数  
	* @return int    返回类型  
	* @throws  
	* @author duanhao
	 */
	public static int getRandomInt(int min,int max){ 
        Random random = new Random();
        //10,100 88%91=88+10=98
        int s = random.nextInt(max)%(max-min+1) + min;
        return s;
	}
	
	
	
	/**
	 * 
	* @Title: randomFloat  
	* @Description: TODO(生成指定范围内的浮点数)  
	* @param @param min
	* @param @param max
	* @param @return    参数  
	* @return float    返回类型  
	* @throws  
	* @author duanhao
	 */
	public static float getRandomFloat(int min,int max){
        Random random = new Random();
        //10,100
        //0.0746273646*100=746.273646=746=7.46
//        int s = random.nextInt(max)%(max-min+1) + min;
        float x=min;//x=10
        while(x<=min){
        	double db = random.nextDouble() * max * 100;
        	x = ((int) db) / 100f;
        }
        return x;
	}
	
	
	
	
	/**
	 * 生成指定数字，向零靠拢的数字，不包含数字本身
	 * @param extent
	 * @return
	 */
	public static int getRandomNumber(int extent) {
		int number = (int) (Math.random() * extent);
		return number;
	}
	
	/**
	 * 随机生成指定长度的数字，以字符串形式返回
	 * @param lengthOfNumber
	 * @return
	 */
	public static String getRandomNumberByLength(int lengthOfNumber) {
		int i, count = 0;
		//098
		StringBuffer randomStr = new StringBuffer("");
		Random rnd = new Random();

		while (count < lengthOfNumber) {
			i = Math.abs(rnd.nextInt(10));
			if (i == 0 && count == 0) {
				//意思是不生成开始为0的数字，比如098,01
			} else {
				randomStr.append(String.valueOf(i));
				count++;
			}
		}
		return randomStr.toString();
	}
	
	
	
	
	/**
	 * 随机生成指定长度的中文字符，以字符串形式返回
	 * @author duanhao
	 * @param lengthOfString
	 * @return
	 */
	public static String getRandomChineseStringByLength(int lengthOfString) {
		int i, count = 0;
		final String chars ="我,国,北,方,地,区,出,现,大,范,围,持,续,性,高,温,的,可,能,性,不,是,很,大,但,在,南,方,夏,秋,交,替,之,际,副,热,带,高,压,开,始,南,退,东,撤,由,于,大,气,环,流,的,不,稳,定,性,副,热,带,高,压,有,时,又,可,能,短,暂,地,西,伸,北,抬,再,次,控,制,我,国,东,部,地,区,造,成,高,温,重,现,民,间,称,之,为,秋,老,虎,因,此,防,御,高,温,仍,然,不,可,掉,以,轻,心";
		String[] charArr = chars.split(",");

		StringBuffer randomStr = new StringBuffer("");
		Random rnd = new Random();
		int strLen = charArr.length;

		while (count < lengthOfString) {
			i = rnd.nextInt(strLen);//strLen如果等于30，i值就在0-30之间
			//System.out.println(i);
			randomStr.append(charArr[i]);
			count++;
		}
		return randomStr.toString().toLowerCase();
	}
	
	
	/**
	 * 随机生成指定长度的纯小写字母英文字符串，以字符串的形式返回
	 * @author duanhao
	 * @param lengthOfString
	 * @return
	 */
	public static String getRandomSmallEnglishByLength(int lengthOfString) {
		int i, count = 0;
		final String chars ="A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
		String[] charArr = chars.split(",");

		StringBuffer randomStr = new StringBuffer("");
		Random rnd = new Random();
		int strLen = charArr.length;

		while (count < lengthOfString) {
			i = rnd.nextInt(strLen);//strLen如果等于26，i值就在0-25之间
			//System.out.println(i);
			randomStr.append(charArr[i]);
			count++;
		}
		return randomStr.toString().toLowerCase();
	}
	
	
	/**
	 * 随机生成指定长度的纯大写字母英文字符串，以字符串的形式返回
	 * @author duanhao
	 * @param lengthOfString
	 * @return
	 */
	public static String getRandomBigEnglishByLength(int lengthOfString) {
		int i, count = 0;
		final String chars ="A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
		String[] charArr = chars.split(",");

		StringBuffer randomStr = new StringBuffer("");
		Random rnd = new Random();
		int strLen = charArr.length;

		while (count < lengthOfString) {
			i = rnd.nextInt(strLen);//strLen如果等于26，i值就在0-25之间
			//System.out.println(i);
			randomStr.append(charArr[i]);
			count++;
		}
		return randomStr.toString();
	}
	
	
	
	
	/**
	 * 随机生成指定长度的数字和小写英文字符串，以字符串的形式返回
	 * @param chars
	 * @param lengthOfString
	 * @return
	 */
	public static String getRandomSmallStringAndNumberByLength(int lengthOfString) {
		int i, count = 0;
		final String chars ="1,2,3,4,5,6,7,8,9,0,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
		String[] charArr = chars.split(",");

		StringBuffer randomStr = new StringBuffer("");
		Random rnd = new Random();
		int strLen = charArr.length;

		while (count < lengthOfString) {
			i = rnd.nextInt(strLen);//strLen如果等于36，i值就在0-35之间
			//System.out.println(i);
			randomStr.append(charArr[i]);
			count++;
		}
		return randomStr.toString().toLowerCase();
	}
	
	/**
	 * 随机生成指定长度的数字和大写英文字符串，以字符串的形式返回
	 * @param chars
	 * @param lengthOfString
	 * @return
	 */
	public static String getRandomBigStringAndNumberByLength(int lengthOfString) {
		int i, count = 0;
		final String chars ="1,2,3,4,5,6,7,8,9,0,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
		String[] charArr = chars.split(",");

		StringBuffer randomStr = new StringBuffer("");
		Random rnd = new Random();
		int strLen = charArr.length;

		while (count < lengthOfString) {
			i = rnd.nextInt(strLen);//strLen如果等于36，i值就在0-35之间
			//System.out.println(i);
			randomStr.append(charArr[i]);
			count++;
		}
		return randomStr.toString();
	}
	
	
    public static int[] getData(String name)throws Exception{
        BufferedImage img = ImageIO.read(new File(name));
        BufferedImage slt = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
        slt.getGraphics().drawImage(img,0,0,100,100,null);
//        System.out.println(slt.getWidth());
//        System.out.println(slt.getHeight());
        
        int[] data = new int[256];
        for(int x = 0;x<slt.getWidth();x++){
            for(int y = 0;y<slt.getHeight();y++){
                int rgb = slt.getRGB(x,y);
                Color myColor = new Color(rgb);
                int r = myColor.getRed();
                int g = myColor.getGreen();
                int b = myColor.getBlue();
                data[(r+g+b)/3]++;
            }
        }
        return data;
    }
    /*
     * 异常情况下的截图操作
     */
    public static void ErrorScreemShot(WebElement element,String ErrorScreenShot) throws Exception{
    	//设置时间格式
    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
    	//生成时间戳
    	String dataString = format.format(new Date());
    	String dir_name = System.getProperty("user.dir")+"\\images";
    	System.out.println("异常图片目录"+dir_name);
    	//由于可能存在异常图片的目录被删除的可能，所以先判断一下目录是否存在
    	if(!(new File(dir_name).isDirectory())){
    		//判断目录是否存在
    		new File(dir_name).mkdirs();
    	}
    	//调用方法捕捉画面
    	File Screen = element.getScreenshotAs(OutputType.FILE);
    	//复制文件到指定的目录
    	//图片最后存放的路径由目录：dir_name+时间桌+测试套件+测试用例+测试步骤组合生成
    	System.out.println("异常图片名称"+dir_name+"\\"+dataString+ErrorScreenShot+".png");
    	FileUtils.copyFile(Screen, new File(dir_name+"\\"+dataString+ErrorScreenShot+".png"));
    }
    
    
    
    
    
    public static float compare(int[] s,int[] t){
        float result = 0F;
        for(int i = 0;i<256;i++){
            int abs = Math.abs(s[i]-t[i]);
            int max = Math.max(s[i],t[i]);
            
            result += (1-((float)abs/(max==0?1:max)));
            
        }
        return (result/256)*100;
    }
    /**
     * 
    * @Title: compareImg  
    * @Description: TODO(图片对比方法)  
    * @param @param srcName
    * @param @param desName
    * @param @param f
    * @param @return
    * @param @throws Exception    参数  
    * @return boolean    返回类型  
    * @throws  
    * @author duanhao
     */
    public static boolean compareImg(String srcName,String desName,float f) throws Exception{
    	if(compare(getData(srcName), getData(desName))>=f){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    

    /**
     * 
    * @Title: send  
    * @Description: TODO(发送邮件的方法)  
    * @param @param title   邮件标题
    * @param @param text    邮件内容  
    * @return void    返回类型  
    * @throws  
    * @author duanhao
     */
    public static void send(String title,String text) {
        try {
        	Properties p = new Properties(); //Properties p = System.getProperties();   
            p.put("mail.smtp.auth", "true");   
            p.put("mail.transport.protocol", "smtp");   
            p.put("mail.smtp.host", "smtp.163.com");   
            p.put("mail.smtp.port", "25");   
            //建立会话   
            Session session = Session.getInstance(p);   
            Message msg = new MimeMessage(session); //建立信息  
            //读取配置文件
            ProUtil pu=new ProUtil(CrazyPath.globalPath);
            String sendUser=pu.getPro("senduser");
            String password=pu.getPro("password");
            msg.setFrom(new InternetAddress(sendUser)); //发件人   
            
        	String[] to=pu.getPro("tomail").split(",");
            String toPersion = getMailList(to);
            InternetAddress[] iaToList = new InternetAddress().parse(toPersion);
            
            msg.setRecipients(Message.RecipientType.TO,iaToList); //收件人   
    
            msg.setSentDate(new Date()); // 发送日期   
            msg.setSubject(title); // 主题   
            msg.setText(text); //内容   
            // 邮件服务器进行验证   
            Transport tran = session.getTransport("smtp");   
            tran.connect("smtp.163.com",sendUser,password);    
            tran.sendMessage(msg, msg.getAllRecipients()); // 发送   
            System.out.println("邮件发送成功");   
    
        } catch (Exception e) {   
            e.printStackTrace();   
        }   
    }   
    
    private static String getMailList(String[] mailArray){
        
        StringBuffer toList = new StringBuffer();
        int length = mailArray.length;
        if(mailArray!=null && length <2){
             toList.append(mailArray[0]);
        }else{
             for(int i=0;i<length;i++){
                     toList.append(mailArray[i]);
                     if(i!=(length-1)){
                         toList.append(",");
                     }
             }
         }
     return toList.toString();
    }
	

    
    
    
    
    
    
    
	public static void main(String[] args) {
		System.out.println(getMysqldata("school", "name", "student", "where name='张一'"));
		System.out.println(getdate());
		
		
		System.out.println(SplitString("127.0.0.1:62001", ":", 0));
//		System.out.println(getIntFromString("appium -p 4490 -bp 2253 -U 127.0.0.1:62001>logs/127.0.0.1:62001.log", 0));
		System.out.println(getRandomInt(10, 11));
		System.out.println(getRandomFloat(1, 2));
		System.out.println(getRandomNumber(1));
		System.out.println(getRandomNumberByLength(10));
		System.out.println(getRandomChineseStringByLength(300));
		System.out.println(getRandomSmallEnglishByLength(50));
		System.out.println(getRandomBigEnglishByLength(50));
	}

	

	


}
