package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;
import java.time.Duration;
import java.util.List;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
        ChromeDriver driver;
        @BeforeClass
        public void TestCases1() {
                System.out.println("Constructor: TestCases");
                WebDriverManager.chromedriver().timeout(30).setup();
                driver = new ChromeDriver();
                driver.manage().window().maximize();
        }

        @AfterTest
        public void endTest() {
                System.out.println("End Test: TestCases");
                driver.close();
                driver.quit();
        }

        @Test(priority = 1, enabled = true)
        public void testCase01() throws InterruptedException {
                System.out.println("Start Test case: testCase01");
                driver.get("https://www.youtube.com/");
                Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/", "Youtube Successfully loaded");
                Thread.sleep(2000);
                WebElement aboutElement = driver
                                .findElement(By.xpath("//a[@slot='guide-links-primary' and text()='About']"));
                WrapperMethod.scrollElement(aboutElement, driver);
                Thread.sleep(2000);
                WrapperMethod.advanceClick(aboutElement, driver);
                WebElement aboutText = driver.findElement(By.xpath("//h1//parent::section/p[1]"));
                WrapperMethod.scrollElement(aboutText, driver);
                String msg = WrapperMethod.advanceGettext(aboutText, driver);
                System.out.println("Message at About page : " + msg);
                System.out.println("End Test case: testCase01");
        }

        @Test(priority = 2, enabled = true)
        public void testCase02() throws InterruptedException {
                System.out.println("Start Test case: testCase02");
                driver.navigate().back();
                Thread.sleep(2000);

                WebElement movies = driver.findElement(By.xpath("//yt-formatted-string[text()='Movies']"));
                WrapperMethod.scrollElement(movies, driver);
                WrapperMethod.advanceClick(movies, driver);
                WebElement rightArrow = WrapperMethod
                                .advanceFindElement(By
                                                .xpath("//div[@id='right-arrow']//div[contains(@class,'shape__fill')]"),
                                                driver);
                rightArrow.click();
                rightArrow.click();
                rightArrow.click();
                SoftAssert sa = new SoftAssert();
                WebElement matureElement = WrapperMethod.advanceFindElement(
                                By.xpath("//div[@id='items']//ytd-grid-movie-renderer[16]/ytd-badge-supported-renderer/div[2]/p"),
                                driver);
                sa.assertEquals('A', matureElement.getText(), "Mature content not matched");
                System.out.println("A grade matched");
                WebElement moviewType = WrapperMethod
                                .advanceFindElement(By.xpath("//div[@id='items']//ytd-grid-movie-renderer[16]/a/span"),
                                                driver);

                sa.assertTrue(moviewType.getText().contains("Comedy") || moviewType.getText().contains("Animation"),
                                "Movie not belongs to Comedy as well as Animation category");
                System.out.println("Comedy category matched");

                System.out.println("End Test case: testCase02");
        }

        @Test(priority = 3, enabled = true)
        public void testCase03() throws InterruptedException {
                System.out.println("Start Test case: testCase03");
                driver.navigate().refresh();
                Thread.sleep(2000);
                WebElement music = driver.findElement(By.xpath("//yt-formatted-string[text()='Music']"));
                // WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
                WrapperMethod.scrollElement(music, driver);
                Thread.sleep(2000);
                WrapperMethod.advanceClick(music, driver);
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollBy(0,450)", "");

                Thread.sleep(2000);
                WebElement rightArrow = driver.findElement(By.xpath(
                                "//ytd-page-manager/ytd-browse[2]//ytd-two-column-browse-results-renderer//ytd-item-section-renderer[1]//div[@id='right-arrow']//button"));
                System.out.println("Scroll done");
                Thread.sleep(2000);
                rightArrow.click();
                rightArrow.click();
                rightArrow.click();
                WebElement titleOfPlaylist = driver.findElement(By.xpath(
                                "//ytd-page-manager/ytd-browse[2]//ytd-two-column-browse-results-renderer//ytd-item-section-renderer[1]//ytd-compact-station-renderer[11]//h3"));
                System.out.println(
                                "Title of Playlist at extreme right : "
                                                + WrapperMethod.advanceGettext(titleOfPlaylist, driver));
                WebElement trackCount = driver.findElement(By.xpath(
                                "//ytd-page-manager/ytd-browse[2]//ytd-two-column-browse-results-renderer//ytd-item-section-renderer[1]//ytd-compact-station-renderer[11]//p[@id='video-count-text']"));
                String actualTrackCount = WrapperMethod.advanceGettext(trackCount, driver);
                String[] splited = actualTrackCount.split(" ");
                int trackNo = Integer.valueOf(splited[0]);
                System.out.println("Number of tracks : " + trackNo);
                SoftAssert sa = new SoftAssert();

                sa.assertTrue(trackNo <= 50, "Assertion failed");
                System.out.println("End Test case: testCase03");

        }

        @Test(priority = 4, enabled = true)
        public void testCase04() throws InterruptedException {
                System.out.println("Start Test case: testCase04");
                driver.navigate().refresh();
                Thread.sleep(3000);
                WebElement news = WrapperMethod.advanceFindElement(By.xpath(
                                "//yt-formatted-string[text()='Explore']/ancestor::ytd-guide-section-renderer//ytd-guide-entry-renderer[7]//yt-formatted-string"),
                                driver);
                WrapperMethod.scrollElement(news, driver);
                WrapperMethod.advanceClick(news, driver);
                WebElement titleLatestNews = driver
                                .findElement(By.xpath("//div[@id='title-text']/span[text()='Latest news posts']"));
                WrapperMethod.scrollElement(titleLatestNews, driver);
                List<WebElement> allTitles = driver.findElements(By.xpath(
                                "//div[@id='title-text']/span[text()='Latest news posts']//ancestor::div[@id='dismissible']//ytd-rich-item-renderer//div[@id='header']/div[2]/a/span"));

                List<WebElement> fullBody = driver.findElements(By.xpath(
                                "//div[@id='title-text']/span[text()='Latest news posts']//ancestor::div[@id='dismissible']//ytd-rich-item-renderer//div[@id='body']/div/yt-formatted-string"));
                List<WebElement> likes = driver.findElements(By.xpath(
                                "//div[@id='title-text']/span[text()='Latest news posts']//ancestor::div[@id='dismissible']//ytd-rich-item-renderer//div[@id='toolbar']//span[@id='vote-count-middle']"));
                int k = 1;
                int sumOfLikes = 0;
                for (int i = 0; i < 3; i++) {
                        System.out.println("Title and body of Post : " + k);
                        System.out.println("title>>" + allTitles.get(i).getText());
                        System.out.println("Body>>" + fullBody.get(i).getText());
                        sumOfLikes = sumOfLikes + Integer.valueOf(likes.get(i).getText());
                        k++;
                }
                System.out.println("Sum of Likes >>" + sumOfLikes);
                System.out.println("End Test case: testCase04");
        }

        @Test(enabled = true, dataProviderClass = DP.class, dataProvider = "data-provider")
        public void testCase05(String toBeSearched) throws InterruptedException {
                System.out.println("Start Test case: testCase05");
                driver.get("https://www.youtube.com/");
                WebElement searchBar = WrapperMethod.advanceFindElement(By.xpath("//input[@id='search']"), driver);
                WrapperMethod.advanceSendkeys(searchBar, toBeSearched, driver);
                searchBar.sendKeys(Keys.ENTER);
                Thread.sleep(5000);
                System.out.println("Youtube opened");
                

                List<WebElement> views = driver
                                .findElements(By.xpath("//ytd-video-renderer/div/div//ytd-video-meta-block//div[@id='metadata-line']//span[1]"));
                if(!views.get(0).isDisplayed()){
                        WrapperMethod.scrollElement(views.get(0), driver);
                }
                Thread.sleep(2000);
                int sum = 0;
                for (int i = 0; i < views.size(); i++) {
                        if(!views.get(i).isDisplayed()){
                                WrapperMethod.scrollElement(views.get(i), driver);
                        }
                        Thread.sleep(1000);
                        int viewInCrore ;
                        System.out.println("Full views: " + views.get(i).getText());
                        String[] viewArray = views.get(i).getText().split(" ", 2);
                        String view = viewArray[0];
                        System.out.println("Exact view after split :" + view);
                        if (view.trim().endsWith("K")) {
                                view = view.replaceAll("K", "");
                                System.out.println("View in K=" + view);
                                viewInCrore = Integer.valueOf(view) / 10000;
                                System.out.println("View in crore=" + viewInCrore);
                        } else if(view.trim().endsWith("M")) {
                                view = view.replaceAll("M", "");
                                System.out.println("View in M=" + view);
                                viewInCrore = Integer.valueOf(view) / 10;
                                System.out.println("View in crore=" + viewInCrore);
                        }else{
                                continue;
                        }
                        sum = sum + viewInCrore;
                        System.out.println("Sum:" + sum);
                        if (sum >= 100) {
                                System.out.println("Sum:" + sum);
                                break;
                        }
                }
                System.out.println("End Test case: testCase05");
        }
}
