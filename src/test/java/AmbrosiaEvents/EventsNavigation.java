package AmbrosiaEvents;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import AbrosiaPOMRepository.POMFunction;
import org.apache.log4j.RollingFileAppender;
import AbrosiaPOMRepository.POMObjectRepo;

public class EventsNavigation extends POMFunction {
	public static Logger log = Logger.getLogger(EventsNavigation.class.getClass());

	@BeforeTest
	public void initlog4j() throws IOException {
		Loadlog4j();
	}

	@Test
	public void clickEvent() throws ParseException {
		try {
			POMFunction.Info("Events Navigation");
			waitforElementVisibile(AbrosiaPOMRepository.POMObjectRepo.clickEvent).click();
			TakeScreenShot();
			log.info("Events Menu clicked"); // Logger
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e);
		}
	}

	@Test
	public void SelectEvent() {
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
			log.error(e);
		}
	}

	@Test
	public void GetRecordDetails() {

	}
}