package com.demo.steps;

import io.cucumber.java.en.Given;

public class DragScreenSteps extends AbstractSteps{

    @Given("drag image to target")
    public void hitLoginButton() {
        dragScreen.dragOne();
    }

}
