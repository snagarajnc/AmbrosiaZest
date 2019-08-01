package AbrosiaReports.Zest;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.firefox.FirefoxDriver;

import AmbrosiaEvents.ManipulateEvents;

public class Test11 extends POMFunction {
	
	public static void main(String[] args) throws IOException, InterruptedException, ParseException {
		Logger log = Logger.getLogger(Test11.class.getName());
		log.info(null);
		/*System.setProperty("webdriver.gecko.driver",
				System.getProperty("user.dir") + ObjRepo().getProperty("GeckoDriverLoc"));
		dr = new FirefoxDriver();
		TakeScreenShot();
		dr.get("https://www.google.com/");
		By SignIn = By.xpath("//*[@id='gb_70']");
		POMFunction.FindElement(SignIn).click();
		TakeScreenShot();*/
	}

	public static void TakeScreenShot() throws ParseException {
		DateFormat ObjD = new SimpleDateFormat("dd-M-yyyy hh:mm:ss.SSSS");
		String Today = ObjD.format(new Date());
		long date = ObjD.parse(Today).getTime();
		File sh = ((TakesScreenshot) dr).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(sh, new File(System.getProperty("user.dir") + ObjRepo().getProperty("ScreenshotLoc")
					+ "Screenshot-" + date + ".png"));
		}

		catch (IOException e) {
			POMFunction.Error(e.getMessage());
		}
	}
}
