package tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestInstance;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AbstractBaseTest {

    public AndroidDriver driver;

    public void setup(String appPackage, String appActivity) throws MalformedURLException, URISyntaxException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setUdid("emulator-5554")
                .setAppPackage(appPackage)
                .setAppActivity(appActivity)
                .setAutomationName("UiAutomator2")
                .setNewCommandTimeout(Duration.ofSeconds(60));
        driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
    }

    @AfterAll
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
