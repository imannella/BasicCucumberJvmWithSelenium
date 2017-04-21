package cucumber.jvm.parallel.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "target/test-classes/cucumber/features/", 
		tags = {"@autocorrect"}, 
		plugin = {"pretty", 
				  "html:target/cucumber-report/autocorrect",
				  "junit:target/cucumber-report/autocorrect/cucumber.xml",
				  "json:target/cucumber-report/autocorrect/cucumber.json"})
public class AutoCorrectATTest {
}
