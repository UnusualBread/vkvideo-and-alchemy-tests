package tests;

import mobile.screens.VkVideoHomeScreen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CheckVideoIsPlayableTest extends BaseTest {

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
