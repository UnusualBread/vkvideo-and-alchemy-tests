package tests;

import mobile.App;
import mobile.screens.VkVideoHomeScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class VkVideoCheckVideoIsPlayableTest extends BaseTest {

    @BeforeEach
    public void setUpApp() {
        startApp(App.VK_VIDEO);
    }

    @Test
    @DisplayName("Check that video is playable")
    public void testCheckVideoIsPlayable() {
        VkVideoHomeScreen vkVideoHomeScreen = new VkVideoHomeScreen(driver);
        vkVideoHomeScreen
                .closeVkAdvertisement()
                .skipGoogleAuth()
                .skipVkAuth()
                .openFirstVideo()
                .checkVideoPlayback();
    }
}
