package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    WebElement topRightCartElement;

    public HomePage(WebDriver driver){
        super(driver);
    }


    public WebElement getTopRightCartElement() {
        return topRightCartElement;
    }
}
