package test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Test;

import baseComponent.BaseMethod;
import baseComponent.TestDataFromExcel;
import pageLocators.Vwo_Login;

public class Login extends BaseMethod {

	private static final Logger logger = LogManager.getLogger(Login.class);

	@Test(dataProvider = "getData", dataProviderClass = TestDataFromExcel.class)
	public void login(String email, String password) throws IOException {
		logger.info("Starting login test with email: " + email);
		Vwo_Login vwo_Login = new Vwo_Login(driver);
		vwo_Login.LoginCredential(email, password);
		vwo_Login.clickOnSumbitButton();
		logger.info("Login attempt completed for email: " + email);
		File file = new File("C:\\Eclipse New\\NewWorkSpace\\XPractice\\target\\Test1.jpg");
		TakesScreenshot src = (TakesScreenshot) driver;
		File source = src.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, file);

	}
}
