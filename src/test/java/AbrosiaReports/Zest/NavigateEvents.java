package AbrosiaReports.Zest;

import org.testng.annotations.Test;

public class NavigateEvents extends POMFunction {
	@Test
	public void Event1() {
		System.out.println("Test CompleteEventsGuide");
		waitforElementVisibile(AbrosiaReports.Zest.POMRepo.clickEvent);
		FindElement(AbrosiaReports.Zest.POMRepo.clickEvent).click();
	}

}
