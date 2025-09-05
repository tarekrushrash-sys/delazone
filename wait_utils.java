package com.hs.utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {
    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public WaitUtils(AndroidDriver driver, int timeoutInSeconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    public WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void clickWhenReady(By locator, int attempts, int delayMillis) {
        int count = 0;
        while (count < attempts) {
            try {
                WebElement element = waitForVisibility(locator);
                element.click();
                return;
            } catch (Exception e) {
                sleep(delayMillis);
                count++;
            }
        }
        throw new RuntimeException("Element not clickable after retries: " + locator);
    }

    private void sleep(int millis) {
        try { Thread.sleep(millis); } catch (InterruptedException ignored) {}
    }
}
