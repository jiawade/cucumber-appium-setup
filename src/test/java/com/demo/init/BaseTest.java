package com.demo.init;

import com.demo.utils.Config;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.Scenario;

public abstract class BaseTest {

    public static String platform = Config.getString("platform");

    protected Scenario scenario;
    public AppiumDriver driver;


    protected void setDriver(AppiumDriver driver) {
        this.driver = driver;
    }

    protected void setScenario(Scenario scenario) {
        this.scenario=scenario;
    }

}
