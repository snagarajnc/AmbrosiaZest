package AbrosiaReports.Zest;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ReportNavigation extends POMRepo {

	public static List<WebElement> allRows;
	public static int ReportCount;
	public static WebElement Reports;

	@Parameters("browser")
	@BeforeTest
	public void BrowserSelection(String browser) throws AWTException, IOException {

		// ChromeDriver
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + ObjRepo().getProperty("ChromeDriverLoc"));
			dr = new ChromeDriver();
		}

		else if (browser.equalsIgnoreCase("gecko")) {
			// FirefoxDriver
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + ObjRepo().getProperty("GeckoDriverLoc"));
			dr = new FirefoxDriver();
		}

		else if (browser.equalsIgnoreCase("ie")) {
			// Internet Explorer
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + ObjRepo().getProperty("IEDriverLoc"));
			dr = new InternetExplorerDriver();
		}
	}

	@Test(priority = 2)
	public static void AmbrosiaLogin() throws IOException, InterruptedException {
		dr.get(ObjRepo().getProperty("URL"));
		Thread.sleep(2000);
		dr.findElement(LoginUserName).sendKeys(ObjRepo().getProperty("UserNameCredential"));
		dr.findElement(LoginPassword).sendKeys(ObjRepo().getProperty("PasswordCredential"));
		dr.findElement(LoginSubmit).click();
		Thread.sleep(2000);
		dr.findElement(Logo).click();
	}

	@Test(priority = 3)
	public static void LoginValidation() {
		String ErrorMsg = dr.getPageSource();
		if (ErrorMsg.contains("PASSWORD")) {
			POMFunction.Error("Your credential is wrong. Test again with valid credential !!!");
			dr.quit();
		} else {
			POMFunction.Pass("Your credential is working !!!");
		}
	}

	@Test(enabled = false)
	public static void GrabbedAllTableData() throws InterruptedException {
		Thread.sleep(8000);
		dr.findElement(By.partialLinkText("REPORTS")).click();
		Thread.sleep(5000);
		// Frame
		int Size = dr.findElements(ReportiFrame).size();
		POMFunction.Info("iFRAME Size : " + Size);
		dr.switchTo().frame(0);
		// dr.findElement(IVRReport).click();

		WebElement TableBody1 = dr.findElement(TableBody);
		WebElement CompleteTable = TableBody1.findElement(Complete);
		// Row
		allRows = CompleteTable.findElements(TableRow);
		POMFunction.Info("-------------------- Complete Table Data --------------------");
		for (WebElement Lwb1 : allRows) {
			List<WebElement> cells = Lwb1.findElements(TableColumn);
			for (WebElement Lwb2 : cells) {
				POMFunction.Info(Lwb2.getText());
			}
		}
	}

	@Test(priority = 4)
	public static WebElement GrabbedOnlyReports() throws InterruptedException {
		Thread.sleep(8000);
		dr.findElement(ReportLink).click();
		Thread.sleep(5000);
		// Frame
		int Size = dr.findElements(ReportiFrame).size();
		POMFunction.Info("iFRAME Size : " + Size);
		dr.switchTo().frame(0);
		WebElement TableBody1 = dr.findElement(TableBody);
		WebElement CompleteTable = TableBody1.findElement(Complete);
		List<WebElement> allRows = CompleteTable.findElements(TableRow);
		ReportCount = allRows.size();
		POMFunction.Info("RowCounts : " + ReportCount);
		POMFunction.Info("--------------------- REPORTS ---------------------");
		for (i = 0; i < ReportCount; i++) {
			incr = i + 1;
			Reports = dr.findElement(By.id("reportLink_Row" + incr));
			ReportNames = Reports.getText();
			POMFunction.Info(ReportNames);
		}
		POMFunction.Info("--------------------- EOR ---------------------");
		return Reports;
	}

	@Parameters("ReportName")
	@Test(priority = 5)
	public static void OpenSelectedReport (String ReportName) throws InterruptedException {
		if(ReportName.equalsIgnoreCase("Interactive Voice Response Report")) {
			GrabbedOnlyReports().click();
		}
		else if(ReportName.equalsIgnoreCase("Music on Hold Report")) {
			
		}

	}

	@AfterTest
	public void afterTest() {
	}
}
