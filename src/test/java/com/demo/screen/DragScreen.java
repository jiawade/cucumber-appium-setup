package com.demo.screen;

import com.demo.components.AppActions;
import com.demo.init.BaseTest;
import io.appium.java_client.AppiumBy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class DragScreen extends BaseTest {
    private final String drag1 = "drag-l2";

    private final String drop1 = "drop-l2";


    private final AppActions app;

    public DragScreen(AppActions appActions) {
        this.app = appActions;
    }

    public void dragOne() {
        app.dragAndDrop(AppiumBy.accessibilityId(drag1), AppiumBy.accessibilityId(drop1));
    }

}
