package com.demo.components;

import com.demo.exception.UnsupportedPlatformException;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.*;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.ALLOW_INSECURE;

@Component
public class AppiumServer {

    @Value("${appium.server.host}")
    private String appServerHost;

    @Value("${appium.server.port}")
    private int appServerPort;

    private AppiumDriverLocalService service;


    public AppiumDriverLocalService startServer(String platform) {
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
        }else {
            throw new UnsupportedPlatformException("Unsupported platfrom: "+platform);
        }
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
        return service;
    }

    public void stopServer() {
        service.stop();
    }

}
