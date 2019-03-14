package AbrosiaReports.Zest;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
}
