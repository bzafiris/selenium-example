package gr.aueb.stvr.selenium.pageobj;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.fail;

public class ContactsSearchPage {

	/**
	 * Extract element classes and ids to constants
	 */
	private static final String TABLE_VIEWS_TABLE_COLS_0 = "table.views-table.cols-0";
	private static final String EDIT_TITLE_FIELD_VALUE = "edit-title-field-value";
	private static final String EDIT_SUBMIT_CONTACTSOPA = "edit-submit-contactsopa";
	private WebDriver driver;

	public ContactsSearchPage(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public void waitToLoad() {
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
	
	public void searchFaculty(String surname) {
		WebElement searchBox = driver.findElement(By.id(EDIT_TITLE_FIELD_VALUE));
		searchBox.clear();
		searchBox.sendKeys(surname);

		WebElement searchButton = driver.findElement(By.id(EDIT_SUBMIT_CONTACTSOPA));
		searchButton.click();
		
	}

	public void assertResultsVisible(String surname) {
		WebElement resultsTable = driver.findElement(By.cssSelector(TABLE_VIEWS_TABLE_COLS_0));
		Assertions.assertTrue(resultsTable.isDisplayed());

		WebElement result = driver.findElement(By.linkText(surname));
		Assertions.assertTrue(result.isDisplayed());
	}

	public void assertNoResults() {
		try {
			WebElement resultsTable = driver.findElement(By.cssSelector(TABLE_VIEWS_TABLE_COLS_0));
			// fail if no exception is thrown
			fail();
		} catch (NoSuchElementException e) {
			
		}
	}

}
