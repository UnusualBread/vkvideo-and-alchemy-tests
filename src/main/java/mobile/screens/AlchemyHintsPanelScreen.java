package mobile.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.extern.slf4j.Slf4j;
import mobile.locators.AlchemyHintsPanelLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
public class AlchemyHintsPanelScreen extends BaseScreen {
    public AlchemyHintsPanelScreen(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private final By hintsCountContainerBy = By.xpath(AlchemyHintsPanelLocators.HINTS_COUNT_CONTAINER_XPATH);

    public AlchemyHintsPanelScreen checkHintsCount() {
        final int startHintsCount = 2;

        WebElement hintsCountContainer = waitUntilElementIsVisible(hintsCountContainerBy);

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
        log.info("Hints count: Verified - {}", hintsCount);
        return this;
    }

    @AndroidFindBy(xpath = AlchemyHintsPanelLocators.WATCH_ADVERTISEMENT_BUTTON_XPATH)
    WebElement watchAdvertisementButton;

    public AlchemyAdvertisementScreen watchAdvertisement() {
        waitUntilElementIsVisible(watchAdvertisementButton);
        watchAdvertisementButton.click();
        log.info("Advertisement: Opened");
        return new AlchemyAdvertisementScreen(driver);
    }
}
