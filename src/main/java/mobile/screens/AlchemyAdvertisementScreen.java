package mobile.screens;

import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
import mobile.locators.AlchemyAdvertisementLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

@Slf4j
public class AlchemyAdvertisementScreen extends BaseScreen {
    public AlchemyAdvertisementScreen(AppiumDriver driver) {
        super(driver);
    }

    private final By closeAdvertisementButtonBy = By.id(AlchemyAdvertisementLocators.CLOSE_ADVERTISEMENT_BUTTON_ID);

    public AlchemyHintsPanelScreen closeAdvertisement() {
        //final int advertisementToCloseCount = 2;

        /*for (int i = 0; i < advertisementToCloseCount; i++) {
            WebElement closeAdvertisementButton = waitUntilElementIsVisible(closeAdvertisementButtonBy, Duration.ofSeconds(60));
            closeAdvertisementButton.click();
            log.info("Advertisement number {}: Closed", i + 1);
        }*/

        int ind = 1;
        int duration = 60;
        int closedAdvertisementScreens = 0;
        try {
            while (true) {
                WebElement closeAdvertisementButton = waitUntilElementIsVisible(closeAdvertisementButtonBy, Duration.ofSeconds(duration));
                closeAdvertisementButton.click();
                log.info("Advertisement number {}: Closed", ind++);
                duration = Math.max(10, duration / 2);
                closedAdvertisementScreens++;
            }
        } catch (Exception _) {}

        log.info("All {} advertisement screens are closed", closedAdvertisementScreens);
        return new AlchemyHintsPanelScreen(driver);
    }
}
