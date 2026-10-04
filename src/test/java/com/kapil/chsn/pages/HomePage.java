package com.kapil.chsn.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import io.appium.java_client.AppiumBy;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

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

    public HomePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

 public void clickSearch() {
    driver.findElement(searchButton).click();
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

public boolean isWatchNowDisplayed() {
    return wait.until(
        ExpectedConditions.visibilityOfElementLocated(watchNowButton)
    ).isDisplayed();
}

}