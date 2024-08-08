package com.demo.init;

import com.demo.components.AppiumServer;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.Scenario;

public abstract class BaseTest {

    protected Scenario scenario;

    public static AppiumServer server;

    public static AppiumDriver driver;


    protected void setDriver(AppiumDriver driver, AppiumServer server) {
        BaseTest.server = server;
        BaseTest.driver = driver;
    }

    protected void setScenario(Scenario scenario) {
        this.scenario=scenario;
    }

}
