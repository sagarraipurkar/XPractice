package baseComponent;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import pageLocators.HomePage;
import pageLocators.ResultsPage;

public class ResuableMethods extends BaseMethod {

    public static String selectedFromCityText;

    public WebDriver driver;
    private HomePage homePage;
    private ResultsPage resultsPage;

    // ✅ Constructor-based initialization
    public ResuableMethods(WebDriver driver) {
        this.driver = driver;
        this.homePage = new HomePage(driver);
        this.resultsPage = new ResultsPage(driver);
    }

    private void performInitialSetup() throws IOException, InterruptedException {
        Actions action = new Actions(driver);
        action.moveByOffset(10, 10).click().perform();

        boolean isSelected = homePage.HeaderList(getTestData("MenuOption"));
        Assert.assertTrue(isSelected, "Header option was not selected properly");
    }

    public void Search() throws IOException, InterruptedException {
        performInitialSetup();

        homePage.clickOnFromCity();
        homePage.EnterFromCity(getTestData("FromCity"));
        homePage.selectFromCityFromTheList(getTestData("SelectFromCity"));

        selectedFromCityText = homePage.afterSelectingTheFromCityText();
        Assert.assertEquals(selectedFromCityText, getTestData("SelectFromCity"));

        Actions action = new Actions(driver);
        action.moveByOffset(10, 10).click().perform();
        homePage.ClickOnGuestFiled();
        homePage.clickOnRoomDropDown();
        homePage.selectRoomCount(4);
        homePage.clickOnApplybutton();

        homePage.clickOnSearch();
        boolean isSearchResultVisible = resultsPage.getSearchResultCount();
        Assert.assertTrue(isSearchResultVisible, "Search results should be visible after clicking search");
    }
}
