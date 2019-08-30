package com.abrosia.zest.utils;

import java.text.ParseException;

import com.abrosia.zest.selenium.actions.POMFunction;

public class AmbrosiaUtils extends POMFunction {

	// Return CurrentTime
	public static long TimeGen() throws ParseException {
		currentDate = dateFormat.parse(TodayDateFormat).getTime();
		return currentDate;
	}

	// Create report location Path for current execution
	public static String CreateReportFolder(long time) {
		currentRunReportPath = System.getProperty("user.dir") + "\\ReportsAuto\\" + time;
		return currentRunReportPath;
	}

	public static String CurrentReportLocation() throws ParseException {
		String CurrentReportLocation = CreateReportFolder(TimeGen());
		POMFunction.Error("AutoReportPath : "+CurrentReportLocation);
		return CurrentReportLocation;
	}

}