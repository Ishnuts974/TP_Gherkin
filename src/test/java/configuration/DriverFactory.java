package configuration;

import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    public static WebDriver driver;

    public static WebDriver getDriver()  {
        if (driver == null) {
            String browser = System.getProperty("selenium.browser","chrome").toLowerCase();
            switch (browser){
                case "chrome":
                    System.out.println("JE SUIS DANS LE DRIVER CHROME");
                    // Ensuite, passe ces options à ton WebDriver
                    ChromeOptions options = new ChromeOptions();
                    Map<String, Object> prefs = new HashMap<>();
                    prefs.put("profile.default_content_setting_values.notifications", 2);
                    prefs.put("credentials_enable_service", false);
                    prefs.put("profile.password_manager_enabled", false);
                    prefs.put("profile.default_content_settings.popups", 0);
                    options.addArguments("--incognito");
                    options.setExperimentalOption("prefs", prefs);
                    try {
                        driver = new RemoteWebDriver(new URL( "http://admin:admin@172.16.14.76:4449/wd/hub"),options);
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "firefox":
                    FirefoxOptions options1 = new FirefoxOptions();
                    try {
                        driver = new RemoteWebDriver(new URL( "http://admin:admin@172.16.14.76:4449/wd/hub"),options1);
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "edge":
                    EdgeOptions options2 = new EdgeOptions();
                    try {
                        driver = new RemoteWebDriver(new URL( "http://admin:admin@172.16.14.76:4449/wd/hub"),options2);
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }

                    EdgeDriver
                    driver = new EdgeDriver();
                default:
                    throw new RuntimeException("Navigateur non supporté : " + browser);
            }
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driver;
    }
    @After
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
