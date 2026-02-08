package steps;

import configuration.DriverFactory;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.PanierPage;
import utils.Basetools;

public class PanierSteps extends BaseSteps{
    WebDriver driver = DriverFactory.getDriver();
    PanierPage page = new PanierPage(driver);
    BaseSteps baseSteps = new BaseSteps();
    Basetools tool = new Basetools(driver);

    @And("il est redirigé sur la page {string}")
    public boolean isPanierDisplayed(String url) {
        return baseSteps.isUrlDisplayed(url);
    }

    @And("il vérifie que le {string} est présent dans le panier")
    public void cartContainsItem(String item){
        WebElement itemInCart = page.getCartItem(item);
        tool.waitDisplayElement(itemInCart);
    }
}
