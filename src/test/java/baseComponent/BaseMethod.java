package baseComponent;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseMethod extends DriverInitilization{
	
	@BeforeMethod
	public void Setup() throws IOException {

		String browser = getProperty("browser");

		switch (browser) {
		case "chrome":
			driver = new ChromeDriver();
			break;

		case "firefox":
			driver = new FirefoxDriver();
			break;

		case "edge":
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("Browser is invalid");
			break;
		}

		driver.get(getProperty("baseurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	public String getProperty(String key) throws IOException {
		FileInputStream FilePath = new FileInputStream(
				"C:\\Eclipse New\\NewWorkSpace\\XPractice\\src\\test\\java\\baseComponent\\global.properties");
		Properties prop = new Properties();
		prop.load(FilePath);
		return prop.get(key).toString();
	}

	public static String getTestData(String key) throws IOException {
		FileInputStream FilePath = new FileInputStream(
				"C:\\Eclipse New\\NewWorkSpace\\XPractice\\src\\test\\java\\baseComponent\\testData.properties");
		Properties prop = new Properties();
		prop.load(FilePath);
		return prop.get(key).toString();
	}
	
    public WebDriver getDriver() {
        return driver;
    }

//
//	@AfterMethod
//	public void tearDown() {
//		driver.quit();
//	}

}
