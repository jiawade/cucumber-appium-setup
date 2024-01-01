package com.demo.steps;

import com.demo.components.AppActions;
import com.demo.screen.DragScreen;
import com.demo.screen.LoginScreen;
import com.demo.screen.MainScreen;
import com.demo.screen.SignUpScreen;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractSteps {
    @Autowired
    protected LoginScreen loginScreen;

    @Autowired
    protected MainScreen mainScreen;

    @Autowired
    protected SignUpScreen signUpScreen;

    @Autowired
    protected DragScreen dragScreen;


    @Autowired
    protected AppActions app;

}
