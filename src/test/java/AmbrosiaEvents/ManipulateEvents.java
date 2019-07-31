package AmbrosiaEvents;

import java.text.ParseException;
import org.testng.annotations.Test;

import AbrosiaReports.Zest.POMFunction;
import AbrosiaReports.Zest.POMRepo;

public class ManipulateEvents {
	@Test
	public void clickEvent() throws ParseException {
		POMFunction.Info("Events Manipulated");
		POMRepo.waitforElementVisibile(AbrosiaReports.Zest.POMRepo.clickEvent).click();
		POMRepo.TakeScreenShot();
	}
	
	@Test
	public void clickEvent1() {
		
	}
}
