package com.kapil.chsn.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.TimeoutException;

public class HomePage {

    private AndroidDriver driver;
    private WebDriverWait wait;



   private final By searchButton =
    AppiumBy.accessibilityId("SEARCH");

    private final By doItLaterButton =
    AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"Do it later\")"
    );

    private final By watchNowButton =
    AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"Watch Now\")"
    );

    private final By eventDetailsButton =
    AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"Event Details\")"
    );

    public HomePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

public void clickSearch() {
    wait.until(
        ExpectedConditions.elementToBeClickable(searchButton)
    ).click();
}

public void clickDoItLater() {

    try {
        wait.until(
            ExpectedConditions.elementToBeClickable(doItLaterButton)
        ).click();

    } catch (Exception e) {
        System.out.println("Do it later popup not displayed. Continuing...");
    }
}

public boolean isHomeContentDisplayed() {

    try {
        wait.until(
            ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(watchNowButton),
                ExpectedConditions.visibilityOfElementLocated(eventDetailsButton)
            )
        );

        return true;

    } catch (TimeoutException e) {
        return false;
    }
}


}