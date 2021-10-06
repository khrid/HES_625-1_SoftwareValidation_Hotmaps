import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxTest {

    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    void setupTest() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        driver = new FirefoxDriver(options);
        System.out.print("Testing...");
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
            System.out.println(" done.");
        }
    }

    @Test
    @DisplayName("hotmapscloud page title")
    void checkPageTitle() {
        driver.get("https://www.hotmapscloud.hevs.ch/map");
        Assertions.assertEquals(driver.getTitle(), "Toolbox");
    }

    @Test
    @DisplayName("[Zoom] + button exists")
    void zoomInButtonExists() {
        driver.get("https://www.hotmapscloud.hevs.ch/map");
        Assertions.assertEquals(1, driver.findElements(By.className("leaflet-control-zoom-in")).size());
    }

    @Test
    @DisplayName("[Zoom] - button exists")
    void zoomOutButtonExists() {
        driver.get("https://www.hotmapscloud.hevs.ch/map");
        Assertions.assertEquals(1, driver.findElements(By.className("leaflet-control-zoom-out")).size());
    }
}
