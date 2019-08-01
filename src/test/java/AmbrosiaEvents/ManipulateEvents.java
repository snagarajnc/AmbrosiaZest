package AmbrosiaEvents;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import AbrosiaReports.Zest.POMFunction;
import AbrosiaReports.Zest.POMRepo;

public class ManipulateEvents extends POMFunction {
	public static ManipulateEvents obj = new ManipulateEvents();

	@BeforeTest
	public void initlog4j() throws IOException {
		Loadlog4j();
	}

	@Test
	public void clickEvent() throws ParseException {
		POMFunction.Info("Events Manipulated");
		waitforElementVisibile(AbrosiaReports.Zest.POMRepo.clickEvent).click();
		TakeScreenShot();
		obj.Log().debug("Events Menu clicked"); // Logger
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