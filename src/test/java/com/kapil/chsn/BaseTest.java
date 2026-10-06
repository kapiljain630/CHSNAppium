package com.kapil.chsn;

import java.io.FileInputStream;
import java.util.Properties;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class BaseTest {

    protected AndroidDriver driver;

   @BeforeEach
public void setup() throws Exception {

    Properties config = new Properties();

try (FileInputStream file =
         new FileInputStream("src/test/resources/config.properties")) {

    config.load(file);
}

    UiAutomator2Options options = new UiAutomator2Options();

    options.setDeviceName(config.getProperty("deviceName"));
    options.setUdid(config.getProperty("udid"));
    options.setPlatformName(config.getProperty("platformName"));
    options.setAutomationName(config.getProperty("automationName"));
    options.setAppPackage(config.getProperty("appPackage"));
    options.setAppActivity(config.getProperty("appActivity"));
    options.setNoReset(
    Boolean.parseBoolean(config.getProperty("noReset"))
);

options.setAutoGrantPermissions(
    Boolean.parseBoolean(config.getProperty("autoGrantPermissions"))
);

    driver = new AndroidDriver(
        new URL(config.getProperty("serverUrl")),
        options
    );
}

@AfterEach
public void tearDown() {
    if (driver != null) {
        driver.quit();
    }
}
}