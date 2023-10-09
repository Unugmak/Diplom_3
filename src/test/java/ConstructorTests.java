import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pagemodels.MainPage;
import constant.BaseUrl;

public class ConstructorTests extends Driver {

    MainPage mainPage;

    @Before
    public void setUp() {
        webDriver();
        mainPage = new MainPage(driver);
        driver.get(BaseUrl.BASE_URL);
    }

    @Test
    @DisplayName("Переход в раздел Соусы")
    public void toTheSaucesSectionTest(){
        mainPage.clickSauceButton();
        Assert.assertTrue("Переход в раздел Соусы не произошел", mainPage.isSauceButtonActive());
    }

    @Test
    @DisplayName("Переход в раздел Начинки")
    public void toTheFillingSectionTest(){
        mainPage.clickFillingButton();
        Assert.assertTrue("Переход в раздел Начинки не произошел", mainPage.isFillingButtonActive());
    }

    @Test
    @DisplayName("Переход в раздел Булки")
    public void toTheBunSectionTest(){
        mainPage.clickFillingButton();
        mainPage.clickBunButton();
        Assert.assertTrue("Переход в раздел Булки не произошел", mainPage.isBunButtonActive());
    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}