package AbrosiaReports.Zest;

import java.awt.AWTException;
import java.io.IOException;

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

	@AfterTest
	public void afterTest() {
	}

}
