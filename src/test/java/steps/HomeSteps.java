package steps;

import org.openqa.selenium.WebElement;
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
import utils.Basetools;


public class HomeSteps extends BaseSteps{
    Basetools tool = new Basetools();
    WebDriver driver = DriverFactory.getDriver();
    HomePage homePage = new HomePage(driver);
    HomeSteps homeSteps = new HomeSteps();
    public ConfigReader settings = new ConfigReader();
    //private static final Logger logger = LogManager.getLogger(BaseTest.class);

    @Then("il est redirigé vers la page d'acceuil")
    public void rediriger(){
        String urlHomePage = settings.getProperty("urlHome");

    }
    @And("l'utilisateur est sur la page d'acceuil {string}")
    public boolean isHomeDisplayed(String url) {
        return homeSteps.isUrlDisplayed(url);
    }

    @Given("l'utilisateur choisi d'ajouter le {string} à son panier")
    public void getItem(String item){
        WebElement itemChoosed = homePage.getButtonItem(item);
        tool.waitAndClick(itemChoosed);
    }

    //public WebElement checkAddButton


}
