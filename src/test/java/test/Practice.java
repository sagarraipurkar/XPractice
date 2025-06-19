package test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Practice {
	WebDriver driver;

	@Test(groups = { "smoke" })
	public void ClickOnSearchButtonWithSvg() throws IOException {
		String browser = System.getProperty("browser");
//		// Setup ChromeOptions
//		ChromeOptions option = new ChromeOptions();
//		FirefoxOptions fireOption = new FirefoxOptions();
//		EdgeOptions edgeOptions = new EdgeOptions();
////		edgeOptions.addArguments("--headless");
//		option.addArguments("--incognito");
//		option.addArguments("--disable-notification");
//
//		HashMap<String, Integer> contentSetting = new HashMap<>();
//		HashMap<String, Object> profile = new HashMap<>();
//		HashMap<String, Object> prefs = new HashMap<>();
//		contentSetting.put("location", 1);
//		profile.put("managed_default_content_settings", contentSetting);
//		prefs.put("profile", profile);
//		option.setExperimentalOption("prefs", prefs);
//
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.get("https://www.spicejet.com/");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Signup')]"))).click();
		String parentWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();

		for (String window : allWindows) {
			if (!window.equals(parentWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first_name"))).sendKeys("Sagar");
		TakesScreenshot src = (TakesScreenshot) driver;
		File source = src.getScreenshotAs(OutputType.FILE);
		String timeStamp = new SimpleDateFormat("yyyymmddHHmmss").format(new Date());
		File destination = new File("C:\\Eclipse New\\NewWorkSpace\\XPractice\\target\\screenshot\\" + timeStamp + "TestCaseJenkin123456.png");
		FileUtils.copyFile(source, destination);
	}
	
	@Test(groups = { "regression" })
	public void ClickOnSearchButtonWithSvgRegression() throws IOException {
		String browser = System.getProperty("browser");
//		// Setup ChromeOptions
//		ChromeOptions option = new ChromeOptions();
//		FirefoxOptions fireOption = new FirefoxOptions();
//		EdgeOptions edgeOptions = new EdgeOptions();
////		edgeOptions.addArguments("--headless");
//		option.addArguments("--incognito");
//		option.addArguments("--disable-notification");
//
//		HashMap<String, Integer> contentSetting = new HashMap<>();
//		HashMap<String, Object> profile = new HashMap<>();
//		HashMap<String, Object> prefs = new HashMap<>();
//		contentSetting.put("location", 1);
//		profile.put("managed_default_content_settings", contentSetting);
//		prefs.put("profile", profile);
//		option.setExperimentalOption("prefs", prefs);
//
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.get("https://www.spicejet.com/");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Signup')]"))).click();
		String parentWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();

		for (String window : allWindows) {
			if (!window.equals(parentWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first_name"))).sendKeys("Vikas");
		TakesScreenshot src = (TakesScreenshot) driver;
		File source = src.getScreenshotAs(OutputType.FILE);
		String timeStamp = new SimpleDateFormat("yyyymmddHHmmss").format(new Date());
		File destination = new File("C:\\Eclipse New\\NewWorkSpace\\XPractice\\target\\screenshot\\" + timeStamp + "TestCaseJenkin123456.png");
		FileUtils.copyFile(source, destination);
	}
}
