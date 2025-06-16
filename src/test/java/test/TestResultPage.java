package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseComponent.BaseMethod;
import baseComponent.ResuableMethods;
import pageLocators.HomePage;
import pageLocators.ResultsPage;

public class TestResultPage extends BaseMethod {

	WebDriver driver;
	private ResultsPage resultsPage;
	private HomePage homePage;
	private ResuableMethods ResuableMethods;

	@BeforeMethod
	public void setUpTest() {
		driver = getDriver();
		resultsPage = new ResultsPage(driver);
		homePage = new HomePage(driver);
		ResuableMethods = new ResuableMethods(driver);
	}

	@Test
	public void VerifyFromCity() throws InterruptedException, IOException {
		ResuableMethods.Search();
		String actualFromCityText = resultsPage.getValueOfFromCity();
		System.out.println("Expected: " + ResuableMethods.selectedFromCityText);
		System.out.println("Actual: " + actualFromCityText);
		Assert.assertEquals(actualFromCityText, ResuableMethods.selectedFromCityText);
	}

	@Test
	public void VerifyClickOnHotel() throws IOException, InterruptedException {
		String ParentWindow = driver.getWindowHandle();
		ResuableMethods.Search();

		resultsPage.clickOnHotel("Crowne Plaza Pune City Centre");
		Thread.sleep(5000);

		Set<String> getAllWindows = driver.getWindowHandles();
		for (String window : getAllWindows) {
			if (!window.equals(ParentWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}

		driver.findElement(By.xpath("//button[normalize-space()='BOOK THIS NOW']")).click();
	}

	@Test
	public void VerifyUserRatingFilter() throws IOException, InterruptedException {
		ResuableMethods.Search();

		List<WebElement> sortBy = driver.findElements(By.xpath("//span[@class = 'srtByFltr__list--itemTitle']"));
		for (WebElement sort : sortBy) {
			String getsortValue = sort.getText().trim();
			System.out.println("Sort option: " + getsortValue);
			if (getsortValue.equals("Price (Highest First)")) {
				sort.click();
				break;
			}
		}

		List<WebElement> pricelist = driver.findElements(By.xpath("//p[@id='hlistpg_hotel_shown_price']"));
		List<Long> priceBeforeSorting = new ArrayList<>();
		for (WebElement price : pricelist) {
			String getpriceValue = price.getText().replace("$", "").trim();
//			System.out.println("Before sort: " + getpriceValue);
			int number = Integer.parseInt(getpriceValue.replace(",", ""));
			priceBeforeSorting.add(Long.valueOf(number));
		}

		for (int i = 0; i < priceBeforeSorting.size() - 1; i++) {
			System.out.println(priceBeforeSorting.get(i));
			Assert.assertTrue(priceBeforeSorting.get(i) >= priceBeforeSorting.get(i + 1));
//
//		for (WebElement price : pricelist) {
//			String getpriceValue = price.getText().replace("$", "").trim();
//			System.out.println("Before sort: " + getpriceValue);
//			priceBeforeSorting.add(Double.valueOf(getpriceValue));
//		}
//
//		List<WebElement> sortBy = driver.findElements(By.xpath("//span[@class = 'srtByFltr__list--itemTitle']"));
//		for (WebElement sort : sortBy) {
//			String getsortValue = sort.getText().trim();
//			System.out.println("Sort option: " + getsortValue);
//			if (getsortValue.equals("Price (Highest First)")) {
//				sort.click();
//				break;
//			}
//		}
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.stalenessOf(pricelist.get(0)));
//		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[@id='hlistpg_hotel_shown_price']")));
//
//		// Re-locate the price list
//		List<WebElement> pricelistAfter = driver.findElements(By.xpath("//p[@id='hlistpg_hotel_shown_price']"));
//		List<Double> priceAfterSorting = new ArrayList<>();
//
//		for (WebElement ratingValue : pricelistAfter) {
//			String getRatingValue = ratingValue.getText().replace("$", "").trim();
//			System.out.println("After sort: " + getRatingValue);
//			priceAfterSorting.add(Double.valueOf(getRatingValue));
//		}
//
//		// Sort in descending order for comparison
//		Collections.sort(priceBeforeSorting, Collections.reverseOrder());
//		Assert.assertEquals(priceBeforeSorting, priceAfterSorting);
		}
	}

	@Test
	public void VerifyPricelowToHighFilter() throws IOException, InterruptedException {
		ResuableMethods.Search();

		List<WebElement> sortBy = driver.findElements(By.xpath("//span[@class = 'srtByFltr__list--itemTitle']"));
		for (WebElement sort : sortBy) {
			String getsortValue = sort.getText().trim();
			System.out.println("Sort option: " + getsortValue);
			if (getsortValue.equals("Price (Lowest First)")) {
				sort.click();
				break;
			}
		}

		List<WebElement> pricelist = driver.findElements(By.xpath("//p[@id='hlistpg_hotel_shown_price']"));
		List<Long> priceBeforeSorting = new ArrayList<>();
		for (WebElement price : pricelist) {
			String getpriceValue = price.getText().replace("$", "").trim();
//			System.out.println("Before sort: " + getpriceValue);
			int number = Integer.parseInt(getpriceValue.replace(",", ""));
			priceBeforeSorting.add(Long.valueOf(number));
		}

		for (int i = 0; i < priceBeforeSorting.size() - 1; i++) {
			System.out.println(priceBeforeSorting.get(i));
			Assert.assertTrue(priceBeforeSorting.get(i) <= priceBeforeSorting.get(i + 1));
		}
	}

	@Test
	public void VerifyTheSearchFunctionality() throws IOException, InterruptedException {
		ResuableMethods.Search();
		resultsPage.EnterSearchText("Diamond");
		Thread.sleep(2000);
		resultsPage.getSearchList();
		resultsPage.ClickOnSearchListName("Diamond 69 Heaven Valley Resort");
		List<String> hotelname = resultsPage.getHotelName();
		String name = hotelname.get(0);
		String actualFromCityText = resultsPage.getValueOfFromCity();
		Assert.assertEquals(name, "Diamond 69 Heaven Valley Resort");
		Assert.assertEquals(actualFromCityText.contains("Diamond 69 Heaven Valley"), name);
	}

	@Test
	public void VerifyTheCurrency() throws IOException, InterruptedException {
		ResuableMethods.Search();
		resultsPage.clickingOnCurrencyDropdown();
		resultsPage.ClickOnCurrency("AUD");
		List<WebElement> pricelist = driver.findElements(By.xpath("//p[@id='hlistpg_hotel_shown_price']"));
		List<Long> priceBeforeSorting = new ArrayList<>();
		for (WebElement price : pricelist) {
			String getpriceValue = price.getText();

			String[] priceSplit = getpriceValue.split(" ");
			String numericPart = priceSplit[1].replace(",", "");
			long priceValue = Long.parseLong(numericPart);
			priceBeforeSorting.add(priceValue);
			System.out.println("Parsed price: " + priceValue);
		}
	}
	@Test
	public void VerifyMMTLowestPrice() throws IOException, InterruptedException {
		ResuableMethods.Search();
		resultsPage.ClickOnToggle();
	}
}