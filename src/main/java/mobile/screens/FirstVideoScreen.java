package mobile.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.extern.slf4j.Slf4j;
import mobile.locators.FirstVideoLocators;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.regex.Pattern;

import static mobile.Initializer.config;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
public class FirstVideoScreen extends BaseScreen {
    public FirstVideoScreen(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = FirstVideoLocators.FIRST_VIDEO_PROGRESS_BAR_ID)
    private WebElement firstVideoProgressBar;

    public void checkVideoPlayback() {
        Pattern pat = Pattern.compile(
                "(onPlaybackStateChanged\\(\\):.*" +
                        Pattern.quote(config.getProperty("app.package")) +
                        ".*state=3)|STATE_PLAYING",
                Pattern.CASE_INSENSITIVE
        );
        Instant deadline = Instant.now().plus(Duration.ofSeconds(10));
        boolean found = false;

        while (Instant.now().isBefore(deadline)) {
            try {
                LogEntries logs = driver.manage().logs().get("logcat");
                for (LogEntry e : logs) {
                    String msg = e.getMessage();
                    if (msg != null && pat.matcher(msg).find()) {
                        log.info("Playback log found: {}", msg);
                        found = true;
                        break;
                    }
                }
            } catch (Exception e) {
                throw new AssertionError("Driver does not support logcat logs: " + e.getMessage(), e);
            }
            if (found) {
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        assertThat(found).as( "Video playback was not detected in logcat").isTrue();
        log.info("Video playback verified by logcat");
    }
}
