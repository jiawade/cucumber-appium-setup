package com.demo.screen;

import com.demo.components.AppActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class SignUpScreen extends AbstractScreen {
    private final By userNameBox = AppiumBy.accessibilityId("input-email");

    @AndroidFindBy(accessibility = "input-password")
    @iOSXCUITFindBy(accessibility = "input-password")
    private WebElement passWordBox;

    @AndroidFindBy(accessibility = "input-repeat-password")
    @iOSXCUITFindBy(accessibility = "input-repeat-password")
    private WebElement repeatPassWordBox;

    @AndroidFindBy(accessibility = "button-SIGN UP")
    @iOSXCUITFindBy(accessibility = "button-SIGN UP")
    private WebElement signUpButton;

    @AndroidFindBy(id = "android:id/message")
    @iOSXCUITFindBy(accessibility = "You successfully signed up!")
    private WebElement sucessText;

    private final AppActions app;

    public SignUpScreen(AppActions appActions, AppiumDriver driver) {
        super(driver);
        this.app = appActions;
    }


    public void enterUserName(String text) {
        app.send(this.userNameBox, text);
    }

    public void enterPassWord(String text) {
        app.send(this.passWordBox, text);
    }

    public void enterRepeatPassWord(String text) {
        app.send(this.repeatPassWordBox, text);
    }

    public void hitSignUpButton() {
        app.click(signUpButton);
    }

    public void verifySignUpSuccess() {
        Assert.assertEquals("You successfully signed up!", app.waitDisplay(this.sucessText).getText());
    }

}
