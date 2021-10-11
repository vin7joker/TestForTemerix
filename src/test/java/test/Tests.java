package test;

import org.testng.annotations.Test;

public class Tests extends TestBase{
    /* 1. в разделе "Скидки" на  трех товарах с тикетом о скидке проверить есть ли в карточке товара акционная цена.
Рандомно, на 3-х первых страничках выбрать товар, провалиться в карточку товара и проверить наличие акционной и
старой цены. Делаем это для 3-х товаров  (выбор количества проверяемых товаров сделать гибким).*/
    @Test(priority = 1)
    public void firstTest(){
        mainPage.goToDiscounts();
        discountsPage.clickOnRandomPageElement().checkOldAndNewPrice().back();
        discountsPage.repeatToPageInclusive(3);
    }
    /* 2. Перейти в раздел "Электроинструменты" / "Дрели". Добавить товар в корзину (3 товара на разных страничках)
Открыть корзину. В корзине проверить  что все товары присутствуют. Удалить один товар из корзины
Проверить что цена "Итого" изменилась */
    @Test(priority = 2)
    public void secondTest()  {
        mainPage.goToSection(drillsPage.drills);
        drillsPage.clickAndAddToBasketOnThreePages();
        drillsPage.openBasket().checkProductArePrecent();
        drillsPage.deleteProductAndCheckForChanges();
    }
    /*3. Перейти в раздел "Электроинструменты" / "Перфараторы"
Вывести "Наименование" всех товаров у которых есть иконка Акция на первых двух страничках.
Проверить что у всех товаров с тикетом Акция есть тикет Скидка. Делаем для 1, 3, 5-й страничек.
Переходим в карточку одного из товаров со Скидкой для проверки наличия цены со скидкой */
    @Test(priority = 3)
    public void thirdTest(){
        mainPage.goToSection(perforatorPage.perforator);
        perforatorPage.displayPromotionalProducts();
        perforatorPage.checkTicketDiscount();
        perforatorPage.clickOnProductWithDiscount().checkDiscountPrice();
    }
    /*4. В разделе "Скидка". Для 5 рандомных товаров провести расчет акционной цены относительно старой.
    В assert упавшего теста вывести наименование товара его ожидаемую и фактическую цену.*/
    @Test(priority = 4)
    public void fourthTest(){
        mainPage.goToDiscounts();
        discountsPage.findFiveRandomProductAndCheckOldAndDiscountPrice();
    }
}
