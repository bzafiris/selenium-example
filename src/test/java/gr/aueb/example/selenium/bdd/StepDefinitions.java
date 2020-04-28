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
	public void user_navigates_to_contacts_search_page() {
		driver.get("https://www.aueb.gr/el/contactsopa");
		searchPage = new ContactsSearchPage(driver);
		searchPage.waitToLoad();  
	}

	@Όταν("εκτελεί αναζήτηση με όρο {string}")
	public void user_searches_with_term(String searchTerm) {
		searchPage.searchFaculty(searchTerm);
		SeleniumUtils.waitForTimeout(3);
	}
	
	@Όταν("επιλέγει αναζήτηση στο τμήμα {string}")
	public void user_selects_department(String departmentName) {
		searchPage.selectDepartment(departmentName);
	}

	@Τότε("εμφανίζεται αποτέλεσμα με ονοματεπώνυμο {string}")
	public void assert_results_for_fullname(String fullname) {
		searchPage.assertResultsVisible(fullname);
	}
	
	@Τότε("δεν εμφανίζεται κανένα αποτέλεσμα")
	public void assert_no_search_results() {
		searchPage.assertNoResults();
	}
	
}
