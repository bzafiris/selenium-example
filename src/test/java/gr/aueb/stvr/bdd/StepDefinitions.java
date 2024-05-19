package gr.aueb.stvr.bdd;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import org.openqa.selenium.WebDriver;

import gr.aueb.stvr.selenium.fluent.ContactsSearchPage;
import gr.aueb.stvr.util.WebDriverUtils;
import io.cucumber.java.el.Όταν;
import io.cucumber.java.el.Δεδομένου;
import io.cucumber.java.el.Τότε;

public class StepDefinitions {
	
	static WebDriver driver;
	ContactsSearchPage searchPage;
	
	@Δεδομένου("ο χρήστης ανοίγει τη σελίδα αναζήτησης επαφών")
	public void user_navigates_to_contacts_search_page() {
		driver.get("https://www.aueb.gr/el/contactsopa");
		searchPage = new ContactsSearchPage(driver);
		searchPage.waitToLoad();  
	}

	@Όταν("εκτελεί αναζήτηση με όρο {string}")
	public void user_searches_with_term(String searchTerm) {
		searchPage.searchFaculty(searchTerm);
		WebDriverUtils.waitForTimeout(5);
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
	
	@BeforeAll
	public static void setup() {
		driver = WebDriverUtils.getWebDriver();
	}
	
	@AfterAll
	public static void tearDown() {
		driver.quit();
	}
	
}
