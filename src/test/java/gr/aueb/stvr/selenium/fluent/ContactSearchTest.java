package gr.aueb.stvr.selenium.fluent;

import gr.aueb.stvr.util.WebDriverUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

/**
 * The basic test rewritten using the Page Object design pattern.
 * Move assertions and interactions related to each page to a respective class.
 * 
 * The Page Object class uses a Fluent API, in other words it returns itself in each call,
 * allowing chained calls that are easy to read
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
		driver.close();
	}
	
	@BeforeEach
	public void setup() {
		driver.get("https://www.aueb.gr/el/contactsopa");
		contactSeachPage = new ContactsSearchPage(driver)
				.waitToLoad();
	}

	@Test
	public void searchNonExistingContactBySurname() {
		
		String surname = "Παπαδημητρίουσ";
		
		contactSeachPage
			.searchFaculty(surname)
			.waitTimeout(3)
			.assertNoResults();
				
	}

	@Test
	public void searchExistingContactBySurname() {
		
		String surname = "ζαφειρης";

		contactSeachPage
			.searchFaculty(surname)
			.waitTimeout(3)
			.assertResultsVisible("Ζαφείρης Βασίλειος");
		
	}

}
