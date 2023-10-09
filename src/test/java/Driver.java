import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Driver {
    WebDriver driver;

    public void webDriver() {
        String browserType = System.getProperty("browserType");

        if (browserType == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
        } else if (browserType.equalsIgnoreCase("yandex")) {
            System.setProperty("webdriver.chrome.driver", "D:\\Java\\Diplom\\yandexdriver\\yandexdriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:\\Users\\Unugmak\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            driver = new ChromeDriver(options);
        }
    }
}