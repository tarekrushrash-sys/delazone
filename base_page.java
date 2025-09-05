package com.hs.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class BasePage {
    protected AndroidDriver driver;
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public BasePage(AndroidDriver driver) {
        this.driver = driver;
    }

    protected void clickElement(WebElement element) {
        try {
            element.click();
            logger.info("Clicked on element: {}", element);
        } catch (Exception e) {
            logger.error("Failed to click element: {}", element, e);
        }
    }

    protected WebElement safeFindElement(AppiumBy locator) {
        try {
            return driver.findElement(locator);
        } catch (Exception e) {
            logger.error("Element not found: {}", locator, e);
            return null;
        }
    }

    protected List<String> getTextFromElements(List<WebElement> elements) {
        List<String> texts = new ArrayList<>();
        for (WebElement el : elements) {
            try {
                texts.add(el.getText());
            } catch (Exception e) {
                logger.warn("Failed to get text from element: {}", el, e);
            }
        }
        return texts;
    }
}
