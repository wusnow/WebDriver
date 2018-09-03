package com.crazy.webdriver.util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * 脚本日志收集类
* <p>Title: Log</p>  
* <p>Description: </p>  
* @author duanhao 
* @date 2018年8月19日
 */
public class Log {
	public static Logger log;
	public static Thread thread;
	public static Log getLogger(Class<?> T) {
		//init();
		//log = Logger.getLogger(T);
		//logger = new Log();
		thread=Thread.currentThread();
		log=Logger.getLogger(T);
		return new Log();
	}
	static{
		PropertyConfigurator.configure("configs/log4j.properties");
	}

	public void debug(Object message) {
		log.debug(thread.getName()+message);
	}

	public  void debug(Object message, Throwable t) {
		log.debug(thread.getName()+message, t);
	}

	public  void info(Object message) {
		log.info(thread.getName()+message);
	}

	public  void info(Object message, Throwable t) {
		log.info(thread.getName()+message, t);
	}

	public  void warn(Object message) {
		log.warn(thread.getName()+message);
	}

	public  void warn(Object message, Throwable t) {
		log.warn(thread.getName()+message, t);
	}

	public  void error(Object message) {
		log.error(thread.getName()+message);
	}

	public  void error(Object message, Throwable t) {
		log.error(thread.getName()+message, t);
	}
}