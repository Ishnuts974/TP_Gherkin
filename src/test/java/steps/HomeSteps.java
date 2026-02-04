package steps;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LoginPage;
import configuration.ConfigReader;
import configuration.DriverFactory;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import java.util.logging.LogManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class HomeSteps {
    WebDriver driver = DriverFactory.getDriver();
    HomePage homePage = new HomePage(driver);
    public ConfigReader settings = new ConfigReader();
    //private static final Logger logger = LogManager.getLogger(BaseTest.class);

    @Then("il est redirigé vers la page d'acceuil")
    public void rediriger(){
        String urlHomePage = settings.getProperty("urlHome");

    }
    @And("elle est affiché")
    public boolean isDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.urlContains("inventory"));
    }



}
