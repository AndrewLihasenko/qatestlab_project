package ua.com.qatestlab.prestashopAutomation.openSite;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class SetTest {

    private WebDriver driver;

    public SetTest(WebDriver driver) {

        this.driver = driver;
    }

    @FindBy(id = "_desktop_currency_selector")
    private WebElement currencySelector;

    private By currencyLocator = By.cssSelector("span:nth-child(2)");

    @FindBy(css = "#_desktop_currency_selector > div > span.expand-more._gray-darker.hidden-sm-down")
    private WebElement contextMenu;

    @FindBy(xpath = "//*[@id=\"_desktop_currency_selector\"]/div/ul/li[3]/a")
    private WebElement currencyChoiceLocator;

    @FindBy(css = "#search_widget > form > input.ui-autocomplete-input")
    private WebElement searchFormLocator;

    @FindBy(css = "button[type=\"submit\"]")
    private WebElement searchButtonLocator;

    @FindBy(xpath = "//*[@id=\"search_widget\"]/form/input[2]")
    private WebElement searchContent;

    @FindBy(className = "select-title")
    private WebElement sortOrder;

    @FindBy(css = "[href=\"http://prestashop-automation.qatestlab.com.ua/ru/search?controller=search&order=product.price.desc&s=dress\"]")
    private WebElement highToLowLocator;


    public void setPriceCurrency() {
        currencySelector.findElement(currencyLocator).click();

        Actions action = new Actions(driver);
        action.moveToElement(contextMenu).moveToElement(currencyChoiceLocator).click().build().perform();
    }

    public void searchBy(String request) {
        searchFormLocator.sendKeys(request);
        searchButtonLocator.click();
        Assert.assertEquals(request, searchContent.getAttribute("value"));
        System.out.println("Строка поиска содержит слово: " + searchContent.getAttribute("value"));
    }

    public void sortHighToLow() throws InterruptedException {
        sortOrder.click();
        Actions action = new Actions(driver);
        action.moveToElement(highToLowLocator).click().build().perform();

        Thread.sleep(2000);
        System.out.println("Выбрана сортировка по - " + sortOrder.getText());
    }
}
