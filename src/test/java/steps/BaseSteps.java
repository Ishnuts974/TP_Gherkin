package steps;

import configuration.ConfigReader;
import configuration.DriverFactory;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LoginPage;
import pages.PanierPage;
import utils.Basetools;

import java.time.Duration;

import static utils.Basetools.log;

public class BaseSteps {
    WebDriver driver =  DriverFactory.getDriver();
    LoginPage loginPage = new LoginPage(driver);
    HomePage homePage = new HomePage(driver);
    PanierPage panierPage = new PanierPage(driver);
    public ConfigReader settings = new ConfigReader();

    public boolean isUrlDisplayed(String url) {
        System.out.println("L'URL DE LA PAGE EST : "+url);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.urlToBe(settings.getProperty(url)));
        //return wait.until(ExpectedConditions.urlContains(settings.getProperty(url)));

    }

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public HomePage getHomePage() {
        return homePage;
    }

    public PanierPage getPanierPage() {
        return panierPage;
    }
}
