package gr.aueb.example.selenium.basic;

import static org.junit.Assert.fail;

import java.util.function.Function;

import org.apache.commons.lang3.SystemUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import gr.aueb.example.selenium.util.Wait;

public class ContactSearchTest {

	private static WebDriver driver;

	@BeforeClass
	public static void setupClass() {
		if (SystemUtils.IS_OS_LINUX) {
			System.setProperty("webdriver.gecko.driver", "./geckodriver/geckodriver-v0.26.0-linux64");
		} else if (SystemUtils.IS_OS_WINDOWS) {
			System.setProperty("webdriver.gecko.driver", "./geckodriver/geckodriver-v0.26.0-win64.exe");
		}

		driver = new FirefoxDriver();
	}

	@AfterClass
	public static void tearDownClass() {
		driver.close();
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

		Wait.forTimeout(3);

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