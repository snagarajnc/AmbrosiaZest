package AbrosiaReports.Zest.base;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class POMObjectRepo {

	public static int i = 0;
	public static int incr;
	public static Random rand = new Random();

	public static List<WebElement> allRows;
	public static int ReportCount;
	public static WebElement ReportsX;

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
	public static String ReportNames;
	public static By ReportLink = By.partialLinkText("REPORTS");
	public static DateFormat ObjD = new SimpleDateFormat("dd-M-yyyy hh:mm:ss.SSSS");
	public static String TodayDateFormat = ObjD.format(new Date());
	public static long currentDate;
	public static String TestFile;

	// Events
	public static By clickEvent = By.linkText("EVENTS");
	public static By TabListMain = By
			.xpath("//*[@role='tablist'][@class='events-tree-accordion ui-accordion ui-widget ui-helper-reset']");
	public static By ListTabMain = By.xpath("//p-accordiontab[contains(@class, 'custom-tab ng-tns')]");

	/* Ignore for reusability */
	public By SibListTabMain(String classWeb) {
		By SibListTabMain = By.xpath("//*[contains(@class,'" + classWeb + "')]//li[1]");
		return SibListTabMain;
	}

	/* Get data from View event table */
	public static By completeViewEvent = By
			.xpath("//div[contains(@class,'relative-block event-view ng-star-inserted')]");
	public static By cellViewEvent = By.xpath(
			"//div[contains(@class,'relative-block event-view ng-star-inserted')]//div[contains(@class,'col-xs-')]");

	/* Create Txt File */
	public static FileWriter FW;
	public static BufferedWriter br;

}
