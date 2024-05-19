package gr.aueb.stvr.selenium.basic;

import gr.aueb.stvr.util.WebDriverUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.fail;

public class ContactSearchTest {

	private static WebDriver driver;

	private ContactsSearchPage searchPage;

	@BeforeAll
	public static void setupClass() {
		driver = WebDriverUtils.getWebDriver();
	}

	@AfterAll
	public static void tearDownClass() {
		driver.quit();
	}

	@BeforeEach
	public void setup() {
		driver.get("https://www.aueb.gr/el/contactsopa");
		waitPageLoad();
		searchPage = new ContactsSearchPage(driver);
	}

	/*
	private void slides_snippet() {
		
		// Εισαγωγή στο path του geckodriver για αλληλεπίδραση με τον firefox
		if (SystemUtils.IS_OS_LINUX) { 
			System.setProperty("webdriver.gecko.driver", "geckodriver/geckodriver-v0.31.0-linux64");
		} else if (SystemUtils.IS_OS_WINDOWS) {
			System.setProperty("webdriver.gecko.driver", "geckodriver/geckodriver-v0.31.0-win64.exe");
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
	
	*/
	@Test
	public void searchNonExistingContactBySurname() {

		String surname = "Παπαδημητρίου";

		searchPage.searchFaculty(surname);
		WebDriverUtils.waitForTimeout(3);
		searchPage.assertNoVisibleFaculty();

	}

	@Test
	public void searchExistingContactBySurname() {
		String surname = "ζαφειρης";

		searchFaculty(surname);
		

		WebElement searchButton = driver.findElement(By.id("edit-submit-contactsopa"));
		searchButton.click();
		
		WebDriverUtils.waitForTimeout(5);

		// assert results
		WebElement resultsTable = driver.findElement(By.cssSelector("table.views-table.cols-0"));
		Assertions.assertTrue(resultsTable.isDisplayed());

		WebElement result = driver.findElement(By.linkText("Ζαφείρης Βασίλειος"));
		Assertions.assertTrue(result.isDisplayed());
		
		WebDriverUtils.waitForTimeout(5);

	}

	private void searchFaculty(String surname) {
		WebElement searchBox = driver.findElement(By.id("edit-title-field-value"));
		searchBox.clear();
		searchBox.sendKeys(surname);

		WebElement searchButton = driver.findElement(By.id("edit-submit-contactsopa"));
		searchButton.click();
	}

	private void assertResultsVisible(String surname) {
		WebElement resultsTable = driver.findElement(By.cssSelector("table.views-table.cols-0"));
		Assertions.assertTrue(resultsTable.isDisplayed());

		WebElement result = driver.findElement(By.linkText(surname));
		Assertions.assertTrue(result.isDisplayed());

	}

	public void assertVisibleFaculty(String visibleSurname) {
		WebElement resultsTable = driver.findElement(By.cssSelector("table.views-table.cols-0"));
		Assertions.assertTrue(resultsTable.isDisplayed());
		WebElement result = driver.findElement(By.linkText(visibleSurname));
		Assertions.assertTrue(result.isDisplayed());
	}
	public void assertNoVisibleFaculty() {
		try {
			WebElement result = driver.findElement(By.id(".views-table.cols-0"));
			fail(); // fail if results table is found
		} catch (NoSuchElementException e) {

		}
	}

	@Test
	public void searchContactWithinDepartment() {
		String surname = "ζαφειρης";

		WebElement selectElement = driver.findElement(By.id("edit-tid-1"));
		selectElement.click();
		Select selectObject = new Select(selectElement);
		selectObject.selectByVisibleText("--Τμήμα Πληροφορικής");
		
		WebDriverUtils.waitForTimeout(3);
		
		searchFaculty(surname);
		
		WebElement searchButton = driver.findElement(By.id("edit-submit-contactsopa"));
		searchButton.click();
		
		WebDriverUtils.waitForTimeout(5);

		
		String visibleSurname = "Ζαφείρης Βασίλειος";
		// assert results
		assertVisibleFaculty(visibleSurname);
		
		WebDriverUtils.waitForTimeout(5);
	}


	

	private void waitPageLoad() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
