package Tests;

import Utils.PropertiesFileReader;
import Utils.WebDriverManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

@CucumberOptions(features = "src/test/resources/features",
glue = "stepDefinitions",
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","html:TestReports/CucumberReport/cucumber.html"}
        ,publish = true)

public class Executor extends AbstractTestNGCucumberTests {
    public static String browser;
    public Executor(String browser){
        this.browser = browser;
    }

}
