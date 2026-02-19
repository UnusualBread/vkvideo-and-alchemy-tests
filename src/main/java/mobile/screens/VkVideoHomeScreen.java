package mobile.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.extern.slf4j.Slf4j;
import mobile.locators.VkVideoHomeLocators;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class VkVideoHomeScreen extends BaseScreen {
    public VkVideoHomeScreen(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = VkVideoHomeLocators.CLOSE_ADVERTISEMENT_BUTTON_XPATH)
    private WebElement closeAdvertisementButton;

    public VkVideoHomeScreen closeVkAdvertisement() {
        try {
            waitUntilElementIsVisible(closeAdvertisementButton);
            closeAdvertisementButton.click();
            log.info("VK advertisement: Closed");
        } catch (Exception e) {
            log.info("VK advertisement: Not found");
        }
        return this;
    }

    @AndroidFindBy(xpath = VkVideoHomeLocators.SKIP_VK_AUTH_BUTTON_XPATH)
    private WebElement skipVkAuthButton;

    public VkVideoHomeScreen skipVkAuth() {
        try {
            waitUntilElementIsVisible(skipVkAuthButton);
            skipVkAuthButton.click();
            log.info("VK auth: Skipped");
        } catch (Exception e) {
            log.info("VK auth: Not found");
        }
        return this;
    }

    @AndroidFindBy(xpath = VkVideoHomeLocators.SKIP_GOOGLE_AUTH_BUTTON_XPATH)
    private WebElement skipGoogleAuthButton;

    public VkVideoHomeScreen skipGoogleAuth() {
        try {
            waitUntilElementIsVisible(skipGoogleAuthButton);
            skipGoogleAuthButton.click();
            log.info("Google auth: Skipped");
        } catch (Exception e) {
            log.info("Google auth: Not found");
        }
        return this;
    }

    @AndroidFindBy(xpath = VkVideoHomeLocators.FIRST_VIDEO_CARD_XPATH)
    private WebElement openFirstVideoButton;

    public FirstVideoScreen openFirstVideo() {
        waitUntilElementIsVisible(openFirstVideoButton);
        openFirstVideoButton.click();
        log.info("First video: Opened");
        return new FirstVideoScreen(driver);
    }
}
