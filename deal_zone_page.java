public class DealZonePage extends BasePage {
    private final WaitUtils wait;

    public DealZonePage(AndroidDriver driver) {
        super(driver);
        this.wait = new WaitUtils(driver, 20);
    }

    public void openDealZone() {
        try {
            WebElement banner = wait.waitForVisibility(AppiumBy.id(Locators.DEAL_ZONE_BANNER_ID));
            clickElement(banner);
        } catch (Exception e) {
            logger.error("Failed to open Deal Zone", e);
        }
    }

    public List<String> getCategoryNames() {
        List<WebElement> categories = driver.findElements(AppiumBy.id(Locators.CATEGORY_TAB_ID));
        return getTextFromElements(categories);
    }

    public boolean hasProductsInCategory(String categoryName) {
        try {
            wait.clickWhenReady(AppiumBy.xpath(
                    "//android.widget.TextView[@resource-id='" + Locators.CATEGORY_TAB_ID + "' and @text='" + categoryName + "']"
            ), 3, 2000);

            List<WebElement> products = driver.findElements(AppiumBy.id(Locators.PRODUCT_TILE_ID));
            boolean hasProducts = !products.isEmpty();
            logger.info("Category [{}] has products: {}", categoryName, hasProducts);
            return hasProducts;
        } catch (Exception e) {
            logger.error("Error checking products in category: {}", categoryName, e);
            return false;
        }
    }
}
