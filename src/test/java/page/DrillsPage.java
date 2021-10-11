package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.HoverOptions;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selenide.*;

public class DrillsPage extends BasePage {
    //В метод поиска передаю строковое значание вместо селектрора. Таким образом всегда селектор актуальный
    public String drills = "Дрели";
    private String basketSelector = ".ut2-icon-outline-cart";
    private String basketContinue = "//span[text()='Продолжить покупки']";
    private String isProductOnPage = ".ty-cart-content__product-title";
    private String totalPrice = "#sec_cart_total";
    private String deleteProductSelector = ".ty-delete-big__icon.ty-icon-cancel-circle";
    private String basketIdOnHidePage = "cart_status_3625";
    private String basketHref = ".ty-btn.ty-btn__secondary";

    //Метод находит рандомный товар на странице и добавляет его в корзину.
    public DrillsPage clickAndAddToBasket() {
        SelenideElement product = mixAndReturnRandomItem(initCollection());
        product.scrollTo();
        moveToElement(product);
        SelenideElement basket = product.$(basketSelector);
        moveToElement(basket);
        waitOneSecond();
        basket.click();
        $x(basketContinue).click();
        return this;
    }

    //Метод делает clickAndAddToBasket на трёх страницах
    public void clickAndAddToBasketOnThreePages(){
        clickAndAddToBasket();
        for(int nextPage=2;nextPage<=3;nextPage++){
            goToNextPage(nextPage);
            clickAndAddToBasket();
        }
    }

    //Метод открывает корзину
    public DrillsPage openBasket(){
        SelenideElement basket = $(By.id(basketIdOnHidePage));
        basket.scrollTo();
        moveToElement(basket);
        SelenideElement basket_href = basket.$(basketHref);
        moveToElement(basket_href);
        waitOneSecond();
        basket_href.click();
        return this;
    }

    //Метод проверяет наличие трёх товаров в корзине
    public void checkProductArePrecent(){
       List<SelenideElement> elements = new ArrayList<>($$(isProductOnPage));
       int sizeList = elements.size();
       Assert.assertEquals(sizeList,3,"Забыли добавить ищё один товар");
    }

    //Метод удаляет товар в корзине
    public void deleteProduct(String selector){
        $(selector).click();
    }

    //Метод удаляет товар в корзине и проверяет изминения итоговой цены
    public DrillsPage deleteProductAndCheckForChanges(){
        int oldTotalPrice = convertStringToNumber(totalPrice);
        deleteProduct(deleteProductSelector);
        int newTotalPrice = convertStringToNumber(totalPrice);
        Assert.assertNotEquals(oldTotalPrice,newTotalPrice,"Цена итого - неизменилась");
        return this;
    }
}
