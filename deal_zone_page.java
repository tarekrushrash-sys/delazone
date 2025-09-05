package com.hs.pages;

import com.hs.locators.Locators;
import com.hs.utils.WaitUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class DealZonePage {
    private final AndroidDriver driver;
    private final WaitUtils wait;

    public DealZonePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, 20);
    }

    public void openDealZone() {
        WebElement banner = wait.waitForVisibility(AppiumBy.id(Locators.DEAL_ZONE_BANNER_ID));
        banner.click();
    }

    public List<String> getCategoryNames() {
        List<WebElement> categories = driver.findElements(AppiumBy.id(Locators.CATEGORY_TAB_ID));
        return categories.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public boolean hasProductsInCategory(String categoryName) {
        wait.clickWhenReady(AppiumBy.xpath(
            "//android.widget.TextView[@resource-id='" + Locators.CATEGORY_TAB_ID + "' and @text='" + categoryName + "']"
        ), 3, 2000);

        List<WebElement> products = driver.findElements(AppiumBy.id(Locators.PRODUCT_TILE_ID));
        return !products.isEmpty();
    }
}
