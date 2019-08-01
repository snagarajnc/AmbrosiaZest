package AbrosiaReports.Zest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class POMFunction extends POMRepo {
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

	public static void Error(String message) {
		System.err.println("FAIL: " + message);
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
		long date = ObjD.parse(Today).getTime();
		File sh = ((TakesScreenshot) dr).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(sh, new File(System.getProperty("user.dir") + ObjRepo().getProperty("ScreenshotLoc")
					+ "Screenshot-" + date + ".png"));
		} catch (IOException e) {
			POMFunction.Error(e.getMessage());
		}
	}

	/* Initialization for log4j */
	public static void Loadlog4j() throws IOException {
		PropertyConfigurator.configure(System.getProperty("user.dir") + ObjRepo().getProperty("log4jLoc"));
	}

	public Logger Log() {
		String ClassName = this.getClass().getSimpleName();
		Logger log = Logger.getLogger(ClassName);
		return log;
	}

}