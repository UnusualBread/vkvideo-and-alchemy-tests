package mobile.helper;

import io.appium.java_client.AppiumDriver;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import mobile.Initializer;
import org.openqa.selenium.WebElement;

import java.util.Objects;

@Slf4j
public class Helper {

    @Setter
    private static AppiumDriver driver;

    public static boolean isTextOnScreen(String expectedText) {
        AppiumDriver driver = Initializer.getDriver();
        if (driver == null) {
            log.error("Driver is not initialized");
            throw new IllegalStateException("Driver is not initialized");
        }
        return Objects.requireNonNull(driver.getPageSource()).contains(expectedText);
    }

    public static String getAttribute(WebElement element, String attributeName) {
        if (element == null) return null;
        try {
            String value = element.getAttribute(attributeName);
            return (value != null && !value.isEmpty()) ? value : null;
        } catch (Exception e) {
            log.warn("Unable to get WebElement attribute '{}'", attributeName, e);
            return null;
        }
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException _) {}
    }
}
