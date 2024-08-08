package com.demo.init;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.demo.components.AppiumServer;
import com.demo.screen.DragScreen;
import com.demo.screen.LoginScreen;
import com.demo.screen.MainScreen;
import com.demo.components.AppiumConfiguration;
import com.demo.screen.SignUpScreen;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.*;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
@CucumberContextConfiguration
@SpringBootTest(classes = AppiumConfiguration.class)
public class Hook extends BaseTest {

    public Hook(AppiumDriver driver, AppiumServer server) {
        super.setDriver(driver, server);
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
    }

    @BeforeAll
    public static void beforeAll() {
    }

    @AfterAll
    public static void afterAll() {
        server.stopServer();
    }

}
