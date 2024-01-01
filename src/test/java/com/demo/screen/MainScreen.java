package com.demo.screen;

import com.demo.components.AppActions;
import io.appium.java_client.AppiumBy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class MainScreen {
    private final String loginButton="Login" ;

    private final String homeButton="Home";

    private final String webviewButton="Webview";

    private final String formsButton="Forms";

    private final String swipeButton="Swipe";

    private final String dragButton="Drag";

    private final AppActions app;

    public MainScreen(AppActions appActions){
        this.app =appActions;
    }


    public void hitLoginButton(){
        app.click(AppiumBy.accessibilityId(this.loginButton));
    }

    public void hitHomeButton(){
        app.click(AppiumBy.accessibilityId(this.homeButton));
    }

    public void hitDragButton(){
        app.click(AppiumBy.accessibilityId(this.dragButton));
    }

    public void hitformsButton(){
        app.click(AppiumBy.accessibilityId(this.formsButton));
    }

    public void hitswipeButton(){
        app.click(AppiumBy.accessibilityId(this.swipeButton));
    }

    public void hitWebviewButton(){
        app.click(AppiumBy.accessibilityId(webviewButton));
    }

}
