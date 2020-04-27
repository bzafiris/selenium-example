package gr.aueb.example.selenium.bdd;

import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import gr.aueb.example.selenium.fluent.ContactsSearchPage;
import gr.aueb.example.selenium.util.SeleniumUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.el.Όταν;
import io.cucumber.java.el.Δεδομένου;
import io.cucumber.java.el.Τότε;

public class StepDefinitions {
	
	WebDriver driver;
	ContactsSearchPage searchPage;
	
	@Before
	public void setup() {
		driver = SeleniumUtils.getWebDriver();
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
	
	
	@Δεδομένου("ο χρήστης ανοίγει τη σελίδα αναζήτησης επαφών")
	public void ο_χρήστης_ανοίγει_τη_σελίδα_αναζήτησης_επαφών() {
		driver.get("https://www.aueb.gr/el/contactsopa");
		searchPage = new ContactsSearchPage(driver);
		searchPage.waitToLoad();  
	}

	@Όταν("ο χρήστης εκτελεί αναζήτηση με όρο Ζαφείρης")
	public void ο_χρήστης_εισάγει_όρο_αναζήτησης_Ζαφείρης() {
		String surname = "ζαφειρης";
		searchPage.searchFaculty(surname);
		SeleniumUtils.waitForTimeout(3);
		searchPage.assertResultsVisible("ΖΑΦΕΙΡΗΣ ΒΑΣΙΛΕΙΟΣ");
	}

	@Τότε("εμφανίζεται αποτέλεσμα με ονοματεπώνυμο ΖΑΦΕΙΡΗΣ ΒΑΣΙΛΕΙΟΣ")
	public void εμφανίζεται_αποτέλεσμα_με_ονοματεπώνυμο_ΖΑΦΕΙΡΗΣ_ΒΑΣΙΛΕΙΟΣ() {
		searchPage.assertResultsVisible("ΖΑΦΕΙΡΗΣ ΒΑΣΙΛΕΙΟΣ");
	}
	
}
