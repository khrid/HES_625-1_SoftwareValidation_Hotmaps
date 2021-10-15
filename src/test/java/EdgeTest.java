import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.springframework.util.Assert;

import java.util.List;

public class EdgeTest {

    private static Utils utils;
    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.edgedriver().setup();
        utils = new Utils();
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

        driver.get("https://www.hotmapscloud.hevs.ch/map");
        // //*[@id="modal-full"]/div/div/div/p[7]/button
        WebElement zoomButton = driver.findElement(By.className("leaflet-control-zoom-in"));
        WebElement closePopup = driver.findElement(By.xpath("//*[@id=\"modal-full\"]/div/div/div/p[7]/button"));
        closePopup.click();
        WebElement placeInput = driver.findElement(By.id("place-input"));
        placeInput.sendKeys("Paris");
        placeInput.sendKeys(Keys.ENTER);
        utils.takeScreenshot(driver, "zoomInButtonExists-parisBeforeZoom.png");
        zoomButton.click();
        utils.takeScreenshot(driver, "zoomInButtonExists-parisAfterZoomIn.png");

    }

    @Test
    @DisplayName("[Zoom] - button exists")
    void zoomOutButtonExists() throws InterruptedException {
        driver.get("https://www.hotmapscloud.hevs.ch/map");
        Assertions.assertEquals(1, driver.findElements(By.className("leaflet-control-zoom-out")).size());

        driver.get("https://www.hotmapscloud.hevs.ch/map");
        // //*[@id="modal-full"]/div/div/div/p[7]/button
        WebElement zoomButton = driver.findElement(By.className("leaflet-control-zoom-out"));
        WebElement closePopup = driver.findElement(By.xpath("//*[@id=\"modal-full\"]/div/div/div/p[7]/button"));
        closePopup.click();
        WebElement placeInput = driver.findElement(By.id("place-input"));
        placeInput.sendKeys("Paris");
        placeInput.sendKeys(Keys.ENTER);
        utils.takeScreenshot(driver, "zoomOutButtonExists-parisBeforeZoom.png");
        zoomButton.click();
        utils.takeScreenshot(driver, "zoomOutButtonExists-parisAfterZoomOut.png");
    }
}
