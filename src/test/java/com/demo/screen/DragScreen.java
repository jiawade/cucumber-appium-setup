package com.demo.screen;

import com.demo.components.AppActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DragScreen extends AbstractScreen {
    @AndroidFindBy(accessibility = "drag-l2")
    @iOSXCUITFindBy(accessibility = "drag-l2")
    private WebElement drag1;

    private final By drop1 = AppiumBy.accessibilityId("drop-l2");

    @Autowired
    public AppActions appActions;


    private final AppActions app;

    public DragScreen(AppActions appActions, AppiumDriver driver) {
        super(driver);
        this.app = appActions;
    }

    public void dragOne() {
        app.dragAndDrop(this.drag1, this.drop1);
    }

}
