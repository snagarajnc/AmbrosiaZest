package AbrosiaReports.Zest;

import java.awt.AWTException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LaunchAmbrosia extends POMRepo {

	static Logger log = Logger.getLogger(LaunchAmbrosia.class.getName());

	@Parameters("browser")
	@BeforeTest
	public void BrowserSelection(String browser) throws AWTException, IOException, ParseException {

		if (browser.equalsIgnoreCase("chrome")) {
			ChromeBrowser("ChromeDriverLoc");
		}

		else if (browser.equalsIgnoreCase("gecko")) {
			FirefoxBrowser("GeckoDriverLoc");
		}

		else if (browser.equalsIgnoreCase("ie")) {
			IEBrowser("IEDriverLoc");
		}
		TakeScreenShot();
	}

	@Test(priority = 2)
	public static void AmbrosiaLogin() throws IOException, InterruptedException, ParseException {
		TakeScreenShot();
		LaunchApplication(GetPropValues("URL"));
		waitforElementVisibile(LoginUserName).sendKeys(GetPropValues("UserNameCredential"));
		waitforElementVisibile(LoginPassword).sendKeys(GetPropValues("PasswordCredential"));
		TakeScreenShot();
		waitforElementVisibile(LoginSubmit).click();
		waitforElementVisibile(Logo).click();
		TakeScreenShot();
	}

	@Test(priority = 3)
	public static void LoginValidation() throws ParseException {
		String ErrorMsg = dr.getPageSource();
		if (ErrorMsg.contains("PASSWORD")) {
			POMFunction.Error("Your credential is wrong. Test again with valid credential !!!");
			dr.quit();
		} else {
			log.info("Your credential worked to Launch ");
			TakeScreenShot();
		}
	}

	@Test(enabled = false)
	public static void clickEvent() throws InterruptedException {
		waitforElementVisibile(clickEvent).click();
	}

	@Test(enabled = false)
	public static void GrabbedAllTableData() throws InterruptedException {
		waitforElementVisibile(ReportLink).click();
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
			ReportsX = dr.findElement(By.id("reportLink_Row" + incr));
			ReportNames = ReportsX.getText();
			POMFunction.Info(ReportNames);
		}
		POMFunction.Info("--------------------- EOR ---------------------");
		return ReportsX;
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
