import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FirefoxTest {

    private static Utils utils;
    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.firefoxdriver().setup();
        utils = new Utils();
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

        driver.get("https://www.hotmapscloud.hevs.ch/map");
        // //*[@id="modal-full"]/div/div/div/p[7]/button
        WebElement zoomButton = driver.findElement(By.className("leaflet-control-zoom-in"));
        WebElement closePopup = driver.findElement(By.xpath("//*[@id=\"modal-full\"]/div/div/div/p[7]/button"));
        closePopup.click();
        WebElement placeInput = driver.findElement(By.id("place-input"));
        placeInput.sendKeys("Paris");
        placeInput.sendKeys(Keys.ENTER);
        Utils.takeScreenshot(driver, "zoomInButtonExists-parisBeforeZoom.png");
        zoomButton.click();
        Utils.takeScreenshot(driver, "zoomInButtonExists-parisAfterZoomIn.png");

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
        Utils.takeScreenshot(driver, "zoomOutButtonExists-parisBeforeZoom.png");
        zoomButton.click();
        Utils.takeScreenshot(driver, "zoomOutButtonExists-parisAfterZoomOut.png");
    }

}
