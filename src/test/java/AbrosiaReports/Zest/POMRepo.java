package AbrosiaReports.Zest;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class POMRepo extends POMFunction {

	public static int i = 0;
	public static int incr;
	public static WebDriver dr;
	public static By LoginUserName = By.name("login");
	public static By LoginPassword = By.name("password");
	public static By LoginSubmit = By.className("dui-login-submit");
	public static By Logo = By.className("dui-login-logo");
	public static By Reports = By.className("dui-header-nav-item dui-active ng-star-inserted");
	public static By TableBody = By.id("rdDataTableDiv-HomeReportTable");
	public static By Complete = By.xpath("/html/body/form/div[2]/div/div/div/span/span/span[1]/div/table/tbody");
	public static By TableRow = By.tagName("tr");
	public static By TableColumn = By.tagName("td");
	public static By ReportiFrame = By.tagName("iframe");
	public static By Report1 = By.id("lblREPORT_NAME_Row2");
	public static By IVRReport = By.partialLinkText("Interactive Voice Response Report");
	public static Random rand = new Random();
	public static String ReportNames;
	public static By ReportLink = By.partialLinkText("REPORTS");

	// Events
	public static By clickEvent = By.linkText("EVENTS");

	public static Wait getWait() {
		WebDriverWait exWait = new WebDriverWait(dr, 30);
		return exWait;
	}

	public static void waitforElementVisibile(By EleName) {
		getWait().until(ExpectedConditions.visibilityOfElementLocated(EleName));
	}

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

	public static String GetPropValues(String Credentials) throws IOException {
		return ObjRepo().getProperty(Credentials);
	}

	public static WebElement FindElement(By ByElementName) {
		return dr.findElement(ByElementName);
	}

	/*--------------------------------MachineTest #1--------------------------------*/

	// Question #1
	public static By MTLeft = By.className("left");
	public static By MTItems = By.className("item");
	public static String St1 = "/html/body/div[1]/div[";
	public static String St2 = "]/div/span";
	public static String St3 = "]/span";
	public static By MTTxtBox = By.id("answer");

	public static By getItem(int i) {
		return By.xpath(St1 + i + St2);
	}

	// Question #2
	public static By MTCalendar = By.xpath("//input[@value='Open calendar']");
	public static By PrevMonth = By.id("prev");
	public static By NxtMonth = By.id("next");

}
