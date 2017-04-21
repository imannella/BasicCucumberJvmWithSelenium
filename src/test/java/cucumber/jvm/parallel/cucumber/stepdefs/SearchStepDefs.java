package cucumber.jvm.parallel.cucumber.stepdefs;

import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.jvm.parallel.pageobjects.SearchPageObject;

public class SearchStepDefs {
	
	private SearchPageObject searchPage;
	private WebDriver driver;

	public SearchStepDefs() {
		searchPage = new SearchPageObject(driver);
	}
	
	@Before
	public void beforeMethod() throws Exception {

		String platform_name = "win7";
		String browser_name = "firefox";
		String browser_version = "47"; // for Chrome leave empty

		DesiredCapabilities capabilities = new DesiredCapabilities();
		if (platform_name.equalsIgnoreCase("win7")) {
			capabilities.setPlatform(Platform.VISTA);
		}
		if (platform_name.equalsIgnoreCase("win8")) {
			capabilities.setPlatform(Platform.WIN8);
		}
		if (platform_name.equalsIgnoreCase("win8_1")) {
			capabilities.setPlatform(Platform.WIN8_1);
		}
		if (platform_name.equalsIgnoreCase("win10")) {
			capabilities.setPlatform(Platform.WIN10);
		}
		if (platform_name.equalsIgnoreCase("linux")) {
			capabilities.setPlatform(Platform.LINUX);
		}
		capabilities.setBrowserName(browser_name);
		capabilities.setVersion(browser_version);
		
		//Chrome specifics
		if (browser_name.equalsIgnoreCase("chrome")){
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobars"); // starting from Chrome 57 the info bar displays with "Chrome is being controlled by automated test software."
			
			// On Linux start-maximized does not expand browser window to max screen size. Always set a window size.
			if (platform_name.equalsIgnoreCase("linux")) {
				options.addArguments(Arrays.asList("--window-position=0,0"));
				options.addArguments(Arrays.asList("--window-size=1920,1080"));	
				} else {
				options.addArguments(Arrays.asList("--start-maximized"));
				}
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			} 
		
		//Firefox specifics
		if (browser_name.equalsIgnoreCase("firefox")){
				// If you are using selenium 3 and test Firefox versions below version 48
				if(Integer.parseInt(browser_version)<48){
				capabilities.setCapability("marionette", false);
				}
		}
	
		driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"),capabilities);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize(); // Always maximize firefox on windows
		
        // On LINUX/FIREFOX the "driver.manage().window().maximize()" option does not expand browser window to max screen size. Always set a window size.
    	if (platform_name.equalsIgnoreCase("linux") && browser_name.equalsIgnoreCase("firefox")) {
    		driver.manage().window().setSize(new Dimension(1920, 1080));	
    	}
        
	}

	@Given("^I am on the website '(.+)'$")
	public void I_am_on_the_website(String url) throws Throwable {
		driver.get(url);
	}

	@When("^I submit the search term '(.+)'$")
	public void I_submit_the_search_term(String searchTerm) throws Throwable {
		searchPage.enterSearchTerm(searchTerm);
		// searchPage.submitSearch();
	}

	@When("^accept the first search result$")
	public void accept_the_first_search_result() throws Throwable {
		searchPage.acceptSearchResult(0);

		// wait up to 5 seconds for redirect to complete
		for (int i = 0; i < 5; i++) {
			if (!driver.getCurrentUrl().contains("google")) {
				break;
			}
			Thread.sleep(1000);
		}
	}

	@Then("^I should be on the page '(.+)'$")
	public void I_should_be_on_the_page(String url) throws Throwable {
		assertEquals(url, driver.getCurrentUrl());
	}
}
