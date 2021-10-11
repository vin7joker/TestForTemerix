package page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.Assert;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class MainPage extends BasePage{
    private final String SITE_URL = "https://shoptool.com.ua/";
    private SelenideElement sectionOfDiscounts = $x("//*[text()='Скидки']");
    private SelenideElement CATALOG = $(By.id("sw_dropdown_3626"));
    private String POWER_TOOL= "a[href='https://shoptool.com.ua/elektroinstrument/'] > .menu-lvl-ctn ";

    //Метод открывает сайт
    public MainPage openSite(){
        Selenide.open(SITE_URL);
        return this;
    }

    //Метод перехода в раздел Скидки
    public void goToDiscounts(){
        sectionOfDiscounts.click();
    }

    /*Метод наводит мышь на Каталог товаров чтобы появились скрытие элементы.
     После этого нажимает на раздел Электроинстументы. Потом переходит в заданный строкой раздел.*/
    public void goToSection(String section){
        CATALOG.scrollTo();
        CATALOG.hover().find(POWER_TOOL).shouldHave(text("Электроинструмент")).hover().$(POWER_TOOL).click();
        String selector = "//a[text()='"+section+"']";
        boolean isSelecor = $x(selector).isDisplayed();
        Assert.assertTrue(isSelecor,"Вы неправильно ввели название раздела");
        $x(selector).click();
    }
}
