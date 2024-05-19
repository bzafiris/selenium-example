package gr.aueb.stvr.util;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverUtils {

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
			System.setProperty("webdriver.gecko.driver", "geckodriver/geckodriver");
		} else if (SystemUtils.IS_OS_WINDOWS) {
			System.setProperty("webdriver.gecko.driver", "geckodriver/geckodriver.exe");
		}
		FirefoxOptions options = new FirefoxOptions();
		options.setPageLoadStrategy(PageLoadStrategy.EAGER);
		return new FirefoxDriver(options);
	}

}
