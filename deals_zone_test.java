public class DealsZoneTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(DealsZoneTest.class);
    private DealZonePage dealZonePage;

    @BeforeMethod
    public void setupPage() {
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
