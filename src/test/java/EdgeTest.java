import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.springframework.util.Assert;

import java.util.List;

public class EdgeTest {

    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.edgedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless");
        driver = new EdgeDriver(options);
    }

    @AfterEach
    void teardown() {
        if(driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("hotmapscloud page title")
    void checkPageTitle() {
        System.out.print("Testing...");
        driver.get("https://www.hotmapscloud.hevs.ch/map");
        Assertions.assertEquals(driver.getTitle(),"Toolbox");
        System.out.println(" done.");
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
