package AmbrosiaLaunchSite;

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

import AbrosiaReports.Zest.base.POMFunction;

public class LaunchAmbrosia extends POMFunction {

	public static Logger log = Logger.getLogger(LaunchAmbrosia.class.getClass());

	@Parameters("browser")
	@BeforeTest
	public void BrowserSelection(String browser) throws AWTException, IOException, ParseException {
		Loadlog4j();

		if (browser.equalsIgnoreCase("chrome")) {
			ChromeBrowser("ChromeDriverLoc");
		}

		else if (browser.equalsIgnoreCase("gecko")) {
			FirefoxBrowser("GeckoDriverLoc");
		}

		else if (browser.equalsIgnoreCase("ie")) {
			IEBrowser("IEDriverLoc");
		}
		log.info("Browser selected as : " + browser); // Logger
	}

	@Test(priority = 2)
	public static void AmbrosiaLogin() throws IOException, InterruptedException, ParseException {
		try {
			LaunchApplication(GetPropValues("URL"));
			log.info("URL passed as " + GetPropValues("URL")); // Logger
			highlightElement(dr, waitforElementVisibile(LoginUserName));
			waitforElementVisibile(LoginUserName).sendKeys(GetPropValues("UserNameCredential"));
			highlightElement(dr, waitforElementVisibile(LoginPassword));
			waitforElementVisibile(LoginPassword).sendKeys(GetPropValues("PasswordCredential"));
			log.info("Credentials passed as " + GetPropValues("UserNameCredential") + "/"
					+ GetPropValues("PasswordCredential")); // Logger
			highlightElement(dr, waitforElementVisibile(LoginSubmit));
			waitforElementVisibile(LoginSubmit).click();
			waitforElementVisibile(Logo).click();
			log.info("Clicked Submit"); // Logger
		} catch (Exception e) {
			// TODO: handle exception
			TakeScreenShot();
			log.error(e);
		}
	}

	@Test(priority = 3)
	public static void LoginValidation() throws ParseException {
		try {
			String ErrorMsg = dr.getPageSource();
			if (ErrorMsg.contains("PASSWORD")) {
				POMFunction.Error("Your credential is wrong. Test again with valid credential !!!");
				dr.quit();
			} else {
				log.info("Your credential worked to Launch "); // Logger
			}
			log.debug("Login Validated"); // Logger
		} catch (Exception e) {
			// TODO: handle exception
			TakeScreenShot();
			log.error(e);
		}
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
