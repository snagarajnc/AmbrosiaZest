package com.abrosia.zest.selenium.actions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.abrosia.zest.objectrepository.POMObjectRepo;
import com.abrosia.zest.utils.AmbrosiaUtils;
import com.relevantcodes.extentreports.model.Test;

public class POMFunction extends POMObjectRepo {

	public static Logger log = Logger.getLogger(POMFunction.class.getClass());

	public static void exeLogg() throws IOException {
		Loadlog4j();
	}

	public static void browserQuit() {
		dr.quit();
	}

	public static Properties ObjRepo() throws IOException {
		FileReader ff = new FileReader(System.getProperty("user.dir") + "/utilsPorperties/ObjectRepoProp.properties");
		Properties pp = new Properties();
		pp.load(ff);
		return (pp);
	}

	/* Get Values From Property File */
	public static String GetPropValues(String Credentials) throws IOException {
		return ObjRepo().getProperty(Credentials);
	}

	/* Browsers Definition */
	public static void ChromeBrowser(String BrowserLocation) throws IOException {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + ObjRepo().getProperty("ChromeDriverLoc"));
		dr = new ChromeDriver();
		System.setProperty("webdriver.chrome.silentOutput", "true");

	}

	public static void FirefoxBrowser(String BrowserLocation) throws IOException {
		System.setProperty("webdriver.gecko.driver",
				System.getProperty("user.dir") + ObjRepo().getProperty("GeckoDriverLoc"));
		dr = new FirefoxDriver();
	}

	public static void IEBrowser(String BrowserLocation) throws IOException {
		System.setProperty("webdriver.ie.driver",
				System.getProperty("user.dir") + ObjRepo().getProperty("IEDriverLoc"));
		dr = new InternetExplorerDriver();
	}

	/* Launch Application with given URL */
	public static void LaunchApplication(String WebURL) {
		dr.get(WebURL);
	}

	/* Print Logger */
	public static void Pass(String message) {
		System.out.println("PASS: " + message);
	}

	public static void Pass(int message) {
		System.out.println("PASS: " + message);
	}

	public static void Error(String string) {
		System.err.println("FAIL: " + string);
	}

	public static void Error(Exception exception) {
		System.err.println("FAIL: " + exception);
	}

	public static void Exception(Object object) {
		System.err.println("FAIL: " + object);
	}

	public static void Error(int message) {
		System.err.println("FAIL: " + message);
	}

	public static void Info(String message) {
		System.out.println("INFO: " + message);
	}

	public static void Info(int message) {
		System.out.println("INFO: " + message);
	}

	/* Wait for 30 seconds */
	public static Wait getWait() {
		WebDriverWait exWait = new WebDriverWait(dr, 30);
		return exWait;
	}

	/* Element Highlighter */
	public static Object highlightElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(
				"arguments[0].setAttribute('style', 'background: #99cc00; border: 2px solid #ffffff;');", element);
	}

	/* WebElement Wait for Element Visible */
	public static WebElement waitforElementVisibile(By EleName) {
		WebElement waitEle = (WebElement) getWait().until(ExpectedConditions.visibilityOfElementLocated(EleName));
		return waitEle;
	}

	/* Find Element By Function */
	public static WebElement FindElement(By ByElementName) {
		return dr.findElement(ByElementName);
	}

	/* Take Screenshots */
	public static void TakeScreenShot() throws ParseException {

		File sh = ((TakesScreenshot) dr).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(sh,
					new File(AmbrosiaUtils.CurrentReportLocation() + "\\Screenshots\\" + "Screenshot" + ".png"));
		} catch (IOException e) {
			POMFunction.Error(e.getMessage());
		}
	}

	/* Initialization for log4j */
	public static void Loadlog4j() throws IOException {
		PropertyConfigurator.configure(System.getProperty("user.dir") + ObjRepo().getProperty("log4jLoc"));
	}

	/* Create Txt file */
	public static void createTxtFile() throws IOException, ParseException {
		TestFile = AmbrosiaUtils.CurrentReportLocation() + "\\" + "EventDataCollections" + ".txt";
//		Error("EEEE : " + TestFile);
		log.info("Data Collectioned in "+TestFile);

		File file = new File(TestFile); // Created object of java File class.
		file.createNewFile();

		FW = new FileWriter(TestFile);
		br = new BufferedWriter(FW);
	}

	/* Dropdown for Event List Pagination */
	public static Select selectDropdown(WebElement element) {
		return selectDropdown = new Select(element);
	}

	/* Select Alert Name using Alert Level */
	public static String alertCheck(int alert) throws IOException {
		if (alert == 5) {
			String alertLevel = "Critical";
			return alertLevel;
		} else if (alert == 4) {
			String alertLevel = "Major";
			return alertLevel;
		} else if (alert == 3) {
			String alertLevel = "Minor";
			return alertLevel;
		} else if (alert == 2) {
			String alertLevel = "Warning";
			return alertLevel;
		} else if (alert == 1) {
			String alertLevel = "Good";
			return alertLevel;
		} else {
			String alertLevel = "No Activity";
			return alertLevel;
		}
	}
}