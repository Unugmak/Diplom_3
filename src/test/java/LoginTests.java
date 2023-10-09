import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pagemodels.LoginPage;
import pagemodels.MainPage;
import pagemodels.PasswordRecoveryPage;
import pagemodels.RegistrationPage;

import constant.BaseUrl;
import user.UserData;
import user.UserStep;

public class LoginTests extends Driver {

    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    PasswordRecoveryPage passwordRecoveryPage;
    UserData user;
    String email;
    String name;
    String password;

    @Before
    public void setUp() {
        webDriver();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        passwordRecoveryPage = new PasswordRecoveryPage(driver);
        driver.get(BaseUrl.BASE_URL);
        user = UserData.generateUserRandom();
        UserStep.createUser(user);
        email = user.getEmail();
        password = user.getPassword();
        name = user.getName();
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void loginMainPageTest() {
        mainPage.clickLoginButton();
        loginPage.waitForLoad();
        loginPage.fillLoginForm(email, password);
        loginPage.clickLoginButton();
        mainPage.waitForLoad();
        Assert.assertTrue("Кнопка оформить заказ не появилась", mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void loginPersonalAccountButtonTest() {
        mainPage.clickPersonalButton();
        loginPage.waitForLoad();
        loginPage.fillLoginForm(email, password);
        loginPage.clickLoginButton();
        mainPage.waitForLoad();
        Assert.assertTrue("Кнопка оформить заказ не появилась", mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginRegistrationPageTest() {
        mainPage.clickLoginButton();
        loginPage.waitForLoad();
        loginPage.clickRegisterButton();
        registrationPage.waitForLoadRegisterPage();
        registrationPage.clickLoginButton();
        loginPage.waitForLoad();
        loginPage.fillLoginForm(email, password);
        loginPage.clickLoginButton();
        mainPage.waitForLoad();
        Assert.assertTrue("Кнопка оформить заказ не появилась", mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginPasswordRecoveryPageTest() {
        mainPage.clickLoginButton();
        loginPage.waitForLoad();
        loginPage.clickResetPasswordButton();
        passwordRecoveryPage.waitForLoadPage();
        passwordRecoveryPage.clickLoginButton();
        loginPage.waitForLoad();
        loginPage.fillLoginForm(email, password);
        loginPage.clickLoginButton();
        mainPage.waitForLoad();
        Assert.assertTrue("Кнопка оформить заказ не появилась", mainPage.isOrderButtonVisible());
    }

    @After
    public void cleanUp() {
        ValidatableResponse response = UserStep.loginUser(user);
        int statusCode = response.extract().statusCode();
        if (statusCode == 200) {
            UserStep.deleteUser(user, response.extract().path("accessToken"));
        }
        driver.quit();
    }
}