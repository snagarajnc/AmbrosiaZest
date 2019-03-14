package AbrosiaReports.Zest;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class POMFunction {
	public static Properties ObjRepo() throws IOException {
		FileReader ff = new FileReader(System.getProperty("user.dir") + "/utilsPorperties/ObjectRepoProp.properties");
		Properties pp = new Properties();
		pp.load(ff);
		return (pp);
	}

	public static void Pass(String message) {
		System.out.println("PASS: " + message);
	}

	public static void Error(String message) {
		System.err.println("FAIL: " + message);
	}

	public static void Info(String message) {
		System.out.println("INFO: " + message);
	}
}