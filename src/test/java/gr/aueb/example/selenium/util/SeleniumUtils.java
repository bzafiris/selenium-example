package gr.aueb.example.selenium.util;

import java.time.Duration;
import java.util.function.Function;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtils {

	public static void waitForTimeout(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static WebDriver getWebDriver() {
		// Select appropriate webdriver implementation based on the OS
		if (SystemUtils.IS_OS_LINUX) {
			System.setProperty("webdriver.gecko.driver", "geckodriver/geckodriver-v0.31.0-linux64");
		} else if (SystemUtils.IS_OS_WINDOWS) {
			System.setProperty("webdriver.gecko.driver", "geckodriver/geckodriver-v0.31.0-win64.exe");
		}
		FirefoxOptions options = new FirefoxOptions();
		options.setPageLoadStrategy(PageLoadStrategy.EAGER);
		return new FirefoxDriver(options);
	}

	public static void forAuebHomePage(WebDriver driver) {

		// Τα νεα του Πανεπιστημιου
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(new Function<WebDriver, Boolean>() {

			public Boolean apply(WebDriver driver) {
				// TODO Auto-generated method stub
				try {
					driver.findElement(By.id("content0_main"));
				} catch (Exception e) {
					return false;
				}
				return true;
			}

		});

	}

}
