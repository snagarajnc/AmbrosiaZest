package AbrosiaReports.Zest;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MachineTest1 extends POMFunction {
	public static WebDriver dr;
	public static int Month;
	public static String monthName;

	public static void main(String[] args) throws IOException, ParseException {
		BrowserDef();
//		Question1();
		Question2("20-June-2019");
	}

	public static void BrowserDef() throws IOException {
		System.setProperty("webdriver.gecko.driver",
				System.getProperty("user.dir") + ObjRepo().getProperty("GeckoDriverLoc"));
		dr = new FirefoxDriver();
	}

	public static void Question1() throws IOException {
		dr.get(POMFunction.ObjRepo().getProperty("MTURL1"));
		WebElement wb1 = dr.findElement(POMRepo.MTLeft);
		List<WebElement> Lwb1 = wb1.findElements(POMRepo.MTItems);
		System.out.println("Total Menu Count: " + Lwb1.size());
		for (int i = 1; i < Lwb1.size(); i++) {
			String Value = dr.findElement(POMRepo.getItem(i)).getText();
			int result = Integer.parseInt(Value);
			if (result < 40) {
				String Res = dr.findElement(By.xpath(POMRepo.St1 + i + POMRepo.St3)).getText();
				dr.findElement(POMRepo.MTTxtBox).sendKeys("\n", Res);
			}
		}
	}

	public static void Question2(String StrDate) throws IOException, ParseException {
		dr.get(POMFunction.ObjRepo().getProperty("MTURL2"));
		SimpleDateFormat objD = new SimpleDateFormat("dd-MMM-yyyy");
		Date Today = new Date(); /* Today's Date */
		Date GivenDate = objD.parse(StrDate);/* Parsed Given Date */
		// Month = Integer.parseInt(StrDate.toString("MM"));
		System.out.println("Today's Date : " + objD.format(Today));
		System.out.println("Given Date : " + objD.format(GivenDate));

		// Get Month Only
		dr.findElement(POMRepo.MTCalendar).click();

		if (Today.compareTo(GivenDate) > 0) {
			System.out.println("Prev Button");
			dr.findElement(POMRepo.PrevMonth).click();
			String SS = dr.findElement(By.className("month-year")).getText();
			System.out.println(SS);

		} else if (Today.compareTo(GivenDate) < 0) {
			System.out.println("Nxt Button");
			dr.findElement(POMRepo.NxtMonth).click();
			/*
			 * String SS = dr.findElement(By.className("month-year")).getText();
			 * System.out.println(SS);
			 */
		}
	}
}