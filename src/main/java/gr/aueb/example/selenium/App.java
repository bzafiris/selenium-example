package gr.aueb.example.selenium;

import java.sql.Driver;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		/*
		 * Set the system property to the path of the Gecko driver You can download an
		 * appropriate gecko driver from here:
		 * https://github.com/mozilla/geckodriver/releases
		 * 
		 * Go to Assets and download a driver that matches your OS and cpu architecture.
		 * 
		 * Drivers are available for linux64 and windows 64
		 */
		if (SystemUtils.IS_OS_LINUX) {
			System.setProperty("webdriver.gecko.driver", "./geckodriver/geckodriver-linux64");
		} else if (SystemUtils.IS_OS_WINDOWS) {
			System.setProperty("webdriver.gecko.driver", "./geckodriver/geckodriver-win64.exe");
		}

		System.out.println("Hello World!");
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.aueb.gr");
		
		Wait.forAuebHomePage(driver);

		navigateToAuebContacts(driver);
		searchExistingSurname("Ζαφειρης", driver);
		Wait.forTimeout(3);
		navigateToResult("ΖΑΦΕΙΡΗΣ ΒΑΣΙΛΕΙΟΣ", driver);
		//navigateToOpaRun(driver);

	}
	
	public static void navigateToResult(String surname, WebDriver driver) {
		WebElement result = driver.findElement(By.linkText(surname));
		result.click();
	}
	
	public static void searchExistingSurname(String surname, WebDriver driver) {
		WebElement searchBox = driver.findElement(By.id("edit-title-field-value"));
		searchBox.clear();
		searchBox.sendKeys(surname);
		
		WebElement searchButton = driver.findElement(By.id("edit-submit-contactsopa"));
		searchButton.click();
	}
	
	public static void navigateToAuebContacts(WebDriver driver) {
		WebElement menuIcon = driver.findElement(By.cssSelector(".fa.grm.fa-bars"));
		menuIcon.click();
		
		WebElement contactLink = driver.findElement(By.linkText("Επικοινωνία"));
		contactLink.click();
		
	}
	
	public static void navigateToOpaRun(WebDriver driver) {
		WebElement opaRunLink = driver.findElement(By.partialLinkText("2ο ΟΠΑ Run"));
		opaRunLink.click();
		
		Wait.forTimeout(5);
		
		WebElement opaRunInfoLink = driver.findElement(By.className("field-items"))
				.findElement(By.linkText("εδώ"));
		// opaRunInfoLink = driver.findElement(By.cssSelector(".rtejustify a"));
		//opaRunInfoLink = driver.findElement(By.linkText("εδώ"));
		opaRunInfoLink.click();
	}
}
