package AmbrosiaEvents;

import java.text.ParseException;
import org.testng.annotations.Test;

import AbrosiaReports.Zest.POMFunction;
import AbrosiaReports.Zest.POMRepo;

public class ManipulateEvents {
	@Test
	public void Event1() throws ParseException {
		POMFunction.Info("Events Manipulated");
		POMRepo.waitforElementVisibile(AbrosiaReports.Zest.POMRepo.clickEvent).click();
		POMRepo.TakeScreenShot();
		POMRepo.dr.quit();
	}
}
