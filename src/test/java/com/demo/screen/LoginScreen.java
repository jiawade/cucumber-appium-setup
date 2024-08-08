package com.demo.screen;

import com.demo.components.AppActions;
import com.demo.init.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope for not singleton use
public class LoginScreen extends AbstractScreen {
    @AndroidFindBy(accessibility = "input-email")
    @iOSXCUITFindBy(accessibility = "input-email")
    private WebElement userNameBox;

    @AndroidFindBy(accessibility = "input-password")
    @iOSXCUITFindBy(accessibility = "input-password")
    private WebElement passWordBox;

    private final By signUpTab = AppiumBy.accessibilityId("button-sign-up-container");

    private final AppActions app;

    public LoginScreen(AppActions appActions, AppiumDriver driver) {
        super(driver);
        this.app = appActions;
    }

    public void enterUserName(String text) {
        app.send(this.userNameBox, text);
    }

    public void enterPassWord(String text) {
        app.send(this.passWordBox, text);
    }

    public void hitSignUpTab() {
        app.click(signUpTab);
    }

}
