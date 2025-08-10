package web.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {

    public static WebDriver createChrome(boolean headless) {
        ChromeOptions options = new ChromeOptions();

        // Kalau headless diaktifkan (misalnya di CI)
        if (headless) {
            options.addArguments("--headless=new"); // Chrome versi baru
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }

        // Optional: set window size biar stabil di CI
        options.addArguments("--window-size=1920,1080");

        return new ChromeDriver(options);
    }
}
