package pak;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import AbrosiaReports.Zest.base.POMFunction;
import AbrosiaReports.Zest.base.POMObjectRepo;
import AmbrosiaLaunchSite.LaunchAmbrosia;

public class PracticeClass extends LaunchAmbrosia {

	public static void main(String[] args) throws IOException, InterruptedException, ParseException {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + ObjRepo().getProperty("ChromeDriverLoc"));
		dr = new ChromeDriver();
		dr.get("https://release.ambrosia.lab.nectarvoip.com/dashboardui/signin");
		AmbrosiaLogin();
		clickEvent();
		selectEvenRandomly();
		GetRecordDetails();
	}

	public static void clickEvent() throws ParseException {
		try {
			highlightElement(dr, waitforElementVisibile(POMObjectRepo.clickEvent));
			waitforElementVisibile(POMObjectRepo.clickEvent).click();
		} catch (Exception e) {
			POMFunction.Error(e);
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
			POMFunction.Error(e);
		}
	}

	public static void GetRecordDetails() throws ParseException {
		try {
			createTxtFile();
			WebElement ViewEventTable = FindElement(completeViewEvent);
			List<WebElement> ViewElemetData = ViewEventTable.findElements(cellViewEvent);
			POMFunction.Info(ViewElemetData.size());
			for (WebElement webElement : ViewElemetData) {
				highlightElement(dr, webElement);
				String cellValue = webElement.getText();
				if (cellValue.length() != 0) {
					br.write(cellValue);
					br.newLine();
					POMFunction.Info(cellValue);
				} else if (webElement.getAttribute("class").contains(alertLevelAttrib)) {
					POMFunction.Info(webElement.getAttribute("title"));
				}
			}
			br.close();
			log.info("Event data collected in " + TestFile + " file");
		} catch (Exception e) {
			// TODO: handle exception
			TakeScreenShot();
			POMFunction.Error(e);
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
