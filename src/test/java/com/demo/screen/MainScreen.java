package com.demo.screen;

import com.demo.components.AppActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class MainScreen extends AbstractScreen {

    @AndroidFindBy(accessibility = "Login")
    @iOSXCUITFindBy(accessibility = "Login")
    private WebElement loginButton;

    private final By homeButton = AppiumBy.accessibilityId("Home");

    @AndroidFindBy(accessibility = "Webview")
    @iOSXCUITFindBy(accessibility = "Webview")
    private WebElement webviewButton;

    @AndroidFindBy(accessibility = "Forms")
    @iOSXCUITFindBy(accessibility = "Forms")
    private WebElement formsButton;

    @AndroidFindBy(accessibility = "Swipe")
    @iOSXCUITFindBy(accessibility = "Swipe")
    private WebElement swipeButton;

    @AndroidFindBy(accessibility = "Drag")
    @iOSXCUITFindBy(accessibility = "Drag")
    private WebElement dragButton;

    private final AppActions app;

    public MainScreen(AppActions appActions, AppiumDriver driver) {
        super(driver);
        this.app = appActions;
    }


    public void hitLoginButton() {
        System.out.println(app.isElementExist(this.loginButton));
        app.click(this.loginButton);
    }

    public void hitHomeButton() {
        app.click(this.homeButton);
    }

    public void hitDragButton() {
        app.click(this.dragButton);
    }

    public void hitformsButton() {
        app.click(this.formsButton);
    }

    public void hitswipeButton() {
        app.click(this.swipeButton);
    }

    public void hitWebviewButton() {
        app.click(this.webviewButton);
    }

}
