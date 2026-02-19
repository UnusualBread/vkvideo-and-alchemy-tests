package tests;

import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
import mobile.App;
import mobile.Initializer;
import mobile.helper.Helper;
import org.junit.jupiter.api.AfterEach;

@Slf4j
public class BaseTest {

    protected static AppiumDriver driver;

    public void startApp(App app) {
        log.info("Driver: Initializing before test\n");
        driver = Initializer.getDriver(app);
        Helper.setDriver(driver);
        Helper.sleep(2000);
        log.info("!!!!! Starting the TEST !!!!!\n");
    }

    @AfterEach
    public  void tearDown() {
        log.info("Driver: Closing after test\n");
        Initializer.quitDriver();
    }
}
