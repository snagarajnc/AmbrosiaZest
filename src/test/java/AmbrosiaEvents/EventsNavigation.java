package AmbrosiaEvents;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import AbrosiaReports.Zest.base.POMFunction;
import AbrosiaReports.Zest.base.POMObjectRepo;

public class EventsNavigation extends POMFunction {
	public static Logger log = Logger.getLogger(EventsNavigation.class.getClass());

	@BeforeTest
	public void initlog4j() throws IOException {
		Loadlog4j();
	}

	@Test(priority = 1)
	public void clickEvent() throws ParseException {
		try {
			highlightElement(dr, waitforElementVisibile(POMObjectRepo.clickEvent));
			waitforElementVisibile(POMObjectRepo.clickEvent).click();
			log.info("Events Menu clicked"); // Logger
		} catch (Exception e) {
			// TODO: handle exception
			TakeScreenShot();
			log.error(e);
		}
	}

	@Test(priority = 2)
	public void selectEventRandomly() throws ParseException {
		try {
			WebElement AllTabList = waitforElementVisibile(POMObjectRepo.TabListMain);
			List<WebElement> ListMenu = AllTabList.findElements(ListTabMain);
			log.debug("Total ElementsList : " + ListMenu.size()); // Logger
			WebElement randLink = ListMenu.get(rand.nextInt(ListMenu.size()));
			log.debug("Randomly Selected Record Name : " + randLink.getText()); // Logger
			String classWeb = randLink.getAttribute("class");
			highlightElement(dr, randLink);
			randLink.click();
			highlightElement(dr, waitforElementVisibile(SibListTabMain(classWeb)));
			waitforElementVisibile(SibListTabMain(classWeb)).click();
			log.info("Selected first sibiling record");
		} catch (Exception e) {
			// TODO: handle exception
			TakeScreenShot();
			log.error(e);
		}
	}

	@AfterTest
	public void collectEventDetails() throws ParseException {
		try {
			createTxtFile();
			WebElement ViewEventTable = FindElement(completeViewEvent);
			List<WebElement> ViewElemetData = ViewEventTable.findElements(cellViewEvent);
			int Lsize = ViewElemetData.size();
			log.debug("Sub Element Count : " + Lsize);
			if (Lsize != 0) {
				for (WebElement webElement : ViewElemetData) {
					highlightElement(dr, webElement);
					String cellValue = webElement.getText();
					if (cellValue.length() != 0) {
						this.br.write(cellValue);
						this.br.newLine();
						log.debug(cellValue); // Logger
					} else if (waitforElementVisibile(alertLevelAttrib).isDisplayed()) {
						String Val = waitforElementVisibile(alertLevelAttrib).getAttribute("title");
						int result = Integer.parseInt(Val);
						String value = alertCheck(result);
						this.br.write(value);
						this.br.newLine();
						log.debug(value);
					}
				}
				br.close();
				log.info("Event data collected successfully in " + TestFile);
			} else {
				log.info("Browser closed because of Lsize = " + Lsize);
			}
		} catch (Exception e) {
			// TODO: handle exception
			TakeScreenShot();
			log.error(e);
		}
	}
}