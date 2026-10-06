package com.kapil.chsn;


import org.junit.jupiter.api.Test;
import com.kapil.chsn.pages.HomePage;
import com.kapil.chsn.pages.SearchPage;
import com.kapil.chsn.pages.VideoDetailPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

public class CHSNTest extends BaseTest {

    @Test
    public void verifyCHSNLaunch() {

        System.out.println("CHSN app launched successfully.");
        HomePage homePage = new HomePage(driver);
        

        homePage.clickDoItLater();
        homePage.clickSearch();

        SearchPage searchPage = new SearchPage(driver);

       //assertTrue(searchPage.isFeaturedGamesDisplayed());
       assertTrue(false);
        assertTrue(searchPage.isFeaturedVideosDisplayed());
        assertTrue(searchPage.isBackButtonDisplayed());

    searchPage.clickBack();
        assertTrue(
            homePage.isHomeContentDisplayed(),
            "Home page should display either Watch Now or Event Details"
);
       
        
        homePage.clickSearch();
       searchPage.enterSearchText("shark");
       searchPage.waitForSearchTabs();

       String actualText = searchPage.getSearchText();
List<String> results = searchPage.getSearchResultTitles();


    for (String result : results) {
        assertTrue(
        result.toLowerCase().contains("shark"),
        "Search result does not contain 'shark': " + result
    );

}


    assertEquals("shark", actualText);
    assertFalse(searchPage.isNoResultsMessageDisplayed());
  

    assertTrue(searchPage.isTabSelected("Top Results"));
    assertTrue(searchPage.isTabNotSelected("Events"));
    assertTrue(searchPage.isTabNotSelected("Videos"));
    assertTrue(searchPage.isTabNotSelected("Episodes"));

int topResultsCount = searchPage.getSearchResultsCount();
    assertTrue(topResultsCount > 0);




searchPage.clickEventsTab();



    assertTrue(searchPage.isTabSelected("Events"));
    assertTrue(searchPage.isTabNotSelected("Top Results"));
    assertTrue(searchPage.isTabNotSelected("Videos"));
    assertTrue(searchPage.isTabNotSelected("Episodes"));

int eventsCount = searchPage.getSearchResultsCount();
    assertTrue(eventsCount > 0);
    assertNotEquals(topResultsCount, eventsCount);
List<String> eventResults = searchPage.getSearchResultTitles();
for (String result : eventResults) {
     assertTrue(
        result.toLowerCase().contains("shark"),
        "Search result does not contain 'shark': " + result
    );
}


searchPage.clickVideosTab();

    assertTrue(searchPage.isTabSelected("Videos"));
    assertTrue(searchPage.isTabNotSelected("Top Results"));
    assertTrue(searchPage.isTabNotSelected("Events"));
    assertTrue(searchPage.isTabNotSelected("Episodes"));
int videosCount = searchPage.getSearchResultsCount();

    assertTrue(videosCount > 0);
    assertNotEquals(eventsCount, videosCount);
List<String> videoResults = searchPage.getSearchResultTitles();

for (String result : videoResults) {
     assertTrue(
        result.toLowerCase().contains("shark"),
        "Search result does not contain 'shark': " + result
    );
}

searchPage.clickEpisodesTab();

    assertTrue(searchPage.isTabSelected("Episodes"));
    assertTrue(searchPage.isTabNotSelected("Top Results"));
    assertTrue(searchPage.isTabNotSelected("Videos"));
    assertTrue(searchPage.isTabNotSelected("Events"));
int episodesCount = searchPage.getSearchResultsCount();

    assertTrue(episodesCount > 0);
    assertNotEquals(videosCount, episodesCount);
List<String> episodesResults = searchPage.getSearchResultTitles();


for (String result : episodesResults) {
    assertTrue(
    !result.isEmpty(),
    "Episodes tab should contain at least one result"
);
}

searchPage.clickTopResultsTab();
    assertTrue(searchPage.isTabSelected("Top Results"));
    assertTrue(searchPage.isTabNotSelected("Events"));
    assertTrue(searchPage.isTabNotSelected("Videos"));
    assertTrue(searchPage.isTabNotSelected("Episodes"));

int finalTopResultsCount = searchPage.getSearchResultsCount();
    assertTrue(finalTopResultsCount > 0);
    assertEquals(topResultsCount, finalTopResultsCount);
    assertNotEquals(videosCount, finalTopResultsCount);



    }


@Test
public void verifyVideoDetailsAndBackNavigation() {


    HomePage homePage = new HomePage(driver);
    SearchPage searchPage = new SearchPage(driver);

    homePage.clickDoItLater();

     // Home → Search

    homePage.clickSearch();

    // Search for text
    searchPage.enterSearchText("shark");

     // Open Videos tab
    searchPage.clickVideosTab();

    // Capture first video title
    String videoTitle = searchPage.getFirstVideoTitle();
    

    System.out.println("First video title: " + videoTitle);

    searchPage.clickFirstVideo();


    VideoDetailPage detailPage = new VideoDetailPage(driver);

String detailTitle = detailPage.getVideoTitle(videoTitle);

assertEquals(videoTitle, detailTitle);

System.out.println("Detail page title: " + detailTitle);


    assertTrue(detailPage.isLoginDisplayed());
    assertTrue(detailPage.isBackButtonDisplayed());
detailPage.clickBack();
    assertTrue(searchPage.isTabSelected("Videos"));
String returnedVideoTitle = searchPage.getFirstVideoTitle();
    assertEquals(videoTitle, returnedVideoTitle);
}
    
   
    
    
    

    
    
    
    // Open first video
    
    // Verify detail page
    
    // Verify Login button
    
    // Verify Back button
    
    // Go back
    
    // Verify Videos tab is selected
    
    // Verify same video is displayed

    
}

