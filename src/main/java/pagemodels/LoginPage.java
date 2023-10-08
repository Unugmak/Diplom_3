package pagemodels;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    private By loginLabelLocator = By.xpath("//h2[text()='Вход']");
    private By emailFieldLocator = By.xpath("//input[@name='name']");
    private By passwordFieldLocator = By.xpath("//input[@name='Пароль']");
    private By loginButtonLocator = By.xpath("//button[text()='Войти']");
    private By registerButtonLocator = By.xpath("//a[text()='Зарегистрироваться']");
    private By resetPasswordButtonLocator = By.xpath("//a[text()='Восстановить пароль']");
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ожидание загрузки страницы логина")
    public void waitForLoad() {
        waitForVisibility(loginLabelLocator);
    }

    @Step("Клик на кнопку 'Зарегистрироваться'")
    public void clickRegisterButton() {
        driver.findElement(registerButtonLocator).click();
    }

    @Step("Заполнение формы авторизации")
    public void fillLoginForm(String email, String password) {
        driver.findElement(emailFieldLocator).sendKeys(email);
        driver.findElement(passwordFieldLocator).sendKeys(password);
    }

    @Step("Клик на кнопку 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButtonLocator).click();
    }

    @Step("Клик на кнопку 'Восстановить пароль'")
    public void clickResetPasswordButton() {
        driver.findElement(resetPasswordButtonLocator).click();
    }

    @Step("Проверка видимости надписи 'Вход'")
    public boolean isEnterLabelVisible() {
        return driver.findElement(loginLabelLocator).isDisplayed();
    }
}