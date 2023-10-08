import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;


public class Driver {
    WebDriver driver;

    public void webDriver() {
        //Хром
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

        //Яндекс браузера
//        System.setProperty("webdriver.chrome.driver", "D:\\Java\\Diplom\\yandexdriver.exe");
//        ChromeOptions options = new ChromeOptions();
//        options.setBinary("C:\\Users\\Unugmak\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-dev-shm-usage");
//        driver = new ChromeDriver(options);
    }
}
