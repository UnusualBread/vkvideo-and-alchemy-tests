package mobile.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.extern.slf4j.Slf4j;
import mobile.locators.AlchemyHintsPanelLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
public class AlchemyHintsPanelScreen extends BaseScreen {
    public AlchemyHintsPanelScreen(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private final By startHintsCountContainerBy = By.xpath(AlchemyHintsPanelLocators.START_HINTS_COUNT_CONTAINER_XPATH);

    public AlchemyHintsPanelScreen checkStartHintsCount() {
        final int startHintsCount = 2;

        WebElement hintsCountContainer = waitUntilElementIsVisible(startHintsCountContainerBy);

        String text = hintsCountContainer.getText().strip();
        int hintsCount;

        try {
            hintsCount = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            log.error("Failed to parse hints count from text: '{}'", text, e);
            throw new AssertionError("Failed to parse hints count from text: '" + text + "'" + e);
        }

        assertThat(hintsCount).as("Number of hints at the start should be equals %d -> %d", startHintsCount, hintsCount)
                .isEqualTo(startHintsCount);
        log.info("Hints count at the start: Verified - {}", hintsCount);
        return this;
    }

    @AndroidFindBy(xpath = AlchemyHintsPanelLocators.WATCH_ADVERTISEMENT_CONTAINER_XPATH)
    WebElement watchAdvertisementContainer;

    public AlchemyAdvertisementScreen watchAdvertisement() {
        waitUntilElementIsVisible(watchAdvertisementContainer, Duration.ofSeconds(60));
        watchAdvertisementContainer.click();
        log.info("Advertisement: Opened");
        return new AlchemyAdvertisementScreen(driver);
    }

    private final By endHintsCountContainerBy = By.xpath(AlchemyHintsPanelLocators.END_HINTS_COUNT_CONTAINER_XPATH);

    private boolean isEndHintsCountEqualsFour() {
        final int endHintsCount = 4;

        WebElement hintsCountContainer = waitUntilElementIsVisible(endHintsCountContainerBy);

        String text = hintsCountContainer.getText().strip();
        int hintsCount;

        try {
            hintsCount = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            log.error("Failed to parse hints count from text: '{}'", text, e);
            throw new AssertionError("Failed to parse hints count from text: '" + text + "'" + e);
        }

        assertThat(hintsCount).as("Number of hints at the end should be equals %d -> %d", endHintsCount, hintsCount)
                .isEqualTo(endHintsCount);
        log.info("Hints count at the end: Verified - {}", hintsCount);
        return hintsCount == endHintsCount;
    }

    public void checkEndHintsCount() {
        boolean equalsFour = isEndHintsCountEqualsFour();
        assertThat(equalsFour).as("Hints count%s equals 4", equalsFour ? "" : " doesn't").isTrue();
        if (equalsFour) {
            log.info("Hints count was verified");
        } else {
            log.warn("Hints count was not changed");
        }
    }
}
