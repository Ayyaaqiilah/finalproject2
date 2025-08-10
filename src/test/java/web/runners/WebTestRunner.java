package web.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import web.utils.WebDriverFactory;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/web",
        glue = {"web.steps"},
        tags = "@web",
        plugin = {"pretty", "html:target/cucumber-reports/web-report.html", "json:target/cucumber-reports/web-report.json"}
)
public class WebTestRunner {

        public static WebDriver driver;

        @BeforeClass
        public static void setup() {
                // Headless true supaya bisa jalan di GitHub Actions
                driver = WebDriverFactory.createChrome(true);
        }

        @AfterClass
        public static void teardown() {
                if (driver != null) {
                        driver.quit();
                }
        }
}
