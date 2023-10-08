package pagemodels;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage  extends BasePage {

    private By registerLabelLocator = By.xpath("//h2[text()='Регистрация']");
    private By nameFieldLocator = By.xpath("//label[text()='Имя']/../input");
    private By emailFieldLocator = By.xpath("//label[text()='Email']/../input");
    private By passwordFieldLocator = By.xpath("//label[text()='Пароль']/../input");
    private By registerButtonLocator = By.xpath("//button[text()='Зарегистрироваться']");
    private By loginButtonLocator = By.xpath("//a[text()='Войти']");
    private By incorrectPasswordLabelLocator = By.xpath("//p[text()='Некорректный пароль']");
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ожидание загрузки страницы Регистрации")
    public void waitForLoadRegisterPage() {
        waitForVisibility(registerLabelLocator);
    }

    @Step("Заполнение формы регистрации")
    public void fillRegistrationForm(String name, String email, String password) {
        driver.findElement(nameFieldLocator).sendKeys(name);
        driver.findElement(emailFieldLocator).sendKeys(email);
        driver.findElement(passwordFieldLocator).sendKeys(password);
    }

    @Step("Клик на кнопку 'Зарегистрироваться'")
    public void clickRegisterButton() {
        driver.findElement(registerButtonLocator).click();
    }

    @Step("Проверка видимости надписи 'Неправильный пароль'")
    public boolean isIncorrectPasswordLabelVisible() {
        return driver.findElement(incorrectPasswordLabelLocator).isDisplayed();
    }

    @Step("Клик на кнопку 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButtonLocator).click();
    }
}