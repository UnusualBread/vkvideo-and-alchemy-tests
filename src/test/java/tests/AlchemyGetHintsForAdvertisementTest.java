package tests;

import mobile.App;
import mobile.screens.AlchemyHomeScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AlchemyGetHintsForAdvertisementTest extends BaseTest {

    @BeforeEach
    public void setUpApp() {
        startApp(App.ALCHEMY);
    }

    @Test
    @DisplayName("Check that video is playable")
    public void testGetHintsForAdvertisement() {
        AlchemyHomeScreen alchemyHomeScreen = new AlchemyHomeScreen(driver);
        alchemyHomeScreen
                .openGameMenu()
                .openHintsPanel()
                .checkStartHintsCount()
                .watchAdvertisement()
                .closeAdvertisement()
                .checkEndHintsCount();
    }
}
