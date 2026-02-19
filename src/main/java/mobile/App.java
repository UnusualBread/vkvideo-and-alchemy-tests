package mobile;

import lombok.Getter;

public enum App {
    VK_VIDEO("com.vk.vkvideo", "com.vk.video.screens.main.MainActivity"),
    ALCHEMY("com.ilyin.alchemy", "com.ilyin.app_google_core.GoogleAppActivity");

    @Getter
    private final String appPackage;
    @Getter
    private final String appActivity;

    App(String appPackage, String appActivity) {
        this.appPackage = appPackage;
        this.appActivity = appActivity;
    }
}

