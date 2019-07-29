package AmbrosiaEvents;

import org.testng.annotations.Test;

import AbrosiaReports.Zest.POMRepo;

public class ManipulateEvents {
	@Test
	public void Event1() {
		System.out.println("Test CompleteEventsGuide");
		POMRepo.waitforElementVisibile(AbrosiaReports.Zest.POMRepo.clickEvent);
		POMRepo.FindElement(AbrosiaReports.Zest.POMRepo.clickEvent).click();
	}
}
