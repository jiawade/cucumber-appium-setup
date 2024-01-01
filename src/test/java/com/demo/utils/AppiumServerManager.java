package com.demo.utils;

import com.demo.init.BaseTest;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.util.Objects;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.*;

public class AppiumServerManager extends BaseTest {

    public static String appServerHost = Config.getString("appium.server.host");

    public static int appServerPort = Config.getInt("appium.server.port");

    private static AppiumDriverLocalService service;

    public static AppiumDriverLocalService getService () {
        if (!Objects.isNull(service)){
            return service;
        }else {
            throw new NullPointerException("appium local service was null.");
        }
    }

    public static void startServer() {
        final AppiumServiceBuilder builder = new AppiumServiceBuilder();
        if (platform.equalsIgnoreCase("android")) {
            builder.withIPAddress(appServerHost)
                    .usingPort(appServerPort)
                    .withArgument(BASEPATH,"/")
                    .withArgument(SESSION_OVERRIDE)
                    .withArgument(LOG_LEVEL, "error")
                    .withArgument(USE_DRIVERS, "uiautomator2")
                    .withArgument(ALLOW_INSECURE, "chromedriver_autodownload");
        } else if (platform.equalsIgnoreCase("ios")) {
            builder.withIPAddress(appServerHost)
                    .usingPort(appServerPort)
                    .withArgument(BASEPATH,"/")
                    .withArgument(SESSION_OVERRIDE)
                    .withArgument(LOG_LEVEL, "error")
                    .withArgument(USE_DRIVERS, "xcuitest")
                    .withArgument(ALLOW_INSECURE, "chromedriver_autodownload");
        }
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
    }

    public static void stopServer() {
        service.stop();
    }
}
