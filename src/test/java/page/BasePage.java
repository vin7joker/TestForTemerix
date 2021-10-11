package page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class BasePage {
    protected String allCardInSection = ".ty-column3";

    //Инициализирует коллекцию товарами со страницы
    public List<SelenideElement> initCollection(){
        List<SelenideElement> elements = new ArrayList<>($$(allCardInSection));
        return elements;
    }

    //Перемешивает коллекцию и возвращает один рандомный элемент
    public SelenideElement mixAndReturnRandomItem(List<SelenideElement> elements){
        Collections.shuffle(elements);
        int size0fCollection = elements.size();
        int randomIndex = (int) ( Math.random() * (size0fCollection-1) );
        return elements.get(randomIndex);
    }

    //Метод переходит на заданную страницу
    public BasePage goToNextPage(int numberOfPage){
        initCollection().clear();
        $x("//a[@data-ca-scroll='.cm-pagination-container' and contains(text(),"+numberOfPage+")]").click();
        waitOneSecond();
        return this;
    }

    //Метод возвращает предыдущее окно сайта
    public BasePage back(){
        Selenide.back();
        return this;
    }

    //Метод принудительно ожидает одну секунду для прогрузки сайта
    public void waitOneSecond(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Метод конвертируе строку в число с помощью css селектора
    public int convertStringToNumber(String selector){
        String string_value = $(selector).getText().replaceAll("\\D","");
        int discountPrice = Integer.parseInt(string_value);
        return discountPrice;
    }

    //Метод наводит мышь на нужный селенид элемент
    public void moveToElement(SelenideElement element){
        actions().moveToElement(element).perform();
    }
}
