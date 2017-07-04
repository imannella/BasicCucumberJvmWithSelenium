package gov.highways.cucumber;

import java.net.URL;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dummy.Test;

public class LiveEventsSteps {

	private URL url;

	// --- step definition here
	@Given("^A valid list of Traffic information from \"([^\"]*)\"$")
	public void a_valid_list_of_Traffic_information_from(String url) throws Throwable {
		//
		System.out.println("Given ^A valid list of Traffic information from \"([^\"]*)\"$");
		this.url = Test.class.getClassLoader().getResource(url);
		//
	}

	@When("^I want to validate events for category \"([^\"]*)\"$")
	public void i_want_to_validate_events_for_category(String cat) throws Throwable {
		//
		System.out.println("When ^I want to validate events for category " + cat);
		//
	}

	@Then("^I validate I have \"([^\"]*)\" elements$")
	public void i_validate_I_have_elements(String elem) throws Throwable {
		//
		System.out.println("Then ^I validate I have " + elem + " elements$");
		//
	}

	@Then("^I store data into CSV file$")
	public void do_CSV_file() throws Throwable {
		//
		System.out.println("Then ^I proof evidence creating the relative CSV file$");
		//
	}

	// -- end step definition

	@Before
	public void beforeScenario() throws Exception {
		//
		System.out.println("> beforeScenario()");
		url = null;
		//
	}

	@After
	public void afterScenario() throws Exception {
		//
		System.out.println("> afterScenario()");
		//
	}
}
