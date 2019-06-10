package utilities;

import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import enums.ScrollType;

public class Util {

	WebDriver driver;
	
	
	public  static void switchTab(WebDriver driver, int i) {
		
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		switch(i) {
		case 0:
			 driver.switchTo().window(tabs2.get(0));
			 break;
		case 1: 
			 driver.switchTo().window(tabs2.get(1));
			 break;
		case 2: 
			 driver.switchTo().window(tabs2.get(2));
			 break;
		}
	   
	}
	public static void scrollToView(WebDriver driver, ScrollType type) {
		
        JavascriptExecutor js = (JavascriptExecutor) driver;

		switch(type) {
		
		case BOTTOM: 
	        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	        break;
			
		case UP: 	
			js.executeScript("scroll(0, -250);");
			break;

		}
	}
	
	public static void waitTillVisible(WebElement element, long time , WebDriver driver) {
		
		WebDriverWait wait  =  new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(element));		
	}
	
}
