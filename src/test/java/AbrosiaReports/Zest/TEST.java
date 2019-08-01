package AbrosiaReports.Zest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TEST extends POMFunction{

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		F1();
	}
	public static void F1() throws IOException, InterruptedException {
		System.setProperty("webdriver.gecko.driver",
				System.getProperty("user.dir") + ObjRepo().getProperty("GeckoDriverLoc"));
		dr = new FirefoxDriver();
		
		dr.get(ObjRepo().getProperty("URL"));
		Thread.sleep(2000);
		dr.findElement(LoginUserName).sendKeys(ObjRepo().getProperty("UserNameCredential"));
		dr.findElement(LoginPassword).sendKeys(ObjRepo().getProperty("PasswordCredential"));
		dr.findElement(LoginSubmit).click();
	}
}
