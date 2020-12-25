/*
This is the runner class of this project
 */

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import stepDefinitions.Hooks;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"stepDefinitions"},
        strict = true,
        plugin = {
                "pretty",
                "junit:target/cucumber-results.xml",
        },
        features = {"src/test/java/features"},
        tags = "@CreateANewBuildxactUser"
)

public class DefaultRunner extends Hooks {

}