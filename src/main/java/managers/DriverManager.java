package managers;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {

	
	

	public static WebDriver driver;
	
	public abstract void startService();
	public abstract void stopService();
	public abstract WebDriver createDriver() throws InterruptedException;
	
	
	
	public WebDriver getDriver() throws InterruptedException {
		if(null==driver) {
			startService();
			driver = createDriver();
		}
		return driver;
	}
	

	public void quitDriver() {
		if(null!=driver) {
			driver.quit();
			driver=null;
		}
	}
	
	
}
