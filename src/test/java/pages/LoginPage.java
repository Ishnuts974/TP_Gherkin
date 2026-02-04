package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    //LOCATORS
    @FindBy(name = "user-name")
    private WebElement username;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(name = "login-button")
    private WebElement loginBtn;

    @FindBy(xpath ="//h3[@data-test='error']" )
    private WebElement errorMessage;

    // CONSTRUCTOR
    public LoginPage(WebDriver driver) {
        super(driver);
    }


    //GETTERS
    public WebElement getUsername(){
        return this.username;
    }

    public WebElement getPassword(){
        return this.password;
    }

    public WebElement getLoginBtn(){
        return this.loginBtn;
    }

    public WebElement getErrorMessage(){
        return this.errorMessage;
    }


}