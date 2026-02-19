package mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

@Slf4j
public class Initializer {
    private static AppiumDriver driver;
    public static Properties config = new Properties();

    static {
        try (InputStream input = Initializer.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                log.error("Unable to find config.properties");
                throw new ExceptionInInitializerError();
            }
            config.load(input);
        } catch (Exception e) {
            log.error("Failed to load configuration", e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static AppiumDriver getDriver(App app) {
        if (driver == null) {
            initDriver(app);
        }
        return driver;
    }

    public static void initDriver(App app) {
        try {
            URI appiumServerURI = new URI(config.getProperty("appium.server.url"));
            URL appiumServerURL = appiumServerURI.toURL();
            driver = new AndroidDriver(appiumServerURL, getOptions(app));
            log.info("Driver initialized successfully");
        } catch (Exception e) {
            log.error("Driver initialization failed", e);
            throw new RuntimeException(e);
        }
    }

    private static UiAutomator2Options getOptions(App app) {
        return new UiAutomator2Options()
                .setDeviceName(config.getProperty("device.name"))
                .setPlatformName(config.getProperty("platform.name"))
                .setAutomationName(config.getProperty("automation.name"))
                .setAppPackage(app.getAppPackage())
                .setAppActivity(app.getAppActivity())
                .setNewCommandTimeout(Duration.ofSeconds(Integer.parseInt(config.getProperty("new.command.timeout"))));
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
