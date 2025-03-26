package demo.wrappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */

    public static void clickOnTitle(ChromeDriver driver, By by, String titleText) {
        List<WebElement> titles = driver.findElements(by);
        for (WebElement title : titles) {
            if (title.getText().contains(titleText)) {
                try {
                    title.click();
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void saveAsJson(String filenName, ArrayList<HashMap<String, Object>> jsonList) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(filenName), jsonList);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
