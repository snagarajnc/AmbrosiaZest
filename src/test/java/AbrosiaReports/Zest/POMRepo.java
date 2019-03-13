package AbrosiaReports.Zest;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class POMRepo {
	
	public static Properties ObjRepo() throws IOException {
		FileReader ff = new FileReader(System.getProperty("user.dir") + "/utilsPorperties/ObjectRepoProp.properties");
		Properties pp = new Properties();
		pp.load(ff);
		return (pp);
	}

	public static WebDriver dr;
	public static By LoginUserName = By.name("login");
	public static By LoginPassword = By.name("password");
	public static By LoginSubmit = By.className("dui-login-submit");
	public static By Logo = By.className("dui-login-logo");

}
