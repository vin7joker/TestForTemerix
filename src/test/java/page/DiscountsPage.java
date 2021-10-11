package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import static com.codeborne.selenide.Selenide.*;

public class DiscountsPage extends BasePage {
    private String oldPrice = "[id^='old_price_update']";
    private String newPrice = ".ty-price-num";
    private String titleName = ".ut2-pb__title";
    private String discountPriceString = ".ut2-pb__price-actual";
    private String oldPriceString = "[id^='old_price']";

    //Метод возвращает отфильтрованную коллекцию карточок на странице
    @Override
    public List<SelenideElement> initCollection(){
        List<SelenideElement> elements = new ArrayList<>( $$(allCardInSection).filterBy(Condition.text("Скидка")));
        return elements;
    }

    //Кликает на рандомную карточку товара на странице
    public DiscountsPage clickOnRandomPageElement(){
        SelenideElement element = mixAndReturnRandomItem(initCollection());
        element.click();
        return this;
    }

    //Метод проверяет наличие старой и новой цены в карточке товара
    public DiscountsPage checkOldAndNewPrice(){
        boolean isOldPrice = $(oldPrice).isDisplayed(), isNewPrice = $(newPrice).isDisplayed();
        Assert.assertTrue(isNewPrice,"Нету акционной цены");
        Assert.assertTrue(isOldPrice,"Нету старой цены");
        return this;
    }

    //Метод повторяет предыдущие действия,я думал сделать цикл, но тогда все влезло бы в один метод
    public void repeatToPageInclusive(int page){
        for(int nextPage=2;nextPage<=page;nextPage++){
            goToNextPage(nextPage);
            clickOnRandomPageElement().checkOldAndNewPrice().back();
        }
    }

    //Делает расчет акционной цены относительно старой
    public DiscountsPage checkDiscountAndOldPrice(){
        String title = $(titleName).getText();
        int discountPrice = convertStringToNumber(discountPriceString);
        int oldPrice = convertStringToNumber(oldPriceString);
        Assert.assertEquals(oldPrice,discountPrice,title);
        return this;
    }

    //Метод находит пять рандомных элементов на странице и делает проверку с каждым из них
    public void findFiveRandomProductAndCheckOldAndDiscountPrice(){
        List<SelenideElement> randomProducts = new ArrayList<>();
        for(int i=0; i<5;i++){
            SelenideElement product = mixAndReturnRandomItem(initCollection());
            randomProducts.add(product);
        }
        for (SelenideElement element:randomProducts){
            element.click();
            checkDiscountAndOldPrice().back();
        }
    }
}
