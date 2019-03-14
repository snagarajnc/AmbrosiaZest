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
			System.out.println("Your credential is wrong. Test again with valid credential !!!");
			dr.quit();
		} else {
			System.out.println("Your credential is working !!!");
		}
	}

	@Test(priority = 4)
	public static void Test() throws InterruptedException {
		Thread.sleep(5000);
		dr.findElement(By.partialLinkText("REPORTS")).click();
		Thread.sleep(5000);
		// Frame
		int Size = dr.findElements(ReportiFrame).size();
		System.out.println("iFRAME Size : " + Size);
		dr.switchTo().frame(0);
		// dr.findElement(IVRReport).click();
		
		WebElement TableMain1 = dr.findElement(TableMain);
		WebElement TableBody1 = TableMain1.findElement(TableBody);
		// Row
		List<WebElement> allRows = TableBody1.findElements(TableRow);
		System.out.println("### Data ###");
		for (WebElement Lwb1 : allRows) {
			List<WebElement> cells = Lwb1.findElements(TableColumn);
			for (WebElement Lwb2 : cells) {
				System.out.println(Lwb2.getText());
			}
		}
		
	}

	@AfterTest
	public void afterTest() {
	}
}
