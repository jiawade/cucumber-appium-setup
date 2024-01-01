package com.demo.init;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.demo.components.AppActions;
import com.demo.screen.DragScreen;
import com.demo.screen.LoginScreen;
import com.demo.screen.MainScreen;
import com.demo.components.AppiumConfiguration;
import com.demo.utils.AppiumServerManager;
import com.demo.screen.SignUpScreen;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.*;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
@CucumberContextConfiguration
@SpringBootTest(classes = {AppActions.class, AppiumServerManager.class, AppiumConfiguration.class,
        MainScreen.class, LoginScreen.class, SignUpScreen.class, DragScreen.class})
public class Hook extends BaseTest {

    @Autowired
    public Hook(AppiumDriver driver) {
        super.setDriver(driver);
    }

    @Before(order = 0)
    public void setComponents(Scenario scenario) {
        super.setScenario(scenario);
    }

    @After
    public void after(Scenario scenario) {
        if (scenario.isFailed()) {
            String png = driver.getScreenshotAs(OutputType.BASE64);
            ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromBase64String(png).build());
        }
        driver.quit();
    }

    @BeforeAll
    public static void beforeAll() {
        AppiumServerManager.startServer();
    }

    @AfterAll
    public static void afterAll() {
        //driver.quit();
        AppiumServerManager.stopServer();
    }

}
