package gov.highways.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "target/test-classes/cucumber/features/", 
		tags = {"@severe-disruption"}, 
		plugin = {"pretty", 
				  "html:target/cucumber-report/liveEvents",
				  "junit:target/cucumber-report/liveEvents/cucumber.xml",
				  "json:target/cucumber-report/liveEvents/cucumber.json"},
		monochrome = true)
public class LiveEventsTest {
}
