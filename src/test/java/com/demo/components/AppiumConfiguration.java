package com.demo.components;

import com.demo.init.BaseTest;
import com.demo.utils.AppiumServerManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.ios.options.simulator.Permissions;
import io.appium.java_client.remote.AutomationName;
import io.github.jiawade.tool.utils.GsonUtils;
import org.openqa.selenium.ScreenOrientation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.Duration;
import java.util.Map;

@Configuration
public class AppiumConfiguration extends BaseTest {
    @Value("${android.apk.path}")
    protected String androidApp;

    @Value("${ios.package.path}")
    protected String iOSApp;

    @Value("${driver.implicit.wait}")
    public int implicitWaitTime;

    @Value("${ios.device.name}")
    public String iosDeviceName;

    @Value("${android.device.name}")
    public String androidDeviceName;


    private AppiumDriver driver;

    @Bean
    @Scope("cucumber-glue")
    public AppiumDriver startDriver() {
        if ("ios".equalsIgnoreCase(platform)){
            return createIOSDriver();
        }
        return createAndroidDriver();
    }

    private AppiumDriver createAndroidDriver() {
        final UiAutomator2Options uiAutomator2Options = new UiAutomator2Options()
                .setAvd(androidDeviceName)
                .setDeviceName(androidDeviceName)
                .setAvdLaunchTimeout(Duration.ofSeconds(300))
                .setAvdReadyTimeout(Duration.ofSeconds(100))
                .setNewCommandTimeout(Duration.ofSeconds(30))
                .setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2)
//                .withBrowserName("chrome")
                .setAppPackage("com.wdiodemoapp")
                .setAppActivity("com.wdiodemoapp.SplashActivity")
                .setAutoGrantPermissions(true)
                .setNoReset(false);
        driver = new AndroidDriver(AppiumServerManager.getService().getUrl(), uiAutomator2Options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitTime));
        return driver;
    }

    private AppiumDriver createIOSDriver() {
        final XCUITestOptions xcuiTestOptions = new XCUITestOptions()
                .setDeviceName(iosDeviceName)
                .setAutomationName(AutomationName.IOS_XCUI_TEST)
                .setNewCommandTimeout(Duration.ofSeconds(60))
                .setCommandTimeouts(Duration.ofSeconds(60))
                .setPlatformVersion("16.2")
                .setApp(iOSApp)
                .setOrientation(ScreenOrientation.PORTRAIT)
                .setNewCommandTimeout(Duration.ofSeconds(30))
                .setNoReset(false);
        driver = new IOSDriver(AppiumServerManager.getService().getUrl(), xcuiTestOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitTime));
        return driver;
    }

}
