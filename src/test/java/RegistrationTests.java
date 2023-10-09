import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pagemodels.LoginPage;
import pagemodels.MainPage;
import pagemodels.RegistrationPage;

import user.UserData;
import user.UserStep;
import constant.BaseUrl;
public class RegistrationTests extends Driver {

    String name;
    String email;
    String password;
    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    UserData user;

    @Before
    public void setUp() {
        webDriver();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        driver.get(BaseUrl.BASE_URL);
        mainPage.clickLoginButton();
        loginPage.waitForLoad();
        loginPage.clickRegisterButton();
        registrationPage.waitForLoadRegisterPage();
    }

    @Test
    @DisplayName("Проверка успешной регистрации")
    public void checkSuccessfulRegistrationTest() {
        user = UserData.generateUserRandom();
        email = user.getEmail();
        password = user.getPassword();
        name = user.getName();
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
        user = UserData.generateUserRandom();
        email = user.getEmail();
        name = user.getName();
        password = "123";
        registrationPage.fillRegistrationForm(name, email, password);
        registrationPage.clickRegisterButton();
        Assert.assertTrue("Ошибка о некорректном пароле не появилась",
                registrationPage.isIncorrectPasswordLabelVisible());
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