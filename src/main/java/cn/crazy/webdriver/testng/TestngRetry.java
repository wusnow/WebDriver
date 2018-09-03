package cn.crazy.webdriver.testng;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.crazy.webdriver.util.Log;

public class TestngRetry implements IRetryAnalyzer {
	private Log logger=Log.getLogger(TestngRetry.class);
	private int retryCount = 1;
	private  int maxRetryCount=3;
	@Override
	public boolean retry(ITestResult result) {
		if (retryCount <= maxRetryCount) {
			String message = "running retry for  '" + result.getName() + "' on class " + this.getClass().getName() + " Retrying "
					+ retryCount + " times";
			logger.info(message);
			Reporter.setCurrentTestResult(result);
			Reporter.log("RunCount=" + (retryCount + 1));
			retryCount++;
			return true;
		}
		return false;
	}

}