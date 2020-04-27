package gr.aueb.example.selenium.util;

import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait {

	public static void forTimeout(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void forAuebHomePage(WebDriver driver) {
	
		// Τα νεα του Πανεπιστημιου
		WebDriverWait wait = new WebDriverWait(driver, 10);
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
