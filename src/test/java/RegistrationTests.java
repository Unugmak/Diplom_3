import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pagemodels.LoginPage;
import pagemodels.MainPage;
import pagemodels.RegistrationPage;

import user.UserData;
import user.UserStep;

public class RegistrationTests extends Driver {

    String name;
    String email;
    String password;
    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;

    @Before
    public void setUp() {
        webDriver();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        driver.get("https://stellarburgers.nomoreparties.site");
        mainPage.clickLoginButton();
        loginPage.waitForLoad();
        loginPage.clickRegisterButton();
        registrationPage.waitForLoadRegisterPage();
    }

    @Test
    @DisplayName("Проверка успешной регистрации")
    public void checkSuccessfulRegistrationTest() {
        name = "BurgerTest";
        email = "BurgerTest@yandex.ru";
        password = "123456789";
        registrationPage.fillRegistrationForm(name, email, password);
        registrationPage.clickRegisterButton();
        loginPage.waitForLoad();
        loginPage.fillLoginForm(email, password);
        loginPage.clickLoginButton();
        mainPage.waitForLoad();
        Assert.assertTrue("Регистрация не произошла", mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Проверка регистрации с некорректным паролем")
    public void checkForBadPasswordLoginTest() {
        name = "BurgerTest";
        email = "BurgerTest@yandex.ru";
        password = "123";
        registrationPage.fillRegistrationForm(name, email, password);
        registrationPage.clickRegisterButton();
        Assert.assertTrue("Ошибка о некорректном пароле не появилась",
                registrationPage.isIncorrectPasswordLabelVisible());
    }

    @After
    public void cleanUp() {
        String token = UserStep.loginUser(new UserData(email, password)).then().extract().path("accessToken");
        if (token != null) {
            UserStep.deleteUser(token);
        }
        driver.quit();
    }
}