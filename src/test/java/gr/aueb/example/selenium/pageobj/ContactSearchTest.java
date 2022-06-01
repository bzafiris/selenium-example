package gr.aueb.example.selenium.pageobj;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import gr.aueb.example.selenium.util.SeleniumUtils;

/**
 * The basic test rewritten using the Page Object design pattern.
 * Move assertions and interactions related to each page to a respective class
 * @author bzafiris
 *
 */
public class ContactSearchTest {

	private static WebDriver driver;
	private ContactsSearchPage contactSeachPage;

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
		contactSeachPage = new ContactsSearchPage(driver);
		contactSeachPage.waitToLoad();
	}

	@Test
	public void searchNonExistingContactBySurname() {
		
		String surname = "Παπαδημητρίου";
		
		contactSeachPage.searchFaculty(surname);
		SeleniumUtils.waitForTimeout(3);
		contactSeachPage.assertNoResults();
				
	}

	@Test
	public void searchExistingContactBySurname() {
		
		String surname = "ζαφειρης";

		contactSeachPage.searchFaculty(surname);
		SeleniumUtils.waitForTimeout(3);
		contactSeachPage.assertResultsVisible("ΖΑΦΕΙΡΗΣ ΒΑΣΙΛΕΙΟΣ");
		
	}

}
