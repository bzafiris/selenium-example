package gr.aueb.example.selenium.basic;

import static org.junit.Assert.fail;

import java.util.function.Function;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import gr.aueb.example.selenium.util.SeleniumUtils;

public class ContactSearchTest {

	private static WebDriver driver;

	@BeforeClass
	public static void setupClass() {
		driver = SeleniumUtils.getWebDriver();
	}

	@AfterClass
	public static void tearDownClass() {
		driver.quit();
	}

	@Before
	public void setup() {
		driver.get("https://www.aueb.gr/el/contactsopa");
		waitPageLoad();
	}

	@Test
	public void searchNonExistingContactBySurname() {

		String surname = "ronaldo";

		WebElement searchBox = driver.findElement(By.id("edit-title-field-value"));
		searchBox.clear();
		searchBox.sendKeys(surname);

		WebElement searchButton = driver.findElement(By.id("edit-submit-contactsopa"));
		searchButton.click();

		try {
			WebElement result = driver.findElement(By.id(".views-table.cols-0"));
			fail(); // fail if results table is found
		} catch (NoSuchElementException e) {

		}

	}

	@Test
	public void searchExistingContactBySurname() {
		String surname = "ζαφειρης";

		WebElement searchBox = driver.findElement(By.id("edit-title-field-value"));
		searchBox.clear();
		searchBox.sendKeys(surname);

		WebElement searchButton = driver.findElement(By.id("edit-submit-contactsopa"));
		searchButton.click();

		SeleniumUtils.waitForTimeout(3);

		// assert results
		WebElement resultsTable = driver.findElement(By.cssSelector("table.views-table.cols-0"));
		Assert.assertTrue(resultsTable.isDisplayed());

		WebElement result = driver.findElement(By.linkText("ΖΑΦΕΙΡΗΣ ΒΑΣΙΛΕΙΟΣ"));
		Assert.assertTrue(result.isDisplayed());

	}
	
	@Test
	public void searchContactWithinDepartment() {
		String surname = "ζαφειρης";

		WebElement selectElement = driver.findElement(By.id("edit-tid-1"));
		selectElement.click();
		Select selectObject = new Select(selectElement);
		selectObject.selectByVisibleText("Τμήμα Πληροφορικής");
		
		WebElement searchBox = driver.findElement(By.id("edit-title-field-value"));
		searchBox.clear();
		searchBox.sendKeys(surname);

		WebElement searchButton = driver.findElement(By.id("edit-submit-contactsopa"));
		searchButton.click();

		SeleniumUtils.waitForTimeout(3);

		// assert results
		WebElement resultsTable = driver.findElement(By.cssSelector("table.views-table.cols-0"));
		Assert.assertTrue(resultsTable.isDisplayed());

		WebElement result = driver.findElement(By.linkText("ΖΑΦΕΙΡΗΣ ΒΑΣΙΛΕΙΟΣ"));
		Assert.assertTrue(result.isDisplayed());
	}

	private void waitPageLoad() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(new Function<WebDriver, Boolean>() {

			public Boolean apply(WebDriver driver) {
				// TODO Auto-generated method stub
				try {
					driver.findElement(By.id("edit-submit-contactsopa"));
				} catch (Exception e) {
					return false;
				}
				return true;
			}

		});
	}

}
