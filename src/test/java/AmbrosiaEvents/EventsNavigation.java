package AmbrosiaEvents;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import AbrosiaPOMRepository.POMFunction;
import AbrosiaPOMRepository.POMObjectRepo;

public class EventsNavigation extends POMFunction {
	public static Logger log = Logger.getLogger(EventsNavigation.class.getClass());
	/*
	 * public static FileWriter write; public static BufferedWriter br;
	 */

	@BeforeTest
	public void initlog4j() throws IOException {
		Loadlog4j();
	}

	@Test(priority = 1)
	public void clickEvent() throws ParseException {
		try {
			POMFunction.Info("Events Navigation");
			waitforElementVisibile(AbrosiaPOMRepository.POMObjectRepo.clickEvent).click();
			TakeScreenShot();
			log.info("Events Menu clicked"); // Logger
		} catch (Exception e) {
			// TODO: handle exception
			TakeScreenShot();
			log.error(e);
		}
	}

	@Test(priority = 2)
	public void SelectEvent() throws ParseException {
		try {
			WebElement AllTabList = waitforElementVisibile(POMObjectRepo.TabListMain);
			List<WebElement> ListMenu = AllTabList.findElements(ListTabMain);
			log.debug("Size of ElementsList #1 : " + ListMenu.size()); // Logger
			WebElement randLink = ListMenu.get(rand.nextInt(ListMenu.size()));
			log.debug("Randomly Selected Record Name : " + randLink.getText()); // Logger
			String classWeb = randLink.getAttribute("class");
			randLink.click();
			waitforElementVisibile(SibListTabMain(classWeb)).click();
			log.info("Selected first sibiling record");
		} catch (Exception e) {
			// TODO: handle exception
			TakeScreenShot();
			log.error(e);
		}
	}

	@AfterTest
	public void GetRecordDetails() throws ParseException {
		try {
			createTxtFile();
			WebElement ViewEventTable = FindElement(completeViewEvent);
			List<WebElement> ViewElemetData = ViewEventTable.findElements(cellViewEvent);
			log.debug("SSS : " + ViewElemetData.size());
			for (WebElement webElement : ViewElemetData) {
				String cellValue = webElement.getText();
				if (cellValue.length() != 0) {
					br.write(cellValue);
					br.newLine();
					log.debug(cellValue); // Logger
				} else {
					log.debug("****** Empty Field ******"); // Logger
				}

			}
			br.close();
			log.info("Event data collected in " + TestFile + " file");
		} catch (Exception e) {
			// TODO: handle exception
			TakeScreenShot();
			log.error(e);
		}
	}
}