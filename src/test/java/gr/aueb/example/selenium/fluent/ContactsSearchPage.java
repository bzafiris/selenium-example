package gr.aueb.example.selenium.fluent;

import static org.junit.Assert.fail;

import java.util.function.Function;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


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

	public ContactsSearchPage waitToLoad() {
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
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
		return this;
	}
	
	public ContactsSearchPage waitTimeout(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public ContactsSearchPage searchFaculty(String surname) {
		WebElement searchBox = driver.findElement(By.id(EDIT_TITLE_FIELD_VALUE));
		searchBox.clear();
		searchBox.sendKeys(surname);

		WebElement searchButton = driver.findElement(By.id(EDIT_SUBMIT_CONTACTSOPA));
		searchButton.click();
		return this;
	}

	public ContactsSearchPage assertResultsVisible(String surname) {
		WebElement resultsTable = driver.findElement(By.cssSelector(TABLE_VIEWS_TABLE_COLS_0));
		Assert.assertTrue(resultsTable.isDisplayed());

		WebElement result = driver.findElement(By.linkText(surname));
		Assert.assertTrue(result.isDisplayed());
		
		return this;
	}

	public ContactsSearchPage assertNoResults() {
		try {
			WebElement resultsTable = driver.findElement(By.cssSelector(TABLE_VIEWS_TABLE_COLS_0));
			// fail if no exception is thrown
			fail();
		} catch (NoSuchElementException e) {
			
		}
		return this;
	}

	public ContactsSearchPage selectDepartment(String departmentName) {
		
		WebElement selectElement = driver.findElement(By.id("edit-tid-1"));
		Select selectObject = new Select(selectElement);
		selectObject.selectByVisibleText(departmentName);
		
		return this;
		
	}

}
