package runners;

import  configuration.Hook;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"steps",
                "utils",
                "configuration"
        },
        plugin = { "pretty", "html:target/cucumber-reports.html","json:target/cucumber.json" },
        monochrome = true
        ,tags= "@POEI2-699 ")
public class TestRunner {


}