package configuration;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import steps.ImportExportResultToXraySteps;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import  steps.ImportExportResultToXraySteps;

import static steps.ImportExportResultToXraySteps.downloadFeatureFiles;
import static utils.ZipUtils.unzipToFeatures;
import  static steps.ImportExportResultToXraySteps.getToken;


public class Hook {

    public static int compteur =0;

    @Before
    public void setUp() {
        DriverFactory.getDriver();
    }

//    @BeforeClass
//    public static void exportJsonFromXray() throws IOException, NoSuchAlgorithmException, KeyStoreException, InterruptedException, KeyManagementException {
//        downloadFeatureFiles("POEI2-890");
//        unzipToFeatures();
//        getToken();
//    }

    @After
    public void tearDown(Scenario scenario) throws IOException{
        if (scenario.isFailed()){
            TakesScreenshot ts = (TakesScreenshot) DriverFactory.getDriver();
            File source = ts.getScreenshotAs(OutputType.FILE);
            File destination = new File("target/screenshots/capture-"+scenario.getName()+".png");
            FileUtils.copyFile(source, destination);
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failure Screenshot");


        }
        DriverFactory.quitDriver();

    }



}