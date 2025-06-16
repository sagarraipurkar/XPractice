package pageLocators;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultsPage {

	WebDriver driver;
	WebDriverWait wait;

	public ResultsPage(WebDriver driver) {
		super();
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	private By FromCity = By.id("city");

	private By resultCount = By.xpath("//div[contains(@class, 'listingRowOuter')]");

	private By getHotelName = By.xpath("//span[contains(@class, 'wordBreak ')]");

	private By hotelListing = By.xpath("//div[contains(@class, 'listingRow')]");

	private By Search = By.xpath("//input[contains(@placeholder, 'Search for locality')]");

	private By searchList = By.xpath("//ul[@role='listbox']/li");

	private By clickOnCurrencyDropdown = By.xpath("//p[@class='currencyText']");

	private By listofCurrency = By.xpath("//div[contains(@class, 'styles__DropdownContainer-sc-1ybcz2e-4')]/div/div/p");
	
	private By mmtToggle = By.cssSelector("label[class= 'switchBtn-label']");

	public String getValueOfFromCity() throws InterruptedException {
		Thread.sleep(5000);
		String selectedText = driver.findElement(FromCity).getAttribute("value");
		System.out.println("Selected city" + selectedText);
		return selectedText;
	}

	public boolean getSearchResultCount() {
		boolean isSearchResultVisible = driver.findElements(resultCount).size() > 0;
		return isSearchResultVisible;
	}

	public void clickOnHotel(String hotelName) {
		WebElement hotelContainer = driver.findElement(By.id("hlistpg_hotel_name"));
		List<WebElement> hotelList = hotelContainer.findElements(By.xpath("//span[contains(@class, 'wordBreak')]"));

		List<String> productNames = new ArrayList<String>();

		for (WebElement hotel : hotelList) {
//			String getlist = hotel.getText();
//			System.out.println(getlist);
			productNames.add(hotel.getText());
			System.out.println(hotel.getText());
			if (hotel.getText().trim().equalsIgnoreCase(hotelName)) {
				hotel.click();
				break;
			}
		}
	}

	public void EnterSearchText(String HotelName) {
		driver.findElement(Search).sendKeys(HotelName);
	}

	public List<String> getHotelName() {
		WebElement hotelContainer = driver.findElement(By.id("hlistpg_hotel_name"));
		List<WebElement> hotelList = hotelContainer.findElements(By.xpath("//span[contains(@class, 'wordBreak')]"));

		List<String> productNames = new ArrayList<String>();

		for (WebElement hotel : hotelList) {
			String getlist = hotel.getText();
//			System.out.println(getlist);
			productNames.add(hotel.getText());
			System.out.println(hotel.getText());
		}
		return productNames;
	}

	// Returns the list of hotel WebElements, not just their names
	public List<WebElement> getSearchList() {
		return driver.findElements(searchList);
	}

	public void ClickOnSearchListName(String hotelName) throws InterruptedException {
		List<WebElement> searchList = getSearchList();
		for (WebElement hotel : searchList) {
			System.out.println(hotel.getText());
			if (hotel.getText().contains(hotelName)) {
				Thread.sleep(2000);
				hotel.click();
				break;
			}
		}
	}

	public void clickingOnCurrencyDropdown() {
		driver.findElement(clickOnCurrencyDropdown).click();
	}

	public void ClickOnCurrency(String Currency) throws InterruptedException {
		List<WebElement> currencyList = driver.findElements(listofCurrency);
		for (WebElement currency : currencyList) {
			System.out.println(currency.getText());
			if (currency.getText().contains(Currency)) {
				Thread.sleep(2000);
				currency.click();
				break;
			}
		}
	}
	public void ClickOnToggle() {
		driver.findElement(mmtToggle).click();;
	}
}
