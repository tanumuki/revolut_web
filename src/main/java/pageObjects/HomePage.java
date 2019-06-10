package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import enums.ScrollType;
import logger.MyLogger;
import resources.DataObjects;
import utilities.Util;

public class HomePage {
	
	/*@author tanu
	 * This class initiates all the page elements /locators using the PageFactory.initElements  method which inturn uses @FindBy methods
	 * 
	 */

	WebDriver driver;
	String userDataFile;
	DataObjects data;

	public HomePage(WebDriver driver, String userDataFile) {
		this.driver = driver;
		this.userDataFile = userDataFile;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='styles__StyledMenu-sc-1vrqgov-0 kAbRap']/div[2]/div[2]/div[4]/div/span")
	private WebElement helpTab;

	@FindBy(xpath = "//div[@class='styles__StyledLinkWrapper-uh23xh-2 eWQRKR']//a[@class='styles__StyledLink-sc-5sfexc-0 dAutwT'][contains(text(),'Community')]")
	private WebElement community;

	@FindBy(id = "search-button")
	private WebElement searchBox;

	@FindBy(id = "search-term")
	private WebElement searchBar;

	@FindBy(xpath = "//div[@class='show-more']/a")
	private WebElement showMore;

	@FindBy(xpath = "//span[@class='topic-title']")
	private List<WebElement> firstResult;

	@FindBy(id = "ember181")
	private List<WebElement> resultList;

	@FindBy(xpath = "//h1[@data-topic-id='72891']/a[2]")
	private WebElement topicText;
	
	@FindBy(id = "toggle-hamburger-menu")
	private WebElement hamburgerMenu;
	
	@FindBy(xpath = "//span[contains(text(),'Keyboard Shortcuts')]")
	private WebElement keyboardShortcuts;
	
	@FindBy(xpath = "//div[@id='keyboard-shortcuts-help']")
	private WebElement keyboardModal; 
	
	@FindBy(xpath = "//div[@class='modal-close']/a")
	private WebElement modalClose;
	
	@FindBy(xpath="//*[@class='rvl-Icon-Icon rvl-Icon--color-gray rvl-Icon--size-xs']")
	private WebElement iconClose;
	
	@FindBy(xpath = "//div[@class='rvl-Banner-banner']")
	private WebElement weAreHiringBanner;
	
	@FindBy(xpath = "//div[@class='styles__StyledSelected-pgefe6-2 ibkJrV']/div[1]/div")
	private WebElement countryFlag;
	
	@FindBy(xpath = "//label[@class='styles__StyledSearch-sc-1ujpcb6-1 jILpJb']//input[contains(@placeholder,'')]")
	private WebElement countryPlaceHolder;
	
	@FindBy(xpath = "//div[@class='styles__Wrapper-sc-8m2s7m-2 kpRiLD']/div/button/a")
	private WebElement unitedStatesFromList;
	
	
	
	
	

	public void hoverOverHelpMenu() throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(helpTab).build().perform();
		community.click();
		Util.switchTab(driver, 1);
	}

	public void clickOnSearchBoxAndSearchText(String text) throws InterruptedException {
		/*
		 * Searching the topic and clicking the element on the search bar
		 */
		searchBox.click();
		searchBar.sendKeys(text);
		for (int i = 0; i < firstResult.size(); i++) {
			if (firstResult.get(i).getText().equals(text)) {
				firstResult.get(i).click();
				break;
			}
		}
	}

	public void verifyTopicText(String text) throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		/*
		 * Opening the topic by clicking on it
		 */

		softAssert.assertEquals(topicText.getText(), text);
		softAssert.assertAll();

		MyLogger.log.info("Topic verified");
		softAssert.assertAll();


	}
	
	public void verifyKeyboardShortcuts() throws InterruptedException {
		/*
		 * Verifying the keyboard shortcuts
		 */
		SoftAssert softAssert = new SoftAssert();
		hamburgerMenu.click();
		softAssert.assertTrue(keyboardShortcuts.getText().equals("Keyboard Shortcuts"));
		keyboardShortcuts.click();
		softAssert.assertTrue(keyboardModal.isDisplayed());
		modalClose.click();
		utilities.Util.switchTab(driver, 0);
		MyLogger.log.info("Keyboad shortcut verified");
		softAssert.assertAll();
	}
	
	public void closeWeAreHiringPopup() throws InterruptedException {
		/*
		 * closing the we are hiring popup
		 */
	
	 	utilities.Util.scrollToView(driver, ScrollType.BOTTOM);
	 	Util.waitTillVisible(weAreHiringBanner, 5, driver);
	 	Actions action = new Actions(driver);
		action.moveToElement(weAreHiringBanner).build().perform();
		if(weAreHiringBanner.isDisplayed()) {
			iconClose.click();
			Util.waitTillVisible(countryFlag, 5, driver);
	}
	}

	
	
	public void changeCountry(String text) throws InterruptedException {
		/*
		 * changing the country
		 */
		SoftAssert softAssert = new SoftAssert();
		countryFlag.click();
		countryPlaceHolder.sendKeys(text);
		Util.waitTillVisible(unitedStatesFromList, 5, driver);
		unitedStatesFromList.click();
		Thread.sleep(6000);
		softAssert.assertTrue(driver.getCurrentUrl().contains("en-US"));
		softAssert.assertAll();		
		MyLogger.log.info("Country change verified");

	}
}
