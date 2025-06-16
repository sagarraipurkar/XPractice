package test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import baseComponent.BaseMethod;
import pageLocators.HomePage;
import pageLocators.ResultsPage;

public class LaunchBrowser extends BaseMethod {

	WebDriver driver;
	private ResultsPage resultsPage;
	private HomePage homePage;

//	@BeforeMethod
//	public void setUpTest() {
//			}

	public static String selectedFromCityText;

//    @Test (groups = {"smoke"})
//    public void performInitialSetup() throws IOException, InterruptedException {
//        Actions action = new Actions(driver);
//        action.moveByOffset(10, 10).click().perform();
//        boolean isSelected = homePage.HeaderList(getTestData("MenuOption"));
//        Assert.assertTrue(isSelected, "Header option was not selected properly");
//    }
//
//    @Test
//    public void TestActionClass() throws IOException, InterruptedException {
//        performInitialSetup(); 
//    }

	@Test(groups = { "smoke" })
	public void Search() throws IOException, InterruptedException {
		driver = getDriver();
		resultsPage = new ResultsPage(driver);
		homePage = new HomePage(driver);
		Actions action = new Actions(driver);
		action.moveByOffset(10, 10).click().perform();
		boolean isSelected = homePage.HeaderList(getTestData("MenuOption"));
		Assert.assertTrue(isSelected, "Header option was not selected properly");
		homePage.clickOnFromCity();
		homePage.EnterFromCity(getTestData("FromCity"));
		homePage.selectFromCityFromTheList(getTestData("SelectFromCity"));
		selectedFromCityText = homePage.afterSelectingTheFromCityText();
		Assert.assertEquals(selectedFromCityText, getTestData("SelectFromCity"));
//        Thread.sleep(2000);
//        homePage.selectCheckInDates(getTestData("CheckIndate"), getTestData("CheckInmonth"), getTestData("Checkinyear"), getTestData("CheckOutdate"));
//        Thread.sleep(5000);

		Actions action1 = new Actions(driver);
		action1.moveByOffset(10, 10).click().perform();
		homePage.ClickOnGuestFiled();
		homePage.clickOnRoomDropDown();
		homePage.selectRoomCount(04);
		homePage.clickOnApplybutton();

		homePage.clickOnSearch();
		boolean isSearchResultVisible = resultsPage.getSearchResultCount();
		Assert.assertTrue(isSearchResultVisible, "Search results should be visible after clicking search");

	}

	public void takeScreenshot() throws IOException {
		TakesScreenshot src = (TakesScreenshot) driver;
		File source = src.getScreenshotAs(OutputType.FILE);
		String timeStamp = new SimpleDateFormat("yyyymmddHHmmss").format(new Date());
		File destination = new File("C:\\Eclipse New\\NewWorkSpace\\XPractice\\target\\" + timeStamp + "TestCase.png");
		FileUtils.copyFile(source, destination);
	}

	@Test(groups = { "smoke" })
	public void gettingTest() {
		System.out.println("Testing");
	}
}
