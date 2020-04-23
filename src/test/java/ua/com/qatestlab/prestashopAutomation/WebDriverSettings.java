package ua.com.qatestlab.prestashopAutomation;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

public class WebDriverSettings {

    public WebDriver driver;

    public String projectPath = System.getProperty("user.dir");
    public String path = projectPath + "//log.txt";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/macbookpro/Projects/IntelliJIDEAProjects/chromedriver");
        driver = new ChromeDriver();

        ((RemoteWebDriver)driver).setLogLevel(Level.SEVERE);
        try {
            FileHandler fileHandler = new FileHandler(path);
            fileHandler.setFormatter(new SimpleFormatter());
            java.util.logging.Logger.getLogger(RemoteWebDriver.class.getName()).addHandler(fileHandler);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void close() {
        driver.quit();
    }
}
