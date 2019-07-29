package AbrosiaReports.Zest;

import org.testng.annotations.Test;

public class NavigateEvents {
	@Test
	public void Event1() {
		System.out.println("Test CompleteEventsGuide");
		POMRepo.waitforElementVisibile(AbrosiaReports.Zest.POMRepo.clickEvent);
		POMRepo.FindElement(AbrosiaReports.Zest.POMRepo.clickEvent).click();
	}

}
