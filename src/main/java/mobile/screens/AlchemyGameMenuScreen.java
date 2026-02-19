package mobile.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.extern.slf4j.Slf4j;
import mobile.locators.AlchemyGameMenuLocators;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class AlchemyGameMenuScreen extends BaseScreen {
    public AlchemyGameMenuScreen(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = AlchemyGameMenuLocators.OPEN_HINTS_PANEL_BUTTON_XPATH)
    private WebElement openHintsPanelButton;

    public AlchemyHintsPanelScreen openHintsPanel() {
        waitUntilElementIsVisible(openHintsPanelButton);
        openHintsPanelButton.click();
        log.info("Hints menu: Opened");
        return new AlchemyHintsPanelScreen(driver);
    }
}
