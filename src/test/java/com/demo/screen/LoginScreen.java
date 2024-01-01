package com.demo.screen;

import com.demo.components.AppActions;
import com.demo.init.BaseTest;
import io.appium.java_client.AppiumBy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class LoginScreen extends BaseTest {
    private final String userNameBox = "input-email";

    private final String passWordBox = "input-password";

    private final String loginButton = "**/XCUIElementTypeStaticText[`name == \"LOGIN\"`]";

    private final String signInTab = "**/XCUIElementTypeStaticText[`name == \"Login\"`]";

    private final String signUpTab = "**/XCUIElementTypeStaticText[`name == \"Sign up\"`]";

    private final String okButton = "OK";

    private final AppActions app;

    public LoginScreen(AppActions appActions) {
        this.app = appActions;
    }

    public void enterUserName(String text) {
        app.send(AppiumBy.accessibilityId(this.userNameBox), text);
    }

    public void enterPassWord(String text) {
        app.send(AppiumBy.accessibilityId(this.passWordBox), text);
    }

    public void hitLoginButton() {
        app.click(AppiumBy.iOSClassChain(this.loginButton));
    }

    public void hitSignInTab() {
        app.click(AppiumBy.iOSClassChain(this.signInTab));
    }

    public void hitSignUpTab() {
        app.click(AppiumBy.iOSClassChain(this.signUpTab));
    }

    public void hitOkButton() {
        app.click(AppiumBy.iOSClassChain(this.okButton));
    }

}
