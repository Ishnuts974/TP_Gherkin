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

    WebDriver driver = DriverFactory.getDriver();
    Basetools tool = new Basetools(driver);
    HomePage homePage = new HomePage(driver);
    BaseSteps baseSteps = new BaseSteps();
    public ConfigReader settings = new ConfigReader();

    //private static final Logger logger = LogManager.getLogger(BaseTest.class);
    public HomeSteps() {
        super();
        tool.setWait(new WebDriverWait(driver, Duration.ofSeconds(10))); // Initialisation du wait
    }
//    @Then("il est redirigé vers la page d'acceuil")
//    public void rediriger(){
//        String urlHomePage = settings.getProperty("urlHome");
//
//    }
    @Then("l'utilisateur est sur la page d'acceuil {string}")
    public boolean isHomeDisplayed(String url) {
        //Assert.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
        System.out.println("L'URL DE LA PAGE TEST EST : "+baseSteps.isUrlDisplayed(url));
        return baseSteps.isUrlDisplayed(url);
}

    @Given("l'utilisateur choisi d'ajouter le {string} à son panier")
    public void getItem(String item){
        WebElement itemChoosed = homePage.getButtonItem(item);
        tool.waitAndClick(itemChoosed);
    }

    @Then("il clique sur le panier en haut a droite")
    public void clickOnCart(){
        WebElement cart = homePage.getTopRightCartElement();
        cart.click();
    }


    public BaseSteps getHomeSteps() {
        return baseSteps;
    }
}
