package tests.vkvideo;

import org.junit.jupiter.api.BeforeAll;
import tests.AbstractBaseTest;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class BaseTestVk extends AbstractBaseTest {
    @BeforeAll
    public void setupVk() throws MalformedURLException, URISyntaxException {
        setup("com.vk.vkvideo", "com.vk.video.screens.main.MainActivity");
    }
}
