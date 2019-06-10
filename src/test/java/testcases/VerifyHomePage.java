package testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.testng.annotations.Parameters;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import enums.DriverType;
import managers.DriverManager;
import managers.DriverManagerFactory;
import pageObjects.HomePage;
import resources.DataObjects;


 public class VerifyHomePage {
	/*@author tanu
	 * Creates the object using BeforeTest annotations and parameters from the xml files. This initiates the webbdriver object which is being used by the test clases
	 * Different test methods are then executed according to the priority set in the testNG methods
	 */
	 
	 	WebDriver driver;
		DriverManager driverManager;
		DataObjects data;
		HomePage homePage;

		
		@BeforeTest
		@Parameters({"userDataFile", "browser"})
		public void init(String userDataFile, DriverType browser) throws JsonParseException, JsonMappingException, IOException, InterruptedException {
			
			data = DataObjects.getData(userDataFile);
			driverManager = DriverManagerFactory.getManager(browser);
			driver = driverManager.getDriver();
			homePage = new HomePage(driver, userDataFile);
			driver.get(data.getUrl());
		}
	 
	
	 @Test (priority=0)
	 public void verifyTextOnHelpModule() throws InterruptedException {
		 
		 homePage.hoverOverHelpMenu();	
		 homePage.clickOnSearchBoxAndSearchText(data.getSearchText());
		 homePage.verifyTopicText(data.getSearchText());
	 }
	 

	 @Test(priority=1)
	 public void verifyKeyboardShortcuts() throws InterruptedException {
		 	homePage.verifyKeyboardShortcuts();
		 	
	 }
	 
	 @Test(priority=2)
	 public void verifyCountry() throws InterruptedException {
		 	homePage.closeWeAreHiringPopup();
		 	homePage.changeCountry(data.getCountry());
		 	
	 }
	
	 
	 @AfterTest
		public void tearDown() {
			driverManager.quitDriver();
		}

	 
	
	
}