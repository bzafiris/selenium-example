package gr.aueb.stvr.selenium.pageobj;

import gr.aueb.stvr.util.WebDriverUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

/**
 * The basic test rewritten using the Page Object design pattern.
 * Move assertions and interactions related to each page to a respective class
 * @author bzafiris
 *
 */
public class ContactSearchTest {

	private static WebDriver driver;
	private ContactsSearchPage contactSeachPage;

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
		contactSeachPage = new ContactsSearchPage(driver);
		contactSeachPage.waitToLoad();
	}

	@Test
	public void searchNonExistingContactBySurname() {
		
		String surname = "Παπαδημητρίου";
		
		contactSeachPage.searchFaculty(surname);
		WebDriverUtils.waitForTimeout(3);
		contactSeachPage.assertNoResults();
				
	}

	@Test
	public void searchExistingContactBySurname() {
		
		String surname = "ζαφειρης";

		contactSeachPage.searchFaculty(surname);
		WebDriverUtils.waitForTimeout(3);
		contactSeachPage.assertResultsVisible("Ζαφείρης Βασίλειος");
		
	}

}
