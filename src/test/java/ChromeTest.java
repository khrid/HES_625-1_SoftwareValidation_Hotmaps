import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

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
        Assertions.assertEquals(driver.getTitle(), "Toolbox");
    }

    @Test
    @DisplayName("[209] + button exists")
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
        Actions actions = new Actions(driver);
        actions.moveToElement(zoomButton).click().perform();
        actions.moveToElement(zoomButton).click().perform();
        actions.moveToElement(zoomButton).click().perform();
        Utils.takeScreenshot(driver, "zoomInButtonExists-parisAfterZoomIn.png");

    }

    @Test
    @DisplayName("[209] - button exists")
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
        Actions actions = new Actions(driver);
        actions.moveToElement(zoomButton).click().perform();
        actions.moveToElement(zoomButton).click().perform();
        actions.moveToElement(zoomButton).click().perform();
        Utils.takeScreenshot(driver, "zoomOutButtonExists-parisAfterZoomOut.png");
    }

    @Test
    @DisplayName("[385] Test")
    void districtHeatingCMGenevaEqualsToValue() {
        driver.get("https://www.hotmapscloud.hevs.ch/map");
        WebElement closePopup = driver.findElement(By.xpath("//*[@id=\"modal-full\"]/div/div/div/p[7]/button"));
        closePopup.click();
        WebElement layerLAU2 = driver.findElement(By.xpath("//*[@id=\"map\"]/div[2]/div[2]/div[1]/section/div[1]/label[5]/div/span"));
        layerLAU2.click();
        WebElement placeInput = driver.findElement(By.id("place-input"));
        placeInput.sendKeys("Geneva");
        placeInput.sendKeys(Keys.ENTER);
        Utils.takeScreenshot(driver, "districtHeatingCMGenevaEqualsToValue-geneva.png");
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.tagName("body")), 0, 0);
        actions.moveByOffset(-50, 10).click().build().perform();
        Utils.takeScreenshot(driver, "districtHeatingCMGenevaEqualsToValue-genevaClick.png");
    }

    @Test
    @DisplayName("[475] LAU2 and NUTS3 border are differents in Danemark")
    void borderLAU2orNUTS3Danemark() {
        driver.get("https://www.hotmapscloud.hevs.ch/map");
        WebElement closePopup = driver.findElement(By.xpath("//*[@id=\"modal-full\"]/div/div/div/p[7]/button"));
        closePopup.click();
        WebElement placeInput = driver.findElement(By.id("place-input"));
        placeInput.sendKeys("Aalborg");
        placeInput.sendKeys(Keys.ENTER);
        WebElement layerLAU2 = driver.findElement(By.xpath("//*[@id=\"map\"]/div[2]/div[2]/div[1]/section/div[1]/label[5]/div/span"));
        layerLAU2.click();
        Utils.takeScreenshot(driver, "borderLAU2Danemark-Aalborg.png");
        WebElement layerNUTS3 = driver.findElement(By.xpath("//*[@id=\"map\"]/div[2]/div[2]/div[1]/section/div[1]/label[4]/div/span"));
        layerNUTS3.click();
        Utils.takeScreenshot(driver, "borderNUTS3Danemark-Aalborg.png");
    }


    @Test
    @DisplayName("[512] Layers of Gross floor subgroup")
    void grossFloorSubgroupLayers() {
        driver.get("https://www.hotmapscloud.hevs.ch/map");
        WebElement closePopup = driver.findElement(By.xpath("//*[@id=\"modal-full\"]/div/div/div/p[7]/button"));
        closePopup.click();
        WebElement placeInput = driver.findElement(By.id("place-input"));
        placeInput.sendKeys("Birmingham");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        placeInput.sendKeys(Keys.ENTER);
        WebElement zoomButton = driver.findElement(By.className("leaflet-control-zoom-in"));
        Actions actions = new Actions(driver);
        actions.moveToElement(zoomButton).click().perform();
        actions.moveToElement(zoomButton).click().perform();
        actions.moveToElement(zoomButton).click().perform();
        actions.moveToElement(zoomButton).click().perform();
        actions.moveToElement(zoomButton).click().perform();
        Utils.takeScreenshot(driver, "grossFloorSubgroupLayers-base.png");
        WebElement layerMenu = driver.findElement(By.xpath("/html/body/app-root/htm-map/div[1]/htm-nav-bar/div[1]"));
        layerMenu.click();
        WebElement grossFloorAreaTotal = driver.findElement(By.name("gfa_tot_curr_density"));
        grossFloorAreaTotal.click();
        Utils.takeScreenshot(driver, "grossFloorSubgroupLayers-grossFloorAreaTotal.png");
        grossFloorAreaTotal.click();
        WebElement grossFloorAreaResidential = driver.findElement(By.name("gfa_res_curr_density"));
        grossFloorAreaResidential.click();
        Utils.takeScreenshot(driver, "grossFloorSubgroupLayers-grossFloorAreaResidential.png");
        grossFloorAreaResidential.click();
        WebElement grossFloorAreaNonResidential = driver.findElement(By.name("gfa_nonres_curr_density"));
        grossFloorAreaNonResidential.click();
        Utils.takeScreenshot(driver, "grossFloorSubgroupLayers-grossFloorAreaNonResidential.png");
        grossFloorAreaNonResidential.click();
    }
}
