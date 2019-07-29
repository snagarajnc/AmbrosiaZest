package AbrosiaReports.Zest;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
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

		if (browser.equalsIgnoreCase("chrome")) {
			ChromeBrowser("ChromeDriverLoc");
		}

		else if (browser.equalsIgnoreCase("gecko")) {
			FirefoxBrowser("GeckoDriverLoc");
		}

		else if (browser.equalsIgnoreCase("ie")) {
			IEBrowser("IEDriverLoc");
		}

	}

	@Test(priority = 2)
	public static void AmbrosiaLogin() throws IOException, InterruptedException {
		dr.navigate().to(GetPropValues("URL"));
		waitforElementVisibile(LoginUserName);
		FindElement(LoginUserName).sendKeys(GetPropValues("UserNameCredential"));
		FindElement(LoginPassword).sendKeys(GetPropValues("PasswordCredential"));
		FindElement(LoginSubmit).click();
		waitforElementVisibile(Logo);
		FindElement(Logo).click();
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
	public static void clickEvent() throws InterruptedException {
		waitforElementVisibile(clickEvent);
		FindElement(clickEvent).click();
	}

	@Test(enabled = false)
	public static void GrabbedAllTableData() throws InterruptedException {
		waitforElementVisibile(ReportLink);
		dr.findElement(ReportLink).click();
		waitforElementVisibile(ReportiFrame);
		// Frame
		int Size = dr.findElements(ReportiFrame).size();
		POMFunction.Info("iFRAME Size : " + Size);
		dr.switchTo().frame(0);
		FindElement(IVRReport).click();

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

	@Test(enabled = false)
	public static WebElement GrabbedOnlyReports() throws InterruptedException {
		waitforElementVisibile(ReportLink);
		FindElement(ReportLink).click();
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
	@Test(enabled = false)
	public static void OpenSelectedReport(String ReportName) throws InterruptedException {
		if (ReportName.equalsIgnoreCase("Interactive Voice Response Report")) {
			GrabbedOnlyReports().click();
		} else if (ReportName.equalsIgnoreCase("Music on Hold Report")) {

		}
		Thread.sleep(3000);
		dr.close();
	}
}
