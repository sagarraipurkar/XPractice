package pageLocators;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Vwo_Login {

	WebDriver driver;
	WebDriverWait wait;

	public Vwo_Login(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait (driver, Duration.ofSeconds(5)); 
	}
	
	private By email = By.id("login-username");
	private By password = By.id("login-password");
	private By submit_Button = By.id("js-login-btn");
	
	public void LoginCredential(String userEmail, String Userpassword) {
		driver.findElement(email).sendKeys(userEmail);
		driver.findElement(password).sendKeys(Userpassword);
	}
	
	public void clickOnSumbitButton() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(submit_Button)).click();
	}
}
