package com.demo.components;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class AppActions {

    private final AppiumDriver driver;

    private final Actions actions;

    private final FluentWait<AppiumDriver> wait;

    public AppActions(AppiumDriver driver, @Value("${appium.driver.timeout}") int timeout) {
        this.driver = driver;
        actions = new Actions(driver);
        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(WebDriverException.class);
    }

    public void hideKeyboard() {
        if (this.isIOS()) {
            ((IOSDriver) driver).hideKeyboard();
        } else if (this.isAndroid()) {
            ((AndroidDriver) driver).hideKeyboard();
        }
    }

    public Boolean isElementExist(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (WebDriverException e) {
            return false;
        }
    }

    public Boolean isElementExist(WebElement element) {
        try {
            element.getLocation();
            return true;
        }catch (WebDriverException e){
            return false;
        }
    }

    public WebElement waitExist(By by) {
        wait.until(driver -> isElementExist(by));
        return driver.findElement(by);
    }

    public WebElement waitExist(WebElement element) {
        wait.until(driver -> isElementExist(element));
        return element;
    }


    public WebElement waitDisplay(By by) {
        wait.until(driver -> waitExist(by).isDisplayed());
        return driver.findElement(by);
    }

    public WebElement waitDisplay(WebElement element) {
        wait.until(driver -> element.isDisplayed());
        return element;
    }

    public WebElement waitClickable(By by) {
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public WebElement waitClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void click(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    public void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void click(Point point) {
        actions.moveToLocation(point.getX(), point.getY()).click().release().perform();
    }

    public void clear(By by){
        WebElement element=waitClickable(by);
        element.click();
        element.clear();
    }

    public void clear(WebElement element){
        waitClickable(element);
        element.click();
        element.clear();
    }

    public void send(By by, String text) {
        WebElement element=waitClickable(by);
        element.sendKeys(text);
    }

    public void send(WebElement element, String text) {
        waitClickable(element);
        element.sendKeys(text);
    }

    public void send(Point point, String text) {
        click(point);
        actions.sendKeys(text).perform();
    }

    public String getText(By by) {
        waitExist(by);
        return driver.findElement(by).getText();
    }

    public String getValue(By by) {
        waitExist(by);
        return driver.findElement(by).getAttribute("value");
    }

    public String getAttribute(By by, String attribute) {
        waitExist(by);
        return driver.findElement(by).getAttribute(attribute);
    }

    public boolean isIOS(){
        return driver instanceof IOSDriver;
    }

    public boolean isAndroid() {
        return driver instanceof AndroidDriver;
    }


    public void dragAndDrop(WebElement from, WebElement to) {
        Point source = getCenter(waitDisplay(from));
        Point target = getCenter(waitDisplay(to));
        dragAndDrop(source, target);
    }

    public void dragAndDrop(By from, By to) {
        Point source = getCenter(waitDisplay(from));
        Point target = getCenter(waitDisplay(to));
        dragAndDrop(source, target);
    }

    public void dragAndDrop(WebElement from, By to) {
        Point source = getCenter(waitDisplay(from));
        Point target = getCenter(waitDisplay(to));
        dragAndDrop(source, target);
    }

    public void dragAndDrop(By from, WebElement to) {
        Point source = getCenter(waitDisplay(from));
        Point target = getCenter(waitDisplay(to));
        dragAndDrop(source, target);
    }

    public void dragAndDrop(Point from, Point to) {
        actions.moveToLocation(from.getX(), from.getY())
                .clickAndHold()
                .pause(Duration.ofMillis(500))
                .moveToLocation(to.getX(), to.getY())
                .pause(Duration.ofMillis(500))
                .release()
                .perform();
    }

    public void dragAndDrop(By from, Point to) {
        Point source = getCenter(waitDisplay(from));
        dragAndDrop(source, to);
    }

    public void dragAndDrop(WebElement from, Point to) {
        Point source = getCenter(waitDisplay(from));
        dragAndDrop(source, to);
    }

    public void dragAndDrop(Point from, By to) {
        Point target = getCenter(waitDisplay(to));
        dragAndDrop(from, target);
    }

    public void dragAndDrop(Point from, WebElement to) {
        Point target = getCenter(waitDisplay(to));
        dragAndDrop(from, target);
    }

    public void dragToOffset(By from, Point to){
        Point source = getCenter(waitDisplay(from));
        actions.moveToLocation(source.getX(),source.getY())
                .clickAndHold()
                .pause(Duration.ofMillis(500))
                .moveByOffset(to.getX(),to.getY())
                .release()
                .perform();
    }

    public void dragToOffset(WebElement from, Point to){
        Point source = getCenter(waitDisplay(from));
        actions.moveToLocation(source.getX(),source.getY())
                .clickAndHold()
                .pause(Duration.ofMillis(500))
                .moveByOffset(to.getX(),to.getY())
                .release()
                .perform();
    }

    private Point getCenter(WebElement element) {
        Point location = element.getLocation();
        Dimension size = element.getSize();
        return new Point(location.getX() + (size.getWidth() / 2), location.getY() + (size.getHeight() / 2));
    }

    /*public void dragAndDrop(WebElement dragMe, WebElement dropTo) {
        Point source = getElementCenter(dragMe);
        Point target = getElementCenter(dropTo);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragAndDrop = new Sequence(finger, 1);
        dragAndDrop.addAction(finger.createPointerMove(ofMillis(0), PointerInput.Origin.viewport(), source.x, source.y));
        dragAndDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        dragAndDrop.addAction(new Pause(finger, ofMillis(600)));
        dragAndDrop.addAction(finger.createPointerMove(ofMillis(600), PointerInput.Origin.viewport(), target.x, target.y));
        dragAndDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(singletonList(dragAndDrop));
    }*/
}
