package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PanierPage extends BasePage {

    public PanierPage(WebDriver driver){
        super(driver);
    }

    public WebElement getCartItem(String item){
        return driver.findElement(By.xpath("//button[@id='remove-sauce-labs-"+item+"']"));

    }
}
