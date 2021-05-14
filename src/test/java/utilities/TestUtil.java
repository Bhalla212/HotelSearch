package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import com.google.common.io.Files;


public class TestUtil extends base.TestBase {

	public static String fileName;

	public static void captureScreenshot() throws IOException {
		Date d = new Date();
		fileName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		FileHandler.copy(screenshot,
				new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\" + fileName));
		FileHandler.copy(screenshot,
				new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + fileName));

	}

	public static Object[][] getData(String sheetName) {

		
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][cols];

		for (int rowNum = 2; rowNum <= rows; rowNum++) {

			for (int colNum = 0; colNum < cols; colNum++) {

				data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);

			}

		}

		return data;
	}
	
	
	public static boolean isFileDownloaded(String downloadPath, String fileName) {
		  File dir = new File(downloadPath);
		  File[] dirContents = dir.listFiles();
		  for (int i = 0; i < dirContents.length; i++) {
		      if (dirContents[i].getName().contains(fileName)) {
		          // File has been found, it can now be deleted:
		    	  System.out.println(dirContents[i]);
		          dirContents[i].delete();
		          return true;
		      }
		          }
		      return false;
		  } 
	
	
}
