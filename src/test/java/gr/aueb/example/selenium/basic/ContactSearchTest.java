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

	private void slides_snippet() {
		
		// Εισαγωγή στο path του geckodriver για αλληλεπίδραση με τον firefox
		if (SystemUtils.IS_OS_LINUX) { 
			System.setProperty("webdriver.gecko.driver", "geckodriver/geckodriver-v0.26.0-linux64");
		} else if (SystemUtils.IS_OS_WINDOWS) {
			System.setProperty("webdriver.gecko.driver", "geckodriver/geckodriver-v0.26.0-win64.exe");
		}
		// δημιουργία WebDriver
		WebDriver driver = new FirefoxDriver();
		// αναζήτηση πεδίου κειμένου για εισαγωγή όρων αναζήτησης
		WebElement searchBox = driver.findElement(By.id("edit-title-field-value"));
		searchBox.sendKeys("μαλεύρης");

		// αναζήτηση κουμπιού υποβολής αναζήτησης
		WebElement searchButton = driver.findElement(By.id("edit-submit-contactsopa"));
		searchButton.click(); // κλικ στο κουμπί

		// assertions
		// εντοπισμός πίνακα αποτελεσμάτων
		WebElement resultsTable = driver.findElement(By.cssSelector("table.views-table.cols-0"));
		
		// αναζήτηση στη σελίδα link με κείμενο "ΜΑΛΕΥΡΗΣ ΝΙΚΟΛΑΟΣ"
		WebElement result = driver.findElement(By.linkText("ΜΑΛΕΥΡΗΣ ΝΙΚΟΛΑΟΣ"));
		
		// Σε περίπτωση μη εντοπισμού των στοιχείων προκύπτει NoSuchElementException
		// και το test αποτυγχάνει
		
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
