package dev.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;

public class HeaderTests {
    private WebDriver driver;

    @BeforeClass
    public void driverSetUp(){
        WebDriverManager.chromedriver().setup();
    }

    @AfterClass
    public void driverRemove(){
        driver = null;
    }

    @BeforeMethod
    public void driverCreate (){
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void close(){
        driver.quit();
    }

    @Test
    public void blogLinkTitle(){
        String url = "https://selenium.dev/blog/";
        String title = "Selenium Blog";
        urlTitleChecker(url, title);
    }

    @Test
    public void supportLinkTitle(){
        String url = "https://selenium.dev/support/";
        String title = "Selenium Support";
        urlTitleChecker(url, title);
    }

    @Test
    public void documentationLinkTitle(){
        String url = "https://selenium.dev/documentation/en/";
        String title = "The Selenium Browser Automation Project :: Documentation for Selenium";
        urlTitleChecker(url, title);
    }

    @Test
    public void projectLinkTitle(){
        String url = "https://selenium.dev/projects/";
        String title = "Selenium Projects";
        urlTitleChecker(url, title);
    }

    @Test
    public void downloadsLinkTitle(){
        String url = "https://selenium.dev/downloads/";
        String title = "Downloads";
        urlTitleChecker(url, title);
    }

    @Test
    public void aboutLinkTitle(){
        String url = "https://selenium.dev/about/";
        String title = "About Selenium";
        urlTitleChecker(url, title);
    }

    @Test
    public void eventsLinkTitle(){
        String url = "https://selenium.dev/events/";
        String title = "Selenium Events";
        urlTitleChecker(url, title);
    }

    @Test
    public void ecosystemLinkTitle(){
        String url = "https://selenium.dev/ecosystem/";
        String title = "Ecosystem";
        urlTitleChecker(url, title);
    }

    @Test
    public void historyLinkTitle(){
        String url = "https://selenium.dev/history/";
        String title = "Selenium History";
        urlTitleChecker(url, title);
    }

    @Test
    public void getInvolvedLinkTitle(){
        String url = "https://selenium.dev/getinvolved/";
        String title = "Get Involved";
        urlTitleChecker(url, title);
    }

    @Test
    public void sponsorsLinkTitle(){
        String url = "https://selenium.dev/sponsors/";
        String title = "Sponsor";
        urlTitleChecker(url, title);
    }

    @Test
    public void mainPageLinkTitle(){
        String url = "https://selenium.dev/";
        String title = "SeleniumHQ Browser Automation";
        urlTitleChecker(url, title);
    }



    public void urlTitleChecker(String url, String title){
        driver.get(url);
        String currentUrl = driver.getCurrentUrl();
        String currentTitle = driver.getTitle();
        assertEquals(currentUrl, url, "Something with URL !");
        assertEquals(currentTitle, title, "Something with Title !");
    }

}
