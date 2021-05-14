package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExcelReader;


public class TestBase {

	public static WebDriver driver;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static Properties Config;
	public static WebDriverWait wait;
	public static WebElement dropdown;
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xls");

	// Initialization of property files
	public TestBase() {
		try {
			Config = new Properties();
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\Properties\\Config.properties");
			Config.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Start of browser
	public static void Initialization() {

		if (Config.getProperty("browser").equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if (Config.getProperty("browser").equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (Config.getProperty("browser").equals("IE")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			// log.debug("IE launched");
		} else if (Config.getProperty("browser").equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			// log.debug("IE launched");
		} else if (Config.getProperty("browser").equals("opera")) {
			WebDriverManager.edgedriver().setup();
			driver = new OperaDriver();
			
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")),
				TimeUnit.SECONDS);

		wait = new WebDriverWait(driver, Integer.parseInt(Config.getProperty("explicit.wait")));
	}

	// update into the config
	public static void AdminLogin() {
		//driver.get("https://tboholidays.com");
		driver.get(Config.getProperty("URL"));
		driver.findElement(By.id("LoginNameNew")).clear();
		driver.findElement(By.id("LoginNameNew")).sendKeys(Config.getProperty("UserName"));
		driver.findElement(By.id("PasswordNew")).clear();
		driver.findElement(By.id("PasswordNew")).sendKeys(Config.getProperty("Password"));
		driver.findElement(By.id("LoginImageNew")).click();
	}

	public static boolean IsElementPresent(String locator) {
		try {
			driver.findElement(By.xpath(locator));
			return true;
		} catch (Exception e) {
			log.debug(e.getMessage());
			return false;
		}
	}

	public static boolean IsElementPresent1(WebElement locator) {
		try {
			driver.findElement((By) locator);
			return true;
		} catch (Exception e) {
			log.debug(e.getMessage());
			return false;
		}

	}

	public void click(WebElement loactor) {
		loactor.click();
	}

	// checking the present of elements like list and check boxes
	public static boolean IsElementPresents(String locator) {
		try {
			return driver.findElements(By.xpath(locator)).size() != 0;
		} catch (Throwable t) {

			log.debug(t.getMessage());
			return false;
		}

	}

	// Common method of slid mover
	public static void sliderMover(WebElement locater, int x, int y) {
		try {
			WebElement Slider = locater;
			Actions action = new Actions(driver);
			action.clickAndHold(Slider);
			action.moveByOffset(x, y).perform();
			action.release().perform();
		} catch (Throwable t) {
			log.debug(t.getMessage());
		}
	}

	public void actionMovetoElement(Action mouseOverQueues, WebElement element) {
		try {
			Actions builder = new Actions(driver);
			mouseOverQueues = builder.moveToElement(element).build();
			mouseOverQueues.perform();
		} catch (Throwable t) {
			log.debug(t.getMessage());
		}
	}

	public static void selectByValue(WebElement locator, String value) {

		try {

			dropdown = locator;
			Select select = new Select(dropdown);
			select.selectByValue(value);
		} catch (Throwable t) {
			log.debug(t.getMessage());
		}
	}

	public static void selectByVisibleText(WebElement locator, String value) {

		try {

			dropdown = locator;
			Select select = new Select(dropdown);
			select.selectByVisibleText(value);
			log.debug("Selecting from Element : " + locator + " selected value as : " + value);
		} catch (Throwable t) {
			log.debug(t.getMessage());
		}
	}

	public static void selectByIndex(WebElement locator, int value) {
		try {
			dropdown = locator;
			Select select = new Select(dropdown);
			select.selectByIndex(value);
			log.debug("Selecting from Element : " + locator + " selected value as : " + value);
		} catch (Throwable t) {
			log.debug(t.getMessage());
		}
	}

	public static void splitMethod(String test) {

		String[] result = test.split("\\|");

		String s0 = result[0];
		String s4 = result[4];
		System.out.println("AIRLINE NAME :" + s0);
		System.out.println("CABIN CLASS :" + s4);
		for (String s : result) {
			System.out.println(s);
		}
	}

	// For start the auto-suggestion
	public static void AutosuggestSelection(String locator) {
		List<WebElement> autoSuggest = driver.findElements(By.xpath(locator));
		System.out.println("Size of the AutoSuggets is = " + autoSuggest.size());
		for (WebElement a : autoSuggest)
			System.out.println("Values are = " + a.getText());
		autoSuggest.get(0).click();
	}

	public static String CurrentDateTimeStamp() {
		// Date object
		Date date = new Date();
		// getTime() returns current time in milliseconds
		long time = date.getTime();
		// Passed the milliseconds to constructor of Timestamp class
		Timestamp ts = new Timestamp(time);
		System.out.println("Current Time Stamp: " + ts);
		String date1 = ts.toString();
		return date1;
	}

//	@AfterSuite
	public void tearDown() {

		driver.close();
		driver.quit();
		log.debug("Test successfully executed !!!");

	}

}
