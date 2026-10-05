package com.kapil.chsn.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class SearchPage {

    private AndroidDriver driver;
    private WebDriverWait wait;

    private final By searchInput =
        AppiumBy.className("android.widget.EditText");

    private final By topResultsTab =
    AppiumBy.androidUIAutomator(
        "new UiSelector().descriptionContains(\"Top Results\")"
    );

private final By eventsTab =
    AppiumBy.androidUIAutomator(
        "new UiSelector().descriptionContains(\"Events\")"
    );

private final By videosTab =
    AppiumBy.androidUIAutomator(
        "new UiSelector().descriptionContains(\"Videos\")"
    );

private final By episodesTab =
    AppiumBy.androidUIAutomator(
        "new UiSelector().descriptionContains(\"Episodes\")"
    );

private final By noResultsMessage =
    AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"There are no results for your search.\")"
    );

private final By searchResultsSummary =
    AppiumBy.androidUIAutomator(
        "new UiSelector().descriptionContains(\"Search results for\")"
    );

private final By searchResultTitles =
    AppiumBy.androidUIAutomator(
        "new UiSelector().className(\"android.view.View\").descriptionMatches(\".+\")"
    );

private final By featuredGames =
    AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"Featured Games\")"
    );

private final By featuredVideos =
    AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"Featured Videos\")"
    );

private final By backButton =
    AppiumBy.accessibilityId("Back");

    
    


    public SearchPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

public void enterSearchText(String text) {

    WebElement searchBox = wait.until(
        ExpectedConditions.elementToBeClickable(searchInput)
    );

    searchBox.clear();
    searchBox.sendKeys(text);
}

public String getSearchText() {
    return wait.until(
        ExpectedConditions.visibilityOfElementLocated(searchInput)
    ).getText();
}

public void waitForSearchTabs() {

    wait.until(ExpectedConditions.visibilityOfElementLocated(topResultsTab));
    wait.until(ExpectedConditions.visibilityOfElementLocated(eventsTab));
    wait.until(ExpectedConditions.visibilityOfElementLocated(videosTab));
    wait.until(ExpectedConditions.visibilityOfElementLocated(episodesTab));
}

public void clickTopResultsTab() {
    wait.until(
        ExpectedConditions.elementToBeClickable(topResultsTab)
    ).click();
}

public void clickEventsTab() {
    wait.until(
        ExpectedConditions.elementToBeClickable(eventsTab)
    ).click();
}

public void clickVideosTab() {
    wait.until(
        ExpectedConditions.elementToBeClickable(videosTab)
    ).click();
}

public void clickEpisodesTab() {
    wait.until(
        ExpectedConditions.elementToBeClickable(episodesTab)
    ).click();
}


private By getTabLocator(String tabName) {

    switch (tabName) {
        case "Top Results":
            return topResultsTab;

        case "Events":
            return eventsTab;

        case "Videos":
            return videosTab;

        case "Episodes":
            return episodesTab;

        default:
            throw new IllegalArgumentException("Unknown tab: " + tabName);
    }
}
public boolean isTabSelected(String tabName) {
    By tab = getTabLocator(tabName);

    return wait.until(
        ExpectedConditions.visibilityOfElementLocated(tab)
    ).getAttribute("contentDescription")
     .startsWith("Selected");
}

public boolean isTabNotSelected(String tabName) {
    By tab = getTabLocator(tabName);

    return wait.until(
        ExpectedConditions.visibilityOfElementLocated(tab)
    ).getAttribute("contentDescription")
     .startsWith("Not Selected");
}

public boolean isNoResultsMessageDisplayed() {
    return !driver.findElements(noResultsMessage).isEmpty();
}

public int getSearchResultsCount() {

    String summary = wait.until(
        ExpectedConditions.visibilityOfElementLocated(searchResultsSummary)
    ).getAttribute("contentDescription");

    String countText = summary.replaceAll(".*: (\\d+) items? found.*", "$1");

    return Integer.parseInt(countText);
}

public List<String> getSearchResultTitles() {

    List<String> titles = new ArrayList<>();

    for (WebElement element : driver.findElements(searchResultTitles)) {

        String description =
            element.getAttribute("contentDescription");

        if (description != null
                && !description.contains("Button")
                && !description.equals("Back")
                && !description.equals("Search View")
                && !description.equals("Clear search")
                && !description.startsWith("Search results for")) {

            titles.add(description);
        }
    }

    return titles;
}

public boolean isFeaturedGamesDisplayed() {
    return wait.until(
        ExpectedConditions.visibilityOfElementLocated(featuredGames)
    ).isDisplayed();
}

public boolean isFeaturedVideosDisplayed() {
    return wait.until(
        ExpectedConditions.visibilityOfElementLocated(featuredVideos)
    ).isDisplayed();
}

public boolean isBackButtonDisplayed() {
    return wait.until(
        ExpectedConditions.visibilityOfElementLocated(backButton)
    ).isDisplayed();
}

public void clickBack() {
    driver.findElement(backButton).click();
}

public String getFirstVideoTitle() {

    By firstVideoTitle =
        AppiumBy.xpath(
            "//android.widget.ScrollView//android.view.View[@content-desc][1]"
        );

    return wait.until(
        ExpectedConditions.visibilityOfElementLocated(firstVideoTitle)
    ).getAttribute("contentDescription");
}

public void clickFirstVideo() {

    By firstVideo =
        AppiumBy.xpath(
            "//android.widget.ScrollView//android.view.View[@content-desc][1]/parent::android.view.View"
        );

    wait.until(
        ExpectedConditions.elementToBeClickable(firstVideo)
    ).click();
}

}
