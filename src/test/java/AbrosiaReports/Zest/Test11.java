package AbrosiaReports.Zest;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Test11 {
	public static void main(String[] args) throws IOException, InterruptedException, ParseException {
		System.setProperty("webdriver.gecko.driver",
				System.getProperty("user.dir") + POMRepo.ObjRepo().getProperty("GeckoDriverLoc"));
		POMRepo.dr = new FirefoxDriver();
		TakeScreenShot();
		POMRepo.dr.get("https://www.google.com/");
		By SignIn = By.xpath("//*[@id='gb_70']");
		POMFunction.FindElement(SignIn).click();
		TakeScreenShot();
	}

	public static void TakeScreenShot() throws IOException, ParseException {
		DateFormat ObjD = new SimpleDateFormat("dd-M-yyyy hh:mm:ss.SSSS");
		String Today = ObjD.format(new Date());
		long date = ObjD.parse(Today).getTime();
		File sh = ((TakesScreenshot) POMRepo.dr).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(sh, new File(System.getProperty("user.dir")
					+ POMRepo.ObjRepo().getProperty("ScreenshotLoc") + "Screenshot-" + date + ".png"));
		}

		catch (IOException e) {
			POMFunction.Error(e.getMessage());
		}
	}
}
