package com.abrosia.zest.extent.reports;

import java.io.File;
import java.text.ParseException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.abrosia.zest.utils.AmbrosiaUtils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportClass extends AmbrosiaUtils {
	public static ExtentReports extent;
	public static ExtentTest logger;

	@BeforeTest
	public static void Definition() throws ParseException {
//		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/EntentRep.html", true);
		extent = new ExtentReports(AmbrosiaUtils.CurrentReportLocation() + "\\EntentRep.html", true);
		extent.addSystemInfo("Host Name", "Ambrosia").addSystemInfo("Environment", "Automation Env")
				.addSystemInfo("User Name", "Apple");

		extent.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
	}

	@Test
	public static void passTest() {
		try {
			logger = extent.startTest("passTest");
			Assert.assertTrue(true);
			logger.log(LogStatus.PASS, "passTest Successfully Executed");
		} catch (Exception e) {
			System.out.println("Exception Handling : " + e);
		}
	}

	/*
	 * @Test public static void failTest() { try { logger =
	 * extent.startTest("failTest"); Assert.assertTrue(false);
	 * logger.log(LogStatus.FAIL, "failTest Failed in Execution"); } catch
	 * (Exception e) { System.out.println("Exception Handling : " + e); } }
	 */

	@Test
	public static void skipTest() {
		try {
			logger = extent.startTest("skipTest");
			throw new SkipException("skipTest Not Ready for Execution");
		} catch (Exception e) {
			System.out.println("Exception Handling : " + e);
		}
	}

	@AfterMethod
	public static void getResult(ITestResult result) {
		try {
			if (result.getStatus() == ITestResult.FAILURE) {
				logger.log(LogStatus.FAIL, "Failed TCase : " + result.getName());
				logger.log(LogStatus.FAIL, "Failed TCase : " + result.getThrowable());
			} else if (result.getStatus() == ITestResult.SKIP) {
				logger.log(LogStatus.SKIP, "Skipped TCase : " + result.getName());
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				System.out.println("ResultSKIPS-TATUS : " + result.getStatus());
				System.out.println("ResultSKIP-TName : " + result.getTestName());
				System.out.println("ResultSKIP-CLASS : " + result.getClass());
				System.out.println("ResultSKIP-NAME : " + result.getName());
			}
			extent.endTest(logger);
		} catch (Exception e) {
			System.out.println("Exception : " + e);
		}
	}

	@AfterTest
	public void endReport() {
		extent.flush();
		extent.close();
	}
}