package com.abrosia.zest.base;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.abrosia.zest.selenium.actions.POMFunction;
import com.abrosia.zest.utils.AmbrosiaUtils;

public class BaseTest extends AmbrosiaUtils {
	public static Logger log = Logger.getLogger(BaseTest.class.getClass());

	@BeforeSuite
	public void beforeSuite() throws ParseException, IOException {
		POMFunction.Loadlog4j();
		file = new File(AmbrosiaUtils.CurrentReportLocation() + "\\Screenshots");
		file.mkdirs();
		
	}

	@AfterSuite
	public void afterSuite() {
	}
}