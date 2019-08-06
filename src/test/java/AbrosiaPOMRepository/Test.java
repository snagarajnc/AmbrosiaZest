package AbrosiaPOMRepository;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Test {

	public static void main(String args[]) throws Exception {

		String TestFile = "D:\\Users\\snagaraj\\eclipse-workspace\\Zest\\src\\excelExportAndFileIO\\new.txt";
		FileWriter FW = new FileWriter(TestFile);
		BufferedWriter BW = new BufferedWriter(FW);

		for (int i = 0; i < 10; i++) {
			BW.write("Itr : " + i);
			BW.newLine();
		}
		BW.close();
	}
}