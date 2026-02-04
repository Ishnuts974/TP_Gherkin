package steps;

//import pages.HomePage;
import com.sun.source.tree.AssertTree;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import configuration.ConfigReader;
import configuration.DriverFactory;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import java.time.Duration;


public class LoginSteps {
    WebDriver driver = DriverFactory.getDriver();
    LoginPage loginPage = new LoginPage(driver);
    public ConfigReader settings = new ConfigReader();

    @Given("l'utilisateur est sur la page de connexion")
    public void openLogin() {
        String urlLogin = settings.getProperty("urlLogin");
        driver.get(urlLogin);
    }

    @When("il saisit le login {string} et le mot de passe {string}")
    public void login(String user, String pass) {
        loginPage.getUsername().sendKeys(user);
        loginPage.getPassword().sendKeys(pass);
        loginPage.getLoginBtn().click();
    }
    @Then("un message d'erreur s'affiche")
    public void errorMessage(){
        Assert.assertTrue(loginPage.getErrorMessage().isDisplayed());
    }

}