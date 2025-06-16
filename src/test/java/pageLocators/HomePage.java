package pageLocators;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	protected WebDriver driver;
	WebDriverWait wait;

	public HomePage(WebDriver driver) {
		super();
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	private By getAttribute = By.xpath("//nav[contains(@class, 'lessItemsNav')]//li//a/span/span");

	private By closeModelPopup = By.xpath("span[data-cy= 'closeModal']");

	private By Headers = By.xpath("//nav[contains(@class, 'lessItemsNav')]//li//a/span[2]");

	private By FromCity = By.id("city");

	private By fromCityInputText = By.xpath("//input[contains(@placeholder, 'Enter city')]");

	private By fromCityList = By.cssSelector("li[role='option']");

	private By searchButton = By.id("hsw_search_button");
	
	private By clickOnGuest = By.id("guest");
	
	private By clickRoomDropDown = By.cssSelector("div[data-testid='gstSlct']");
	
	private By roomCountlist = By.xpath("//ul[@class='gstSlct__list']/li");
	
	private By clickApply = By.xpath("//button[@data-cy='RoomsGuestsNew_327']");

	public void ClosemodelPopup() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(closeModelPopup));
		driver.findElement(closeModelPopup).click();
	}

	public boolean HeaderList(String MenuOption) throws InterruptedException {
		List<WebElement> headerlist = driver.findElements(Headers);
		for (WebElement list : headerlist) {
			String getList = list.getText();
			System.out.println(getList);
			if (getList.equalsIgnoreCase(MenuOption)) {
				list.click();
				WebElement updatedElement = driver
						.findElement(By.xpath("//a[contains(@href, '/" + MenuOption.toLowerCase() + "/')]"));
//				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.attributeContains(updatedElement, "class", "active"));
				String updatedClass = updatedElement.getAttribute("class");
				System.out.println("Class after click: " + updatedClass);

				return updatedClass != null && updatedClass.contains("active");
			}
		}
		return false;
	}

	public void clickOnFromCity() {
		driver.findElement(FromCity).click();
	}

	public void EnterFromCity(String FromCity) throws InterruptedException {
		driver.findElement(fromCityInputText).sendKeys(FromCity);
		Thread.sleep(2000);
	}

	public void selectFromCityFromTheList(String FromCity) throws InterruptedException {
		List<WebElement> fromCity = driver.findElements(fromCityList);
		for (WebElement list : fromCity) {
			String getFromCityText = list.getText();
			System.out.println(getFromCityText);
			if (getFromCityText.contains(FromCity)) {
				list.click();
				break;
			}
		}

	}

	public String afterSelectingTheFromCityText() throws InterruptedException {
		Thread.sleep(5000);
		String selectedText = driver.findElement(FromCity).getAttribute("value");
		System.out.println("Selected city" + selectedText);
		return selectedText;
	}

	public void clickOnSearch() {
		driver.findElement(searchButton).click();

	}

	public void selectCheckInDates(String checkinDate, String checkinMonth, String checkinYear, String checkOutDate) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    while (true) {
	        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("(//div[@role='heading'])[1]/div")
	        ));
	        String text = heading.getText();

	        String currentMonth = text.replaceAll("[^A-Za-z]", "");
	        String currentYear = text.replaceAll("[^0-9]", "");

	        System.out.println("Text: " + text);
	        System.out.println("Month: " + currentMonth);
	        System.out.println("Year: " + currentYear);

	        if (currentMonth.equalsIgnoreCase(checkinMonth) && currentYear.equals(checkinYear)) {
	            break;
	        }
	        driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
//	        wait.until(ExpectedConditions.stalenessOf(heading));
	    }

	    // Click on check-in and check-out dates
	    List<WebElement> allDates = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
	        By.xpath("//div[@role='gridcell']")
	    ));

	    for (WebElement dateElement : allDates) {
	        String dateText = dateElement.getText().trim();
	        if (dateText.equals(checkinDate)) {
	            wait.until(ExpectedConditions.elementToBeClickable(dateElement)).click();
	        }else if(dateText.equals(checkOutDate)) {
	            wait.until(ExpectedConditions.elementToBeClickable(dateElement)).click();
	        }
	    }
	}


	public void selectCheckOutDates(String checkoutdate, String checkoutmonth, String checkoutyear) {
		String text = driver.findElement(By.xpath("(//div[@role='heading'])[1]/div")).getText();

		String month = text.replaceAll("[^A-Za-z]", "");
		String year = text.replaceAll("[^0-9]", "");

		System.out.println("Text: " + text);
		System.out.println("Month: " + month);
		System.out.println("Year: " + year);
		if (!month.equals(checkoutmonth)) {
			driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
		} else if (!year.equals(checkoutyear)) {
			driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
		}

		List<WebElement> checkoutdatess = driver.findElements(By.xpath("//div[@role=\"gridcell\"]"));

		for (WebElement checkoutdates : checkoutdatess) {
			String getDates = checkoutdates.getText();
			if (getDates.equals(checkoutdate)) {
				checkoutdates.click();
			}
		}
	}
	
	public void ClickOnGuestFiled() {
		driver.findElement(clickOnGuest).click();
	}
	
	public void clickOnRoomDropDown() {
		driver.findElement(clickRoomDropDown).click();
	}
	
	public void selectRoomCount(int roomCount) {
		
		List<WebElement> count = driver.findElements(roomCountlist);
		
		for(WebElement counts : count) {
			String getCountList = counts.getText();
			int roomcount = Integer.parseInt(getCountList);
			System.out.println(getCountList);
			if(roomcount==roomCount) {
				counts.click();
				break;
			}
		}
	}
	
	public void clickOnApplybutton() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(clickApply));
		driver.findElement(clickApply).click();
	}
}