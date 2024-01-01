package com.demo.screen;

import com.demo.components.AppActions;
import io.appium.java_client.AppiumBy;
import org.junit.Assert;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class SignUpScreen {
    private final String userNameBox = "input-email";

    private final String passWordBox = "input-password";

    private final String repeatPassWordBox = "input-repeat-password";

    private final String signUpButton = "**/XCUIElementTypeStaticText[`name == \"SIGN UP\"`]";

    private final String sucessText = "You successfully signed up!";

    private final AppActions app;

    public SignUpScreen(AppActions appActions) {
        this.app = appActions;
    }


    public void enterUserName(String text) {
        app.send(AppiumBy.accessibilityId(this.userNameBox), text);
    }

    public void enterPassWord(String text) {
        app.send(AppiumBy.accessibilityId(this.passWordBox), text);
    }

    public void enterRepeatPassWord(String text) {
        app.send(AppiumBy.accessibilityId(this.repeatPassWordBox), text);
    }

    public void hitSignUpButton() {
        app.click(AppiumBy.iOSClassChain(this.signUpButton));
    }

    public void verifySignUpSuccess(){
        Assert.assertTrue(app.waitDisplay(AppiumBy.accessibilityId(this.sucessText)).isDisplayed());
    }

}
