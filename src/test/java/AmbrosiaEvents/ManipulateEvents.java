package AmbrosiaEvents;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import AbrosiaReports.Zest.POMFunction;
import AbrosiaReports.Zest.POMRepo;

public class ManipulateEvents extends POMFunction {
	public static Logger log = Logger.getLogger(ManipulateEvents.class.getClass());

	@BeforeTest
	public void initlog4j() throws IOException {
		Loadlog4j();
	}

	@Test
	public void clickEvent() throws ParseException {
		try {
		POMFunction.Info("Events Manipulated");
		waitforElementVisibile(AbrosiaReports.Zest.POMRepo.clickEvent).click();
		TakeScreenShot();
		log.debug("Events Menu clicked"); // Logger
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e);
		}
	}

	@Test
	public void TakeEventsList() {
		try {
		WebElement AllTabList = waitforElementVisibile(POMRepo.TabListMain);
		List<WebElement> ListMenu = AllTabList.findElements(ListTabMain);
		Info(ListMenu.size());

		WebElement randLink = ListMenu.get(rand.nextInt(ListMenu.size()));
		randLink.click();
		log.debug("Record selected randomly : " + randLink.getText()); // Logger
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e);
		}
	}
}