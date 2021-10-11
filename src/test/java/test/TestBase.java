package test;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import page.DiscountsPage;
import page.DrillsPage;
import page.MainPage;
import page.PerforatorPage;

public class TestBase {
    MainPage mainPage = new MainPage();
    DiscountsPage discountsPage = new DiscountsPage();
    DrillsPage drillsPage = new DrillsPage();
    PerforatorPage perforatorPage = new PerforatorPage();
    @BeforeClass
    public void setting(){
        Configuration.startMaximized = true;
        Configuration.timeout = 10000;
    }
    @BeforeMethod
    public void start(){
        mainPage.openSite();
    }
}
