package mobile.screens;

import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
import mobile.App;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;

import java.time.Duration;
import java.time.Instant;
import java.util.regex.Pattern;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
public class VkVideoFirstVideoScreen extends BaseScreen {
    public VkVideoFirstVideoScreen(AppiumDriver driver) {
        super(driver);
    }

    public void checkVideoPlayback() {
        boolean playing = isVideoPlaying();
        assertThat(playing).as(playing ? "Video is playing" : "Video is not playing").isTrue();
        if (playing) {
            log.info("Video playback verified by logcat");
        } else {
            log.warn("Video playback was not detected in logcat");
        }
    }

    public boolean isVideoPlaying() {
        Pattern pat = Pattern.compile(
                "(onPlaybackStateChanged\\(\\):.*" +
                        Pattern.quote(App.VK_VIDEO.getAppPackage()) +
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
                    if (pat.matcher(msg).find()) {
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
        return found;
    }
}
