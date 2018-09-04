package com.crazy.webdriver.util;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.SimpleFormatter;

import com.crazy.webdriver.imageUtils.ScreenShort;


/**
 * Created by lenovo on 2016/11/10.
 */
public class DateFormat {
    final static Log logger=Log.getLogger(DateFormat.class);
    public static final String ZH_DATE_FORMAT="yyyy-MM-dd HH:mm:ss";
    public static final String ZN_DATE_FORMAT="yyyy年MM月dd日 HH:mm:ss";
    public static final String ZC_DATE_FORMAT="yyyy年MM月dd日";
    public static final String SHORT_DATE_FORMAT = "yy-MM-dd HH:mm";
    public static final String CHECK_LOG_FORMAT = "yyyyMMdd";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String REPORT_CSV_FORMAT = "yyyyMMdd_HHmmss";

    public static String format(String type){
    	SimpleDateFormat formatter =new SimpleDateFormat(type);
    	Date curDate = new Date(System.currentTimeMillis());
        String s=formatter.format(curDate);
        logger.info("当前时间为："+s);
        return s;
    }
    public static String time(){
       long currtime= System.currentTimeMillis()/100;
        String randomNum=String.valueOf(currtime);
        logger.info("11位的随机数为："+randomNum);
        return randomNum;
    }
    
    
    public static void main(String[] args) {
		System.out.println(DateFormat.format(DateFormat.CHECK_LOG_FORMAT));
	}
}
