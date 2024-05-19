package gr.aueb.example.selenium.basic;

import gr.aueb.example.util.SeleniumUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.fail;

public class ContactsSearchPage {
	
	private WebDriver driver;

	public ContactsSearchPage(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public void searchFaculty(String surname) {
		WebElement searchBox = driver.findElement(By.id("edit-title-field-value"));
		searchBox.clear();
		searchBox.sendKeys(surname);
		
		WebElement searchButton = driver.findElement(By.id("edit-submit-contactsopa"));
		searchButton.click();
		//new Actions(driver).moveToElement(searchButton).click().perform();
		SeleniumUtils.waitForTimeout(3);
	}

	public void assertVisibleFaculty(String visibleSurname) {
		WebElement resultsTable = driver.findElement(By.cssSelector("table.views-table.cols-0"));
		Assertions.assertTrue(resultsTable.isDisplayed());
		WebElement result = driver.findElement(By.linkText(visibleSurname));
		Assertions.assertTrue(result.isDisplayed());
	}
	
	public void assertNoVisibleFaculty() {
		try {
			WebElement result = driver.findElement(By.id(".views-table.cols-0"));
			fail(); // fail if results table is found
		} catch (NoSuchElementException e) {

		}
	}
}
