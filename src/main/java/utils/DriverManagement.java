package utils;

import base.PageBase;
import base.ReadConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DriverManagement {
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    public static void setup() {
        String browser = ReadConfig.getProperty("browser").toLowerCase();
        switch (browser) {
            case "chrome": {
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
                break;
            }
            case "firefox": {
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
                break;
            }
            default: {
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
                break;
            }
        }
        DriverManagement.driver.get().manage().window().maximize();
        PageBase.openRailway();
    }

    public static void quitBrowser() {
        DriverManagement.driver.get().quit();
    }

    public static WebElement waitElementToBeClickable(By xpath, int second) {
        WebDriverWait wait = new WebDriverWait(DriverManagement.driver.get(), Duration.ofSeconds(second));
        return wait.until(ExpectedConditions.elementToBeClickable(xpath));
    }
}
