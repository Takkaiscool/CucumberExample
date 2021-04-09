package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    public static void setDriver(String browserName){
        switch (browserName.toLowerCase()){
            case "firefox": driver.set(new FirefoxDriver());
                break;
            case "chrome":

                driver.set(new ChromeDriver());
                break;
        }

    }

    public static WebDriver getDriver(){
        return driver.get();
    }
}
