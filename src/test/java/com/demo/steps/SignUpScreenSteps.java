package com.demo.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class SignUpScreenSteps extends AbstractSteps {


    @Given("fill in account info")
    public void hitLoginButton() {
       signUpScreen.enterUserName("abc@123.com");
       signUpScreen.enterPassWord("11111111");
       signUpScreen.enterRepeatPassWord("11111111");
    }

    @And("hit sign up button")
    public void hitSignUpButton(){
        signUpScreen.hitSignUpButton();
    }

    @Then("successfully sign up a account")
    public void verifyAccountHasBeenCreated(){
        signUpScreen.verifySignUpSuccess();
    }

}
