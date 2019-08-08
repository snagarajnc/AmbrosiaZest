package pak;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import AbrosiaReports.Zest.base.POMFunction;

public class PracticeClass extends POMFunction {
	public static Logger log = Logger.getLogger(PracticeClass.class.getClass());
	public static void main(String[] args) throws IOException, InterruptedException, ParseException {
		Loadlog4j();
		System.out.println("DDD : " + log);

		System.setProperty("webdriver.gecko.driver",
				System.getProperty("user.dir") + ObjRepo().getProperty("GeckoDriverLoc"));
		dr = new FirefoxDriver();
		log.info("Browser Launched");
		TakeScreenShot();
		dr.get("https://www.google.com/");
		log.debug("URL Passed");
		By SignIn = By.xpath("//*[@id='gb_70']");

		WebElement wb = POMFunction.FindElement(SignIn);
		JavascriptExecutor js = (JavascriptExecutor) dr;
//		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", wb);
		highlightElement(dr,wb);
		wb.click();
//		highLight(wb);
		log.debug("Sign In clicked");
		TakeScreenShot();
	}

	public static void TakeScreenShot() throws ParseException {
		DateFormat ObjD = new SimpleDateFormat("dd-M-yyyy hh:mm:ss.SSSS");
		String Today = ObjD.format(new Date());
		long date = ObjD.parse(Today).getTime();
		File sh = ((TakesScreenshot) dr).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(sh, new File(ObjRepo().getProperty("ScreenshotLoc") + "Screenshot-" + date + ".png"));
		}

		catch (IOException e) {
			POMFunction.Error(e.getMessage());
		}
	}

	public static void highLight(WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) dr;
		// use executeScript() method and pass the arguments
		// Here i pass values based on css style. Yellow background color with solid red
		// color border.
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", ele);

	}
}
