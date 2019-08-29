package com.abrosia.zest.testcases;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.abrosia.zest.objectrepository.POMObjectRepo;
import com.abrosia.zest.selenium.actions.POMFunction;

public class TC002_EventNavigation extends POMFunction {
	public static Logger log = Logger.getLogger(TC002_EventNavigation.class.getClass());

	@BeforeTest
	public void initlog4j() throws IOException {
		Loadlog4j();
	}

	@Test
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

	@Test
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
			WebElement ViewEventTable = FindElement(completeViewEvent);
			List<WebElement> ViewElemetData = ViewEventTable.findElements(cellViewEvent);
			int Lsize = ViewElemetData.size();
			log.debug("Sub Element Count : " + Lsize);
			if (Lsize != 0) {
				createTxtFile();
				for (WebElement webElement : ViewElemetData) {
					highlightElement(dr, webElement);
					String cellValue = webElement.getText();
					if (cellValue.length() != 0) {
						br.write(cellValue);
						br.newLine();
						log.debug(cellValue); // Logger
					} else if (waitforElementVisibile(alertLevelAttrib).isDisplayed()) {
						String Val = waitforElementVisibile(alertLevelAttrib).getAttribute("title");
						int result = Integer.parseInt(Val);
						String value = alertCheck(result);
						br.write(value);
						br.newLine();
						log.debug(value);
					}
				}
				br.close();
			} else {
				browserQuit();
				log.info("Browser closed because of Lsize = " + Lsize);
			}
		} catch (Exception e) {
			// TODO: handle exception
			TakeScreenShot();
			log.error(e);
		}
	}
}