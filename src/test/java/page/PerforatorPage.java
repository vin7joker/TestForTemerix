package page;

import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PerforatorPage extends BasePage{
    public final String perforator = "Перфораторы";
    public String discountPrice = ".ut2-pb__price-actual";
    public String nameOfProduct = ".product-title";

    //Метод возвращает отфильтрованную коллекцию карточок на странице
    @Override
    public List<SelenideElement> initCollection(){
        List<SelenideElement> elements = new ArrayList<>( $$(allCardInSection).filterBy(text("Акция")));
        return elements;
    }

    //Метод выводит на экран название товаров со скидкой и переходит на следующую страницу
    public PerforatorPage displayPromotionalProducts(){
        dispalayProducts(initCollection());
        goToNextPage(2);
        dispalayProducts(initCollection());
        goToNextPage(1);
        return this;
    }

    //Метод выводит на экран название товаров со скидкой
    public void dispalayProducts(List<SelenideElement> list){
        for (SelenideElement element : list){
            String textOfCard = element.$(nameOfProduct).getText();
            System.out.println(textOfCard);
        }
    }

    //Метод проверяет есть ли тикет скидка и переходит на слудующую страницу
    public void checkTicketDiscount(){
        /* Должен был сделать проверку для 1, 3, 5 страницы.
        Но на момент написания теста страниц всего 3 в разделе Перфораторы */
        checkDiscount(initCollection());
        goToNextPage(2);
        checkDiscount(initCollection());
        goToNextPage(3);
        checkDiscount(initCollection());
    }

    //Проверяет карточки с тикетом Акция на наличие тикета Скидка
    public void checkDiscount(List<SelenideElement> list){
        for (SelenideElement element: list){
            element.shouldHave(text("Скидка"));
        }
    }

    //Проваливается в карточку товара и деалет проверку на наличие тикета Скидка
    public PerforatorPage clickOnProductWithDiscount(){
        List<SelenideElement> list = initCollection();
        for (SelenideElement element:list){
            element.shouldHave(text("Скидка"));
            element.click();
            break;
        }
        return this;
    }

    //Проверяет наличие цены со скидкой
    public void checkDiscountPrice(){
        boolean isDiscountPrice = $(discountPrice).isDisplayed();
        Assert.assertTrue(isDiscountPrice,"Нету цены со скидкой");
    }
}
