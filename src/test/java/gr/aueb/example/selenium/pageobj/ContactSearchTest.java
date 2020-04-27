package gr.aueb.example.selenium.pageobj;

import org.apache.commons.lang3.SystemUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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
		contactSeachPage = new ContactsSearchPage(driver);
		contactSeachPage.waitToLoad();
	}

	@Test
	public void searchNonExistingContactBySurname() {
		
		String surname = "ronaldo";
		
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
