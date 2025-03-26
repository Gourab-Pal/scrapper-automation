package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;
//import dev.failsafe.internal.util.Assert;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation.
     * Follow `testCase01` `testCase02`... format or what is provided in
     * instructions
     */

    /*
     * Do not change the provided methods unless necessary, they will help in
     * automation and assessment
     */
    @BeforeTest
    public void startBrowser() {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void testCase01() throws JsonProcessingException {
        // opening the url
        driver.get("https://www.scrapethissite.com/pages/");

        // selecting the title
        String titleText = "Hockey Teams: Forms, Searching and Pagination";
        Wrappers.clickOnTitle(driver, By.xpath("//h3[@class='page-title']/a"), titleText);

        ArrayList<HashMap<String, Object>> jsonList = new ArrayList<>();

        // loop fot first 4 pages
        for (int i = 0; i < 4; i++) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            // clicking on pagination button
            String xpath = "(//ul[@class='pagination']//a)[" + (i + 1) + "]";
            WebElement paginationButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
            paginationButton.click();

            System.out.println("On page number " + (i+1));

            // wait for table to be visible
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@class='table']")));

            // list all valid rows where win percentage is < 0.4
            List<WebElement> validRows = driver.findElements(
                    By.xpath("//tr[@class='team']//td[contains(@class, 'pct') and text()<0.4]/parent::tr"));

            // loop throw row and get details in map
            for (WebElement row : validRows) {
                HashMap<String, Object> map = new HashMap<>();
                String name = row.findElement(By.xpath("./td[@class='name']")).getText().trim();
                int year = Integer.parseInt(row.findElement(By.xpath("./td[@class='year']")).getText().trim());
                double winPct = Double
                        .parseDouble(row.findElement(By.xpath("./td[contains(@class, 'pct')]")).getText().trim());
                map.put("Team Name", name);
                map.put("Year", year);
                map.put("Win %", winPct);
                map.put("Epoch Time of Scrape", Instant.now().getEpochSecond());

                // add the map to arraylist
                jsonList.add(map);
            }
        }

        // convert arrayList to json and save it in file
        Wrappers.saveAsJson("hockey-team-data.json", jsonList);

        System.out.println("Json file saved successfully");

    }

    @Test(priority = 2)
    public void testCase02() {
        // opening the url
        driver.get("https://www.scrapethissite.com/pages/");

        // selecting the title
        String titleText = "Oscar Winning Films";
        Wrappers.clickOnTitle(driver, By.xpath("//h3[@class='page-title']/a"), titleText);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        //arraylist to strore the maps
        ArrayList<HashMap<String, Object>> jsonList = new ArrayList<>();

        //iterate through the years
        int numberOfYears = driver.findElements((By.xpath("//a[contains(@class, 'year-link')]"))).size();
        for (int i = 0; i < numberOfYears; i++) {
            String xpathYear = "//a[contains(@class, 'year-link')][" + (i + 1) + "]";
            WebElement yearNavigation = driver.findElement(By.xpath(xpathYear));
            int year = Integer.parseInt(yearNavigation.getText());
            System.out.println("the current year is " + year);

            //clicking on years
            try {
                yearNavigation.click();
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }

            //get all the award numbers for sorting
            List<WebElement> awardNumberElements = driver.findElements(By.xpath("//td[@class='film-awards']"));
            ArrayList<Integer> awardNumber = new ArrayList<>();
            for (WebElement awardNumberElement : awardNumberElements) {
                awardNumber.add(Integer.parseInt(awardNumberElement.getText()));
            }

            //sort the award numbers in decreasing order
            Collections.sort(awardNumber, Comparator.reverseOrder());

            //loop through top 5 award number and get the details
            for (int j = 1; j <= 5; j++) {
                HashMap<String, Object> map = new HashMap<>();
                long epochTime = Instant.now().getEpochSecond();

                String xpathTitle = "//td[@class='film-awards' and text()=" + awardNumber.get(j - 1)
                        + "]/preceding-sibling::td[@class='film-title']";
                String title = driver.findElement(By.xpath(xpathTitle)).getText();

                String xpathNomination = "//td[@class='film-awards' and text()=" + awardNumber.get(j - 1)
                        + "]/preceding-sibling::td[@class='film-nominations']";
                int nomination = Integer.parseInt(driver.findElement(By.xpath(xpathNomination)).getText());
                int awards = awardNumber.get(j - 1);

                String xpathWinner = "//td[@class='film-awards' and text()=" + awardNumber.get(j - 1)
                        + "]/parent::tr//td[@class='film-best-picture']/i";
                boolean isWinner = false;
                int flag_count = driver.findElements(By.xpath(xpathWinner)).size();
                if (flag_count > 0) {
                    isWinner = true;
                }

                //saving details in map
                map.put("Epoch Time of Scrape", epochTime);
                map.put("Year", year);
                map.put("Title", title);
                map.put("Nomination", nomination);
                map.put("Awards", awards);
                map.put("isWinner", isWinner);

                //add map to jsonArray list
                jsonList.add(map);

            }

        }

        //save the jsonArray list to json file
        Wrappers.saveAsJson("oscar-winner-data.json", jsonList);
        System.out.println("Json file saved successfully");

        //TestNG assertion for file exist and non-empty
        File oscarJsonzFile = new File("oscar-winner-data.json");
        Assert.assertTrue(oscarJsonzFile.exists() && oscarJsonzFile.isFile() && oscarJsonzFile.length() > 0,
                "Error saving json file");

    }

    @AfterTest
    public void endTest() {
        driver.close();
        driver.quit();

    }
}