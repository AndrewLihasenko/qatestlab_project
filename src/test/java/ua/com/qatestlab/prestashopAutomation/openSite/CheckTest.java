package ua.com.qatestlab.prestashopAutomation.openSite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckTest {

    private WebDriver driver;

    public CheckTest(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(id = "_desktop_currency_selector")
    private WebElement currencySelector;

    private By currencyLocator = By.cssSelector("span:nth-child(2)");

    @FindBy(xpath = "//*[@id=\"content\"]/section")
    private WebElement contentSection;

    private By priceLocator = By.className("price");

    @FindBy(id = "js-product-list-top")
    private WebElement sortByList;

    private By searchResultLocator = By.cssSelector("p:first-child");

    @FindBy(id = "js-product-list")
    private WebElement productList;

    private By itemLocator = By.tagName("article");

    private By sortPriceSelector = By.className("product-price-and-shipping");

    private By sortPriceLocator = By.cssSelector("span:nth-child(1)");

    private By discountLocator = By.cssSelector("span:nth-child(2)");


    public String checkProductCurrency() {
        String[] currency = currencySelector.findElement(currencyLocator).getText().split(" ");
        System.out.println("Текущая валюта: " + currency[0] + " " + currency[1]);
        return currency[1];
    }

    public void matchProductCurrency() {
        List<WebElement> prices = contentSection.findElements(priceLocator);
        for (WebElement price : prices) {
            if (price.getText().contains(checkProductCurrency()))
                System.out.println("Цена: " + price.getText() + " и установленная валюта - совпадают");
            else {
                System.out.println("Цена: " + price.getText() + " и установленная валюта - не совпадают");
            }
        }
    }

    public List<WebElement> collectProducts() {
        return productList.findElements(itemLocator);
    }

    public String [] getNumFoundProducts() {
        return sortByList.findElement(searchResultLocator).getText().split(" ");
    }

    public void compareNumFoundProducts() {
        String [] total = getNumFoundProducts();
        StringBuilder sb = new StringBuilder(total[1]);
        sb.deleteCharAt(1);
        if (Integer.parseInt(sb.toString()) == collectProducts().size()) {
            System.out.println("Товаров: " + Integer.parseInt(sb.toString()) +
                    ", количество действительно найденных товаров: " + collectProducts().size() + " - совпадение");
        } else {
            System.out.println("Товаров: " + Integer.parseInt(sb.toString()) +
                    ", количество действительно найденных товаров: " + collectProducts().size() + " - несовпадение");
        }
    }

    public void matchSearchProductCurrency() {
        for (WebElement webElement : collectProducts()) {
            String[] price = webElement.findElement(priceLocator).getText().split(" ");
            if (price[1].equals(checkProductCurrency())) {
                System.out.println("Цена: " + price[0] + " " + price[1] + " и установленная валюта - совпадают");
            } else {
                System.out.println("Цена: " + price[0] + " " + price[1] + " и установленная валюта - не совпадают");
            }
        }
    }

    public void checkSortByPrice() {
        Double oldPrice = 0.0;
        for (WebElement webElement : collectProducts()) {
            WebElement priceElement = webElement.findElement(sortPriceSelector);
            String[] allText = priceElement.findElement(sortPriceLocator).getText().split(" ");
            String stringPrice = allText[0].replace(',', '.');
            Double newPrice = Double.parseDouble(stringPrice);

            if (oldPrice == 0.0) {
                System.out.println(newPrice);
            } else if (newPrice <= oldPrice) {
                System.out.println(newPrice);
            } else {
                System.out.println("ошибка");
            }
            oldPrice = newPrice;
        }
    }

    public String findNumber(String element) {
        Pattern p = Pattern.compile("\\d?,?\\d\\d?+");
        Matcher m = p.matcher(element);
        if (m.find()) {
            return m.group().replace(',', '.');
        }
        return element;
    }

    public void checkSizeDiscount() {
        for (WebElement webElement : collectProducts()) {
            WebElement priceElement = webElement.findElement(sortPriceSelector);
            if (priceElement.getText().contains("%")) {

                double regularPrice = Double.parseDouble(
                        findNumber(priceElement.findElement(By.className("regular-price")).getText()));
                System.out.println("Цена до скидки: " + regularPrice);
                Integer discount = Integer.parseInt(
                        findNumber(priceElement.findElement(By.className("discount-percentage")).getText()));
                double price = Double.parseDouble(
                        findNumber(priceElement.findElement(By.className("price")).getText()));
                System.out.println("Цена после скидки: " + price);
                Integer tempDiscount = 100 - Math.toIntExact(Math.round((price / regularPrice) * 100));
                if (discount.equals(tempDiscount)) {
                    System.out.println("Расчетная скидка: " + tempDiscount + "%; " +
                            "Указанная скидка: " + discount + "%; - совпадают");
                } else {
                    System.out.println("Расчетная скидка: " + tempDiscount + "%; " +
                            "Указанная скидка: " + discount + "%; - не совпадают");
                }
            }
        }
    }
}
