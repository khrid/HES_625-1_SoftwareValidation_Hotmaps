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
    static String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmm"));

    static void takeScreenshot(WebDriver driver, String filename) {
        String browser = ((RemoteWebDriver) driver).getCapabilities().getBrowserName().toLowerCase(Locale.ROOT);
        try {
            Thread.sleep(2000); // pour s'assurer que l'action que l'on veut capturer a été faite
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File file = screenshot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File("C://tmp//selenium//" + browser + "//" + filename);
        try {
            FileUtils.copyFile(file, DestFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
