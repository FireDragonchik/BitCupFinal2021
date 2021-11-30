package framework.browser;

import framework.utils.ConfigManager;
import framework.utils.Logger;
import framework.utils.TestDataManager;
import framework.utils.ConfigManager.configProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
    static WebDriver driver;
    public static Logger logger = Logger.getInstance();

    public static WebDriver getInstanceDriver() {

        if (driver == null) {
            ConfigManager.getConfigProperties();
            TestDataManager.getTestDataProperties();
            logger.info("Open " + configProperties.browser.getProperty());
            switch (configProperties.browser.getProperty()) {
                case "Chrome":
                    setChrome(configProperties.localization.getProperty());
                    break;
                case "Firefox":
                    setFirefox(configProperties.localization.getProperty());
                    break;
                default:
                    logger.error("Illegal argument" + configProperties.browser.getProperty());
                    throw new IllegalArgumentException("Illegal argument, couldn't find browser with name " + configProperties.browser.getProperty());

            }
        }
        return driver;
    }

    public static void quit() {

        logger.info("Driver quit");
        driver.quit();
        driver = null;
    }

    public static void setChrome(String localization) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("lang=" + localization);
        driver = new ChromeDriver(options);
    }

    public static void setFirefox(String localization) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("intl.accept_languages", localization);
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);
        driver = new FirefoxDriver();
    }
}