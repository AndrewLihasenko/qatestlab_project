package ua.com.qatestlab.prestashopAutomation.openSite;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class OpenSitePage {

    private WebDriver driver;

    public OpenSitePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {

        String baseUrl = "http://prestashop-automation.qatestlab.com.ua/ru/";
        driver.get(baseUrl);

        String expectedTitle = "prestashop-automation";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
        System.out.println("Ожидаемый заголовок сайта: " + expectedTitle);
        System.out.println("Имеется заголовок сайта: " + actualTitle);
    }
}
