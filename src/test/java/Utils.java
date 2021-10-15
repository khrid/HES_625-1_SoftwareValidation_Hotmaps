import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Utils {
    static String timestamp;

    public Utils() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmm");
        timestamp = LocalDateTime.now().format(formatter);
    }

    static void takeScreenshot(WebDriver driver, String filename) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File file = screenshot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File("C://tmp//selenium//" + timestamp + "//" + ((RemoteWebDriver) driver).getCapabilities().getBrowserName().toLowerCase(Locale.ROOT) + "//" + filename);
        try {
            FileUtils.copyFile(file, DestFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
