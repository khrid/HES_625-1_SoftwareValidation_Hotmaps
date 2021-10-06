import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ChromeTest {

    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        System.out.print("Testing...");
    }

    @AfterEach
    void teardown() {
        if(driver != null) {
            driver.quit();
            System.out.println(" done.");
        }
    }

    @Test
    @DisplayName("hotmapscloud page title")
    void checkPageTitle() {
        driver.get("https://www.hotmapscloud.hevs.ch/map");
        Assertions.assertEquals(driver.getTitle(),"Toolbox");
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
