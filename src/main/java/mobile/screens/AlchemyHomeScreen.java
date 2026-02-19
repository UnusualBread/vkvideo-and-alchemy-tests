package mobile.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.extern.slf4j.Slf4j;
import mobile.locators.AlchemyHomeLocators;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class AlchemyHomeScreen extends BaseScreen {
    public AlchemyHomeScreen(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = AlchemyHomeLocators.OPEN_GAME_MENU_BUTTON_XPATH)
    private WebElement openGameMenuButton;

    public AlchemyGameMenuScreen openGameMenu() {
        waitUntilElementIsVisible(openGameMenuButton);
        openGameMenuButton.click();
        log.info("Game menu: Opened");
        return new AlchemyGameMenuScreen(driver);
    }
}
