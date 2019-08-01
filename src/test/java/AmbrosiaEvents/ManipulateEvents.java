package AmbrosiaEvents;

import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import AbrosiaReports.Zest.POMFunction;
import AbrosiaReports.Zest.POMRepo;

public class ManipulateEvents extends POMFunction {
	static Logger log = Logger.getLogger(ManipulateEvents.class.getName());

	@Test
	public void clickEvent() throws ParseException {
		POMFunction.Info("Events Manipulated");
		waitforElementVisibile(AbrosiaReports.Zest.POMRepo.clickEvent).click();
		TakeScreenShot();
	}

	@Test
	public void TakeEventsList() {
		WebElement AllTabList = waitforElementVisibile(POMRepo.TabListMain);
		List<WebElement> ListMenu = AllTabList.findElements(ListTabMain);
		Info(ListMenu.size());
		for (WebElement webElement : ListMenu) {
			
			
		}
	}
}