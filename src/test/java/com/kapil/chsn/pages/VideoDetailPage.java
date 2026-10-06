package com.kapil.chsn.pages;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class VideoDetailPage {

    private WebDriverWait wait;



    public String getVideoTitle(String expectedTitle) {

    By title =
        AppiumBy.androidUIAutomator(
            "new UiSelector().text(\"" + expectedTitle + "\")"
        );

    return wait.until(
        ExpectedConditions.visibilityOfElementLocated(title)
    ).getAttribute("text");
}
    private final By loginButton =
        AppiumBy.androidUIAutomator(
            "new UiSelector().text(\"Login\")"
        );

    private final By backButton =
        AppiumBy.accessibilityId("Back");

    public VideoDetailPage(AndroidDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }



    public boolean isLoginDisplayed() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(loginButton)
        ).isDisplayed();
    }

    public boolean isBackButtonDisplayed() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(backButton)
        ).isDisplayed();
    }

    public void clickBack() {
        wait.until(
            ExpectedConditions.elementToBeClickable(backButton)
        ).click();
    }
}