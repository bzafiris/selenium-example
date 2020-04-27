package gr.aueb.example.selenium.fluent;

import org.apache.commons.lang3.SystemUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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

	@BeforeClass
	public static void setupClass() {
		if (SystemUtils.IS_OS_LINUX) {
			System.setProperty("webdriver.gecko.driver", "./geckodriver/geckodriver-linux64");
		} else if (SystemUtils.IS_OS_WINDOWS) {
			System.setProperty("webdriver.gecko.driver", "./geckodriver/geckodriver-win64.exe");
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
		contactSeachPage = new ContactsSearchPage(driver)
				.waitToLoad();
	}

	@Test
	public void searchNonExistingContactBySurname() {
		
		String surname = "ronaldo";
		
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
			.assertResultsVisible("ΖΑΦΕΙΡΗΣ ΒΑΣΙΛΕΙΟΣ");
		
	}

}
