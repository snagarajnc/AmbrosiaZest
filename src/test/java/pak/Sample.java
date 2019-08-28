package pak;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Sample {
	public FileWriter FWW;
	public BufferedWriter brr;
	public File file;
	public String currentRunReportPath;
	// Date
	public DateFormat ObjD = new SimpleDateFormat("dd-M-yyyy hh:mm:ss:SSSS");
	public String TodayDateFormat = ObjD.format(new Date());
	public long currentDate;

	public static void main(String[] args) throws IOException, ParseException {
		Sample obj = new Sample();
		obj.F2();
	}

	public void F1() throws IOException {
		file = new File("D:/myfile.txt");
		file.createNewFile();
		FWW = new FileWriter(file);
		brr = new BufferedWriter(FWW);
	}

	public void F2() throws ParseException {
		System.out.println("currentRunReportPath : " + FileCreate(TimeGen()));
		file = new File(FileCreate(TimeGen()) + "\\Screenshots");
		file.mkdirs();
	}

	public String FileCreate(long time) throws ParseException {
		currentRunReportPath = System.getProperty("user.dir") + "\\ReportsAuto\\" + time;
		return currentRunReportPath;
	}

	public long TimeGen() throws ParseException {
		currentDate = ObjD.parse(TodayDateFormat).getTime();
		return currentDate;
	}
}
