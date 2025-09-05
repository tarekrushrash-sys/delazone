package com.hs.tests;

import com.hs.pages.DealZonePage;
import io.appium.java_client.android.AndroidDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class DealsZoneTest {
    private static final Logger logger = LoggerFactory.getLogger(DealsZoneTest.class);

    private AndroidDriver driver; 
    private DealZonePage dealZonePage;

    @BeforeMethod
    public void setup() {
        dealZonePage = new DealZonePage(driver);
        dealZonePage.openDealZone();
    }

    @Test
    public void verifyAllCategoriesHaveProducts() {
        List<String> categories = dealZonePage.getCategoryNames();
        logger.info("Found {} categories in Deal Zone.", categories.size());

        for (String category : categories) {
            logger.info("Validating category: {}", category);
            Assert.assertTrue(
                dealZonePage.hasProductsInCategory(category),
                "Category [" + category + "] returned no products!"
            );
        }
    }
}
