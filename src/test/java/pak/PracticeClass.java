package pak;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import AbrosiaReports.Zest.base.POMFunction;
import AbrosiaReports.Zest.base.POMObjectRepo;
import AmbrosiaLaunchSite.LaunchAmbrosia;

public class PracticeClass extends LaunchAmbrosia {
	public static Logger logg = Logger.getLogger(LaunchAmbrosia.class.getClass());
	public static File FCC;
	public static BufferedWriter brr;
	public static FileWriter FWW;

	public static void main(String[] args) throws InterruptedException, ParseException, IOException {
		Loadlog4j();
		Launch();
		AmbrosiaLogin();
		clickEvent();
		selectEvenRandomly();
		GetRecordDetails();
	}

	public static void createSampleFile() throws IOException, ParseException {
		try {
			TestFile = ObjRepo().getProperty("TxtOutputLoc") + "PracticeClass" + Today() + ".txt";
			FCC = new File(TestFile);// Created object of java File class.
			FCC.createNewFile();

			FWW = new FileWriter(TestFile);
			brr = new BufferedWriter(FWW);
		} catch (Exception e) {
			POMFunction.Error("Problem in Txt File Creation - " + e);
		}
	}

	public static void Launch() throws IOException {
		try {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + ObjRepo().getProperty("ChromeDriverLoc"));
			dr = new ChromeDriver();
			dr.get("https://release.ambrosia.lab.nectarvoip.com/dashboardui/signin");
		} catch (Exception e) {
			POMFunction.Error("Problem in Launching URL - " + e);
		}
	}

	public static void clickEvent() throws ParseException {
		try {
			highlightElement(dr, waitforElementVisibile(POMObjectRepo.clickEvent));
			waitforElementVisibile(POMObjectRepo.clickEvent).click();
		} catch (Exception e) {
			POMFunction.Error("Problem While Clicking Events - " + e);
		}
	}

	public static void selectEvenRandomly() throws ParseException {
		try {
			WebElement AllTabList = waitforElementVisibile(POMObjectRepo.TabListMain);
			List<WebElement> ListMenu = AllTabList.findElements(ListTabMain);
			POMFunction.Info(ListMenu.size());
			WebElement randLink = ListMenu.get(rand.nextInt(ListMenu.size()));
			POMFunction.Info(randLink.getText());
			String classWeb = randLink.getAttribute("class");
			highlightElement(dr, randLink);
			randLink.click();
			highlightElement(dr, waitforElementVisibile(SibListTabMain(classWeb)));
			waitforElementVisibile(SibListTabMain(classWeb)).click();
			POMFunction.Info("Selected first sibiling record");
		} catch (Exception e) {
			// TODO: handle exception
			TakeScreenShot();
			POMFunction.Error("Problem While Selecting Record Randomly - " + e);
		}
	}

	public static void GetRecordDetails() throws ParseException {
		try {
			createSampleFile();
			WebElement ViewEventTable = FindElement(completeViewEvent);
			List<WebElement> ViewElemetData = ViewEventTable.findElements(cellViewEvent);
			POMFunction.Info(ViewElemetData.size());
			for (WebElement webElement : ViewElemetData) {
				highlightElement(dr, webElement);
				String cellValue = webElement.getText();
				if (cellValue.length() != 0) {
					POMFunction.Info(cellValue);
					brr.write(cellValue);
					brr.newLine();
				} else if (cellValue.length() != 0 && waitforElementVisibile(alertLevelAttrib).isDisplayed()) {
					String Val = waitforElementVisibile(alertLevelAttrib).getAttribute("title");
					int result = Integer.parseInt(Val);
					POMFunction.Info(alertCheck(result));
				}
			}
			log.info("Event data collected in " + TestFile + " file");
		} catch (Exception e) {
			// TODO: handle exception
			TakeScreenShot();
			POMFunction.Error("Problem While Getting Data - " + e);
		}
	}

	/*
	 * public static void Dropdown() { highlightElement(dr,
	 * waitforElementVisibile(By.xpath("//*[normalize-space()='Show' and @class]")))
	 * ;
	 * waitforElementVisibile(By.xpath("//*[normalize-space()='Show' and @class]")).
	 * click(); }
	 */
}
