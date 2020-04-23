package ua.com.qatestlab.prestashopAutomation.openSite;

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import ua.com.qatestlab.prestashopAutomation.WebDriverSettings;

public class OpenSite extends WebDriverSettings {

    @Test
    public void openSiteTest() throws InterruptedException {

//        1 сценарий
        System.out.println("\n 1 сценарий");
        OpenSitePage sitePage = PageFactory.initElements(driver, OpenSitePage.class);
        sitePage.open();

//        2 сценарий
        System.out.println("\n 2 сценарий");
        CheckTest checkTest = PageFactory.initElements(driver, CheckTest.class);
        checkTest.matchProductCurrency();

//        3 сценарий
        System.out.println("\n 3 сценарий");
        SetTest setTest = PageFactory.initElements(driver, SetTest.class);
        setTest.setPriceCurrency();
        checkTest.checkProductCurrency();

//        4 сценарий
        System.out.println("\n 4 сценарий");
        setTest.searchBy("dress");

//        5 сценарий
        System.out.println("\n 5 сценарий");
        checkTest.getNumFoundProducts();
        checkTest.compareNumFoundProducts();

//        6 сценарий
        System.out.println("\n 6 сценарий");
        checkTest.matchSearchProductCurrency();

//        7 сценарий
        System.out.println("\n 7 сценарий");
        setTest.sortHighToLow();

//        8 сценарий
        System.out.println("\n 8 сценарий");
        checkTest.checkSortByPrice();

//        9 сценарий
        System.out.println("\n 9 сценарий");
        checkTest.checkSizeDiscount();
    }
}
